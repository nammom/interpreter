<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script
  src="https://code.jquery.com/jquery-2.2.4.min.js"
  integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
  crossorigin="anonymous"></script>

<script src = "${pageContext.request.contextPath}/js/address.js"></script>

</head>
<title>소개서 작성</title>
</head>
<body>
	<c:if test = "${category == 'employee'}">
		<h1>알바생 소개서 수정하기</h1>
	</c:if>
	<c:if test = "${category == 'employer'}">
		<h1>사장님 소개서 수정하기</h1>
	</c:if>
	
  <form action = "${pageContext.request.contextPath}/writeIntro/${category}" 
  		method = "POST"
  		onsubmit = "return submitCheck();">
  		
  	<input type ="hidden" name = "userCode" value = "${myuserCode}"/>
  	희망 근무지
  	<br/>
	<select name="sido1" id="sido1" required></select>
	<select name="gugun1" id="gugun1" required></select>
	<input type = "hidden" name = "workspace"/>
	<br/><br/>
	
	<c:if test = "${category == 'employee'}">
		경력사항
		<button type = "button" id = "plusCareer">+</button>
		<button type = "button" id = "minusCareer">-</button>
		<ul class = "career">
		<c:forEach var = "cc" items="${career}" varStatus = "Loop">
			<li><input type = "text" name = "company" value = "${cc.company}"/>
			<input type = "text" name = "startDate" value = "${cc.startDate}"/>
			<input type = "text" name = "endDate" value = "${cc.endDate}"/></li>
		</c:forEach>
		</ul>
		
	</c:if>

	<br/>
  	<label>자기 소개 <br/><br/>
  		<input type = "textarea" id = "contents" name = "contents" 
  		value = "${intro.contents}" style="width:400px;height:100px" required>
  		
  	</label>
  	
  	<br/>
  	<button type = "button" id = "close">취소</button>
  	<button type = "button" id = "submit">등록</button>
  </form>

<script>
		var ajaxUrl ='';
		if(${category=='employee'}){
	 		ajaxUrl = "${pageContext.request.contextPath}/myPageEe";
		}else{
			ajaxUrl = "${pageContext.request.contextPath}/myPageEr";
		} 
	
		

		 $("#plusCareer").click(function(){
			 var check = '1';	
			 editCareerForm(check);
		 });
		
		 $("#minusCareer").click(function(){
			 var check = '0';	
			 editCareerForm(check);
		 });
		 
		 $("#close").click(function(){
			 window.location.replace(ajaxUrl);
		 });
		 
		 $("#submit").click(function(){
			 submitCheck();/* 전송메서드 */
		 });
		 


	
	 function editCareerForm(e){
		 var editForm = '';
		 if(e =='1'){
			 editForm = '<li><input type = "text" name = "company" placeholder = "회사명">';
			 editForm += '<input type = "text" name = "startDate" placeholder = "재직 시작 일자">';
			 editForm += '<input type = "text" name = "endDate" placeholder = "재직 종료 일자"></li>';

			 $(".career").append(editForm);
		 }else{
			 if($(".career").children().size() != 0){
					$(".career").children().last().remove();		 	
			 }
		 }
	 }
	 
	 function submitCheck(){
			if($("#sido1").val().length == 0 || $("#gugun1").val().length == 0 || $("#contents").val().length == 0){
					alert("빈항목을 입력해주세요")
					if($("#sido1").val().length == 0){
						$("#sido1").focus();
					}else if($("#gugun1").val().length == 0){
						$("#gugun1").focus();
					}else{
						$("#contents").focus();
					}
					return ;
			}
			
			else{
	 			var adr = $("#sido1").val() + " " + $("#gugun1").val(); 
				$("input[name = workspace]").val(adr);
				
				var CareerList = new Array() ;
				
				var workspace = $("input[name = workspace]").val();
				var contents = $("#contents").val();
				
				var data = new Object();
				 
				 data.userCode = "${myuserCode}";
		         data.category = "${category}";
		         data.openCheck = "${openCheck}";  
		         data.workspace = workspace;
		         data.contents = contents;
				
		    	 CareerList.push(data) ; 

		         var Csize = $(".career").children().size();
				
				 if(Csize != 0){
					alert(Csize);	 
					 for(var i=0 ; i< Csize; i++){
						 var company = document.getElementsByName("company")[i].value;
						 var start = document.getElementsByName("startDate")[i].value;
						 var end = document.getElementsByName("endDate")[i].value;
						 
						 if(company == '' || start == '' || end == ''){
							 alert("경력사항 빈항목을 채워주세요");
							 if(company == ''){
								 document.getElementsByName("company")[i].focus();
							 }else if( start == ''){
								 document.getElementsByName("startDate")[i].focus();
							 }else{
								 document.getElementsByName("endDate")[i],focus();
							 }
							 return ;
						 }
						 
						 console.log(company+start+end);
						 var data = new Object();
						 
						 data.company = company;
				         data.startDate = start;
				         data.endDate = end;  
				            // 리스트에 생성된 객체 삽입
				            CareerList.push(data) ;
				        }		     		        
				        
			} 
				
				 submitSuccess(CareerList);
		 }
		}
		 
		 function submitSuccess(introList){
			 var jsonData = JSON.stringify(introList);   
			 alert(jsonData);
			 $.ajax({
				  type : "POST",
				  url : "${pageContext.request.contextPath}/editSuccess/${category}",
				  data : jsonData,
				  contentType: "application/json",
				  success : function(data) {  
				  	alert("ajax성공")
				  	var ajaxUrl = "${pageContext.request.contextPath}/" + data;
				  	window.location.replace(ajaxUrl);
				  }
			});	
		 }
		 
	
	 
	 function workspaceSet(){
		 var targetIndex = '';
		 for(var i =0; i < area0.length ; i++){
			 if(area0[i] == "${workspaceList[0]}"){
				 targetIndex = i;
			 }
		 }
		 
		 sido();
		 $('option[value=${workspaceList[0]}]').attr('selected', 'selected');
		 changeGugun(targetIndex);
		 $('option[value=${workspaceList[1]}]').attr('selected', 'selected');
	}
	 
	workspaceSet();



</script>
</body>
</html>
