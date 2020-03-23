<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 탈퇴</title>
<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<script>var contextPath = "${pageContext.request.contextPath}";</script>
</head>
<body>
<jsp:include page="mypageMain.jsp" flush="true"/>
회원 탈퇴
<br>
<br>
-쪽지,포인트 등의 개인정보 데이터는 삭제됩니다.<br>
-각종 게시판의 게시글, 댓글 등의 데이터는 삭제되지 않습니다. 반드시 탈퇴 전 직접 삭제하셔야 합니다.<br>
-회원 탈퇴 뒤에는 모든 회원 정보가 삭제되며 재가입시에도 기존 아이디는 더 이상 사용하실 수 없습니다.<br>
<br><br>
<label>
<input type="checkbox" required>안내사항을 모두 확인하였으며, 이에 동의합니다.
</label>
<br><br>
비밀번호 <input type="password" id="password" required>
<br><br>
<input type="button" id="deleteAccount" value="회원 탈퇴">

<script src="${pageContext.request.contextPath}/js/mypage.js" charset="UTF-8"></script>
</body>
</html>