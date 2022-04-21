function addShop() {

	if (!confirm('정말로 저장하시겠습니까?')) {
		alert('정상적으로 취소했습니다.');
		return false;
	}

	var formData = new FormData($("#form")[0]);
	
	$.ajax({
		url: '/bar/shop/board',
		method: 'post',
		enctype: 'multipart/form-data',
		cache: false,
		data: formData,
		processData: false,
		contentType: false,
		dataType: 'json',
		success: function(res) {
			if (res.added) {
				alert('저장 성공!');
			} else {
				alert('저장 실패');
			}
			location.href = "/bar/shop/list";
		},
		error: function(xhr, status, err) {
			alert('err' + err);
		}
	});
	//alert('데이터'+formData);
	return false;
}


function removeThum(img){
	//alert(img);
	$(img).remove();
	$("#image").val("");
}

function setThumbnail(event) {
   for (var image of event.target.files) {
      var reader = new FileReader();
 
      reader.onload = function(event) {
         var img = document.createElement("img");
         img.setAttribute("src", event.target.result);
         img.setAttribute("style", "width:32%;height:width;");         
         img.setAttribute('onclick', "removeThum(this)");
         document.querySelector("div#image_container").appendChild(img);         
 
      };
      
      console.log(image);
      reader.readAsDataURL(image);
   }
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