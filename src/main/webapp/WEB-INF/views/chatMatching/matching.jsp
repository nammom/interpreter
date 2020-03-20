<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script
  src="https://code.jquery.com/jquery-2.2.4.min.js"
  integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
  crossorigin="anonymous"></script>
</head>

<title>매칭하기</title>
</head>
<body>
	    
    <!--  1:1채팅내역 for문으로띄움-->
    <table>
    	<tr>
    		<th><th>
    		<th>글코드</th>
    		<th>제목</th>
    		<th>근무일자</th>
    		<th>근무시간대</th>
    		
    	</tr>
    	<c:forEach var = "eb" items = "${employerBoardVoList}" varStatus = "Loop">
			<tr>
				<td><input type = "radio" name = "ebcode" class = "ebcode" id="${eb.start}" value="${eb.ebcode}"/></td>
				<td>${eb.ebcode}</td>
				<td>${eb.title}</td>
				<td>${eb.start} ~ ${eb.end}</td>
				<td>${eb.workTime}</td>
			</tr>
		</c:forEach>
    
    </table>
    
    <button id ="sendEbBt" value ="sendEbBt">게시글전송</button>
    <button id = "matchingBt">매칭하기</button>
  
<script>

	var wsocket;
	
	$(document).ready(function(){
		var ebcode = '';
		$(".ebcode").change(function(){
			ebcode = $("input[name = ebcode]:checked").val();
		});
		
		$("#sendEbBt").click(function(){
			var ebUrl = "이게시글로 매칭할까요? <a href = '/게시글 경로 /" + ebcode + "'>" + ebcode + "</a>";
			sendEb(ebUrl);
		});
		
		$("#matchingBt").click(function(){
			var ebUrl = "이게시글로 매칭되었어요! <a href = '/게시글 경로 /" + ebcode + "'>" + ebcode + "</a>";
			sendEb(ebUrl);
			matching(ebcode);
		});
		
		
	});
	
	/* 채팅전송 함수 */
	function sendEb(ebUrl){
		opener.parent.send(ebUrl);
	};
	
	/* 매칭하기 함수 */
	function matching(ebcode){
		var ebStart =  $("input[name = ebcode]:checked").attr('id');
		
		var obj = { "ebCode" : ebcode, "ebStart" : ebStart }
		
		console.log(ebcode);
		$.ajax({
	        type : "POST",
	        url : "${pageContext.request.contextPath}/makeMatching",
	        data : JSON.stringify(obj),
	        contentType : "application/json",
		});	
		window.close();
	};


</script>
</body>
</html>
