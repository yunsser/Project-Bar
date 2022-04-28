function goLogin() {
	var url = "/bar/login";
	location.href = url;
}
function goSignup() {
	var url = "/bar/signup";
	location.href = url;
}
function goLogout() {
	var url = "/bar/logout";
	location.href = url;
}
function goDetail() {
	var url = "/bar/detail";
	location.href = url;
}
function goMaster() {
	var url = "/bar/master/main"
	location.href = url;
}


/*체크*/
$("#allCheck").click(function() {
	var chk = $("#allCheck").prop("checked");
	if (chk) {
		$(".userCheck").prop("checked", true);
	} else {
		$(".userCheck").prop("checked", false);
	}

});

/*전체체크*/
$(".userCheck").click(function() {
	$("#allCheck").prop("checked", false);
});


/*체크삭제*/
$("#delete").click(function() {
	var confirm_val = confirm("정말 삭제하시겠습니까?");

	if (confirm_val) {
		var checkArr = new Array();

		$("input[class='userCheck']:checked").each(function() {
			checkArr.push($(this).attr("data-num"));
		});
		//alert('번호'+checkArr);

		$.ajax({
			url: '/bar/master/userDelete',
			type: 'post',
			data: { chbox: checkArr },
			dataType: 'json',
			success: function(res) {
				//alert('res'+res.userDelete);
				alert('삭제되었습니다');
				location.href = "/bar/master/list";



			}
		});
	}
});