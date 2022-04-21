<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lnag="ko">
<head>
<meta charset="UTF-8">
<title>회원정보수정</title>
<link rel="stylesheet" href="/css/login/edit.css">
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
<!-- 탭로고 -->
 <link rel="shortcut icon" type="image/x-icon" href="/images/home/homelog.png">
<script>
function updateMem() {
	var serData = $('#updateForm').serialize();
	$.ajax({
		url:'/bar/update',
		method:'post',
		cache:false,
		data:serData,
		dataType:'json',
		success:function(res){
			if(res.updated) {
				alert('수정성공!');
			} else {
				alert('수정 실패');
			}
			location.href = "/bar/detail";
		},
		error:function(xhr,status,err){
			alert('에러:'+err);
		}
	});
	return false;
}
</script>
</head>
<body>
<form id="updateForm" onsubmit="return updateMem();">
<input type="hidden" id="id" name="id" value="${login.id}"> 
	<!-- header -->
	<div id="header">
		<a href="/bar/home" target="_blank" title="회원가입 페이지"><img
			src="/images/login/looog.png" id="logo"></a>
	</div>

		<!-- wrapper -->
		<div id="wrapper">

			<!-- content-->
			<div id="content">
			
			<!-- ID -->
				<div>
					<h3 class="join_title">
						<label for="id">아이디</label>
						<span class="box int_id">${login.id}</span>
					</h3>
				</div>
			

				<!-- PW1 -->
				<div>
					<h3 class="join_title">
						<label for="pswd1">비밀번호</label>
					</h3>
					<span class="box int_pass"> <input type="password" name="pw" id="pw"
						class="int" maxlength="20"> <span
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
						id="pw2" class="int" maxlength="20"> <img
						src="/images/login/lock.png" id="pswd2_img1" class="pswdImg">
					</span> <span class="error_next_box"></span>
				</div>


				<!-- EMAIL -->
				<div>
					<h3 class="join_title">
						<label for="email">본인확인 이메일<span class="optional">(선택)</span></label>
					</h3>
					<span class="box int_email"> <input type="text" name="email" id="email"
						class="int" maxlength="100" placeholder="선택입력" value="${login.email}">
					</span> <span class="error_next_box"></span>
				</div>

				<!-- MOBILE -->
				<div>
					<h3 class="join_title">
						<label for="phone">휴대전화</label>
					</h3>
					<span class="box int_mobile"> <input type="tel" name="phone" id="mobile"
						class="int" maxlength="16" placeholder="전화번호 입력" value="${login.phone}">
					</span> <span class="error_next_box"></span>
				</div>



				<!-- JOIN BTN-->
				<div class="btn_area">
					<button type="submit" id="btnJoin">수정하기</button>
					<button type="button" id="btnJoin" onclick="history.back()">돌아가기</button>
				</div>



			</div>
			<!-- content-->

		</div>
</form>
	<!-- wrapper -->
	<script src="/js/login/edit.js"></script>
</body>
</html>