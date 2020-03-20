<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<script>var contextPath = "${pageContext.request.contextPath}";</script>

</head>
<body>
	<form action="step3" method="POST" onsubmit="return confirmCheck2();">
		아이디(Email) 
		<input type="email" name="email" id="email" placeholder="이메일주소" required >
		<input type="button" id="emailCheck" value="중복확인">
		<input type="checkbox" id="emailConfirm" style="display:none">
		<br> 
		
		닉네임 
		<input type="text" name="usercode" id="userCode" placeholder="닉네임" required > 
		<input type="button" id="usercodeCheck" value="중복확인">
		<input type="checkbox" id="usercodeConfirm" style="display:none" >
		<br>
		
		비밀번호 <input type="password" name="password" id="password1" class="password" placeholder="비밀번호" required > <br>
		비밀번호 확인 <input type="password" id="password2" class="password" placeholder="비밀번호 확인 " required >
		<div style="display:inline" id="alert-wrong">비밀번호가 일치하지 않습니다.</div>
		<br>
		
		생년월일 <input type="text" id="year" class="birthdate" placeholder="년도" required >
		 <select id="month"  class="birthdate" required >
			<option value="">월</option>
			<%for(int i = 1; i<=9; i++){%>
			<option value="0<%=+i %>" id="month"><%=i %>월</option>
			<%} %>
			<%for(int i = 10; i<=12; i++){%>
			<option value="<%=+i %>" id="month"><%=i %>월</option>
			<%} %>
		</select> 
		<select id="day" class="birthdate" required >
			<option value="" >일</option>
			<%for(int i = 1; i<=9; i++){%>
			<option value="0<%=i%>" id="day"><%=i %> 일</option>
			<%} %>
			<%for(int i = 10; i<=31; i++){%>
			<option value="<%=i%>" id="day"><%=i %> 일</option>
			<%} %>
		</select>
		<input type="hidden" id="birthdate" name="birthdate"><br> 		
		<div style="display:inline" id="year-wrong" style="display:none" >생년월일을 올바르게 입력해주세요.</div>
		<br> 
		
		휴대폰 번호 <input type="text" name="phone" class="join" id="phone" placeholder="-없이 숫자로만 입력">
		<input type="button" id="send" value="인증번호 발송"> <br> 
		
		인증번호 입력 <input type="text" id="sendCheck" placeholder="인증번호 확인" class="join"> 
		<br>
		<br>
		<input type="submit" value="가입하기">

	</form>
<script src="${pageContext.request.contextPath}/js/join.js" charset="UTF-8"></script>
</body>
</html>