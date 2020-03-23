<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>
</head>
<body>
<jsp:include page="mypageMain.jsp" flush="true"/>
회원 탈퇴가 정상적으로 완료되었습니다.
<br><br>
	<input type="button" value="Home으로 이동"
	onclick="location.href = '${pageContext.request.contextPath}/home'">
</body>
</html>