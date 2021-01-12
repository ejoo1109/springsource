<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<h4>검색할 도서정보를 입력하세요</h4>
	<form action="search" method="post">
	 <div class="form-row">
		<div class="form-group col-md-6">     
	      <select id="inputState" name="criteria" class="form-control">
	        <option value="code" selected>코드</option>
	        <option value="writer">작가</option>
	      </select>
	    </div>
		<div class="form-group col-md-6">	     
	      <input type="text" class="form-control" id="userinput" name="keyword">
	    </div>
	  </div>
	  <div class="form-row">
	  	<div class="form-group col-md-6">
			<button type="submit" class="btn btn-primary">검색</button>
			<button type="reset" class="btn btn-secondary">취소</button>
		</div>
	  </div>
	</form>



