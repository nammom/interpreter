<%@page import="org.springframework.web.context.request.SessionScope"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>환영합니다</title>
</head>
<body>
<%
	String title ="";
	if(session.getAttribute("myuserCode") != null){
		title = "님이 로그인 하셨습니다.";
%>
	${myuserCode}<%=title %> <br><br>
	<input type="button" id="logout" value="로그아웃"
	 onclick = "location.href = '${pageContext.request.contextPath}/logout'">
<%
	}else{
		title ="정상적으로 로그아웃 되었습니다.";
%>
	
	${myuserCode}<%=title %> <br><br>
	<input type="button" id="login" value="로그인"
	onclick = "location.href = '${pageContext.request.contextPath}/login/login'">
<%
	}
%>

<br><br>

</body>
</html>