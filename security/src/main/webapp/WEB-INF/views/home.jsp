<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	home 페이지
</h1>
<%--로그인 정보가 있다면 로그아웃 버튼/ 정보가 없는경우 로그인 버튼을 보이게 하기
	isAnonymous() : 익명 상태라면 true
	isRememberMe() : 현재 사용자가 remember me 사용자라면 true
	isAuthenticated() : 현재 사용자가 로그인 상태라면 true
	isFullyAuthenticated() : 현재 사용자가 익명이거나 Remember Me 사용자가 아니라면 true
 --%>
<sec:authorize access="isAnonymous()">
	<button onclick="location.href='/security/login'">로그인</button>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
	<form action="/logout" method="post">
	    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
		<button>로그아웃</button>
	</form>
</sec:authorize>	
</body>
</html>
