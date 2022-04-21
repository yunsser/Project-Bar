<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lnag="ko">
<head>
<meta charset="UTF-8">
<title>회원상세보기</title>
<link rel="stylesheet" href="/css/login/detail.css">
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
<!-- 탭로고 -->
 <link rel="shortcut icon" type="image/x-icon" href="/images/home/homelog.png">
<script>
	function del_mem() {

		if (!confirm('정말 회원탈퇴 하시겠습니까?')) {
			return;
		}
		var uid = $('#id').text();
		var obj = {};
		obj.id = uid;

		$.ajax({
			url : '/bar/delete',
			method : 'post',
			cache : false,
			data : obj,
			dataType : 'json',
			success : function(res) {
				alert(res.deleted ? '이용해주셔서 감사합니다' : '회원탈퇴를 취소하였습니다');
				location.href = '/bar/logout';
			},
			error : function(xhr, status, err) {
				alert('에러 : ' + err);
			}
		});
	}
</script>
</head>
<body>
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
				</h3>
				<span class="box int_id" id="id">${login.id}</span>
			</div>

			<!-- NAME -->
			<div>
				<h3 class="join_title">
					<label for="name">이름</label>
				</h3>
				<span class="box int_name">${login.name}</span>
			</div>

			<!-- BIRTH -->
			<div>
				<h3 class="join_title">
					<label for="yy">생년월일</label>
				</h3>
			</div>
			<div id="bir_wrap">
				<!-- BIRTH_YY -->
				<div id="name">
					<span class="box">${login.yy}.${login.mm}.${login.dd}</span>
				</div>
			</div>
			<!-- GENDER -->
			<div>
				<h3 class="join_title">
					<label for="gender">성별</label>
				</h3>
				<span class="box gender_code">${login.gender}</span>
			</div>

			<!-- EMAIL -->
			<div>
				<h3 class="join_title">
					<label for="email">본인확인 이메일</label>
				</h3>
				<span class="box int_email">${login.email}</span>
			</div>

			<!-- MOBILE -->
			<div>
				<h3 class="join_title">
					<label for="phone">휴대전화</label>
				</h3>
				<span class="box int_mobile">${login.phone}</span>
			</div>



			<!-- JOIN BTN-->
			<div class="btn_area">
				<button type="button" id="btnJoin"
					onclick="location.href='/bar/edit'">수정하기</button>
				<button type="button" id="btnJoin" onclick="del_mem();">회원탈퇴</button>
				<button type="button" id="btnJoin"
					onclick="window.history.back()">돌아가기</button>
			</div>



		</div>
		<!-- content-->

	</div>

	<!-- wrapper -->
	<script src="/js/main.js"></script>
</body>
</html>