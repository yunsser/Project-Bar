function goLogin() {
	var url = "/bar/login";
	location.href = url;
}

function goWriting() {
	var url = "/bar/shop/board";
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


/*체크*/
$("#checkAll").click(function() {
	var chk = $("#checkAll").prop("checked");
	if (chk) {
		$(".checkitem").prop("checked", true);
	} else {
		$(".checkitem").prop("checked", false);
	}

});

/*전체체크*/
$(".checkitem").click(function() {
	$("#checkAll").prop("checked", false);
});


/*체크값 넘기기*/
 $("#choice").click(function(){
  var confirm_val = confirm("장바구니에 담으시겠습니까??");
  
  
  if(confirm_val) {
   var checkArr = new Array();
   
   $("input[class='checkitem']:checked").each(function(){
    checkArr.push($(this).attr("data-num"));
   });
   
  // alert(checkArr); 
   
   $.ajax({
    url : '/bar/shop/shopChoice',
    type : 'post',
    data : { chbox : checkArr },
    dataType:'json',
    success : function(res){
		alert('장바구니로 담았습니다');
		location.href = "/bar/cart/list";
    },
    error:function(xhr, status, err){
		alert('에러:'+err);
	}
    
   });
  } 
 });
 
 
 
/*function cartAdd()
{
	
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
}*/
