/**
 * joinForm.jsp유효성 검증
아이디-5~12자리, 영어대소문자, 숫자, 특수문자허용
비밀번호-8~15자리, 어대소문자, 숫자, 특수문자허용
비밀번호 확인 - 이전비밀번호와 동일
이름-2~4자리 허용
성별-무조건 하나 선택
이메일-이메일 검증
 */

$(function(){
    $("#regist").validate({
        //규칙 정의-이름
    rules :{
        userid :{ 
			required:true,
            validId:true,//임의로 정한 규칙
			remote:{ /*화면 이동없이 확인할때*/
				url:"/member/checkId",
				type:"post",
				data:{
					userid:function(){
						return $("#userid").val();
					}
				}
			}
        },
        password:{
			required:true,
            validPwd : true,
        },
        confirm_password:{
			required:true,
            validPwd : true,
            equalTo : "#password" 
        },
        name :{
			required:true,
		},
        gender :{
            required : true
        },
        email :{
            required : true,
            email:true 
        }
},
//규칙에 대한 메세지 정의
	messages:{ 
		userid :{ 
			required:"아이디는 필수 입력 입니다.",
			remote:"아이디 사용 불가"
	},
		password :{ 
			required:"비밀번호는 필수 입력 입니다."
	},
		confirm_password :{ 
			required:"비밀번호는 필수 입력 입니다.",
			equalTo:"이전 비밀번호와 다릅니다."
	},
		name :{ 
			required:"이름은 필수 입력 입니다.",
			rangelength:"이름은 2~4자리로 입력해주세요"
	},
		gender :{ 
			required:"성별을 선택해 주세요."
	},
		email :{ 
			required:"이메일은 필수 입력 입니다."
	}

  },//에러 메시지가 나올 위치 잡기: small 아이디 뒤로
	errorPlacement:function(error,element){
		$(element).closest("form").find("small[id='"+element.attr('id')+"']").append(error);
	},

	success:function(label){
	var name = label.attr('for');
	label.text(name+'is ok!');
	}

});

});
//사용자 검증 메소드 추가
$.validator.addMethod("validId",function(value){
    let idcheck =/(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^*])[A-Za-z\d!@#$%^*]{5,12}$/;
    return idcheck.test(value);  //true or false return
},"아이디를 영대소문자, 숫자, 특수문자의 조합으로 5~12자리로 만들어주세요");
$.validator.addMethod("validPwd",function(value){
    let pwcheck = /(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^*])[A-Za-z\d!@#$%^*]{8,15}$/;
    return pwcheck.test(value);
},"비밀번호를 영대소문자, 숫자, 특수문자의 조합으로 8~15자리로 만들어주세요");
$.validator.addMethod("email",function(value){
    let emailcheck = /(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))/
    ;return emailcheck.test(value);
},"이메일을 확인해 주세요");