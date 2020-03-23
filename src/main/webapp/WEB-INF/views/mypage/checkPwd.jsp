<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인정보 수정</title>
<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<script>var contextPath = "${pageContext.request.contextPath}";</script>
</head>
<body>
<jsp:include page="mypageMain.jsp" flush="true"/>
비밀번호
<input type="password" id="password">
<br><br>
<input type="button" id="pwdCheckBtn" value="다음>">

<script src="${pageContext.request.contextPath}/js/mypage.js" charset="UTF-8"></script>
</body>
</html>