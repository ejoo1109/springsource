/**
 * read.jsp에서 사용하는 스크립트
 */
 $(function(){
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
	
	
 })