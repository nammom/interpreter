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
	<title>랭킹</title>

	<style>
		.star-rating { width:76.875px; }
		.star-rating,.star-rating span { display:inline-block; height:14.625px; overflow:hidden; background:url(star3.png)no-repeat; background-size: 75px; }
		.star-rating span{ background-position:left bottom; line-height:0; vertical-align:top; }
	</style>
	
</head>
<body>
<div class = "ranking">
	<div class = "eeRank">
		<table>
		<th>순위</th><th>닉네임</th><th>평균별점</th><th>리뷰수</th>
		<c:forEach var = "ee" items="${eeRanking}" varStatus = "Loop">
			<tr>
			<td>${ee.rankNum}</td>
			<td>${ee.usercode}</td>
			<td><small>${ee.avg}</small><span class="star-rating"><span style ="width:${ee.avg*25}%"></span></span></td>
			<td>${ee.count}</td>
			</tr>
		</c:forEach>
		</table>
	</div>
	
	<div class = "erRank">
		<table>
		<th>순위</th><th>닉네임</th><th>평균별점</th><th>리뷰수</th>
		<c:forEach var = "er" items="${erRanking}" varStatus = "Loop">
			<tr>
			<td>${er.rankNum}</td>
			<td>${er.usercode}</td>
			<td><small>${er.avg}</small><span class="star-rating"><span style ="width:${er.avg*25}%"></span></span></td>
			<td>${er.count}</td>
			</tr>
		</c:forEach>
		</table>
	</div>
</div>	
		<script type="text/javascript">
		$(document).ready(function(){		
			startAjax();
		});	
		
		function updateChatList(){
			$.ajax({
		        type : "POST",
		        url : "${pageContext.request.contextPath}/newRanking",
		        dataType: "json",
		        error : function() {
		            alert('통신실패!!');
		        },
		        success : function(data) {
		        
		        	var newEeList = '';	
		        		newEeList += '<table><th>순위</th><th>닉네임</th><th>평균별점</th><th>리뷰수</th>'
		        			for(i = 0; i < data.newEe.length; i++){
		        				var newEe = data.newEe[i];
		        					newEeList += '<tr><td>' + newEe.rankNum + '</td><td>' + newEe.usercode + '</td><td><small>';
		        					newEeList += newEe.avg +'</small><span class="star-rating"><span style ="width:' + newEe.avg * 25 + '%"></span></span></td>';
		        					newEeList += '<td>' + newEe.count + '</td></tr>';
		        					
		        			}
		        		newEeList += '</table>'
		        		
		        	var newErList = '';	
		        		newErList += '<table><th>순위</th><th>닉네임</th><th>평균별점</th><th>리뷰수</th>'
		        			for(i = 0; i < data.newEr.length; i++){
		        				var newEr = data.newEr[i];
		        					newErList += '<tr><td>' + newEr.rankNum + '</td><td>' + newEr.usercode + '</td><td><small>';
		        					newErList += newEr.avg +'</small><span class="star-rating"><span style ="width:' + newEr.avg * 25 + '%"></span></span></td>';
		        					newErList += '<td>' + newEr.count + '</td></tr>';
		        					
		        			}	
		        		newErList += '</table>'	
		        		$('.eeRank').html(newEeList);
		        		$('.erRank').html(newErList);
		        }      
		       
			});
		}
		
		function startAjax(){
			setInterval(function(){updateChatList()}, 3600000);
		}
		
</script>

</body>
</html>
