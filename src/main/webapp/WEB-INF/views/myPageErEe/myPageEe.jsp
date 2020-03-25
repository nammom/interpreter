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
  		
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/onoff.css">
		
	<title>알바생소개서관리</title>
<% %>
</head>


<body>

	<a href = "myPageEr"><button id = "erIntro">사장님소개서관리</button></a>
	<a href = "myPageEe"><button id = "eeIntro">알바생소개서관리</button></a>

	<div id = "openStatus">
	<input type = "checkbox" id = "open" checked> 
	공개여부 <label for = "open"><span></span></label>
	</div><br/><br/>


	<div class = "intro">
	<c:if test="${intro != null}">
		<p>희망 근무지 : ${intro.workspace}</p>
		<c:if test="${!empty career}">
		경력사항
		<table>
			<th>회사</th><th>근무기간</th>
		<c:forEach var = "cc" items="${career}" varStatus = "Loop">
			<tr>
			<td>${cc.company}</td>
			<td>${cc.startDate}~${cc.endDate}</td>
			</tr>
		</c:forEach>
		</table>
		</c:if>
		<p>${intro.contents}</p>
		<button onclick="goPage('editIntro')">수정</button>
		<button id = "delete" value = "${intro.introductionCode}">삭제</button>		
	</c:if>
	<c:if test="${intro == null}">
		<button onclick="goPage('writeIntro')">소개서 작성하기</button>
		<p>등록된 소개서가 없습니다.</p>
	</c:if>
	</div>
	
<script>
	$(document).ready(function(){
		openStatus()	
		
		$("#open").change(function(){
			updateOpenCheck();
		});
		
		$("#delete").click(function(){
			if (confirm("정말 삭제하시겠습니까??") == true){    //확인
				deleteIntro();
			}else{   //취소
			    return;
			}
		});
	});
	
	function deleteIntro(){
		var introductionCode = $("#delete").val();
		$.ajax({
	        type : "POST",
	        url : "${pageContext.request.contextPath}/deleteIntro/${category}",
	        data : introductionCode,
	        contentType : "application/String",
	        success : function(data) {  
	        	alert("소개서가 삭제되었습니다");
	         	location.reload(); 
	        }
		});			
	};
	
	function updateOpenCheck(){
		var openCheck = '';
		if($("#open").is(":checked")){
			openCheck = 'Y';
		}else{
			openCheck = 'N';
		}		

		$.ajax({
	        type : "POST",
	        url : "${pageContext.request.contextPath}/updateOpenCheck/${category}",
	        data : openCheck,
	        contentType: "application/String",
	        success : function(data) {  
	        	/* alert("OpenCheck ajax성공") */
	        }
		});			
	};
	
	function openStatus(){
		if(${intro.openCheck == 'Y'}){
			$("#open").attr("checked", true);					
		}else if(${intro.openCheck == 'N'}){
			$("#open").attr("checked", false);	
		}else{
			$("#open").attr("checked", false);	
		}
	} 
	
	function goPage(goUrl){
		var resultUrl = '';
		if(goUrl == 'writeIntro'){
			var openCheck = '';
			if($("#open").is(":checked")){
				openCheck = 'Y';
			}else{
				openCheck = 'N';
			}
			resultUrl = goUrl + '/${category}?open='+ openCheck;
		}
		else{
			resultUrl = goUrl + '/${category}';
		}
		location.replace(resultUrl);
	}
	
</script>
</body>
</html>
