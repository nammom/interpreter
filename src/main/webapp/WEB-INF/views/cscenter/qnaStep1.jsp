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
				  <form action="${pageContext.request.contextPath}/qnaStep2" method="POST" enctype="multipart/form-data">
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
				            <input type="text" name="complain" disabled >
				            <input type="button" id="usercodeCheck" value="회원확인" disabled><br>
				              첨부파일     
				              <input multiple="multiple" type="file" name="uploadFile" /><br>
				              <br>
				              <input type="submit" value="등록">
		  		 </form>
		   </section>
		    <nav>
		        <ul>
		            <li><a href='${pageContext.request.contextPath}/noticeList'>공지사항</a></li>
					<li><a href='${pageContext.request.contextPath}/tos'>이용약관</a></li>
					<li><a href='${pageContext.request.contextPath}/qnaStep1'>1:1문의</a></li>
		        </ul>
		    </nav>
		    
		    
	<script>
		
		//닉네임칸 활성화
		$('#inquirytype').change(function(){
			if($("select[name=inquirytype]").val() == "불만 및 신고"){
				$("input[name=complain]").attr('disabled', false);
				$('#usercodeCheck').attr('disabled', false);
			}else {
				$("input[name=complain]").attr('disabled', true);
				$('#usercodeCheck').attr('disabled', true);
			}
		});
		
		
		//닉네임 중복확인
		$('#usercodeCheck').click(function(){
			var userCode = $("input[name=complain]").val();
			console.log("확인 할 닉네임 :"+userCode);
			$.ajax({
				url : "${pageContext.request.contextPath}/cs/usercodeCheck",
				type : "POST",
				data : userCode,
				contentType : "application/String",
				dataType : "text",
				success : function(data){
					console.log("온 data"+data)
					if(data == 0 ){
						alert("존재하지 않는 회원입니다.")
						$("input[name=complain]").val('');
					} 
				}
			})		
		});
		
		
		
		
	</script>
	
		    
</body>

</html>
