<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>비밀번호 변경</title>
    <link
      rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
      crossorigin="anonymous"
    />
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/jquery.validate.js"></script>
    <script>
    $(function(){
    	var error ='${error}';
    	if(error!=''){
    		alert(error);
    	}
    })
    </script>
    <style>
      body {
        margin-top: 100px;
      }
    </style>
     </head>
  <body>
    <div class="card border-success mb-3 mx-auto" style="max-width: 25rem;">
      <div class="card-header">비밀번호 변경</div>
      <div class="card-body">
        <form id="changePwd" method="post">
          <div class="form-group row">
            <input
              type="password"
              class="form-control"
              size="50"
              id="password"
              name="password"
              placeholder="현재 비밀번호"
            />
            <small id="password" class="text-info"></small>
          </div>
          <div class="form-group row">
            <input
              type="password"
              class="form-control"
              size="50"
              id="new_password"
              name="new_password"
              placeholder="새 비밀번호"
            />
            <small id="new_password" class="text-info"></small>
          </div>
          <div class="form-group row">
            <input
              type="password"
              class="form-control"
              size="50"
              id="confirm_password"
              name="confirm_password"
              placeholder="새 비밀번호 확인"
            />
            <small id="confirm_password" class="text-info"></small>
          </div>
          <div class="form-group row">
            <button type="submit" class="btn btn-primary btn-block">
              변경
            </button>
          </div>
        </form>
      </div>
    </div>
    <script>
$(function(){
	$(".btn-primary").click(function(e){
		e.preventDefault();
		// 전송할 데이터 객체 생성
		
		let param={
				password : $("#password").val(),
				new_password:$("#new_password").val(),
				confirm_password:$("#confirm_password").val(),

		}
		
		$.ajax({
			url:'/changePwd',
			type:'put',
			contentType:'application/json',
			data:JSON.stringify(param),
			success:function(data){
				if(data=="success"){
					alert("비밀번호 변경에 성공했습니다. \n 다시 로그인해 주세요");
					location.href="/";
				}
			},
			error : function(xhr,txtStatus,error){
				alert(xhr.responseText);
			}
		})
	})	
})
</script>
  </body>
</html>