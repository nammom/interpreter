<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디/비밀번호 찾기</title>
<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<script>var contextPath = "${pageContext.request.contextPath}";</script>
</head>
<body>
회원정보에 등록된 휴대폰 번호로 아이디 / 비밀번호 찾기
<br>
휴대폰 번호 
<input type="text" name="phone" class="join" id="phone" placeholder="-없이 숫자로만 입력">
<input type="button" id="send" value="인증번호 발송"> <br> 
<br>
인증번호 입력 <input type="text" id="sendCheck" placeholder="인증번호 확인" class="join"> 
		
<input type="button" id="SearchEmailPwd" value="다음>">

<script src="${pageContext.request.contextPath}/js/join.js" charset="UTF-8"></script>
<script src="${pageContext.request.contextPath}/js/login.js" charset="UTF-8"></script>
</body>
</html>