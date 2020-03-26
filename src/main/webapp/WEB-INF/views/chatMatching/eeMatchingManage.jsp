<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사장님 매칭관리</title>
<script
  src="https://code.jquery.com/jquery-2.2.4.min.js"
  integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
  crossorigin="anonymous"></script>
 
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/onoff.css">

</head>
<body onload = updateEeMatching();smsStatus();>
<a href = "erMatchingManage"><button id = "eeMatching">나의사장님매칭</button></a>
<a href = "eeMatchingManage"><button id = "erMatching">나의알바생매칭</button></a>

<div id = "smsStatus">
<input type = "checkbox" id = "sms" checked> 
문자알림 <label for = "sms"><span></span></label>
</div><br/><br/>
<div>
	<ul class = "content">
	</ul>
</div>	
	<script>
	$(document).ready(function(){
		smsStatus()
		startAjax();
		var employerCode = $("#review").val();
		
		 $("#sms").change(function(){
			updateSms();
		});
	});	
	
 	 function updateEeMatching(){
		$.ajax({
	        type : "POST",
	        url : "${pageContext.request.contextPath}/neweeList",
	        dataType: "json",
	        error : function() {
	            alert('통신실패!!');
	        },
	        success : function(data) {
	        	var newList = '';
	        	if(data.hasOwnProperty('neweeList')){
	        		for(i = 0; i < data.neweeList.length; i++){ 
	        			var eeList = data.neweeList[i];
	        			newList += '<li>게시글코드 : ' + eeList.ebCode + ' ' + eeList.title; 
	        			newList += ' 사장님  ' + eeList.employerCode + '<br/>';
	        			
	        			if(eeList.cfCheck == 'Y' && eeList.reviewCode == 'null'){
	        				newList += '<button id = "review' + eeList.ebCode + '" onclick = "openChildReview(this)" value="'
										+ eeList.employerCode+'" name = "'+eeList.matchingCode+'">리뷰쓰기</button>';
	        			}else if(eeList.cfCheck == 'N'){
	        				newList += '<button id = "review' + eeList.ebCode + '" disabled>미인증</button>';
	        			}else if(eeList.reviewCode != 'null'){
	        				newList += '<button id = "review' + eeList.ebCode + '" disabled>리뷰완성</button>';
	        			}
	        			
	        			if(eeList.cfCheck == 'Y'){
	        				newList += '<button id ="cancelM' + eeList.ebCode + '" disabled>매칭취소</button></li>';	  	  
	        			}else{
	        				newList += '<button id ="cancelM' + eeList.ebCode + '" onclick = "cancel(this)" value="' 
										+ eeList.matchingCode + '">매칭취소</button></li>';	  
	        			}
	        		} 		
	        	}
	        	else if(data.hasOwnProperty('not')){
	        		newList = data.not;
	        	}		        		  	 
	            $('.content').html(newList);
	        }
		});
	}
	
	function startAjax(){
		setInterval(function(){updateEeMatching()}, 3000)
	}
	
	
 	function openChildReview(e){	
		var employerCode = e.value
		var matchingCode = e.name;
		
		var name = "review/employer";
		var url = "${pageContext.request.contextPath}/review/employer?targetCode="+employerCode+ "&matchingCode=" +matchingCode;
        var name = "review";
        var option = "width = 600, height = 300, top = 100, left = 200, location = no";
        var openWin = window.open(url, name, option);

	} 
 	
	function cancel(e){
		var matchingCode = e.value;
		
		var obj = { "matchingCode" :matchingCode }
		
		$.ajax({
	        type : "POST",
	        url : "${pageContext.request.contextPath}/cancelMatching",
	        data :JSON.stringify(obj),
	        contentType: "application/json",
	        success : function(data) {  
	        	alert("매칭취소되었습니다")
	        }
		});			
	};
	
	function updateSms(){
		var smsCheck = '';
		if($("#sms").is(":checked")){
			smsCheck = 'Y';
		}else{
			smsCheck = 'N';
		}		
		var obj = { "smsCheck" :smsCheck }
		
		$.ajax({
	        type : "POST",
	        url : "${pageContext.request.contextPath}/updateSms",
	        data :JSON.stringify(obj),
	        contentType: "application/json",
	        success : function(data) {  
	        	alert("sms ajax성공")
	        }
		});			
	};
	
	function smsStatus(){
		if(${smsCheck == 'Y'}){
			$("#sms").attr("checked", true);					
		}else{
			$("#sms").attr("checked", false);	
		}
	} 
	
</script>
</body>
</html>