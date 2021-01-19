<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="" method="post" enctype="multipart/form-data">
		<input type="text" name="name"/>
		<input type="file" name="uploadFile" id="" multiple/>
		<!-- multiple : 파일 여러개 선택 가능 -->
		<button type="submit">전송</button>
	</form>
</body>
</html>