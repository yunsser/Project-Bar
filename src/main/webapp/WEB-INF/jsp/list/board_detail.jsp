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
							<a class="paymentlist" href="/bar/cart/list">Cart</a>
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

			<h4 id="h2">게시판 글 내용</h4>
			<p>
			<div name="addForm" id="form">
				<div class="mb-3">
					<label for="title">제목</label> <span type="text"
						class="form-control">${board.title}</span>
				</div>


				<div class="mb-3">

					<label for="author">작성자</label> <span class="form-control"
						name="reg_id" id="reg_id"><input type="hidden"
						name="author" value="${board.author}"
						style="border: none; color: gray;" readonly>${board.author}</span>
				</div>

				<div class="mb-3">
					<label for="content">내용</label> <span class="form-control"
						id="contents">${board.contents}</span>
				</div>

				<div>
					<!-- class="form-control" -->
					<label for="content">파일</label>

					<c:choose>
						<c:when test="${fn:length(board.attach)>0}">
							<c:forEach var="f" items="${board.attach}">
								<fmt:formatNumber var="kilo" value="${f.filesize/1024}"
									maxFractionDigits="0" />
								<div>
									<span class="form-control">
									<a href="/bar/file/download/${f.att_num}"><img src="/upload/${f.filename}" width="30%" alt="" class="thumb" style="display: block; margin: auto;"/></a>
									</span>
								</div>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<span class="form-control">첨부파일 없음</span>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="mb-3"
				style="width: 96%; margin-left: 2%; padding-top: 14px; padding-right: 24px;"
				id="image_container"></div>
			<!-- 수정 목록 버튼 -->
			<div class="btlistsav">
				<c:if test="${id eq board.author || id eq 'master'}">
					<button type="button" class="btn btn-sm btn-primary"
						onclick="location.href='/bar/boardEdit?num=${board.num}'">수정</button>
					<button type="button" class="btn btn-sm btn-primary"
						onclick="location.href=del_board('${board.num}')">삭제</button>
				</c:if>

				<button type="button" class="btn btn-sm btn-primary"
					onclick="location.href='/bar/list'">목록</button>
			</div>

		</div>
	</article>


	<form name="commentInsertForm">
		<label for="content" style="margin-left: 26.2%;">댓글</label>

		<div class="input-group" style="width: 47.8%; margin-left: 26.3%;">
			<input type="hidden" name="bno" value="${board.num}"> <input
				type="text" class="form-control" id="content" name="content"
				placeholder="댓글을 입력하세요"> <span class="input-group-btn">
				<button class="btn btn-default" type="button"
					name="commentInsertBtn"
					style="color: #fff; background-color: black; border-color: black;">등록</button>
			</span>
		</div>
	</form>
	<p>
	<div class="container" style="width: 49%">
		<div class="commentList">
			<div class="commentArea"
				style="border-bottom: 1px solid darkgray; margin-bottom: 15px;">
			</div>
		</div>
	</div>





	<!-- 글쓰기 -->

	<p>
		<!-- 하단 배너 -->
	<p class="text-center text-muted"
		style="border-top: 1px solid #dee2e6; padding-top: 10px;">© 2022
		Yun Company, Inc</p>
	<!-- 	<script src="/js/board_detail.js"></script> -->
	<script src="/js/list/board_detail.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>
</body>

</html>

