<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <h1>View</h1>
	<form action="" method="post">
 	<div>
 		<label for="writer">작성자</label>
 		<input type="text" name="title" id="title" value="${vo.writer}"/>
 	</div>
 	 	<div>
 		<label for="title">제목</label>
 		<input type="text" name="title" id="title" value="${vo.title}"/>
 	</div>
 	 <div>
 		<label for="content">내용</label>
 		<textarea name="content" id="content" cols="30" rows="10">${vo.content}</textarea>
 	</div>
 	<div>
 		<label for="password">비밀번호</label>
 		<input type="password" name="password" id="password" value="${vo.password}"/>
 	</div>
 	<div>
 		<button type="submit">전송</button>
 		<button type="reset">취소</button>
</body>
</html>