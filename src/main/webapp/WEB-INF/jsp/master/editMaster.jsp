<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lnag="ko">
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" href="/css/master/editMaster.css">
<!-- 부트 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
<!-- 탭로고 -->
<link rel="shortcut icon" type="image/x-icon"
	href="/images/home/homelog.png">
<script>
	
</script>
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
					<li><a href="/bar/notice/noticelist" class="nav-link px-2 text-dark">공지사항</a></li>
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
						<!-- <button type="button" class="btn btn-outline-light me-2"
							style="border: none; color: gray; padding-bottom: 1px; padding-right: 3px;"
							onclick="goMaster();">Master</button> -->
						<span id="login_log"
							style="border-bottom: 1px solid white; padding-bottom: 1px; color: gray; position: relative; top: 5px; right: 6px;">
							Master </span>
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
	

	<form id="addForm" onsubmit="return addMemder();" novalidate>
		<!-- wrapper -->
		<div id="wrapper">

			<!-- content-->
			<div id="content">


				<!-- ID -->
				<div>
					<h3 class="join_title">
						<label for="id">아이디</label>
					</h3>
					<span class="box int_id" id="id" style="color: gray; padding: 12px;">${login.id}</span>
				</div>

				<!-- NAME -->
				<div>
					<h3 class="join_title">
						<label for="name">이름</label>
					</h3>
					<span class="box int_name" style="color: gray; padding: 12px;">${login.name}</span>
				</div>

				<!-- BIRTH -->
				<div>
					<h3 class="join_title">
						<label for="yy">생년월일</label>
					</h3>

					<div id="bir_wrap">
						<!-- BIRTH_YY -->
						<div id="bir_yy">
							<span class="box"> <input type="text" name="yy" id="yy"
								class="int" maxlength="4" placeholder="년(4자)" numOnly required value="${login.yy}">
							</span>
						</div>

						<!-- BIRTH_MM -->
						<div id="bir_mm">
							<span class="box"> <input type="text" name="mm" id="mm"
								class="int" maxlength="2" placeholder="월" numOnly required value="${login.mm}">
							</span>
						</div>

						<!-- BIRTH_DD -->
						<div id="bir_dd">
							<span class="box"> <input type="text" name="dd" id="dd"
								class="int" maxlength="2" placeholder="일" numOnly required value="${login.dd}">
							</span>
						</div>

					</div>
					<span class="error_next_box">생년월일을 확인해주세요</span>
				</div>

				<!-- GENDER -->
				<div>
					<h3 class="join_title">
						<label for="gender">성별</label>
					</h3>
					<span class="box gender_code" style="color: gray; padding: 12px;">${login.gender}</span>


				</div>

				<!-- MOBILE -->
				<div>
					<h3 class="join_title">
						<label for="phone">휴대전화</label>
					</h3>
					<span class="box int_mobile"> <input type="tel" name="phone"
						id="mobile" class="int" minlength="11" maxlength="16"
						placeholder="전화번호 입력" required numOnly value="${login.phone}">
					</span> <span class="error_next_box"></span>
				</div>

				<!-- EMAIL -->
				<div>
					<h3 class="join_title">
						<label for="email">본인확인 이메일<span class="optional">(선택)</span></label>
					</h3>
					<span class="box int_email"> <input type="text" name="email"
						id="email" class="int" maxlength="100" placeholder="선택입력" value="${login.email}">
					</span> <span class="error_next_box"></span>
				</div>


				<!-- JOIN BTN-->
				<div class="btn_area">
					<button type="submit" id="btnJoin" name="btnJoin">수정</button>
					<button type="button" id="btnJoin" name="btnJoin" onclick="golistMaster();">목록</button>
				</div>



			</div>
			<!-- content-->

		</div>
	</form>

	<!-- wrapper -->
	<script src="/js/master/editMaster.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>
	<!-- 하단베너 -->
</body>
</html>