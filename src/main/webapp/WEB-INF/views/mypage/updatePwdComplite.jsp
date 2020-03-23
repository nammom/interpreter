<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경완료</title>
<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<script>var contextPath = "${pageContext.request.contextPath}";</script>
</head>
<body>
<jsp:include page="mypageMain.jsp" flush="true"/>
비밀번호 변경이 완료 되었습니다.
<br><br>
<%
	if(session.getAttribute("myuserCode") != null){
%>
	<input type="button" value="확인"
	onclick="location.href = '${pageContext.request.contextPath}/mypage/mypageMain'">
<%
	}else{
%>
	<input type="button" value="main으로 이동"
	onclick="location.href = '${pageContext.request.contextPath}/mypage/mypageMain'">
	<input type="button" value="로그인 하기"
	onclick="location.href = '${pageContext.request.contextPath}/login/login'">
<%
	}
%>
</body>
</html>