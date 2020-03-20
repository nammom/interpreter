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

<title>리뷰</title>

<style>
*{margin:0; padding:0;}
.star{
  display:inline-block;
  width: 15px;height: 30px;
  cursor: pointer;
}
.star_left{
  background: url(http://gahyun.wooga.kr/main/img/testImg/star.png) no-repeat 0 0; 
  background-size: 30px; 
  margin-right: -3px;
}
.star_right{
  background: url(http://gahyun.wooga.kr/main/img/testImg/star.png) no-repeat -15px 0; 
  background-size: 30px; 
  margin-left: -3px;
}
.star.on{
  background-image: url(http://gahyun.wooga.kr/main/img/testImg/star_on.png);
}
</style>
</head>
<body>

  <form action="<c:url value='/reviewSuccess'/>" method = "POST" onsubmit = "return reviewCheck();">
  	<input type ="hidden" name = "category" value = "${category}"/>
  	<input type ="hidden" name = "targetCode" value = "${targetCode}"/>
  	<input type = "hidden" name = "matchingCode" value = "${matchingCode}"/>
 	<label>별점
 	<div class="star-box">
 	<span class="star star_left"></span>
 	<span class="star star_right"></span>

  	<span class="star star_left"></span>
  	<span class="star star_right"></span>

  	<span class="star star_left"></span>
  	<span class="star star_right"></span>

 	<span class="star star_left"></span>
 	<span class="star star_right"></span>
	
 	<span class="star star_left"></span>
 	<span class="star star_right"></span>
	</div>
		<input type = "hidden" name = "star"/>
	</label>
	
	<br/>
  	<label>한줄평<br/><br/>
  		<input type = "textarea" id = "content" name = "content" style="width:400px;height:100px"></textarea>
  	</label>
  	
  	<br/>
  	<button type = "button" id = "close">취소</button><input type = "submit">
  </form>



<script>
	
	 $(document).ready(function(){
		
		 $(".star").on('click',function(){
			   var idx = $(this).index();
			   $(".star").removeClass("on");
			     for(var i=0; i<=idx; i++){
			        $(".star").eq(i).addClass("on");
			   }
			     $("input[name = star]").val((idx +1)/2);	
		 });
		 
		 $("#close").click(function close(){
			 	window.close();
		 });
	});
	
	 function reviewCheck(){
		if( $("input[name = star]").val().length == 0 || $("#content").val().length == 0 ){
			alert("빈항목을 입력해주세요")
			return false;
		}
		else{
			return true;
		} 
	 }
	


</script>
</body>
</html>
