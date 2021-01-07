<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%--넘겨와서 받을떄에는 맨 처음글자 소문자 사용 --%>
	<h1>Result</h1>
	<h2>num1 : ${numVO.num1}</h2>
	<h2>num2 : ${numVO.num2}</h2>
	<h2>result : ${result}</h2>
	<hr />
	<h2>num1 : ${vo.num1}</h2>
	<h2>num2 : ${vo.num2}</h2>
	<h2>result : ${result}</h2>
</body>
</html>
