/**
 * list.jsp에서 사용하는 스크립트 
 */

$(function(){
	//BoardController 에서 넘긴값 가져오기
	var result = '${result}';
	
	checkModal(result);
	
	//모달 창 띄우기
	function checkModal(result){
		if(result === ''){
			return;
		}
		if(parseInt(result)>0){
			$(".modal-body").html("게시글 "+result+" 번이 등록 되었습니다.");
		}
		$("#myModal").modal("show");		
	}
	
})