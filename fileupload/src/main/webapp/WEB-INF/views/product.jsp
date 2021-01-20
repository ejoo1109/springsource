<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
  <body>
    <div class="container">
      <form>
        <div class="form-row">
          <div class="form-group col-md-6">
            <label for="code">상품코드</label>
            <input type="text" name="code" class="form-control" id="code" />
          </div>
          <div class="form-group col-md-6">
            <label for="category">카테고리</label>
            <select name="category" id="category" class="form-control">
              <option value="P01">신발/잡화</option>
              <option value="P02">패션의류</option>
              <option value="P03">화장품/헤어</option>
              <option value="P04">식품</option>
            </select>
          </div>
          </div>
        <div class="form-row">
          <div class="form-group col-md-6">
            <label for="pname">상품명</label>
            <input type="text" name="pname" class="form-control" id="pname" />
          </div>
          <div class="form-group col-md-6">
            <label for="amount">수량</label>
            <input type="text" name="amount" class="form-control" id="amount" />
          </div>
        </div>
        <div class="form-row">
          <div class="form-group col-md-6">
            <label for="price">상품가격</label>
            <input type="text" class="form-control" id="price" name="price" />
          </div>
          <div class="form-group col-md-6">
            <label for="etc">기타</label>
            <input type="text" class="form-control" id="etc" name="etc" />
          </div>
          </div>
          <div class="form-group">
            <label for="file">파일첨부</label>
            <input type="file" class="form-control" id="file" name="file" />
          </div>      
          <button type="submit" class="btn btn-primary">입력</button>
      </form>
    </div>
<script src=/resources/js/product2.js></script>
</body>
</html>