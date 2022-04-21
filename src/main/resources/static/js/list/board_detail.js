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


function del_board(num) {

	if (!confirm('정말 삭제 하시겠습니까?')) {
		return;
	}

	var obj = {};
	obj.num = num;
	/* alert(obj.uid); */

	$.ajax({
		url: '/bar/boardDelete',
		method: 'post',
		cache: false,
		data: obj,
		dataType: 'json',
		success: function(res) {
			alert(res.boardDeleted ? '삭제 성공' : '삭제 실패!');
			location.href = "/bar/list";
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
	
	
	
/*function regisgterComment(){
	alert('saddasdsadasdsa');
	
	if(!confirm('정말 저장하시겠습니까?')) {
		alert('정상적으로 취소했습니다');
		return false;
	}
	
	var serData = $('#commentForm').serialize();
	
	$.ajax({
		url:'/bar/comment',
		method:'post',
		cache:false,
		data:serData,
		dataType:'json',
		success:function(res){
			alert(res.comment ? '댓글성공' : '댓글성공');
		},
		error: function(xhr,status, err) {
			alert('에러:'+err);
		}
	});
	return false;
}	
*/
	
	

	

var bno = '${board.num}'; //게시글 번호
 
$('[name=commentInsertBtn]').click(function(){ //댓글 등록 버튼 클릭시 
    var insertData = $('[name=commentInsertForm]').serialize(); //commentInsertForm의 내용을 가져옴
    commentInsert(insertData); //Insert 함수호출(아래)
});
 
//댓글 목록 
function commentList(){
    $.ajax({
        url : '/bar/comment/list',
        type : 'post',
        data : {'bno':bno},
        success : function(data){
            var a =''; 
            $.each(data, function(key, value){ 
                a += '<div class="commentArea" style="border-bottom:1px solid darkgray; margin-bottom: 15px;">';
                a += '<div class="commentInfo'+value.cno+'">'+'작성자 : '+value.writer;
                a += '<a onclick="commentUpdate('+value.cno+',\''+value.content+'\');"> 수정 </a>';
                a += '<a onclick="commentDelete('+value.cno+');"> 삭제 </a> </div>';
                a += '<div class="commentContent'+value.cno+'"> <p> 내용 : '+value.content +'</p>';
                a += '</div></div>';
            });
            
            $(".commentList").html(a);
        }
    });
}



 
//댓글 등록
function commentInsert(insertData){
    $.ajax({
        url : '/bar/comment/insert',
        type : 'post',
        data : insertData,
        success : function(data){
            if(data == 1) {
                commentList(); //댓글 작성 후 댓글 목록 reload
                $('[name=content]').val('');
            }
        }
    });
}
 
//댓글 수정 - 댓글 내용 출력을 input 폼으로 변경 
function commentUpdate(cno, content){
    var a ='';
    a += '<div class="input-group">';
    a += '<input type="text" class="form-control" name="content_'+cno+'" value="'+content+'"/>';
    a += '<span class="input-group-btn"><button class="btn btn-default" type="button" onclick="commentUpdateProc('+cno+');">수정</button> </span>';
    a += '</div>';
    
    $('.commentContent'+cno).html(a);
    
}
 
//댓글 수정
function commentUpdateProc(cno){
    var updateContent = $('[name=content_'+cno+']').val();
    
    $.ajax({
        url : '/bar/comment/update',
        type : 'post',
        data : {'content' : updateContent, 'cno' : cno},
        success : function(data){
            if(data == 1) commentList(bno); //댓글 수정후 목록 출력 
        }
    });
}
 
//댓글 삭제 
function commentDelete(cno){
    $.ajax({
        url : '/bar/comment/delete/'+cno,
        type : 'post',
        success : function(data){
            if(data == 1) commentList(bno); //댓글 삭제후 목록 출력 
        }
    });
}
 
 
$(document).ready(function(){
    commentList(); //페이지 로딩시 댓글 목록 출력 
});
