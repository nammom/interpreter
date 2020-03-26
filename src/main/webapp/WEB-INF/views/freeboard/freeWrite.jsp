<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- include libraries(jQuery, bootstrap) -->
<link
	href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css"
	rel="stylesheet">
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script
	src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<!-- include summernote css/js-->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.css"
	rel="stylesheet">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.js"></script>
<!-- include summernote-ko-KR -->
<script src="${pageContext.request.contextPath}/js/summernote-ko-KR.js"></script>
<title>글쓰기</title>
<script>
	$(function() {
		$('#summernote').summernote(
				{
					placeholder : '내용을 입력하세요',
					lang : 'ko-KR',
					minHeight : 370,
					maxHeight : null,
					fontNames : [ '맑은고딕', 'Arial', 'Arial Black',
							'Comic Sans MS', 'Courier New', ],
					fontNamesIgnoreCheck : [ '맑은고딕' ],
					focus : true,

					callbacks: {	//여기 부분이 이미지를 첨부하는 부분
						onImageUpload : function(files) {
							uploadSummernoteImageFile(files[0],this);
						}
					}

				});
		
		/**
		* 이미지 파일 업로드
		*/
		function uploadSummernoteImageFile(file, editor) {
			data = new FormData();
			data.append("file", file);
			$.ajax({
				data: data,
				type: "POST",
				url: "${pageContext.request.contextPath}/uploadSummernoteImageFile",
				contentType: false,
				processData: false,
				dataType: "json",
				success: function(data) {
	            	//항상 업로드된 파일의 url이 있어야 한다.
	            	alert(data);
	            	alert(data.url);
					$(editor).summernote('insertImage', data.url);
				}
			});
		}

	})

</script>
</head>
<body>
	<h2 style="text-align: center;">글 작성</h2>
	<br>
	<br>
	<br>

	<div style="width: 60%; margin: auto;">
		<form method="post" action="freeWrite">
			<input type="hidden" id="writercode" name="writercode" value="${myuserCode}">
			<input type="text" name="title" style="width: 40%;" placeholder="제목" />
			<br> <br>
			<textarea id="summernote" name="content"></textarea>
			<button type="button" style="float: right;"
				onclick="location.href='freeList' ">목록으로</button>
			<input id="subBtn" type="button" value="글 작성" style="float: right;"
				onclick="goWrite(this.form)" />
		</form>
	</div>

	<script>
		function goWrite(frm) {
			var title = frm.title.value;
			var content = frm.content.value;

			if (title.trim() == '') {
				alert("제목을 입력해주세요");
				return false;
			}
			if (content.trim() == '') {
				alert("내용을 입력해주세요");
				return false;
			}
			frm.submit();
		}
	</script>

</body>
</html>