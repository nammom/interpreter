<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이 페이지</title>
<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<script>var contextPath = "${pageContext.request.contextPath}";</script>
</head>
<body>
<div>
<ul>
<li>
<a href="${pageContext.request.contextPath}/mypage/mypageMain">-main</a>
</li>
<li>
<a href="${pageContext.request.contextPath}/mypage/updateInfo">-개인정보 수정</a>
</li>
<li>
<a href="${pageContext.request.contextPath}/mypage/updatePwd">-비밀번호 변경</a>
</li>
<li>
<a>-소개서 관리</a>
</li>
<li>
<a>-사업자 인증 관리</a>
</li>
<li>
<a>-일자리 매칭 관리</a>
</li>
<li>
<a>-나의 일정 관리</a>
</li>
<li>
<a href="${pageContext.request.contextPath}/mypage/deleteAccount">-회원 탈퇴</a>
</li>
</ul>
</div>

<div>
<ul>
<li>$ 포인트</li>
<li><a href="${pageContext.request.contextPath}/mypage/myQnaList">■ 나의 1:1문의</a></li>
</ul>
</div>

</body>
</html>