<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lnag="ko">
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" href="/css/login/signup.css">
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
<!-- 탭로고 -->
 <link rel="shortcut icon" type="image/x-icon" href="/images/home/homelog.png">
<script>


</script>
</head>
<body>
	<!-- header -->
	<div id="header">
		<a href="/bar/home" target="_blank" title="회원가입 페이지"><img
			src="/images/login/looog.png" id="logo"></a>
	</div>

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
					<span class="box int_id"> <input type="text" name="id" id="id"
						class="int" minlength="5" maxlength="20" telOnly required> <span
						class="step_url"><button class="bt" type="button" onclick="idchecked();">중복확인</button></span>
					</span> <span class="error_next_box"></span>
				</div>

				<!-- PW1 -->
				<div>
					<h3 class="join_title">
						<label for="pswd1">비밀번호</label>
					</h3>
					<span class="box int_pass"> <input type="password" name="pw" id="pw"
						class="int" maxlength="20" required> <span
						id="alertTxt">사용불가</span> <img src="/images/login/lock.png"
						id="pswd1_img1" class="pswdImg">
					</span> <span class="error_next_box"></span>
				</div>

				<!-- PW2 -->
				<div>
					<h3 class="join_title">
						<label for="pswd2">비밀번호 재확인</label>
					</h3>
					<span class="box int_pass_check"> <input type="password"
						id="pw2" class="int" maxlength="20" required> <img
						src="/images/login/lock.png" id="pswd2_img1" class="pswdImg">
					</span> <span class="error_next_box"></span>
				</div>

				<!-- NAME -->
				<div>
					<h3 class="join_title">
						<label for="name">이름</label>
					</h3>
					<span class="box int_name"> <input type="text" name="name" id="name"
						class="int" maxlength="20" required>
					</span> <span class="error_next_box"></span>
				</div>

				<!-- BIRTH -->
				<div>
					<h3 class="join_title">
						<label for="yy">생년월일</label>
					</h3>

					<div id="bir_wrap">
						<!-- BIRTH_YY -->
						<div id="bir_yy">
							<span class="box"> <input type="text" name="yy" id="yy" class="int"
								maxlength="4" placeholder="년(4자)" numOnly required>
							</span>
						</div>

						<!-- BIRTH_MM -->
						<div id="bir_mm">
							<span class="box"> <input type="text" name="mm" id="mm" class="int"
								maxlength="2" placeholder="월" numOnly required>
							</span>
						</div>

						<!-- BIRTH_DD -->
						<div id="bir_dd">
							<span class="box"> <input type="text" name="dd" id="dd" class="int"
								maxlength="2" placeholder="일" numOnly required>
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
					<span class="box gender_code"> <select id="gender" name="gender"
						class="sel" required>
							<option>성별</option>
							<option value="M">남자</option>
							<option value="F">여자</option>
					</select>
					</span> <span class="error_next_box">필수 정보입니다.</span>
		
				</div>

				<!-- MOBILE -->
				<div>
					<h3 class="join_title">
						<label for="phone">휴대전화</label>
					</h3>
					<span class="box int_mobile"> <input type="tel" name="phone" id="mobile"
						class="int" minlength="11" maxlength="16" placeholder="전화번호 입력" required numOnly>
					</span> <span class="error_next_box"></span>
				</div>
				
				<!-- EMAIL -->
				<div>
					<h3 class="join_title">
						<label for="email">본인확인 이메일<span class="optional">(선택)</span></label>
					</h3>
					<span class="box int_email"> <input type="text" name="email" id="email"
						class="int" maxlength="100" placeholder="선택입력">
					</span> <span class="error_next_box"></span>
				</div>

				
				<!-- JOIN BTN-->
				<div class="btn_area">
					<button type="submit" id="btnJoin" name="btnJoin">가입하기</button>
				</div>



			</div>
			<!-- content-->

		</div>
	</form>

	<!-- wrapper -->
	<script src="/js/login/signup.js"></script>
</body>
</html>