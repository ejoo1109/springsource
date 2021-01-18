<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html> 
<head>
    <title>List</title>
    
    <!-- Page level plugin CSS-->
    <th:block layout:fragment="contentsCss">
    </th:block>
</head>
 
<body>
    <!-- header를 작성하지 않아도 header가 이 위치에 구성된다. -->
 
    <div class="container" layout:fragment="content">
        <div class="page-header">
            <h1>게시글 목록</h1>
        </div>
        <br/>
        <br/>
        <br/>
            </div>
	  <!-- Table -->
	  <div>
	  <table class="table">
         <th class="col-sm-1">No</th>
         <th class="col-sm-5">제목</th>
         <th class="col-sm-3">작성 날짜</th>
        <th class="col-sm-3">수정 날짜</th>
          <tbody>
                    <!-- 리스트 내용 보여주기 -->
                  <c:forEach var="vo" items="${list}">
                       <tr>
                           <td>${vo.idx}</td>
                           <td>${vo.title}</td>
                           <td><fmt:formatDate value="${vo.createdate}" pattern="yyyy-MM-dd HH:mm"/></td>
                           <td><fmt:formatDate value="${vo.updatedate}" pattern="yyyy-MM-dd HH:mm"/></td>
                        </tr>
                        </c:forEach>
                    </tbody>
	  </table>
	  </div>
</body>
</html>