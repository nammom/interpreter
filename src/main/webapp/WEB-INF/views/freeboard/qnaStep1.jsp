<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>새 글 쓰기</title>
		<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
		
	
	</head>
<body>
		<header>
		 일맺다
		</header>
			<section>
				  <form action="qnaStep2" method="POST">
				  	   <input type="hidden" id="writercode" name="writercode" value="${myuserCode}">
				             제목
				      <input type="text" name="title" required><br>
					     상담유형
					    <select name="inquirytype" id="inquirytype" required>
				            <option value="이용문의">이용문의
				            <option value="불만 및 신고">불만 및 신고
				            <option value="기타">기타
				        </select><br>
				              설명
				            <textarea rows="1" cols="10" name="content" style=
				            width:500px;height:100px;resize:none; required></textarea><br>
				              신고 회원 닉네임
				            <input type="text" name="complain" disabled ><br>
				            <input type="submit" value="등록">
		  		 </form>
		   </section>
		    <nav>
		        <ul>
		            <li><a href='/finallyproject/noticeList'>공지사항</a></li>
					<li><a href='/finallyproject/tos'>이용약관</a></li>
					<li><a href='/finallyproject/qnaStep1'>1:1문의</a></li>
		        </ul>
		    </nav>
		    
		    
	<script>
		
		$('#inquirytype').change(function(){
			if($("select[name=inquirytype]").val() == "불만 및 신고"){
				$("input[name=complain]").attr('disabled', false);
			}else {
				$("input[name=complain]").attr('disabled', true);
			}
		});
		
	</script>
	
		    
</body>

</html>
