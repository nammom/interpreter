<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>공지사항</title>
</head>
<body>
                <h1>${NoticeVo.title}</h1><br>
                                   작성일  ${NoticeVo.regdate} <br>
                                   조회수 ${NoticeVo.hit}<br>
                                   내용  ${NoticeVo.content}<br>
                <br>
                <br>
         <div>
                <a href="<c:url value="/noticeList" />">목록</a>
        </div>
</body>
</html>