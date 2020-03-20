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
	<title>마이블로그 사장님탭</title>

	<style>
		.star-avg { width:307.5px; }
		.star-avg,.star-avg span { display:inline-block; height:29.25px; overflow:hidden; background:url(imagefile/review/star3.png)no-repeat; background-size: 150px; }
		.star-avg span{ background-position:left bottom; line-height:0; vertical-align:top; }
		
		.star-rating { width:205px; }
		.star-rating,.star-rating span { display:inline-block; height:19.5px; overflow:hidden; background:url(imagefile/review/star3.png)no-repeat; background-size: 100px; }
		.star-rating span{ background-position:left bottom; line-height:0; vertical-align:top; }
	</style>

</head>
<body>
	${myuserCode}알바생
	<br/><br/><small>평균별점</small>
	<span class="star-avg">
	<span style ="width:${avgStar*10}%"></span>
	</span>
	<br/><br/>
----------------------------------------------------
	<!--erIntro-->

		<div class = "intro">
	<c:if test="${eeIntro.openCheck == 'Y'}">
		<p>희망 근무지 : ${eeIntro.workspace}</p>
		<c:if test="${!empty career}">
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
		<p>${eeIntro.contents}</p>		
	</c:if>
	<c:if test="${eeIntro.openCheck != 'Y'}">
		<p>등록된 소개서가 없습니다.</p>
	</c:if>
	</div>
----------------------------------------------------	
	<div class="review">		
	<c:if test="${empty review}">
		<p>등록된 리뷰가 없습니다.</p>
	</c:if>
	<c:forEach var = "rv" items="${review}" varStatus = "Loop">
		<p>${rv.reviewerCode} 
		<span class="star-rating">
	<span style ="width:${rv.star*10}%"></span>
	</span><br/>
			<small>한줄평 :</small> ${rv.contents}</p>		
	</c:forEach>
	</div>
</body>
</html>
