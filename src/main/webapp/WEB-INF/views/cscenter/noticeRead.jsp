<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>��������</title>
</head>
<body>
                <h1>${NoticeVo.title}</h1><br>
                                   �ۼ���  ${NoticeVo.regdate} <br>
                                   ��ȸ�� ${NoticeVo.hit}<br>
                                   ����  ${NoticeVo.content}<br>
                <br>
                <br>
         <div>
                <a href="<c:url value="/noticeList" />">���</a>
        </div>
</body>
</html>