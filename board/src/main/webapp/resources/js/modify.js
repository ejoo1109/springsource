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
			
		}else if(oper == 'list'){
			 form.attr("action","list")
			 //메소드 속성을 get으로 바꿔서 submit요청
			 form.attr("method","get"); 
			 //폼 안의 bno 제거하기
			 form.find("input[name='bno']").remove();
		}
		form.submit();
	})
	

 })