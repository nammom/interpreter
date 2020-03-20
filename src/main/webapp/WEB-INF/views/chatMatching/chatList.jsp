<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<html>
<head>
	<meta http-equiv ="Content-Type" content = "text/html; charset=UTF-8">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script
 		src="https://code.jquery.com/jquery-2.2.4.min.js"
  		integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
  		crossorigin="anonymous"></script>
	<title>채팅목록</title>
</head>
<body onload = updateChatList()>
	${myuserCode}님의 채팅목록
	<!--model에 담긴 채팅목록 for문으로 출력 (버튼 url : /chat/userCode)  --> 
	<div id="Context">	
	<%-- <%@include file="chatListData.jsp" %> --%>
		<c:forEach var = "chat" items="${allChatVoList}" varStatus = "Loop">
	
			<c:if test="${myuserCode == chat.fromCode}">
				<p>${chat.toCode}님과의 대화 : ${chat.chatContent}  ${chat.chatTime} (${chat.readCheck})
				<a href = "<c:url value = "/chat/${chat.toCode}"/>">1:1채팅하기</a>
				<a href = "<c:url value = "/delete/${chat.toCode}"/>">대화방나가기</a>
				
				</p>
			</c:if>
			<c:if test="${myuserCode == chat.toCode}">
				<p>${chat.fromCode}님과의 대화 : ${chat.chatContent} ${chat.chatTime} (${chat.readCheck})
				<a href = "<c:url value = "/chat/${chat.fromCode}"/>">1:1채팅하기</a>
 				<a href = "<c:url value = "/delete/${chat.fromCode}"/>">대화방나가기</a>
				
				</p>
			</c:if>			
			
	</c:forEach>
	</div>

	<!--ajax추가  -->
	<script type="text/javascript">
		$(document).ready(function(){
			startAjax();
		});	
		
		function updateChatList(){
			$.ajax({
		        type : "POST",
		        url : "${pageContext.request.contextPath}/newChatList",
		        dataType: "json",
		        error : function() {
		            alert('통신실패!!');
		        },
		        success : function(data) {
		        	/* alert('ajax성공'); */
		        	var newList = '';
 		        	for(i = 0; i < data.newChatList.length; i++){ 
		        			if(data.newChatList[i].myuserCode == data.newChatList[i].fromCode){
		        				var toCode = data.newChatList[i].toCode;
		        				newList += '<p>'+ toCode +'님과의 대화 : ' + data.newChatList[i].chatContent + ' '   
		        						+ data.newChatList[i].chatTime + ' (' + data.newChatList[i].readCheck + ') ';
		                   		newList += " <a href = 'chat/" + toCode +"'>1:1채팅하기</a>";
		                    	newList += " <a href = 'delete/" + toCode +"'>대화방나가기</a>";		                 
		        			}
		        			else if(data.newChatList[i].myuserCode == data.newChatList[i].toCode){
		        				var fromCode = data.newChatList[i].fromCode;
			        			newList += '<p>'+ fromCode +'님과의 대화 : ' + data.newChatList[i].chatContent + ' '   
			        						+ data.newChatList[i].chatTime + ' (' +  data.newChatList[i].readCheck + ') ';
			                    newList += " <a href = 'chat/" + fromCode +"'>1:1채팅하기</a>";
			                    newList += " <a href = 'delete/" + fromCode +"'>대화방나가기</a>";		                 
			        		}
		        	} 		  	
		            $('#Context').html(newList);
		        }
			});
		}
		
		function startAjax(){
			setInterval(function(){updateChatList()}, 3000);
		}
</script>
</body>
</html>
