<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<link rel="shortcut icon" type="image/x-icon"
	href="/images/home/homelog.png">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
<!-- css -->
<link rel="stylesheet" href="/css/cart/cartlist.css">
<!-- 탭로고 -->
<link rel="shortcut icon" type="image/x-icon"
	href="/images/home/homelog.png">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge"> -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<head>
<meta charset="utf-8">
<title>title</title>
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
						<!-- <button type="button" class="btn btn-outline-light me-2"
							style="border: none; color: gray; padding-bottom: 1px; padding-right: 3px;"
							onclick="goMaster();">Master</button> -->
						<span id="login_log"
							style="border-bottom: 1px solid white; padding-bottom: 1px; color: gray; position: relative; top: 5px; right: 6px;">
							Master </span>
					</c:if>
					<c:if test="${id != 'master'}">
						<span id="login_log"
							style="border-bottom: 1px solid white; padding-bottom: 1px; color: gray; position: relative; top: 5px; right: 6px;">
							${id}님 </span>
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

	<!-- 게시글 -->
	<div class="container admin_container">
		<div class="row">
			<h1 style="text-align: center; margin: 20px 0;">CART</h1>
			<table class="table table-hover"
				style="width: 70%; text-align: center; margin: auto;">
				<thead>
					<tr>
						<th><input type="checkbox" id="checkAll" name="checkAll"
							class="checkAll"></th>
						<th></th>
						<th>PRODUCT</th>
						<th>CONT</th>
						<th>PRICE</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="p" items="${list}">
						<tr>
							<th><input type="checkbox" class="cartCheck"
								name="cartCheck" id="cartCheck" data-num="${p.num_pr}"></th>

							<td><img src="/upload/${p.imgname}" alt="사진"
								style="width: 75px;"></td>
							<td>${p.name}</td>
							<td>${p.qty}</td>
							<td>${p.price}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<!-- 게시판 -->
	<p>
		<!-- 총액 -->
	<div class="totalnum">TOTAL : ${total}원</div>
	<!-- 총액 -->

	<p>
	<div class="btn-holder">
		<c:if test="${id!='master'}">
			<button class="btn btn-1 hover-filled-slide-right" type="button"
				onclick="goPay" style="display: inline-block;">
				<span>결제</span>
			</button>
		</c:if>
		<button class="btn btn-1 hover-filled-slide-right" type="button"
			id="delete" style="display: inline-block;">
			<span>삭제</span>
		</button>
	</div>

	<p>

		<!-- 하단베너 -->
		<div class="text-center text-muted"
		style="border-top: 1px solid #dee2e6; padding-top: 10px;">© 2022
		Yun Company, Inc</div>


	<script src="/js/cart/cartlist.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous">
		
	</script>

</body>
</html>