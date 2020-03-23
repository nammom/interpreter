<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<script>var contextPath = "${pageContext.request.contextPath}";</script>
</head>
<body>
회원정보에 등록된 이메일로 비밀번호 찾기.
<br><br>
이메일<input type="email" name="email" id="email" placeholder="이메일 입력" required >
	<input type="button" id="sendEmail" value="인증번호 전송">
	<br>
	<input type="text" id="sendCheck" placeholder="인증번호 입력" >
	<input type="checkbox" id="emailConfirm" style="display:none">
	
	<input type="button" id="SearchPwd" value="다음>">

<script src="${pageContext.request.contextPath}/js/join.js" charset="UTF-8"></script>
<script src="${pageContext.request.contextPath}/js/login.js" charset="UTF-8"></script>
</body>
</html>