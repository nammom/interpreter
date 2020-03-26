<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>일 맺 다 자 유 게 시 판</title>

	<!-- 제이쿼리 -->
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	
	<!-- 합쳐지고 최소화된 최신 CSS -->
	<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	
	<!-- 부가적인 테마 -->
	<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	
	<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	
	<style>
	
	a#TopButton {
    position: fixed;
    right: 2%;
    bottom: 700px;
    display: none;
    z-index: 999;
	}
	
	
	a#BottomButton {
    position: fixed;
    right: 2%;
    bottom: 50px;
    display: none; 
    z-index: 999;
	}
	
	
	
	</style>



</head>
<body>
	<a id="TopButton" href="#"><img src="imagefile/top.png"></a>
	<a id="BottomButton" href="#"><img src="imagefile/bottom.png"></a>
	<!-- <div id="root"> -->
	<div class="container">
		<header>
			<h1>일맺다 자유게시판</h1>
		</header>
		<!--<section id="container">  -->
		<section>
			<h2>글 목록</h2>
			<button>
				<a href="<c:url value="/freeWrite"/>">새글</a>
			</button>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>no.</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>조회수</th>
					</tr>
				</thead>

				<!-- 목록 시작 -->
				<c:forEach items="${freeList}" var="list">
					<tr>
						<td>${list.freecode}</td>
						<td><a
							href="<c:url value="/freeList/read/${list.freecode}" />">
								${list.title}</a></td>
						<td>${list.writercode}</td>
						<td>${list.regdate}</td>
						<td>${list.hit}</td>
					</tr>
				</c:forEach>
				<!-- 목록 끝 -->
			</table>

			<!-- 검색창 -->
			<div class="search row">
				<div class="col-xs-2 clo-sm-2">
					<select name="searchType" class="form-control">
						<option value="t"
							<c:out value="${sc.searchType eq 't' ? 'selected' : ''}"/>>제목</option>
						<option value="c"
							<c:out value="${sc.searchType eq 'c' ? 'selected' : ''}"/>>내용</option>
						<option value="w"
							<c:out value="${sc.searchType eq 'w' ? 'selected' : ''}"/>>작성자</option>
						<option value="tc"
							<c:out value="${sc.searchType eq 'tc' ? 'selected' : ''}"/>>제목+내용</option>
					</select>
				</div>

				<div class="col-xs-10 clo-sm-10">
					<div class="input-group">
						<input type="text" name="keyword" id="keywordInput"
							value="${sc.keyword}" class="form-control" /> <span
							class="input-group-btn">
							<button id="searchBtn" class="btn btn-default">검색</button>
						</span>
					</div>
				</div>

				<script>
					$(function() {
						$('#searchBtn').click(
								function() {
									self.location = "freeList"
											+ '${pageMaker.makeQuery(1)}'
											+ "&searchType="
											+ $("select option:selected").val()
											+ "&keyword="
											+ encodeURIComponent($(
													'#keywordInput').val());
								});
					});
				</script>
			</div>


			<!-- 페이징 -->
			<div class="col-md-offset-3">
				<ul class="pagination">
					<c:if test="${pageMaker.prev}">
						<li><a
							href="freeList${pageMaker.makeSearch(pageMaker.startPage - 1)}">이전</a></li>
					</c:if>

					<c:forEach begin="${pageMaker.startPage}"
						end="${pageMaker.endPage}" var="idx">
						<li
							<c:out value="${pageMaker.pagerd.page == idx ? 'class=active' : ''}"/>>
							<a href="freeList${pageMaker.makeSearch(idx)}">${idx}</a>
						</li>
					</c:forEach>

					<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
						<li><a
							href="freeList${pageMaker.makeSearch(pageMaker.endPage + 1)}">다음</a></li>
					</c:if>
				</ul>
			</div>
		</section>
	</div>
	<a id="footer"></a>
</body>

	<script>
	
	//브라우저 뒤로가기하면 리스트 새로고침
	window.onpageshow = function(event) {
		if (event.persisted
				|| (window.performance && window.performance.navigation.type == 2)) {
			// Back Forward Cache로 9 브라우저가 로딩될 경우 혹은 브라우저 뒤로가기 했을 경우 하고싶은거 밑에 쓰세용
			document.location.reload();
			//저는 새로고침하게 만들어놨어유
		}
	}
     
   </script>

	<script src="${pageContext.request.contextPath}/js/topNbottom.js" charset="UTF-8"></script>
	
</html>