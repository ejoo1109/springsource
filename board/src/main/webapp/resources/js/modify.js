/**
 *  modify.jsp 의 스크립트
 */

 $(function(){
	var form = $("#myform");
	
	$("button").click(function(e){
		//버튼을 누르면 submit기능 막기
		e.preventDefault(); 
		
		var oper= $(this).data("oper");
		console.log(oper);
		
		if(oper == 'remove'){
			 form.attr("action","remove");
		
		}else if(oper == 'modify'){
			//원래의 수정 폼 보내기
			form = $("form[role='form']");
			
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

		form.append(str);
			
			
		}else if(oper == 'list'){
			 form.attr("action","list")
			 //메소드 속성을 get으로 바꿔서 submit요청
			 form.attr("method","get"); 
			 //폼 안의 bno 제거하기
			 form.find("input[name='bno']").remove();
		}
		form.submit();
	})

 
 // 첨부파일 가져오기
$(document).ready(function(){ //문서가 준비되면 j쿼리 사용

	var uploadResult = $(".uploadResult ul");
				
	$.getJSON({
		url:'getAttachList',
		data: {
			bno:bnoVal
		},
		success:function(data){
			console.log("######### data 확인 : "+data);	

			var str="";
								
			$(data).each(function(idx,obj){
				if(obj.fileType){
					
					//썸네일 이미지 경로 - 한글과 기호때문에 인코딩해서 보내줌
					var fileCallPath = encodeURIComponent(obj.uploadPath+"\\s_"+obj.uuid+"_"+obj.fileName);
										
					str+= "<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"'";
					str+= "data-filename='"+obj.fileName+"' data-type='"+obj.fileType+"'>";
					str+= "<img src='/display?fileName="+fileCallPath+"'><div>"+obj.fileName;
					str+= "<button type='button' class='btn btn-danger btn-circle' data-file='";
					str+= fileCallPath+"' data-type='image'>";
					str+= "<i class='fa fa-times'></i>";
					str+= "</button>";
					str+= "</div></li>";
				}else{
		
					str+= "<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"'";
					str+= "data-filename='"+obj.fileName+"' data-type='"+obj.fileType+"'>";
					str+= "<img src='/resources/img/attach.png'><div>"+obj.fileName+"</a>";	
					str+= "<button type='button' class='btn btn-danger btn-circle' data-file='";
					str+= fileCallPath+"' data-type='file'>";
					str+= "<i class='fa fa-times'></i>";
					str+= "</button>";	
					str+= "</div></li>"; //첨부파일 (img제외한파일) 그림표시 작업
				}
			})
			uploadResult.html(str);
		}
	}) //getJSON 종료
	
	//이미지 클릭시 원본 이미지 보여주기, 일반 파일은 다운로드
	$(".uploadResult").on("click","li",function(){
		var liobj=$(this); //li
		
		var path=encodeURIComponent(liobj.data("path")+"\\"+liobj.data("uuid")+"_"+liobj.data("filename"));
		
		if(liobj.data("type")){
			showImage(path.replace(new RegExp(/\\/g),"/"));
		}else{
			location.href="/download?fileName="+path;
		}		
	})// 이미지 닫기 종료
	
		//x버튼 클릭 - 이벤트 위임
	 $(".uploadResult").on("click","button",function(e){
		 
		//이벤트 전파 막기
		e.stopPropagation();

		if(confirm("정말로 파일을 삭제하시겠습니까?")){
		//span 태그가 속한 부모 li태그 가져오기
		 var targetLi=$(this).closest("li");
		 //화면 목록에서 제거
		 targetLi.remove();
		}
	 })//x버튼 종료



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
 })
 })
	//이미지 파일 첨부한후 밑에 큰 이미지 다시 누르면 사라지게 하기
	$(".bigPictureWrapper").click(function(){
		$(".bigPicture").animate({width:'0%', height:'0%'},1000);
		setTimeout(function(){
			$(".bigPictureWrapper").hide();
		},1000);
	})