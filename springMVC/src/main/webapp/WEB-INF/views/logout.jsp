<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%--EL 에서 $를 붙여서 사용할 수 있는 값은 request.session 에 담긴 값만 불러다
	    사용 가능
	 --%>
	<h1>로그아웃</h1>
	<%--@service --%>
	<h2>아이디 : ${loginVO.userid}</h2>
	<h2>비밀번호 : ${loginVO.password}</h2>
	<hr/>
	<h2>아이디 : ${login.userid}</h2>
	<h2>비밀번호 : ${login.password}</h2>
</body>
</html>