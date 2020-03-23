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
	
	//아이디 기억하기
	
	//(아이디 입력후 체크박스 체크)
	var cookie = getCookie("key");
	console.log("쿠키:"+cookie);
	$("#email").val(cookie);
	
	//저장되어있는 쿠키가 있다면 '아이디기억하기'체크박스 기본표시
	if(cookie.length != 0){
		$("input:checkbox[id=emailSave]").attr("checked",true);
	}else{
		$("input:checkbox[id=emailSave]").prop("checked",false);
	}
	
	$("#emailSave").click(function(){
		if($("input:checkbox[id=emailSave]").is(":checked")){
			setCookie("key",$("#email").val(),7); //7일동안 쿠키저장			
		//아이디 기억하기 해제시
		}else{
			deleteCookie("key");
		}
	})
	//(체크박스 먼저 체크후 아이디 입력)
	$("#email").keyup(function(){
		if($("input:checkbox[id=emailSave]").is(":checked")){
			setCookie("key",$("#email").val(),7); //7일동안 쿠키저장	
		}
	})
	
	//쿠키저장
	function setCookie(cookieName,value,exdays){
		console.log("쿠키저장");
		//만료일
		var exdate = new Date();
		exdate.setDate(exdate.getDate()+exdays);
		var cookieValue = escape(value) + ((exdays==null) ? "" : "; expires=" + 
			    exdate.toGMTString());
		document.cookie = cookieName + "=" + cookieValue;
		console.log(cookieValue);
	}
	
	//쿠키 삭제
	function deleteCookie(cookieName){
	    var expireDate = new Date();
	    expireDate.setDate(expireDate.getDate() - 1);
	    document.cookie = cookieName + "= " + "; expires=" + expireDate.toGMTString();
	}

	//쿠키 가져오기
	function getCookie(cookieName) {
	    cookieName = cookieName + '=';
	    var cookieData = document.cookie;
	    var start = cookieData.indexOf(cookieName);
	    var cookieValue = '';
	    if(start != -1){
	        start += cookieName.length;
	        var end = cookieData.indexOf(';', start);
	        if(end == -1)end = cookieData.length;
	        cookieValue = cookieData.substring(start, end);
	    }
	    return unescape(cookieValue);
	}
	
	//아이디 & 비밀번호 찾기 (인증번호 검사)
	$("#SearchEmailPwd").click(function(){
			console.log("검사 함수실행")
			var sendCheck = $("#sendCheck").val();
			console.log(sendCheck);
			$.ajax({
				url : contextPath+"/join/CheckRanPhone",
				type : "POST",
				data : sendCheck,
				contentType : "application/String",
				success : function(data){
					if(data == 0){
						location.href=contextPath+"/login/searchEmailComplite";
						return true;
					}else{
						alert("인증번호를 다시한번 확인해주세요.")
						return false;
					}
				}
			});
		});	
	
	//비밀번호 찾기 (인증번호 검사)
	$("#SearchPwd").click(function(){
		console.log("검사 함수실행")
		var sendCheck = $("#sendCheck").val();
		console.log(sendCheck);
		$.ajax({
			url : contextPath+"/join/CheckRanEmail",
			type : "POST",
			data : sendCheck,
			contentType : "application/String",
			success : function(data){
				console.log(data)
				if(data == 0){
					location.href=contextPath+"/login/searchPwdComplite";
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
			url : contextPath+"/login/sendEmail",
				type : "POST",
				data : email,
				contentType : "application/String",
				success : function(){
					alert("이메일 전송 완료.")
			}
		})
		
	})
})