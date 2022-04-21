<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<link rel="stylesheet" href="/css/list/list.css">
<!-- 탭로고 -->
<link rel="shortcut icon" type="image/x-icon"
	href="/images/home/homelog.png">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge"> -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<head>
<meta charset="utf-8">
<title>게시판</title>
</head>
<body>
	<script>
		function selChange() {
			var sel = document.getElementById('cntPerPage').value;
			location.href = "list?nowPage=${paging.nowPage}&cntPerPage=" + sel;
		}
	</script>

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


	<div id="outter">
		<div class="right">

			<select id="cntPerPage" class="select_btn" name="sel"
				onchange="selChange()" style="display: none;">
				<option value="15"
					<c:if test="${paging.cntPerPage == 15}">selected</c:if>>15줄
					보기</option>
			</select>

		</div>
	</div>
	<!-- 게시판 -->
	<table class="table" style="text-align: center;">
		<tr>
			<th>#</th>
			<th>제목</th>
			<!-- 			<th>내용</th> -->
			<th>작성자</th>
			<th>작성일</th>
		</tr>
		<c:forEach var="u" items="${viewAll}">

			<tr>
				<!-- 돌아갈 프로그램지목 ? 뒤에있는 프로그램을 받아와서 쓰기  -->
				<td>${u.num}</td>
				<td><a href="/bar/boardDetail?num=${u.num}">${u.title}</a></td>
				<%-- 				<td><a href="/bar/boardDetail?num=${u.num}">${u.contents}</a></td> --%>
				<td>${u.author}</td>
				<td>${u.date}</td>
			</tr>
		</c:forEach>
	</table>
	<!-- 게시판 -->

	<P>

		<!-- 버튼 -->
	<div class="btn-holder">
		<button class="btn btn-1 hover-filled-slide-right" type="button"
			onclick="goWriting();" style="display: block;">
			<span>글작성</span>
		</button>
	</div>
	<!-- 버튼 -->


	<!-- 숫자버튼 -->
	<p>
	
	<div>
		<ul class="pagination" id="pagination">

			<c:if test="${paging.startPage != 1 }">
				<a id="be" class="page-link"
					href="list?nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}">Before</a>
			</c:if>

			<c:forEach begin="${paging.startPage }" end="${paging.endPage }"
				var="p">
				<c:choose>
					<c:when test="${p == paging.nowPage }">
						<li class="page-item"><span aria-hidden="true"><b
								class="page-link">${p }</b></span></li>
					</c:when>
					<c:when test="${p != paging.nowPage }">
						<li class="page-item"><span aria-hidden="true"><a
								class="page-link"
								href="list?nowPage=${p }&cntPerPage=${paging.cntPerPage}">${p }</a></span></li>
					</c:when>
				</c:choose>
			</c:forEach>

			<c:if test="${paging.endPage ne paging.lastPage}">
				<a class="page-link"
					href="list?nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}">Next</a>
			</c:if>

		</ul>
	</div>
	<!-- 숫자버튼 -->

	<p>
		<!-- 하단베너 -->
		<p class="text-center text-muted"
		style="border-top: 1px solid #dee2e6; padding-top: 10px;">© 2022
		Yun Company, Inc</p>
	<script src="/js/list/list.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>
	<!-- 하단베너 -->
	</body>
</html>