<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<form id="leaveform" action="" method="post">
	<div class="card"  style="width: 40rem;margin:40px auto;">	
		<div class="card-header">
	    	<h4>회원탈퇴</h4>
	  	</div>
	 	<div class="card-body">	
			<div class="form-group row justify-content-center">		
				<div class="col-sm-10">	
					<input type="text" name="userid" id="userid" class="form-control" value="aassddad123" readonly/>
			 		<small id="userid" class="text-info"></small>		
				</div>
			</div>	
			<div class="form-group row justify-content-center">		
				<div class="col-sm-10">	
					<input type="password" name="password" id = "password" class="form-control" placeholder="비밀번호" autofocus="autofocus"/>
					<small id="password" class="text-info"></small>
				</div>	
			</div>				
			<div class="form-group text-center">		
				<button type="submit" class="btn btn-primary">탈퇴</button>
			    <button type="button" class="btn btn-secondary" id="leavecancel">취소</button>		
			</div>
		</div>
	</div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
$(function(){
	$(".btn-primary").click(function(e){
		e.preventDefault();
		// 전송할 데이터 객체 생성
		
		let param={
				userid : $("#userid").val(),
				password:$("#password").val(),
		}
		$.ajax({
			url:'/remove',
			type:'delete',
			contentType:'application/json',
			data:JSON.stringify(param),
			success:function(data){
				if(data=="success"){
					alert("회원 탈퇴 성공!!");
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
</form>