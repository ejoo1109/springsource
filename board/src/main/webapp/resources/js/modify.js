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
	$(uploadResult).on("click","li",function(){
		var liobj=$(this); //li
		
		var path=encodeURIComponent(liobj.data("path")+"\\"+liobj.data("uuid")+"_"+liobj.data("filename"));
		
		if(liobj.data("type")){
			showImage(path.replace(new RegExp(/\\/g),"/"));
		}else{
			location.href="/download?fileName="+path;
		}		
	})// 이미지 닫기 종료
	
		//x버튼 클릭 - 이벤트 위임
	 $(".uploadResult ul").on("click","button",function(e){
		 
		if(confirm("정말로 파일을 삭제하시겠습니까?")){
		//span 태그가 속한 부모 li태그 가져오기
		 var targetLi=$(this).closest("li");
		 //화면 목록에서 제거
		 targetLi.remove();
		}
		
	//이벤트 전파 막기
		e.stopPropagation();
	 })//x버튼 종료
 })
 })
	//이미지 파일 첨부한후 밑에 큰 이미지 다시 누르면 사라지게 하기
	$(".bigPictureWrapper").click(function(){
		$(".bigPicture").animate({width:'0%', height:'0%'},1000);
		setTimeout(function(){
			$(".bigPictureWrapper").hide();
		},1000);
	})