<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일맺다 공지사항</title>
	
	<style>
	
	*{margin:20; padding:0;}
	ul{list-style: none;}
	a{color: inherit; text-decoration: none;}
	img{max-width:100%;}
	#nav{flex-basis: 20vw;flex-grow: 1; flex-shrink: 4; text-align:center;}
	#nav ul li{line-height: 50px; font-weight:bold; font-size: 20px; }
	#navdiv {float:left;}
	</style>





</head>
<body>
	<h1>전체공지</h1>
	<table>
		<c:forEach items="${noticeList}" var="vo">
			<tr>
				<!-- <td><a href="#">${vo.title}</a></td> -->
				<td><a href="<c:url value="/noticeList/read/${vo.no}" />">
						${vo.title}</a></td>
			<tr>
		</c:forEach>
	</table>

	<div id="navdiv">
		<nav id="nav">
	            <ul>
		            <li><a href='${pageContext.request.contextPath}/noticeList'>공지사항</a></li>
					<li><a href='${pageContext.request.contextPath}/tos'>이용약관</a></li>
					<li><a href='${pageContext.request.contextPath}/qnaStep1'>1:1문의</a></li>
	            </ul>
	    </nav>
	</div>










</body>
</html>