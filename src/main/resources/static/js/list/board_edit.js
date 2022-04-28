function del_board(num) {

	if (!confirm('정말 삭제 하시겠습니까?')) {
		return;
	}

	var obj = {};
	obj.num = num;

	$.ajax({
		url: '/bar/file/delete',
		method: 'post',
		cache: false,
		data: obj,
		dataType: 'json',
		success: function(res) {
			alert(res.boardDeleted ? '삭제 성공' : '삭제 실패!');
			location.reload();
		},
		error: function(xhr, status, err) {
			alert('에러 : ' + err);
		}
	});
}

var delfiles = new Array();

function del_file(fnum) {
	//alert('fnum'+fnum);
	delfiles.push(fnum);
	$('#'+fnum).remove();
}

function updateBoard() {

	//alert('del'+del_file);
	if (!confirm('정말로 저장하시겠습니까?')) {
		alert('정상적으로 취소했습니다.');
		return false;
	}

	var formData = new FormData($("#updateForm")[0]);
	formData.append("delfiles", delfiles);
	
	$.ajax({
		url: '/bar/boardUpdate',
		method: 'post',
		enctype: 'multipart/form-data',
		cache: false,
		data: formData,
		processData: false,
		contentType: false,
		dataType: 'json',
		success: function(res) {
			if (res.boardUpdated) {
				alert('저장 성공!');
			} else {
				alert('저장 실패');
			}
			location.href = "/bar/list";
		},
		error: function(xhr, status, err) {
			alert('err' + err);
		}
	});
	return false;
}

/*function del_file(num) {
		
		if(!confirm('정말로 삭제하시겠어요?')){
			return;
		}
		var obj = {};
		obj.num = num;
		$.ajax({
			url:'/bar/file/delete',
			method:'post',
			cache:false,
			data:obj,
			dataType:'json',
			success:function(res){
				alert(res.deleteFileInfo ? '삭제 성공':'삭제 실패');
				location.reload();
			},
			error:function(xhr,status,err){
				alert('에러:'+err);
			}
		});
	}
	
	
*/
	function setThumbnail(event) {
   for (var image of event.target.files) {
      var reader = new FileReader();
 
      reader.onload = function(event) {
         var img = document.createElement("img");
         img.setAttribute("src", event.target.result);
         img.setAttribute("style", "width:32%;height:width;");         
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