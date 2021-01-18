<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link
      rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
      integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
      crossorigin="anonymous"
    />
    <script
      src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
      integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
      crossorigin="anonymous"
    ></script>
    <script
      src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
      integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
      crossorigin="anonymous"
    ></script>
<meta charset="UTF-8">
<title>글쓰기</title>
<h1>게시판</h1>
</head>
<body>
 <div class="container-fluid">
      <div class="row">
        <div class="col-xl-3">
          <div class="list-group" id="add" role="tablist">
            <a
              class="list-group-item list-group-item-action"
              data-toggle="list"
              role="tab"
              href="/board/insert"
              >글쓰기</a>
            <a
              class="list-group-item list-group-item-action"
              data-toggle="list"
              role="tab"
              href="/board/list"
              >글목록 보기</a>
</body>
<script>
$(function(){
	$("#add").click(function(){
		location.href="insert";
		})
})
</script>
</html>