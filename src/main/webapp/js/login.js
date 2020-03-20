$( document ).ready( function() {
	//로그인 회원 검증
	$("#loginBtn").click(function(){
		console.log("함수실행")
		var email = $("input[name=email]").val();
		var password = $("input[name=password]").val();
		console.log(email+"/"+password);
		
		var data = JSON.stringify(
				{
				"email" : email,
				"password" : password
			});
		console.log(data);
		$.ajax({
			url : contextPath +"/login/login",
			type : "POST",
			data : data,
			contentType : "application/json",
			dataType : "text",
			success : function(data){
				console.log("data :" + data);
				if(data == 0){
					location.href=contextPath+"/home";
				}else if(data == 1){
					alert("가입된 회원정보가 없습니다.")
				}else{
					alert("입력하신 정보를 다시 확인해주세요.")
				}
			}
		});
	});

	//아이디 & 비밀번호 찾기 (인증번호 검사)
	$("#SearchEmailPwd").click(function(){
			console.log("검사 함수실행")
			var sendCheck = $("#sendCheck").val();
			console.log(sendCheck);
			$.ajax({
				url : "contextPath/join/CheckRan",
				type : "POST",
				data : sendCheck,
				contentType : "application/String",
				success : function(data){
					if(data == 0){
						location.href="contextPath/login/searchEmailComplite";
						return true;
					}else{
						alert("인증번호를 다시한번 확인해주세요.")
						return false;
					}
				}
			});
		});	
	
	//이메일 전송
	$("#sendEmail").click(function(){
		var email = $("#email").val();
		console.log("이메일 전송 :"+email);
		
		$.ajax({
			url : "contextPath/login/sendEmail",
				type : "POST",
				data : email,
				contentType : "application/String",
				success : function(){
					alert("이메일 전송 완료.")
			}
		})
		
	})
})