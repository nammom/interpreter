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
  	
	<title>광고</title>
	
<style>
.logo{
	width: 130px;
  	height: auto;
}

.cBt{
	background-color: white;
	border : 0;
}

#c{
	margin : 0;
	padding : 0;
	list-style : none;
	display:inline;
}

.cad{
	align : left;
	width: 700px;
}


</style>
	
</head>
<body onload = updateAd()>

<div class = "ad">
	<button class = "adApp"  onclick = "adApp(this)" value = "company">신청하기</button>
	<div class = "cad">
	
	</div>
	
	<button class = "adApp" onclick = "adApp(this)" value = "employer">신청하기</button>
	<div class = "erad">

	</div>
	
	<button class = "adApp" onclick = "adApp(this)" value = "employee">신청하기</button>
	<div class = "eead">

	</div>
	

</div>	
		<script type="text/javascript">
		$(document).ready(function(){	
			startAjax();
		});	
		
		function updateAd(){
			$.ajax({
		        type : "POST",
		        url : "${pageContext.request.contextPath}/newAd",
		        dataType: "json",
		        error : function() {
		            alert('통신실패!!');
		        },
		        success : function(data) {
		        	/* 이미지 띄워주는 법 알아오기  */
		        	var cAd = '';	
	        		cAd += '<ul id = "c">'
	        		var j = 0;
	        			for(i = 0; i < data.cAdList.length; i++){
	        				
	        				var c = data.cAdList[i];
	        				cAd += '<button class = "cBt" value= "' + c.link + '" onclick= "company(this)">';
	        				cAd +=  '<li><bold><img class="logo" src="companyLogo/' + c.image + '"/>';
	        				cAd += '</bold><br/>' + c.enterprise + '</li></button>';	        					
	        			}
	        		cAd += '</ul>'
	        			
		        	var erAd = '';	
	        		erAd += '<ul>'
		        			for(i = 0; i < data.erAdList.length; i++){
		        				var er = data.erAdList[i];
		        				erAd += '<li><bold><a href = "myblog/'+ er.userCode + '">' + er.userCode + '</a></bold><br/>'; 
		        				erAd += '<a href = "content/' + er.link + '">' +  er.title + '<br/></a>';
		        				erAd += '<small>' + er.address +' | 급여 : '+ er.salary +'</small></li>';	        					
		        			}
	        		erAd += '</ul>'
		        		
		        	var eeAd = '';	
	        		eeAd += '<ul>'
		        			for(i = 0; i < data.eeAdList.length; i++){
		        				var ee = data.eeAdList[i];
		        				eeAd += '<li><bold><a href = "myblog/'+ ee.userCode + '">' + ee.userCode + '</a></bold><br/>'; 
		        				eeAd += '<a href = "content/' + ee.link + '">' +  ee.title + '<br/></a>';
		        				eeAd += '<small>' + ee.address +' | 급여 : '+ ee.salary +'</small></li>';	             					
		        			}	
	        		eeAd += '</ul>'	
		        	
	        		$('.cad').html(cAd);
		        	$('.erad').html(erAd);
		        	$('.eead').html(eeAd);
		        }      
		       
			});
		}
		
		function startAjax(){
			setInterval(function(){updateAd()}, 5000);
		}
		
		function adApp(e){
			var category = e.value;
			
			var url = "${pageContext.request.contextPath}/adApp?category=" + category;
	        var name = "adApp";
	        var option = "width = 500, height = 700, top = 100, left = 200, location = no"
	        window.open(url, name, option);
		}
		
		function company(e){
			var url = e.value;
	        var name = "company";
	        var option = "width = 800, height = 700, top = 100, left = 200, location = no"
	        window.open(url, name, option);
		}


		   
		   
		
		
</script>

</body>
</html>
