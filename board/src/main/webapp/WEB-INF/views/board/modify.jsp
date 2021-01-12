<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="../includes/header.jsp" %>
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Board Modify</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>            
            <div class="row">
                <div class="col-lg-12">
                	<div class="panel panel-default">
                        <div class="panel-heading">
                           Board Modify Page
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                			<form action="" method="post" role="form">
                				<div class="form-group">
                					<label>Bno</label>
                					<input class="form-control" name="bno" readonly="readonly" value="${board.bno}">                				
                				</div> 
                				<div class="form-group">
                					<label>Title</label>
                					<input class="form-control" name="title" value="${board.title}">                				
                				</div>  
                				<div class="form-group">
                					<label>Content</label>
                					<textarea class="form-control" rows="3" name="content">${board.content}</textarea>               				
                				</div> 
                				<div class="form-group">
                					<label>Writer</label>
                					<input class="form-control" name="writer" readonly="readonly" value="${board.writer}">                				
                				</div>  
                				<button type="submit" data-oper='modify' class="btn btn-default">Modify</button>              			
                				<button type="submit" data-oper='remove' class="btn btn-danger">Remove</button>              			
                				<button type="submit" data-oper='list' class="btn btn-info">List</button>              			
                			</form>
                		</div>
                	</div>
                </div>
            </div>
<%-- remove와 list를 위한 폼--%>
<%-- 페이지 나누기를 위해 필요한 값 세팅 --%>     
<form action="" id="myform" method="post">
	<input type="hidden" name="bno" value="${board.bno}"/>
</form>
<%-- 스크립트 --%>
<script>
 $(function(){
	var form = $("#myform");
	
	$("button").click(function(e){
		e.preventDefault(); //버튼을 누르면 submit기능 막기
		
		var oper= $(this).data("oper");
		console.log(oper);
		
		if(oper == 'remove'){
			 form.attr("action","remove");
		}else if(oper == 'modify'){
			//원래의 수정 폼 보내기
			form = $("form[role='form']");
		}else if(oper == 'list'){
			 form.attr("action","list")
			 	 .attr("method","get"); //메소드 속성을 getd으로 바꿔서 submit요청
			 	 //폼 안의 bno 제거하기
			 form.find("input[name='bno']").remove();
		}
		form.submit();
	})
 })
</script>
<%@include file="../includes/footer.jsp" %>       