<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form method='post' action=''>	
  <div class="form-group">
    <label for="title">제목</label>
    <input type="text" class="form-control" name="title" placeholder="title" required="required">
  </div>
  <div class="form-group">
    <label for="writer">내용</label>
    <input type="text" class="form-control" name="content" placeholder="content" required="required">
  </div>
  <button type="submit" class="btn btn-primary">입력</button>
  <button type="reset" class="btn btn-secondary">취소</button>	
</form>	
