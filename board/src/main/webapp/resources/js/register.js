/**
 * 파일 첨부와 관련된 스크립트
 */
$(function(){
	//게시글 등록버튼 동작막기
	$("button[type='submit']").click(function(e){
		e.preventDefault();
		
		var str="";
		//첨부파일 영역의 정보 수집
		$(".uploadResult ul li").each(function(idx,obj){
			var job=$(obj);
		//수집된 정보를 hidden 태그로 작성
			str+="<input type='hidden' name='attachList["+idx+"].uuid' value='"+job.data("uuid")+"'>";
			str+="<input type='hidden' name='attachList["+idx+"].uploadPath' value='"+job.data("path")+"'>";
			str+="<input type='hidden' name='attachList["+idx+"].fileName' value='"+job.data("filename")+"'>";
			str+="<input type='hidden' name='attachList["+idx+"].fileType' value='"+job.data("type")+"'>";
		})
		console.log(str);

		
		//hidden 태그를 게시글 등록 폼에 추가한 후 폼 전송하기
		//1.게시글 등록 폼 가져오기
		var form = $("form");
		//2. 폼에 추가하기
		form.append(str);
		//3.전송
		form.submit();
				
	})	
	
	
	//파일버튼이 클릭되어 변화가 일어나는 경우
	//현재 목록의 파일을 서버로 보내서 저장하기
	$("input[type='file']").change(function(){
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

		$.ajax({
			url:"/uploadAjax",
			type:"post",
			//데이터를 querystring 형태로 보낼것인지 결정(기본값은 application/x-www-form-urlencoded임)
			processData:false,
			//기본값은 application/x-www-form-urlencoded임(파일첨부이므로 multipart/form-data로 보내야함)
			contentType:false,
			data:formData,
			beforeSend:function(xhr){
				xhr.setRequestHeader(csrfHeaderName,csrfTokenValue);
			},
			success:function(result){ //result는 컨트롤러에서 상태코드가 넘어오는 값을 의미함
				console.log(result);
				showUploadFile(result); //첨부파일 업로드 후 파일이름 남지않게 하기
				$("input[name='uploadFile']").val("");
			},
			error:function(xhr,status,error){
				console.log(status);
			}
		})
	}) //input close
	
	
		function showUploadFile(uploadResultArr){
		//파일을 올렸을때 파일명을 보여줄 영역 가져오기
		var uploadResult = $(".uploadResult ul");
		var str="";
		$(uploadResultArr).each(function(idx,obj){ //obj: 임의의 변수
			if(obj.fileType){
					//썸네일 이미지 경로 - 한글과 기호때문에 인코딩해서 보내줌
					var fileCallPath = encodeURIComponent(obj.uploadPath+"\\s_"+obj.uuid+"_"+obj.fileName);
					//원본 이미지 경로
					var originPath = obj.uploadPath+"\\"+obj.uuid+"_"+obj.fileName;
					
					originPath = originPath.replace(new RegExp(/\\/g),"/");
					
					//만약 다운로드 작업하려면 s_ 는 제외한 fileCallPath 수정한다.
					//str += "<li>"+obj.uploadPath+"\\" + obj.uuid + "\\" + obj.fileName+"</li>";
					
					str+="<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"'";
					str+="data-filename='"+obj.fileName+"' data-type='"+obj.fileType+"'>";
					str+="<a href=\"javascript:showImage(\'"+originPath+"\')\">";
					str+="<img src='/display?fileName="+fileCallPath+"'><div>"+obj.fileName+"</a>";
					str+="<button type='button' class='btn btn-danger btn-circle' data-file='";
					str+=fileCallPath+"' data-type='image'>";
					str+="<i class='fa fa-times'></i>";
					str+="</button>";
					str+="</div></li>";
			}else{
				//일반 파일 경로 2021\01\21\fdfd_text.txt
					var fileCallPath = encodeURIComponent(obj.uploadPath+"\\"+obj.uuid+"_"+obj.fileName);
					str+="<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"'";
					str+="data-filename='"+obj.fileName+"' data-type='"+obj.fileType+"'>";
					str+="<a href='/download?fileName="+fileCallPath+"'>";
					str+="<img src='/resources/img/attach.png'><div>"+obj.fileName+"</a>";
					str+="<button type='button' class='btn btn-danger btn-circle' data-file='";
					str+=fileCallPath+"' data-type='file'>";
					str+="<i class='fa fa-times'></i>";
					str+="</button>";
					str+="</div></li>"; //첨부파일 (img제외한파일) 그림표시 작업
			}
		});
		uploadResult.append(str);
	} //showUploadFile close
	
		//이미지 파일 첨부한후 밑에 큰 이미지 다시 누르면 사라지게 하기
	$(".bigPictureWrapper").click(function(){
		$(".bigPicture").animate({width:'0%', height:'0%'},1000);
		setTimeout(function(){
			$(".bigPictureWrapper").hide();
		},1000);
	})

		//x버튼 클릭 - 이벤트 위임
	 $(".uploadResult ul").on("click","button",function(){
		 
		 //해당 파일 경로 가져오기 this==span 속성.data
		 var targetFile=$(this).data("file");
		 //파일 타입 가져오기
		 var type=$(this).data("type");
		 //span 태그가 속한 부모 li태그 가져오기
		 var targetLi=$(this).closest("li");
		 
		 //서버폴더에서 제거
		 $.ajax({
			url:'/deleteFile',
			type:'post',
			data:{
				fileName:targetFile,
				type:type
			},
			success:function(result){
				console.log(result);
				 //화면 목록에서 제거
				 targetLi.remove();
			}
		 })
	 })//x버튼 종료
})
function showImage(fileCallPath){
	$(".bigPictureWrapper").css("display","flex").show();
	$(".bigPicture").html("<img src='/display?fileName="+fileCallPath+"'>")
					.animate({width:'100%', height:'100%'},1000); //1초 동안 서서히 띄우기
}
