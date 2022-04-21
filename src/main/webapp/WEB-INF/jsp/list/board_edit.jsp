<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">

<!-- 탭로고 -->
<link rel="shortcut icon" type="image/x-icon"
	href="/images/home/homelog.png">

<!--  -->
<link rel="stylesheet" href="/css/list/board_detail.css">

<title>게시글수정</title>
<!-- <script>
	$(function(){
		alert('제이쿼리 시좍');
	});
</script> -->

</head>

<body>
	<!-- 헤더 -->
	<header class="p-3 bg-white text-dark">
		<div class="container">
			<div
				class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
				<a href="/bar/home"
					class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
					<img alt="로고" src="/images/login/looog.png" height="40" width="50">
				</a>

				<ul
					class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
					<li><a href="/bar/notice/noticelist"
						class="nav-link px-2 text-dark">공지사항</a></li>
					<!-- <li><a href="#" class="nav-link px-2 text-dark">제품</a></li> -->
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#"
						id="navbarDropdownMenuLink" role="button"
						data-bs-toggle="dropdown" aria-expanded="false"
						style="padding: 8px; color: black;"> 제품 </a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
							<li><a class="dropdown-item" href="/bar/shop/list">모래</a></li>
							<!-- <li><a class="dropdown-item" href="/bar/master/orderlist">두부모래</a></li>
							<li><a class="dropdown-item" href="/bar/master/list">악세서리</a></li> -->
						</ul></li>
					<li><a href="/bar/list" class="nav-link px-2 text-dark">후기</a></li>
					<!-- <li><a href="#" class="nav-link px-2 text-dark">Q&A</a></li> -->
					<c:if test="${id == 'master'}">
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#"
							id="navbarDropdownMenuLink" role="button"
							data-bs-toggle="dropdown" aria-expanded="false"
							style="padding: 8px; color: gray;"> Master </a>
							<ul class="dropdown-menu"
								aria-labelledby="navbarDropdownMenuLink">
								<li><a class="dropdown-item" href="/bar/master/orderlist">주문
										현황</a></li>
								<li><a class="dropdown-item" href="/bar/master/list">회원
										관리</a></li>
							</ul></li>
					</c:if>
				</ul>

				<%
				//로그인된 아이디가 있는지 읽어와보기
				String id = (String) session.getAttribute("id");
				%>
				<%
				if (id == null) {
				%>
				<div class="text-end">
					<button type="button" class="btn btn-outline-light me-2"
						style="color: white; background-color: black; border-color: white;"
						onclick="goLogin();">Login</button>
					<button type="button" class="btn btn-warning"
						style="color: white; background-color: black; border-color: white;"
						onclick="goSignup();">Sign-up</button>
				</div>
				<%
				} else {
				%>
				<div class="text-end">
					<c:if test="${id == 'master'}">
						<span id="login_log"
							style="border-bottom: 1px solid white; padding-bottom: 1px; color: gray; position: relative; top: 5px; right: 9px;">
							Master </span>
					</c:if>
					<c:if test="${id != 'master'}">
						<span id="login_log"
							style="border-bottom: 1px solid white; padding-bottom: 1px; color: gray; position: relative; top: 5px; right: 6px;">
							${id}님 </span>
							<a class="paymentlist" href="/bar/payment/list">Cart</a>
					</c:if>
					<button type="button" class="btn btn-outline-light me-2"
						style="color: white; background-color: black; border-color: white;"
						onclick="goLogout();">Logout</button>
					<button type="button" class="btn btn-warning"
						style="color: white; background-color: black; border-color: white;"
						onclick="goDetail();">Detail</button>
					<%
					}
					%>

				</div>
			</div>
		</div>
		<p>
		<p class="text-center text-muted"
			style="border-top: 1px solid #dee2e6;"></p>
	</header>
	<!-- 헤더 -->

	<!-- 글쓰기 -->
	<article>
		<div class="container" role="main">
			<h4 id="h2">게시판 글 수정</h4>
			<form id="updateForm" action="/bar/boardUpdate"
				enctype="multipart/form-data" onsubmit="return updateBoard();">
				<input type="hidden" id="num" name="num" value="${board.num}">
				<p>
				<div name="addForm" id="form">
					<div class="mb-3">
						<label for="title">제목</label><input type="text" id="title"
							name="title" class="form-control" value="${board.title}">
					</div>


					<div class="mb-3">

						<label for="author">작성자</label> <span class="form-control"
							name="reg_id" id="reg_id"><input type="hidden" id="author"
							name="author" value="${board.author}"
							style="border: none; color: gray;" readonly>${board.author}</span>
					</div>

					<div class="mb-3">
						<label for="content">내용</label>
						<textarea class="form-control" id="contents" name="contents">${board.contents}</textarea>
					</div>

					<div>
						<!-- class="form-control" -->
						<label for="content">파일</label> <input type="file" id="files"
							name="files" class="form-control" accept="image/*"
							onchange="setThumbnail(event);">
						<c:choose>
							<c:when test="${fn:length(board.attach)>0}">
								<c:forEach var="f" items="${board.attach}">
									<fmt:formatNumber var="kilo" value="${f.filesize/1024}"
										maxFractionDigits="0" />
									<div id="${f.att_num}">
										<span class="form-control"> <a
											href="/bar/file/download/${f.att_num}">${f.filename}</a>
											[${kilo}kb] <img src="/upload/${f.filename}" width="100px"
											height="100px" alt="" class="thumb" /> <%-- 	<a class="link_del" href="javascript:del_file(${f.att_num});">삭제</a></span> --%>
											<a class="link_del" href="javascript:del_file(${f.att_num});">삭제</a></span>
									</div>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<!-- 			첨부파일 없음 -->
							</c:otherwise>
						</c:choose>
					</div>


				</div>

				<div class="mb-3"
					style="width: 96%; margin-left: 2%; padding-top: 14px; padding-right: 24px;"
					id="image_container"></div>

				<!-- 수정 목록 버튼 -->
				<div class="btlistsav">
					<button type="submit" class="btn btn-sm btn-primary">완료</button>
					<button type="button" class="btn btn-sm btn-primary"
						onclick="location.href='/bar/list'">목록</button>
				</div>

			</form>
		</div>
	</article>
	<!-- 글쓰기 -->

	<!-- 하단 배너 -->
	<p class="text-center text-muted"
		style="border-top: 1px solid #dee2e6; padding-top: 10px;">© 2022
		Yun Company, Inc</p>
	<script src="/js/list/board_edit.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>
</body>

</html>

