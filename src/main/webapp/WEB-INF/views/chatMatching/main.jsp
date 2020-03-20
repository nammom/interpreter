<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>임시메인화면</title>
</head>
<body>

${myuserCode}님의 main
<br/><br/>
<a href = "<c:url value = "/chatList"/>"><button type = "button" id= "login">채팅목록</button></a>
<br/><br/>
<a href = "<c:url value = "/chat/F1"/>"><button type = "button" id= "login">F1님과 1:1채팅하기</button></a>
<a href = "<c:url value = "/chat/U1"/>"><button type = "button" id= "login">U1님과 1:1채팅하기</button></a>
<br/><br/>
<a href = "<c:url value = "/erMatchingManage"/>"><button type = "button" id= "login">매칭관리</button></a>

<br/><br/>
마이블로그<br/>
<a href = "<c:url value = "/myBlogEr"/>"><button type = "button" id= "login">사장님탭</button></a>
<br/>
<a href = "<c:url value = "/myBlogEe"/>"><button type = "button" id= "login">알바생탭</button></a>


</body>
</html>