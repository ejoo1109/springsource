/**
 * list.jsp에서 사용하는 스크립트 
 */

$(function(){
	
	checkModal(result);
	
	//히스토리 재지정-리플레이스
	history.replaceState({},null,null);
	
	//모달 창 띄우기
	function checkModal(result){
		if(result === ''|| history.state){
			return;
		} 
		if(parseInt(result)>0){
			$(".modal-body").html("게시글 "+result+" 번이 등록 되었습니다.");
		}
		$("#myModal").modal("show");		
	}
	
	//actionForm 가져오기 - 페이지 이동시 사용할 폼
	 var actionForm = $("#actionForm");

	//페이지 번호 클릭시 동작
	//하단에 previous , 숫자, next의 버튼의 a 태그는 모두 해당되기 때문에 클래스로 지정
	$(".paginate_button a").click(function(e){
		//a태그의 이동기능이 걸려있기때문에 기능 중지
		e.preventDefault();
		//페이지 이동시 pageNum의 값을 사용자가 선택한 값으로 변경
		actionForm.find("input[name='pageNum']").val($(this).attr("href"));
		actionForm.submit();
	})
	
	//amount값(페이지에 보여지는 게시물 갯수) 변경시 동작
	$(".form-control").change(function(){
		//amount값을 사용자가 선택한 값으로 변경
		actionForm.find("input[name='amount']").val($(this).val());
		actionForm.submit();
		
	})
	
	//list게시판의 제목 클릭시 동작 (현재글번호, pageNum, acmount, 검색정보)
	$(".move").click(function(e){
		e.preventDefault();
		actionForm.append("<input type='hidden' name='bno' value='"+$(this).attr("href")+"'>");
		actionForm.attr("action","read"); 
		// /board/read
		actionForm.submit();
		
		
	})
	
	
	
})