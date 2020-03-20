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
</head>
<body onload = updateErMatching()>
<a href = "erMatchingManage"><button id = "eeMatching">나의사장님매칭</button></a>
<a href = "eeMatchingManage"><button id = "erMatching">나의알바생매칭</button></a>
<div >
	<ul class = "content">

	</ul>
</div>

<script>
	$(document).ready(function(){
		startAjax();
	
	});	
	
	function updateErMatching(){
		$.ajax({
	        type : "POST",
	        url : "${pageContext.request.contextPath}/newerList",
	        dataType: "json",
	        error : function() {
	            console.log('통신실패!!');
	        },
	        success : function(data) {
	        	var newList = '';
	        	if(data.hasOwnProperty('newerList')){
	        		for(i = 0; i < data.newerList.length; i++){ 
	        			var erList = data.newerList[i];
	        			newList += '<li>게시글코드 : ' + erList.ebCode + ' ' + erList.title; 
	        			newList += ' 알바생 ' + erList.employeeCode + '<br/>';
	        			
	        			if(erList.cfCheck == 'Y'){
	        				newList += '<button id = "cf' + erList.ebCode + '" disabled>인증완료</button>';
	        			}
	        			else{
	        				newList += '<button id = "cf' + erList.ebCode + '" onclick = "cf(this)" value="' + erList.employeeCode 
	        							+ '" name = "' + erList.matchingCode+'">인증</button>';
	        			}
	        			
	        			if(erList.cfCheck == 'Y' && erList.reviewCode == 'null'){	
	        				newList += '<button id = "review' + erList.ebCode + '" onclick = "openChildReview(this)" value="' 
	        							+ erList.employeeCode+'" name = "'+erList.matchingCode+'">리뷰쓰기</button>';
	        			}
	        			else if (erList.cfCheck == 'N'){
	        				newList += '<button id = "review' + erList.ebCode + '" disabled>미인증</button>';
	        			}
	        			else if(erList.reviewCode != 'null'){
	        				newList += '<button id = "review' + erList.ebCode + '" disabled>리뷰완료</button>';
	        			}
	        			
	        			if(erList.cfCheck == 'Y'){
	        				newList += '<button id ="cancelM' + erList.ebCode + '" disabled>매칭취소</button></li>';	  	  
	        			}else{
	        				newList += '<button id ="cancelM' + erList.ebCode + '" onclick = "cancel(this)" value="' 
										+ erList.matchingCode + '">매칭취소</button></li>';	  
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
		setInterval(function(){updateErMatching()}, 3000)
	}
	
	function openChildReview(e){	
		var employeeCode = e.value;
		var matchingCode = e.name;
		var name = "review/employee";
		var url = "${pageContext.request.contextPath}/review/employee?targetCode="+employeeCode+"&matchingCode=" +matchingCode;
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
	
	function cf(e){
		var employeeCode = e.value;
		var matchingCode = e.name;
		var obj = { "targetCode" :employeeCode ,
					"matchingCode" :matchingCode}
		
		$.ajax({
	        type : "POST",
	        url : "${pageContext.request.contextPath}/cf",
	        data :JSON.stringify(obj),
	        contentType: "application/json",
	        success : function(data) {
	            $('.cfSuccess').html("인증완료");
	        }
		});			
		$(e).attr("disabled", true);
	};
	
	
</script>
</body>
</html>