<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 완료</title>
<script>var contextPath = "${pageContext.request.contextPath}";</script>
</head>
<body>
${user.usercode}님 1M 회원이 되신것을 축하드립니다!!!<br>
<input type="button" value="home으로 이동"> 
<input type="button" value="로그인하기" 
	onclick="location.href= '${pageContext.request.contextPath}/login/login'">
</body>
</html>