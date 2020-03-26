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

<title>1:1채팅</title>
</head>
<body>
	    
    <!--  1:1채팅내역 for문으로띄움-->
    <c:forEach var = "chat" items = "${oneChatVoList}" varStatus = "Loop">
		<div>${chat.fromCode} 님 : ${chat.chatContent} ${chat.chatTime}</div><br/>
	</c:forEach>
    
    <div id="list"></div>
	     
	<c:if test="${fromCode != '관리자코드'}" >     
    	<div>
        	<input type="text" id="messageinput">
        	<button id ="sendBt" value ="Send">Send</button>
        	<button id = "matchingBt">매칭하기</button>
        	<button id = "roadMapBt">길찾기</button>        
    	</div>   
	</c:if>
  	
<script>

	var wsocket;
	var employeeCode = $("#anotherCode").val();
	
	$(document).ready(function(){
		wsocket = new WebSocket("ws://localhost:8080<c:url value='/echo'/>");
		wsocket.onmessage = onMessage;
		wsocket.onclose = onClose;
		wsocket.onopen = onOpen;
						
		$("#sendBt").click(function(){
			var message = $("#messageinput").val();
			send(message);
			$("#messageinput").val('');
		});
		
		$("#matchingBt").click(function(){
			openChildMatching();
		});
		
		$("#roadMapBt").click(function(){
			openRoadMap();			
		});	
	});
	
	function send(message){
		wsocket.send(message);		
	};
		
	function onOpen(e){
		console.log(e);
		alert("소켓 연결");
	};
	         
	function onMessage(i){
		var f = i.data
		var e = JSON.parse(f)
 		var fromCode = e.myuserCode;
		var chatContent = e.chatContent;
		var chatTime = e.chatTime;
		 
		$("#list").append("<div class='.msg'>"+fromCode+ " : " + chatContent + " " + chatTime + "<div>");
	};
	
	function onClose(e){
		wsocket = new WebSocket("ws://localhost:8080<c:url value='/echo'/>");
		wsocket.onmessage = onMessage;
		wsocket.onclose = onClose;
		wsocket.onopen = onOpen;
		
	};
	
	function openChildMatching(){		
		var url = "${pageContext.request.contextPath}/matching/${employeeCode}";
		alert(url);
        var name = "matching";
        var option = "width = 600, height = 300, top = 100, left = 200, location = no"
        window.open(url, name, option);
	};
	
	function openRoadMap (){
		var url = "${pageContext.request.contextPath}/roadMap";
        var name = "readMap";
        var option = "width = 800, height = 600, top = 100, left = 200, location = no"
        window.open(url, name, option);
	};
	
</script>
</body>
</html>
