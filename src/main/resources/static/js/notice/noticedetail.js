 function setThumbnail(event) {
   for (var image of event.target.files) {
      var reader = new FileReader();
 
      reader.onload = function(event) {
         var img = document.createElement("img");
         img.setAttribute("src", event.target.result);
         img.setAttribute("style", "width:50%;height:width;");               
         document.querySelector("span#form-control").appendChild(img);         
 
      };
      
      console.log(image);
      reader.readAsDataURL(image);
   }
}


function del_notice(num) {

	if (!confirm('정말 삭제 하시겠습니까?')) {
		return;
	}

	var obj = {};
	obj.num = num;

	$.ajax({
		url: '/bar/notice/boardDelete',
		method: 'post',
		cache: false,
		data: obj,
		dataType: 'json',
		success: function(res) {
			alert(res.noticeDeleted ? '삭제 성공' : '삭제 실패!');
			location.href = "/bar/notice/noticelist";
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
	
	


	


