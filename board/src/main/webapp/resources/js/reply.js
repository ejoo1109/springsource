/**
 * 댓글 스크립트
 */

var replyService = (function(){
	
	function add(reply, callback){
		console.log("add method 호출");
		
		$.ajax({
			type:'post',
			url:'/replies/new',
			contentType:'application/json;charset=utf-8',
			beforeSend:function(xhr){
				xhr.setRequestHeader(csrfHeaderName,csrfTokenValue);
			},
			data:JSON.stringify(reply),
			success:function(result){
				if(callback){
					callback(result);
				}
			}
		})
	}// add end
	
	
	//private 개념이라 외부에서 직접적으로 메소드 호출 불가 사용하고 싶으면 return 으로 사용
	function getList(param,callback){ 
		console.log("getList 호출");
		
		var bno = param.bno;
		var page = param.page||1;
		
		$.getJSON({
			url:'/replies/pages/'+bno+'/'+page,
			success:function(data){
				if(data!=null){
					if(callback){
						//console.log(data.replyCnt,data.list);
						callback(data.replyCnt,data.list);
					}
				}
			}
			
		})
	}//getList end
	
	//댓글 삭제
	function remove(rno, replyer, callback){
		console.log("remove 호출");
		
		$.ajax({
			url:'/replies/'+rno,
			type:'delete',
			contentType:"application/json;charset=utf-8",
			beforeSend:function(xhr){
				xhr.setRequestHeader(csrfHeaderName,csrfTokenValue);
			},
			data:JSON.stringify({
				replyer:replyer
			}),
			success:function(result){
				if(callback){
					callback(result);
				}
			}
		})
	}// delete end
	
	//댓글 수정
	function update(reply, callback){
		console.log("update 호출");
		$.ajax({
			url:'/replies/'+reply.rno,
			type:'put',
			contentType:'application/json;charset=utf-8',
			beforeSend:function(xhr){
				xhr.setRequestHeader(csrfHeaderName,csrfTokenValue);
			},
			data:JSON.stringify(reply),
			success:function(result){
				if(callback){
					callback(result);
				}
			}
		})
	} //update end
	
	//댓글 하나 가져오기
	function get(rno, callback){
		console.log("댓글 하나 요청");
		
		$.getJSON({
			url:'/replies/'+rno,
			success:function(data){
				if(callback){
					callback(data);
				}
			}
		})
	}// get end
	
	//댓글 시간 형식
	function displayTime(timeVal){
		console.log(timeVal);
		
		var today=new Date();
		
		var gap = today.getTime()-timeVal;
		var dateObj=new Date(timeVal);
		
		if(gap < (1000*60*60*24)){ //댓글 작성한 날짜가 당일이라면 시/분/초 형태
			var hh=dateObj.getHours();
			var mi=dateObj.getMinutes();
			var ss=dateObj.getSeconds();
			
			//9보다 클 경우 그대로, 작을경우 앞에 '0'르 붙여서 출력
			return [(hh>9?'':'0')+hh, ':', (mi>9?'':'0')+mi,':',(ss>9?'':'0')+ss].join('');
		}else{//댓글 작성한 날짜가 당일이 아니라면 년/월/일
			var yy=dateObj.getFullYear();
			var mm=dateObj.getMonth()+1;
			var dd=dateObj.getDate();
			
			return [yy,'/',(mm>9?'':'0')+mm,'/',(dd>9?'':'0')+dd].join('');
			
		}
	}
	
	return {
		add: add,
		getList:getList,
		remove:remove,
		update:update,
		get : get,
		displayTime:displayTime
	}
})();//즉시 호출을 뜻하는 함수의 형태