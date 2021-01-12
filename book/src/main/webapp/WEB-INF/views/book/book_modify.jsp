<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<h4>가격을 변경할 책의 코드 번호와 가격을 입력하세요</h4>		
	<form method='post' action='modify'>	
		<div class="form-row">	
			<div class="form-group col-md-6">  
			 	<input type="text" name="code" class="form-control form-control-lg" required="required" placeholder="코드">
			</div> 
			<div class="form-group col-md-6">  
			 	<input type="text" name="price" class="form-control form-control-lg" required="required" placeholder="가격">
			</div> 
		</div>
		<div class="form-row">
	  	<div class="form-group col-md-6">
			<button type="submit" class="btn btn-primary">입력</button>
			<button type="reset" class="btn btn-secondary">취소</button>
		</div>
	  </div>
	</form>



