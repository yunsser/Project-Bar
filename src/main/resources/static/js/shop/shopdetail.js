function del_board(num) {

	if (!confirm('정말 삭제 하시겠습니까?')) {
		return;
	}

	var obj = {};
	obj.num = num;
	
	$.ajax({
		url: '/bar/shop/delete',
		method: 'post',
		cache: false,
		data: obj,
		dataType: 'json',
		success: function(res) {
			alert(res.boardDeleted ? '삭제 성공' : '삭제 실패!');
			location.href = "/bar/shop/list";
		},
		error: function(xhr, status, err) {
			alert('에러 : ' + err);
		}
	});
}

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
	
	
/*===================================================================================*/

function cartAdd()
{
	var obj = {};
	obj.num_pr = $('#num_pr').val();
	obj.name = $('#name').text();
	obj.price = $('#price').text();
	obj.count = $('#count').val();
	
	$.ajax({
		url:'/bar/cart/add',
		method:'post',
		cache:false,
		data:obj,
		dataType:'json',
		success:function(res){
			alert(res.added ? '장바구니에 담겼습니다':'장바구니에 담기를 실패하였습니다');
			if(res.added){
				location.href='/bar/shop/list';
			}
		},
		error:function(xhr,status,err){
			alert('에러:'+err);
		}
	});
	return false;
}
