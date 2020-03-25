/*
 *top버튼 bottom버튼 스크립트
 */


	$(function() {
	        $(window).scroll(function() {
	            if ($(this).scrollTop() > 250) {   // 스크롤 위치 지정된 곳 보다 위라면
	                $('#TopButton').fadeIn(); 		//버튼이 나타남
	                $('#BottomButton').fadeIn();
	            } else {
	                $('#TopButton').fadeOut();		//버튼이 숨겨짐
	                $('#BottomButton').fadeOut();
	            }
	        });
	        
	        $("#TopButton").click(function() {		//top 클릭시
	            $('html, body').animate({
	                scrollTop : 0					
	            }, 400);
	            return false;
	        })
	        
	        var scrollHeight = $(document).height();	
	        $("#BottomButton").click(function() {    //bottom 클릭시
	            $('html, body').animate({
	                scrollTop : scrollHeight
	            }, 400);
	            return false;
	        })
	    });
 
 
 
	