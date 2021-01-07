<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 	<h1>Insert</h1>
 	<form action="view" method="post">
 	<div>
 		<label for="writer">작성자</label>
 		<input type="text" name="writer" id="writer"/>
 	</div>
 	 <div>
 		<label for="title">제목</label>
 		<input type="text" name="title" id="title"/>
 	</div>
 	 <div>
 		<label for="content">내용</label>
 		<textarea name="content" id="content" cols="30" rows="10"></textarea>
 	</div>
 	<div>
 		<label for="password">비밀번호</label>
 		<input type="password" name="password" id="password"/>
 	</div>
 	<div>
 		<button type="submit">전송</button>
 		<button type="reset">취소</button>
 	</div>
 	</form>
</body>
</html>