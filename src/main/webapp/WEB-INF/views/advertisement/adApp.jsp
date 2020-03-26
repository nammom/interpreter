<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>광고신청</title>

<style>
#foo{
	width: 160px;
  	height: auto;
}
</style>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>

 <c:if test="${category == 'employee' || category == 'employer'}">

 <form action = "adAppSuccess1">	
 	<input type = "hidden" name = "userCode" value= "${userCode}">
 	<input type = "hidden" name = "adcategory" value= "${category}">
 	
 	<label for = "link">게시글코드</label>
 	<input type = "text" name = "link" required/>
	<br/><br/>
 	
 	<label for = "title">게시글제목</label>
 	<input type = "text" name = "title" required/>
 	<br/><br/>
 	
 	<label for = "content">건의사항</label>
 	<input type = "textarea" name = "content"/>
 	<br/><br/>
 	
 	<input type = "submit" value = "등록">
 </form>
 </c:if>
 
  <c:if test="${category == 'company'}">
  <form action = "adAppSuccess2" enctype="multipart/form-data"
  method = "POST" onsubmit = "return check();">
 	<input type = "hidden" name = "userCode" value= "${userCode}">
 	<input type = "hidden" name = "adcategory" value= "${category}">

 	<label for = "enterprise">업체명</label>
 	<input type = "text" name = "enterprise" required/>
 	<br/><br/>
	
	<label for = "enterprise">업체로고</label>
 	<input type='file' id="imgInp" name = "file" required/>
	<br/><br/>
        <img id="foo" src="#"/>
 	<br/><br/>
 	
 	<label for = "link">업체 사이트</label>
 	<input type = "text" name = "link" required/>
 	<span id = "linkCheck"></span>
	<br/><br/>
 	
 	<label for = "adDate">광고 기간</label>
 	<input type = "number" name = "adDate" required/>일
 	<br/><br/>
 	
 	<label for = "phone">전화번호</label>
 	<input type = "text" name = "phone1" onchange = "makePhone()" value = "010" required/>
 	-<input type = "text" name = "phone1" onchange = "makePhone()" required/>
	-<input type = "text" name = "phone1" onchange = "makePhone()" required/>
	<span id = "phoneCheck"></span>
 	<input type = "hidden" id = "phone" name = "phone"/>
 	<br/><br/>
 	
 	<label for = "email">이메일</label>
 	<input type = "email" name = "email" required/>
 	<span id = "emailCheck"></span>
	<br/><br/>
 	
 	<label for = "content">건의사항</label>
 	<input type = "textarea" name = "content"/>
 	<br/><br/>
 	
 	<input type = "submit" value = "등록">
 </form>
 </c:if>
  	<br/><br/>

  
 <script>
    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function(e) {
            	$('#foo').attr('src', e.target.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }

    $("#imgInp").change(function() {
    	console.log(this.value);
        readURL(this);
    });
    
    function makePhone(){
    	var phoneNum = document.getElementsByName("phone1");
    	var phoneVal = phoneNum[0].value + phoneNum[1].value + phoneNum[2].value;
    	$("#phone").val(phoneVal);
    }
    
    function check(){
    	
    	var link = $("input[name = link]").val();  	
    	var phone = $("input[name = phone]").val();
    	var linkRule = /(http(s)?:\/\/)([a-z0-9\w]+\.*)+[a-z0-9]{2,4}/gi;
    	var phoneRule = /^\d{11}$/;

    	if(!phoneRule.test(phone)||!linkRule.test(link)){
    		if(!phoneRule.test(phone)){
    			$("#phoneCheck").html("올바른 휴대폰 번호 형식이 아닙니다.");
    		}
    		if(!linkRule.test(link)){
    			$("#linkCheck").html("올바른  url 형식이 아닙니다.");
    		}
    	  	return false;
     	}else{
    		return true;
    	} 		
    }
    
   </script>

</body>
</html>