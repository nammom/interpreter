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
<form action="updateInfo" method="POST" onsubmit="return updateInfo();">
	개인정보 수정
	<br><br>
	닉네임
	<input type="text" name="usercode" id="userCode"  value="${member.usercode }" > 
	<input type="button" id="usercodeCheck" value="중복확인">
	<input type="checkbox" id="usercodeConfirm"> <!-- style="display:none" --> 
	<br>
	생년월일 <input type="text" id="year" class="birthdate" value="${year}"  >
			 <select id="month"  class="birthdate">
				<option value="">${month}월</option>
				<%for(int i = 1; i<=9; i++){%>
				<option value="0<%=+i %>" id="month"><%=i %>월</option>
				<%} %>
				<%for(int i = 10; i<=12; i++){%>
				<option value="<%=+i %>" id="month"><%=i %>월</option>
				<%} %>
			</select> 
			<select id="day" class="birthdate" >
				<option value="" >${day}일</option>
				<%for(int i = 1; i<=9; i++){%>
				<option value="0<%=i%>" id="day"><%=i %> 일</option>
				<%} %>
				<%for(int i = 10; i<=31; i++){%>
				<option value="<%=i%>" id="day"><%=i %> 일</option>
				<%} %>
			</select>
			<input type="hidden" id="birthdate" name="birthdate"><br>		
			<div style="display:inline" id="year-wrong" style="display:none" >생년월일을 올바르게 입력해주세요.</div>
	<br>
	휴대폰 번호 
	<input type="text" class="join" id="phone" placeholder="-없이 숫자로만 입력">
	<input type="button" id="send" value="인증번호 발송"> <br> 
			
	인증번호 입력
	<input type="text" id="sendCheck" placeholder="인증번호 확인" class="join"> 
	<br><br>
	<input type="submit" id="updateInfoBtn" value="정보수정">
	<input type="hidden" name="phone" value="${member.phone}">

</form>
<script src="${pageContext.request.contextPath}/js/mypage.js" charset="UTF-8"></script>
<script src="${pageContext.request.contextPath}/js/join.js" charset="UTF-8"></script>
</body>
</html>