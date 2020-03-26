<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<!-- �������� -->
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<!-- �������� �ּ�ȭ�� �ֽ� CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- �ΰ����� �׸� -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- �������� �ּ�ȭ�� �ֽ� �ڹٽ�ũ��Ʈ -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<title>�� �� �� �� �� �� �� ��</title>


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

	<a id="TopButton" href="#"><img src="${pageContext.request.contextPath}/imagefile/top.png"></a>
	<a id="BottomButton" href="#"><img src="${pageContext.request.contextPath}/imagefile/bottom.png"></a>

	<div id="root">
		<header>
			<h1>�����Խ���</h1>
		</header>
		<section id="container">
			<form method="post"
				action="${pageContext.request.contextPath}/delete/${freeBoardVo.freecode}"
				name="removefrm">
				
				<div class="form-group">
					<label for="bno" class="col-sm-2 control-Label">�� ��ȣ</label>
					<div class="col-sm-10">
					 <input type="text" id="bno" name="bno" class="form-control" value="${freeBoardVo.freecode}" readonly="readonly" />
					</div>
				</div>
				
				<div class="form-group">
					<label for="title" class="col-sm-2 control-Label">�� ����</label>
					<div class="col-sm-10">
						<input type="text" id="title" name="title" class="form-control" value="${freeBoardVo.title}" readonly="readonly" />
					</div>
				</div>
				
				<div class="form-group">
					<label for="content" class="col-sm-2 control-Label">�� ����</label>
					<div class="col-sm-10">
					<br> ${freeBoardVo.content}
					</div>
				</div>
				
				<p>
					<label for="writer">�ۼ���</label> <input type="text" id="writerAtc"
						name="writer" value="${freeBoardVo.writercode}"
						readonly="readonly" /><br /> <label>�ۼ� ��¥</label> <span>${freeBoardVo.regdate}</span>
				</p>
				<button id="editAtc">
					<a href="<c:url value="/edit/${freeBoardVo.freecode}" />">�����ϱ�</a>
				</button>
				<input id="delAtc" type="button" value="�����ϱ�"
					onclick="removeCheck()">
			</form>
		</section>
	</div>

	<div class="container">
		<form id="commentForm" name="commentForm" method="post">
			<input type="hidden" id="articleCode" name="articleCode"
				value="${freeBoardVo.freecode}"> <br> <br>
			<div>
				<div>
					<span><strong>Comments</strong></span> <span id="cCnt"></span>
				</div>
				<div>
					<table class="table">
						<tr>
							<td><textarea style="width: 700px" rows="3" cols="30"
									id="comments" name="comments" placeholder="����� �Է��ϼ���"></textarea>
								<br>
								<div>
									<input id="addComments" type="button" value="��� ���"
										onclick="addComment()">
								</div></td>
						</tr>
					</table>
				</div>
			</div>
		</form>
	</div>
	<div class="container">
		<form id="commentListForm" name="commentListForm" method="post">
			<div id="commentList"></div>
		</form>
	</div>

</body>

<script>

	$(window).load(function() {
		atcidcheck()
		getCommentList()
	});

	
	

	//���α� ����,������ư ����
	function atcidcheck() {
		var id = "${myuserCode}";
		var writer = $('#writerAtc').val();
		console.log(id);
		console.log(writer);
		if (id == writer) {
			$('#editAtc').show();
			$('#delAtc').show();
		} else {
			$('#editAtc').hide();
			$('#delAtc').hide();
		}
	};

	function removeCheck() {

		if (confirm("���� �����Ͻðڽ��ϱ�?") == true) { //Ȯ��
			document.removefrm.submit();
		} else { //���
			return false;
		}
	};

	/* ��� ����ϱ�(Ajax)*/
	function addComment() {
		var articleCode = $('#articleCode');
		var comments = $('#comments');

		var obj = {
			"articleCode" : articleCode.val(),
			"comments" : comments.val()
		};
		obj = JSON.stringify(obj);

		$.ajax({
			type : 'POST',
			url : '${pageContext.request.contextPath}/freeboard/addComment',
			data : obj,
			contentType : "application/json",
			success : function(data) {
				if (data == "success") {
					getCommentList();
					$("#comment").val("");
				}
			},
			error : function(request, status, error) {
				alert("code:" + request.status + "\n" + "message:"
						+ request.responseText + "\n" + "error:" + error);
			}

		});
	}

	
	/* ��� �ҷ�����(Ajax)*/
	function getCommentList() {
		var articleCode = $('#articleCode');

		$
				.ajax({
					type : 'post',
					url : '${pageContext.request.contextPath}/freeboard/commentList',
					dataType : "json",
					data : articleCode.val(),
					contentType : "application/String",
					success : function(data) {

						var html = "";
						var cCnt = data.length;

						if (data.length > 0) {
							var arr = new Array();
							for (i = 0; i < data.length; i++) {

								html += "<div>";
								html += "<div><table class='table'><h6><strong>"
										+ data[i].userCode + "</strong></h6>";
								html += "<div id='comment" + [i] + "' value='" + data[i].num + "'>"
										+ data[i].comment
										+ "</div><tr><td></td></tr>";

								arr.push(data[i].userCode);
								for (var j = i; j < arr.length; j++) {
									var id = "${myuserCode}";
									var a = arr[j];
									console.log('�۾���' + id);
									console.log(a);
									if (id == a) {
										html += "<button id='edit"
												+ [ i ]
												+ "' type='button' name='"
												+ data[i].num
												+ "' value='"
												+ data[i].comment
												+ "' onclick='modifyCmt(this)'>����</button>";
										html += "<button id='del"
												+ [ i ]
												+ "' type='button' value='"
												+ data[i].num
												+ "' onclick='deleteCmt(this)'>����</button>";
										html += "</table></div>";
										html += "</div>";
										break;

									} else if (id != a) {
										html += "</table></div>";
										html += "</div>";
									}

								}

							}

						} else {

							html += "<div>";
							html += "<div><table class='table'><h6><strong>��ϵ� ����� �����ϴ�.</strong></h6>";
							html += "</table></div>";
							html += "</div>";

						}

						$("#cCnt").html(cCnt);
						$("#commentList").html(html);

					},
					error : function(request, status, error) {

					}

				});
	}

	/*�ڱⰡ �� ��۸� ���� ���� �� �� �ֵ��� Ȯ���ϴ� �Լ�*/
	function idcheck() {
		var id = "${myuserCode}";
		var a = $('.writer').val();
		console.log(id);
		console.log(a);
		if (id == a) {
			$('#edit').show();
			$('#del').show();
		} else {
			$('#edit').hide();
			$('#del').hide();
		}
	};

	//��� ���� ���� �Լ�
	function modifyCmt(e) {
		var comment = e.value; //��۳���
		var name = e.id; // �� ��ư�� id 
		var commentId = name.replace('edit', 'comment');

		var editSucessId = name.replace('edit', 'editSucess');
		var textareaId = name.replace('edit', 'textarea');
		$('#' + name).hide();
		$("#" + commentId).html(
				"<textarea id='test'>" + comment + "</textarea><button id='"
						+ editSucessId
						+ "' onclick='editSucess(this)'>���</button>");

	}

	//��� ���� �Ϸ� �Լ�
	function editSucess(e) {

		var newCmt = $('#test').val();//���� �ۼ��� ��� ����

		var str = e.id;
		var i = str.substring(10);
		var num = $('#del' + i).val();//���Ĺ�ȣ

		var obj = {
			"num" : num,
			"comments" : newCmt
		};
		obj = JSON.stringify(obj);
		var url = '${pageContext.request.contextPath}/freeboard/editComment';
		editDel(url, obj);
	}

	//��� ���� �Լ�
	function deleteCmt(e) {
		var num = e.value;//���Ĺ�ȣ

		var obj = {
			"num" : num
		};

		obj = JSON.stringify(obj);
		var url = '${pageContext.request.contextPath}/freeboard/deleteCmt';
		editDel(url, obj);

	}

	function editDel(ajaxUrl, ajaxData) {

		$.ajax({
			type : 'POST',
			url : ajaxUrl,
			data : ajaxData,
			contentType : "application/json",
			success : function(data) {
				if (data == "success") {
					getCommentList();
					/* $("#comment").val(""); */
				}
			},
			error : function(request, status, error) {
				alert("code:" + request.status + "\n" + "message:"
						+ request.responseText + "\n" + "error:" + error);
			}

		});

	}
	
	
</script>

<script src="${pageContext.request.contextPath}/js/topNbottom.js" charset="UTF-8"></script>

</html>