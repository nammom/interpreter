<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script
  src="https://code.jquery.com/jquery-2.2.4.min.js"
  integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
  crossorigin="anonymous"></script>
</head>
<body>

		<div>
		포인트가 부족합니다.
		<br/>
		<button id = "close">확인</button>
		</div>
	
</body>

<script>
$(document).ready(function(){
	$("#close").click(function(){
		window.close();
	});	
});
</script>
</html>