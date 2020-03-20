
var bool=false;
var ranNum ="";

	//약관동의 submit활성화하는 함수 
	function confirmCheck(){
		bool = bool;
		
		console.log("submit함수 실행"+bool)
		if(bool){
			return true;
		}else{
			alert("필수 약관에 동의 해주세요.");
			return false;
		}
	}
	
	//회원가입 submit활성화 하는 함수
	function confirmCheck2(){
		console.log("회원가입 체크확인");
		if($("input:checkbox[id=emailConfirm]").is(":checked") && $("input:checkbox[id=usercodeConfirm]").is(":checked")){
			console.log("중복체크 OK");
			if(CheckRan()){
				console.log("인증번호 확인 OK");
				return true;
			}else{
				alert("인증번호를 확인해주세요");
				return false;
			}
			
		}else if($("input:checkbox[id=usercodeConfirm]").is(":checked")==false){
			alert("닉네임 중복확인을 확인 해주세요.")
			return false;
		}else if($("input:checkbox[id=emailConfirm]").is(":checked")==false){
			alert("이메일 중복확인을 확인 해주세요.")
			return false;
		}
	}

	
	$( document ).ready( function() {
	
	//생년월일 value만들기
	$(".birthdate").on("propertychange chage keyup paste input",function(){
		console.log("생년월일 함수실행");
		var birthdate = $("#year").val() + $("#month").val() + $("#day").val() ;
		console.log("생년월일->"+birthdate);		
		$("#birthdate").val(birthdate);
		if(birthdate.length === 8){
			$("#year-wrong").hide();
		}else{
			$(".birthdate").attr("required",true);
			$("#year-wrong").show();
		}
	})

	
	
	/*모두동의 선택시 모든 체크박스 선택*/
	//만약 전체 선택 체크박스가 체크된상태일경우
	$("#checkAll").click(function(){		
		if($("#checkAll").prop("checked")) {
		$("input[type=checkbox]").prop("checked",true)
			} 
		// 전체선택 체크박스가 해제된 경우 
		else { 
			//해당화면에 모든 checkbox들의 체크를해제시킨다. 
			$("input[type=checkbox]").prop("checked",false); 
		} 
	}); 

	
	//[필수]체크박스 선택시 submit활성화 여부 (check box가 선택될때마다 확인)
	$('.check').click(function(){
		bool=submitOnf();
		
		if(bool){
			bool = true;
//			$(":input[type='submit']").removeAttr('disabled');
		}else{
			bool = false;
//			$(":input[type='submit']").attr('disabled','disabled');
		}
	})
	

	function submitOnf(){
		if($('#check1').prop("checked") && $('#check2').prop("checked")){
			console.log("true 리턴");
			return true;

		}else{
			console.log("false 리턴")
			return false;
		}
	}
	
	//아이디 중복확인	
	$('#emailCheck').click(function(){
		var email = $('#email').val();
		$.ajax({
			url : "contextPath/join/emailCheck",
			type : "POST",
			data : email,
			contentType : "application/String",
			dataType : "text",
			success : function(data){
				console.log("온 data"+data)
				if(data == 0){
					alert("사용 불가능한 email입니다.")
				}else{
					alert("사용 가능한 email입니다.")
					$('#emailConfirm').prop("checked",true);
				}
			}
		});		
	})
	
	//닉네임 중복확인
	$('#usercodeCheck').click(function(){
		var userCode = $('#userCode').val();
		console.log("닉네임 중복확인 :"+userCode);
		$.ajax({
			url : "contextPath/join/usercodeCheck",
			type : "POST",
			data : userCode,
			contentType : "application/String",
			dataType : "text",
			success : function(data){
				console.log("온 data"+data)
				if(data == 0){
					alert("사용 불가능한 닉네임 입니다.")
				}else{
					alert("사용 가능한 닉네임 입니다.")
					$('#usercodeConfirm').prop("checked",true);
				}
			}
		});		
	})
	
	//실시간 중복확인 변경 확인
	//이메일 변경될때
	$('#email').on("propertychange chage keyup paste input",function(){
		$('#emailConfirm').prop("checked",false);
	})
	//닉네임 변경될때
	$('#userCode').on("propertychange chage keyup paste input",function(){
		$('#usercodeConfirm').prop("checked",false);
	})
	
	
	
	//정규식
	//생년월일 숫자입력 검증
	$(function(){
		$("#year-wrong").hide();
		$("#year").on("propertychange chage keyup paste input",function(){		
			var reg = /^[0-9]{1,9999}$/g;
			if(reg.test($("#year").val())){
				console.log("숫자만입력됨")
					$("#year-wrong").hide();
				if($("#year").val().length >=5){
					console.log("4자리만입력")
					$("#year-wrong").show();
				}
			}else{
				console.log("다시입력")
				$("#year-wrong").show();
			}
		})
	})
	
	//휴대폰번호 숫자입력검증
	function phonReg(){
		console.log("휴대폰번호 검증");		
			var reg =  /^[0-9]{1,9999}$/g;
			if(reg.test($("#phone").val())){
				if($("#phone").val().length <=11){
					return true;
				}else{
					alert("휴대폰 번호를 확인해주세요.")
					return false;
				}
			}else{
				alert("휴대폰 번호를 확인해주세요.")
				return false;
		}
	}
	
	
	//비밀번호 확인 
	$(function(){
		console.log("비밀번호확인");
		$("#alert-wrong").hide();
		$(".password").keyup(function(){
			var pwd1 = $("#password1").val();
			var pwd2 = $("#password2").val();
			if(pwd1 != "" && pwd2 != ""){
				if(pwd1 != pwd2){
					$("#alert-wrong").show();
				}else{
					$("#alert-wrong").hide();
					$("#updatePwdBtn").removeAttr('disabled');
				}
			}
		})
	})
	

	//인증번호 발송
	$("#send").click(function(){
		if(phonReg()){
			console.log("인증번호 발송")
			var phone = $("#phone").val();
			console.log("phone:"+phone);
			$.ajax({
				url : "contextPath/join/send",
				type : "POST",
				data : phone,
				contentType : "application/String",
				dataType : "text",
				success : function(data){
					alert(data);
				}
			})
		}		
	})
	
	//발송된 인증번호 검사
	function CheckRan(){
		//사용자가 입력한 인증번호
		var sendCheck = $("#sendCheck").val();
		$.ajax({
			url : "contextPath/join/CheckRan",
			type : "POST",
			data : sendCheck,
			contentType : "application/String",
			dataType : "text",
			success : function(data){
				if(data == 0){
					return true;
				}else{
					return false;
				}
			}
		})
	}
})

