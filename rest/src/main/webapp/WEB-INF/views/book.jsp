<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
	<h3>도서정보</h3>
	<button type="button" id="all">전체 도서 정보</button>
	<button type="button" id="get">특정 도서 정보</button>
	<button type="button" id="remove">도서 정보 삭제</button>
	<button type="button" id="update">도서 정보 수정</button>
	<button type="button" id="input">도서 정보 입력</button>
	<div id="result">
		<table class="table"></table>
	</div>
</div>
<script>
$(function(){
	//전체 리스트 가져오기
	$("#all").click(function(){
		$.getJSON({ //JSON 형태로 데이터 보낸것 가져오기 - 비동기식
			url:'/list', //컨트롤러에서 보내준 경로 동일하게 맞추기
			success:function(data){
				console.log(data);
				let str="";
				$(data).each(function(idx,item){
					str+="<tr><td>";
					str+=item.code+"</td><td>"+item.title+"</td>";
					str+="<td>"+item.writer+"</td>"+"<td>"+item.price+"</td></tr>";
		})
				$(".table").html(str);
		}
	})
	})
			//특정 도서 정보 가져오기
	$("#get").click(function(){
 		$.getJSON({ //JSON 형태로 데이터 보낸것 가져오기 - 비동기식
			url:'/1004', //컨트롤러에서 보내준 경로 동일하게 맞추기
			success:function(item){
				console.log(item);
				let str="";
					//한개의 정보만 조회하기 때문에 each 필요없음
					str+="<tr><td>";
					str+=item.code+"</td><td>"+item.title+"</td>";
					str+="<td>"+item.writer+"</td>"+"<td>"+item.price+"</td></tr>";

				$(".table").html(str);
		}
	})  
	})
		//도서 정보 삭제
	$("#remove").click(function(){
		$.ajax({
			url:'1003',
			type:'delete', //메소드 방식 기재
			success:function(data){
				$(".table").html(data);
			},
			error:function(xhr,txtSatus,error){
				$(".table").html(xhr.responseText);
			}
		})
	})
	//도서 정보 수정
	$("#update").click(function(){ //코드 , 가격
		let param = { //자바스크립트 param 객체 (key,value형태)
			code : 1004,
			price : 33000
	}
	$.ajax({
		url:'/update',
		type:"put", //patch 수정
		contentType:"application/json", //json형태로 보여주기 위한 type 변환
		//변환을 안시키면 기본형태로 가기 때문에 에러 application/x-www-form-urlencoded;charset=UTF-8
		data:JSON.stringify(param), //자바스크립트 객체를 JSON형태로 변환시킴
		success:function(data){
			$(".table").html(data);
		},
		error:function(xhr,txtSatus,error){
			$(".table").html(xhr.responseText);
		}
	})
	})
	$("#input").click(function(){
		let param = { 
				code : 1013,
				title : 'do it java',
				writer : '홍길동',
				price : 33000
		}
		$.ajax({
			url:'/new',
			type:"post", 
			contentType:"application/json", //json형태로 보여주기 위한 type 변환
			//변환을 안시키면 기본형태로 가기 때문에 에러 application/x-www-form-urlencoded;charset=UTF-8
			data:JSON.stringify(param), //자바스크립트 객체를 JSON형태로 변환시킴
			success:function(data){
				$(".table").html(data);
			},
			error:function(xhr,txtSatus,error){
				$(".table").html(xhr.responseText);
			}
	})
	
	})
	
})
</script>
</body>
</html>