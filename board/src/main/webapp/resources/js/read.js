/**
 * read.jsp에서 사용하는 스크립트
 */
 $(function(){ //j쿼리 시작
	//댓글 리스트 함수 호출
	showList(1); //1페이지로 시작
	
	var form = $("#myform");

	
	$(".btn-default").click(function(){
		form.submit();
	})
	
	//read에서 리스트로 갈때에 form에서 bno는 제외하고 경로 list로 바꾸기
	$(".btn-info").click(function(){
		form.find("input[name='bno']").remove(); 
		form.attr('action','list');
		form.submit();
	})
	
	//댓글 작업 호출하기
	//댓글 삽입 - bno, reply(댓글내용), replyer(댓글 작성자)
	$("#addReplyBtn").click(function(){
		//모달창을 띄우기 전 기본 모달창에서 댓글 입력을 위한 화면 변경
		modal.find("input").val("");
		
		//현재 로그인한 사용자 보여주기
		modalInputReplyer.val(replyer);
		
		//readonly 속성 없애기
		modalInputReplyer.prop("readonly","readonly");
		
		//작성일 요소 숨기기- replyDate를 감싸고 있는 div
		modalInputReplyDate.closest("div").hide();
		//등록, 닫기 버튼만 보여주기
		//1. 닫기 버튼을 제외한 모든 버튼 숨기기
		modal.find("button[id!='modalCloseBtn']").hide();
		//2.등록 버튼 보여주기
		modalRegisterBtn.show();
		
		//모달창 보여주기 모달이란? 뒷배경은 그대로 있고 팝업형태로 나오는 창
		modal.modal("show");
	}); 
	
	modalRegisterBtn.click(function(){
		
		var reply = {
			bno:bnoVal,
			reply:modalInputReply.val(),
			replyer:modalInputReplyer.val()
			};
			
		replyService.add(reply,function(result){
		if(result){
			//alert("result : "+ result);
			//성공 메세지가 돌아오면
			
			//1.현재 모달창에 있는 내용 모두 지우기
			modal.find("input").val("");
			//2. 모달창 닫기
			modal.modal("hide");
			//3.리스트 보여주기-댓글 작성시 마지막 페이지 보여주기
			showList(-1);
		}
	});
	})//add end
	
	
	
	//댓글 리스트 가져오기 -1602/1(글번호/페이지번호)
	function showList(page){
	replyService.getList({bno:bnoVal,page:page},function(total,data){
		console.log(data);
		
		//새 댓글 작성시 http://localhost:8080/replies/pages/-1
		if(page ==-1){ //마지막 페이지를 알아내기 위한 작업
			pageNum=Math.ceil(total/10.0);
			showList(pageNum);
			return;
		}

		//보여줄 댓글이 없다면 
		if(data==null||data.length==0){
			replyUl.html("");
			return;		
		}

		//댓글이 존재한다면
		let str="";
		for(var i=0,len=data.length||0;i<len;i++){
			str+='<li class="left clearfix" data-rno="'+data[i].rno+'">';
			str+='<div><div class="header">';
			str+='<strong class="primary-font">'+data[i].replyer+'</strong>';
			str+='<small class="pull-right text muted">'+replyService.displayTime(data[i].replyDate)+'</small>';
			str+='<p>'+data[i].reply+'</p>';
			str+='</div></div></li>';
		}
		replyUl.html(str);
		showReplyPage(total);
		
	});//getList end
}

	//댓글 페이지 영역 ->1.2.3.4
	var pageNum=1;
	function showReplyPage(total){
		console.log("total : "+total);
		
		//마지막 페이지 계산
		var endPage = Math.ceil(pageNum/10.0)*10;
		//시작 페이지 계산
		var startPage = endPage-9;
		//이전버튼
		var prev = startPage!=1;
		//다음버튼
		var next = false;
		
		if(endPage*10 >= total){
			endPage=Math.ceil(total/10.0);
		}
		if(endPage*10 < total){
			next=true;
		}
		
		var str="<ul class='pagination pull-right'>";
		if(prev){
			str+="<li class='page-item'><a class='page-link' href='"+(startPage-1)+"'>";
			str+="Previous</a></li>";
		}
		for(var i=startPage;i<=endPage;i++){
			var active=pageNum==i?"active":"";
			str+="<li class='page-item "+active+"'>";
			str+="<a class='page-link' href='"+i+"'>"+i+"</a></li>";
		}
		if(next){
			str+="<li class='page-item'><a class='page-link' href='"+(endPage-1)+"'>";
			str+="next</a></li>";
		}
		str+="</ul>";
		replyPageFooter.html(str);
	};
		
		//댓글 페이지 나누기 번호 클릭시 동작
		replyPageFooter.on("click","li a",function(e){
			e.preventDefault();
			
			pageNum = $(this).attr("href");
			showList(pageNum);
		})
	
	//댓글 삭제
	$(modalRemoveBtn).click(function(){
		
		//로그인 여부 확인
		if(!replyer){
			alert("로그인이 필요합니다.");
			modal.modal("hide");
			return;
		}
		
		//로그인이 되었다면 로그인한 사용자와 모달창에 있는 사용자가
		//같은지 확인하기
		var originalReplyer = modalInputReplyer.val();
		
		if(originalReplyer != replyer){
			alert("자신의 댓글만 삭제가 가능합니다.");
			modal.modal("hide");
			return;
		}
		
		//댓글 작성자도 컨트롤러로 보내기
		replyService.remove(modal.data("rno"),originalReplyer,function(result){
			if(result){
			//alert("result : "+ result);
			modal.modal("hide");
			showList(pageNum);
		}
	}); //delete end
		
	})

	//댓글수정
	$(modalModBtn).click(function(){
		
		var reply = {
			rno:modal.data("rno"),
			reply:modalInputReply.val(),
			replyer:modalInputReplyer.val()
		};
			
		//로그인 여부 확인
		if(!replyer){
			alert("로그인이 필요합니다.");
			modal.modal("hide");
			return;
		}
		
		//로그인이 되었다면 로그인한 사용자와 모달창에 있는 사용자가
		//같은지 확인하기
		var originalReplyer = modalInputReplyer.val();
		
		if(originalReplyer != replyer){
			alert("자신의 댓글만 수정이 가능합니다.");
			modal.modal("hide");
			return;
		}
		
		replyService.update(reply,function(result){
		if(result){
			//alert("result : "+ result);
			modal.modal("hide");
			showList(pageNum);
		}
	})//update end
		
	})

	
	
	//댓글 하나 가져오기
	//이벤트 위임 : li태그는 나중에 생기는 영역이기 떄문에 현재 있는 요소에 이벤트를 걸고 
	//나중에 li 태그에 넘겨주는 방식
	$(replyUl).on("click","li",function(){
		
		//현재 클릭된 li요소의 rno(댓글의 pk) 가져오기
		var rno = $(this).data("rno");
		console.log("rno - "+rno);
		
		replyService.get(rno,function(data){
		if(data!=null){
			console.log(data);
			
			//요청한 댓글 화면에 보여주기
			modalInputReply.val(data.reply);
			modalInputReplyer.val(data.replyer).attr("readonly","readonly");
			modalInputReplyDate.val(replyService.displayTime(data.replyDate)).attr("readonly","readonly");
			modal.data("rno",data.rno);
			
			//숨겨놨던 작성날짜 영역에 보여주기
			modalInputReplyDate.closest("div").show();
			modal.find("button").show();
			modal.find("button[id='modalRegisterBtn']").hide();
			modal.modal("show");
			
		}
	});	
	})
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
			console.log(data);	
			
			var str="";
								
			$(data).each(function(idx,obj){
				if(obj.fileType){
					
					//썸네일 이미지 경로 - 한글과 기호때문에 인코딩해서 보내줌
					var fileCallPath = encodeURIComponent(obj.uploadPath+"\\s_"+obj.uuid+"_"+obj.fileName);
										
					str+="<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"'";
					str+="data-filename='"+obj.fileName+"' data-type='"+obj.fileType+"'>";
					str+="<img src='/display?fileName="+fileCallPath+"'><div>"+obj.fileName;
					str+="</div></li>";
				}else{
		
					str+="<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"'";
					str+="data-filename='"+obj.fileName+"' data-type='"+obj.fileType+"'>";
					str+="<img src='/resources/img/attach.png'><div>"+obj.fileName+"</a>";		
					str+="</div></li>"; //첨부파일 (img제외한파일) 그림표시 작업
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
	})

	//이미지 파일 첨부한후 밑에 큰 이미지 다시 누르면 사라지게 하기
	$(".bigPictureWrapper").click(function(){
		$(".bigPicture").animate({width:'0%', height:'0%'},1000);
		setTimeout(function(){
			$(".bigPictureWrapper").hide();
		},1000);
	})
})
function showImage(fileCallPath){
	$(".bigPictureWrapper").css("display","flex").show();
	$(".bigPicture").html("<img src='/display?fileName="+fileCallPath+"'>")
					.animate({width:'100%', height:'100%'},1000); //1초 동안 서서히 띄우기
}