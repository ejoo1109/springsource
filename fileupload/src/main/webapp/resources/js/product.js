/**
 * jsp에서 컨트롤러로 데이터 보내기
	form 안에 일반 요소만 있을 경우
 */

$(function(){
	$("button").click(function(e){
		e.preventDefault(); //submit 막기
		
		//일반 방식으로 전송할때
		$.ajax({
			url:'/product',
			type:'post',
			data: $("form").serialize(),
			success:function(result){
				//alert(result); //컨트롤러에서 success 메세지받아서 페이지에서 노출
				console.log(result);
			},
			error:function(xhr,txtSatus,error){
				console.log(txtSatus.responseText);
			}
		})
		
		//json 으로 전송하기
/*		let form ={
			code:$("#code").val(),
			category:$("#category").val(),
			pname:$("#pname").val(),
			amount:$("#amount").val(),
			price:$("#price").val(),
			etc:$("#etc").val()		
		};
		
		$.ajax({
			url:'/product',
			type:'post',
			contentType:'application/json;charset=utf-8',
			data: JSON.stringify(form),
			success:function(result){
				//alert(result); //컨트롤러에서 success 메세지받아서 페이지에서 노출
				console.log(result);
			},
			error:function(xhr,txtSatus,error){
				console.log(txtSatus.responseText);
			}
		})*/
	})
})
