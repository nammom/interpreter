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
  	
	<title>임시메인</title>

</head>
<body onload = updateAtc()>

	<div class = "eb" style="float:left;">

	</div>
	
	<div class = "wb" style="float:left;">

	</div>



		<script type="text/javascript">
		$(document).ready(function(){	
			startAjax();
		});	
		
		function updateAtc(){
			$.ajax({
		        type : "POST",
		        url : "${pageContext.request.contextPath}/newPop",
		        dataType: "json",
		        error : function() {
		            alert('통신실패!!');
		        },
		        success : function(data) {
		        	/* 이미지 띄워주는 법 알아오기  */
		        	var eb = '';	
	        		eb += '<ul>'
		        			for(i = 0; i < data.ebList.length; i++){
		        				var e = data.ebList[i];
		        				eb += '<li><bold><a href = "myblog/'+ e.writerCode + '">' + e.writerCode + '</a></bold><br/>'; 
		        				eb += '<a href = "content/' + e.ebcode + '">' +  e.title + '<br/></a>';
		        				eb += '<small>' + e.address +' | 급여 : '+ e.salary +'</small></li>';	        					
		        			}
	        		eb += '</ul>'
		        		
		        	var wb = '';	
	        		wb += '<ul>'
		        			for(i = 0; i < data.wbList.length; i++){
		        				var w = data.wbList[i];
		        				wb += '<li><bold><a href = "myblog/'+ w.writerCode + '">' + w.writerCode + '</a></bold><br/>'; 
		        				wb += '<a href = "content/' + w.wbcode + '">' +  w.title + '<br/></a>';
		        				wb += '<small>' + w.address +' | 급여 : '+ w.salary +'</small></li>';	             					
		        			}	
	        		wb += '</ul>'	
		        	
	        		$('.eb').html(eb);
		        	$('.wb').html(wb);
		        }      
		       
			})
		};
		
		function startAjax(){
			setInterval(function(){updateAtc()}, 5000);
		};
		

		   
		
		
</script>

</body>
</html>
