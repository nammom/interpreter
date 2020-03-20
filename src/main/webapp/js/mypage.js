window.onload = function(){
	//수정 생략을 위한 checkbox 체크
	$('#emailConfirm').prop("checked",true);
	$('#usercodeConfirm').prop("checked",true);
	
	//비밀번호 변경페이지 submit활성화 설정
	$("#updatePwdBtn").attr('disabled','disabled');
}

//회원정보 수정
	function updateInfo(){
		console.log("업데이트 중복확인 체크확인");
		if ($("input:checkbox[id=usercodeConfirm]").is(
				":checked")) {
			console.log("중복체크 OK");
			if ($("#sendCheck").val() == ranNum) {						
				var phone = $("#phone").val();
				console.log("휴대폰번호->"+phone);
				$("input:hidden[name=phone]").val(phone);
				console.log("true리턴");
				return true;
			} else {
				alert("인증번호를 확인해주세요");
				console.log("false리턴");
				return false;
			}
	
		} else if ($("input:checkbox[id=usercodeConfirm]")
				.is(":checked") == false) {
			alert("닉네임 중복확인을 확인 해주세요.")
			console.log("false리턴");
			return false;
		}
	}


$( document ).ready( function() {
	//회원 비밀번호 일치 검증
	$("#pwdCheckBtn").click(function(){
		var password = $("#password").val();
		console.log("비밀번호:"+password);
		$.ajax({
			url : "contextPath/mypage/checkPwd",
			type : "POST",
			data : password,
			contentType : "application/String",
			dataType : "text",
			success : function(data){
				console.log("data :" + data);
				if(data == 0){
					location.href="contextPath/mypage/updateInfo";
				}else if(data == 1){
					alert("가입된 회원정보가 없습니다.")
				}else{
					alert("입력하신 정보를 다시 확인해주세요.")
				}
			}
		});
	})
	

	//비밀번호 수정
	$("#updatePwdBtn").click(function(){
		var oldPwd = $("#oldpassword").val();
		var newPwd = $("#password1").val();
		var updateData =
					{
						"oldPassword" : oldPwd,
						"newPassword" : newPwd
					}
		console.log("updateData:"+updateData);
		$.ajax({
			url : "contextPath/mypage/updatePwd",
			type : "POST",
			data : JSON.stringify(updateData),
			contentType : "application/json",
			dataType : "text",
			success : function(data){
				console.log("check :"+data)
				if(data ==0){
					location.href="contextPath/mypage/updatePwdComplite";
				}else{
					alert("현재 비밀번호를 다시 확인해주세요.")
				}
			}
				
		})
	})
	
})