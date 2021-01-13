<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="../includes/header.jsp" %>
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Board List</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Board List Page
                            <button id='regBtn' type='button' class='btn btn-xs pull-right btn-success' onclick="location.href='register'">Register New Board</button>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>번 호</th>
                                        <th>제 목</th>
                                        <th>작성자</th>
                                        <th>작성일</th>
                                        <th>수정일</th>
                                    </tr>									
                                </thead>
								<!-- 게시판 리스트 반복문 -->
								<tbody>
								<c:forEach var="vo" items="${list}">
									<tr>
                                        <td>${vo.bno}</td>
                                        <td><a href="${vo.bno}" class="move">${vo.title}</a></td>
                                        <td>${vo.writer}</td>
                                        <td><fmt:formatDate value="${vo.regdate}" pattern="yyyy-MM-dd HH:mm"/></td>
                                        <td><fmt:formatDate value="${vo.updatedate}" pattern="yyyy-MM-dd HH:mm"/></td>
                                    </tr>
								</c:forEach>
								</tbody>
                            </table>
							<div class="row"> <!-- start search -->
                            	<div class="col-md-12">
                            	  <div class="col-md-8"><!--search Form-->
                            		<form action="" id="searchForm">
                            			<select name="type" id="">
                            				<option value="">----</option>
                            				<option value="T">제목</option>
                            				<option value="C">내용</option>
                            				<option value="W">작성자</option>
                            				<option value="TC">제목 or 내용</option>
                            				<option value="TW">제목 or 작성자</option>
                            				<option value="TCW">제목 or 내용 or 작성자</option>  
                            			</select>
                            			<input type="text" name="keyword"/>
                            			<button type="button">검색</button>
                            		</form>
                            	   </div>
                            	   <div class="col-md-2 col-md-offset-2">
                            	   	<!--페이지 목록에서 보여질 갯수 지정하는 폼-->
                            	   	<select class="form-control" id="amount">
                            	   		<option value="10" <c:out value="${pageVO.cri.amount==10?'selected':''}"/>>10</option>
                            	   		<option value="20" <c:out value="${pageVO.cri.amount==20?'selected':''}"/>>20</option>
                            	   		<option value="30" <c:out value="${pageVO.cri.amount==30?'selected':''}"/>>30</option>
                            	   		<option value="40" <c:out value="${pageVO.cri.amount==40?'selected':''}"/>>40</option>
                            	   	</select>
								  </div>
                             	 </div>                             	 
                      		 </div><!-- end search -->
                            <!-- start Pagination -->
                            <div class="text-center">
                            	<ul class="pagination">
                            		<c:if test="${pageVO.prev}">
                            			<li class="paginate_button previous"><a href="${pageVO.startPage-1}">Previous</a>
                            		</c:if>
                            		<c:forEach var="idx" begin="${pageVO.startPage}" end="${pageVO.endPage}">
                            			<li class="paginate_button" ${pageVO.cri.pageNum==idx?'active':''}><a href="${idx}">${idx}</a>
                            		</c:forEach>
                            		<c:if test="${pageVO.next}">
                            			<li class="paginate_button next"><a href="${pageVO.endPage+1}">Next</a>
                            		</c:if>
                            	</ul>
                            </div>
                            <!-- end Pagination -->   
                            </div>
                            <!-- end panel-body -->
                        </div>
                        <!-- end panel -->
                    </div>                   
                </div>               
            <!-- /.row -->
<!-- 페이지 링크 값을 넘기기 위한 폼 :
주소줄에 가지고 다녀야하는 값이 여러개여서 폼을 하나짜서 움직임. 
value값 잘 넘어왔는지 확인하려면 F12 source에서 확인 -->
<form action="" id="actionForm">
	<input type="hidden" name="pageNum" value="${pageVO.cri.pageNum}" />
	<input type="hidden" name="amount" value="${pageVO.cri.amount}" />
</form>
<!-- 모달 추가 -->
<div class="modal" tabindex="-1" id="myModal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">게시글 등록</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <p>처리가 완료 되었습니다.</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">확인</button>
      </div>
    </div>
  </div>
</div>
<!-- 스크립트 -->
<script>
	//BoardController 에서 넘긴값 가져오기
	var result = '${result}';
</script>
<script src="/resources/js/list.js"></script>
<%@include file="../includes/footer.jsp" %>       