<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/mycss.css" />
</head>
<body>
	<h1>Upload Ajax</h1>
	<div class="uploadDiv">
		<input type="file" name="uploadFile" id="" multiple/>
	</div>
	<button>upload</button>
	<div class="uploadResult">
		<ul></ul>
	</div>
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<script>
$(function(){
	$("button").click(function(){
		console.log("업로드 호출");
		
		var inputFile = $("input[name='uploadFile']");
		console.log(inputFile);
		
		//첨부파일 목록
		var files = inputFile[0].files;//한개의 파일일때는  $("input[name='uploadFile']")[0].files[0]
		
		// <form>~~</form> 대체로 ajax로 데이터를 쉽게 전송할 수 있도록 해줌
		var formData = new FormData();
		
		//객체 안에 요소 추가
		for(var i=0;i<files.length;i++){
			formData.append("uploadFile",files[i]);
		}
	/* 	for(let key of formData.keys()){
			console.log(key);
		}
		for(let value of formData.keys()){
			console.log(key);
		} */
		$.ajax({
			url:"/uploadAjax",
			type:"post",
			//데이터를 querystring 형태로 보낼것인지 결정(기본값은 application/x-www-form-urlencoded임)
			processData:false,
			//기본값은 application/x-www-form-urlencoded임(파일첨부이므로 multipart/form-data로 보내야함)
			contentType:false,
			data:formData,
			success:function(result){ //result는 컨트롤러에서 상태코드가 넘어오는 값을 의미함
				console.log(result);
				showUploadFile(result);
			},
			error:function(xhr,status,error){
				console.log(status);
			}
		})
	})
	
	function showUploadFile(uploadResultArr){
		//파일을 올렸을때 파일명을 보여줄 영역 가져오기
		var uploadResult = $(".uploadResult ul");
		var str="";
		$(uploadResultArr).each(function(idx,obj){ //obj: 임의의 변수
			if(obj.image){
				//썸네일 이미지 경로 - 한글과 기호떄문에 인코딩해서 보내줌
				var fileCallPath = encodeURIComponent(obj.uploadPath+"\\s_"+obj.uuid+"_"+obj.fileName);
				//str += "<li>"+obj.uploadPath+"\\" + obj.uuid + "\\" + obj.fileName+"</li>";
				str+="<li><img src='/display?fileName="+fileCallPath+"'>"+obj.fileName+"</li>";
			}else{
				//첨부파일 (img제외한파일) 그림표시 작업
			str += "<li><img src='/resources/img/attach.png'>"+obj.fileName+"</li>";
			}
		});
		uploadResult.append(str);
	}
}) 
</script>
</body>
</html>