<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.math.BigInteger" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<script>var contextPath = "${pageContext.request.contextPath}";</script>
<script src="${pageContext.request.contextPath}/js/login.js"
	charset="UTF-8"></script>

</head>
<body>
	<%
    String clientId = "Vagel85RDbRxBGlWkEa3";//애플리케이션 클라이언트 아이디값";
    String redirectURI = URLEncoder.encode("http://localhost:8080/finalproject/join/step1", "UTF-8");
    SecureRandom random = new SecureRandom();
    String state = new BigInteger(130, random).toString();
    String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
    apiURL += "&client_id=" + clientId;
    apiURL += "&redirect_uri=" + redirectURI;
    apiURL += "&state=" + state;
    session.setAttribute("state", state);
 	%>

	<input type="email" name="email" id="email" placeholder="이메일">
	<br>
	<input type="password" name="password" placeholder="비밀번호">
	<br>
	<br>

	<input type="button" value="Login" id="loginBtn">
	<br>
	<label><input type="checkbox" id="emailSave">아이디 저장</label>
	<a href="${pageContext.request.contextPath}/login/searchEmailPwd">아이디 & 비밀번호 찾기</a>/
	<a href="${pageContext.request.contextPath}/login/searchPwd">비밀번호 찾기</a>
	
	<!-- 네이버 로그인 창으로 이동 -->
	<div id="naver_id_login" style="text-align: center">
		<a href="<%=apiURL%>"><img height="50" src="http://static.nid.naver.com/oauth/small_g_in.PNG"/></a>
	</div>
	<%System.out.println("apiURL출력"+apiURL); %>
	<br>
	아직 회원이 아니세요? <a href="${pageContext.request.contextPath}/join/step1">회원가입하기</a>


</body>

</html>