<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>로그인</title>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;700&display=swap"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/css/login/login.css">
<script src="https://kit.fontawesome.com/53a8c415f1.js"
	crossorigin="anonymous"></script>
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
	<!-- 탭로고 -->
 <link rel="shortcut icon" type="image/x-icon" href="/images/home/homelog.png">
<script>
	function login() {
		var serData = $('#loginForm').serialize();

		$.ajax({
			url : '/bar/login',
			method : 'post',
			cache : false,
			data : serData,
			dataType : 'json',
			success : function(res) {
				if (res.ok) {
					location.href = "/bar/home";
				} else {
					alert('아이디와 비밀번호를 확인해주세요');
				}
			},
			error : function(xhr, status, err) {
				alert('err: ' + err);
			}
		});
		return false;
	}
</script>
</head>
<body>
	<form id="loginForm" onsubmit="return login();">
		<div class="wrap">
			<div class="login">
				<h2>
					<img onclick="location.href='/bar/home'"
						src="/images/login/looog.png" style="width: 70px; height: 50px;">
				</h2>
				<div class="login_id">
					<h4>이메일</h4>
					<input type="text" name="id" id="id" placeholder="Id">
				</div>
				<div class="login_pw">
					<h4>비밀번호</h4>
					<input type="password" name="pw" id="pw" placeholder="Password">
				</div>
				<div class="login_etc">
					<div class="checkbox">
						<input type="checkbox" name="" id=""> 저장하기
					</div>
					<div class="forgot_pw">
						<a href="/bar/signup">회원가입</a>
					</div>
				</div>
				<div class="submit">
					<input type="submit" value="로그인">
				</div>
			</div>
		</div>
	</form>
</body>
</html>


