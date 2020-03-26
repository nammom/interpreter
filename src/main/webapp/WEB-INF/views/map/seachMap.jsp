<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	    <meta charset="utf-8">
	    <title>  1MAP SEARCH  </title>
	    <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
	    <script type="text/javascript" src="http://jsgetip.appspot.com"></script>
	    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=187df647b367c95dcb90f3336f3e852b&libraries=clusterer"></script>
	    
	    <style>
	    
	    
	    #search{ 
		width:300px;
		height:200px;
		position: relative;
		left: 10px;
		top: -690px;
		}
	    
	    
	    
	    </style>
	    
	    
	</head>
	
		<body>
		  
		  
  		  <div id="tab">
			<input id="guinTab" type="button" value="구인">
			<input id="gujicTab" type="button" value="구직">
		 </div>
		
		  <div id="map" style="width:700px;height:450px;float:left;"></div>

		
		<script>
						
						//화면 로드되면
						
					 	$(window).load(function(){
							guinStart()
						});
				
						

			
						//아이피가져오기
			 
						var centerlat = '';
						var centerlng = '';
					    var clientIp = '';
		    
						$.ajaxSetup({ async: false }); //동기식으로 ajax 실행되게 설정
						console.log("1");
						$.getJSON('http://jsonip.com/', function(data) {
							console.log("2");
							clientIp = data.ip;
							console.log('사용자ip:' + clientIp);
						});
						
						getUrl();
		
						
						
						//스크롤 만들어주는 함수 
						$('#placesList').scroll(function() {
							var scrollT = $(this).scrollTop(); //스크롤바의 상단위치
							var scrollH = $(this).height();    //스크롤바를 갖는 div의 높이
							var contentH = $('#List').height(); //문서 전체 내용을 갖는 div의 높이
					    //  if(scrollT + scrollH +1 >= contentH) {  스크롤바가 아래 쪽에 위치할 때 스크롤  어떻게 처리할건지 구현하는건데 원래 목적은 무한스크롤이였음
						//	 	$("#List").html(itemStr); 	}		지금 필요한 기능이 아닌것 같아서 보류
							 
						});
		
						
						
						
						
						// 사용자IP위치를 중심좌표로 찍는 함수 
						
						function getUrl() {
							console.log("검색");
							console.log("3");
							//var apiKey = 'dabe2a7ae025e14feda9f6af1467228c';
							//var apiKey = 'd0a0b605f0f6eddfe61b6b04ff602d37';	
							var ipAdd = clientIp;
							//var regUrl = 'http://api.ipstack.com/' + ipAdd + '?access_key=' + apiKey;
							var regUrl = 'http://api.ipstack.com/115.90.212.21?access_key=dabe2a7ae025e14feda9f6af1467228c';
							
							console.log(regUrl);
							$.ajax({
								type : "POST",
								url : regUrl,
								dataType : "json",
								async: false,
								success : function(json) {
									console.log("4");
									console.log(json); // 리턴받은 json
									centerlat = json.latitude; // 위도
									centerlng = json.longitude; // 경도
									//console.log(centerlat);
									//console.log(centerlng);
								},
								error : function(xhr, status, error) {
									alert(error);
								}
							});
						};
		
		
			
		
			
			
			//지도 생성
		    var map = new kakao.maps.Map(document.getElementById('map'), { 
		        center : new kakao.maps.LatLng(centerlat, centerlng), // 지도의 중심좌표 : 사용자 아이피를 중심으로
		        minLevel: 2,  // 지도 최대 확대레벨
		        level : 6 	  // 지도의 확대 레벨 
		    });
		    
			
			
			
		    //마커 클러스터러 생성 
		    var clusterer = new kakao.maps.MarkerClusterer({
		        map: map,            // 마커들을 클러스터로 관리하고 표시할 지도 객체 
		        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
		        minLevel: 0,         // 클러스터 할 최소 지도 레벨  *이거랑
		        gridSize: 50,       
		        minClusterSize: 1    // *이거중요 이거 두개를 적용함으로 마커가 정확히 건물 위치에 찍히는것을 숨길 수 있음. 
		    });
		 
		    
		    
		    
		    
		    //----------------------------------------------------------		    
		    
		    
		    function makeMarker(data) {
		    	
		    	clusterer.clear();
		    	 var markers = $(data.positions).map(function(i, position) {
			            return new kakao.maps.Marker({
			                position : new kakao.maps.LatLng(position.lat, position.lng)
			            });
			        });
			        // 클러스터러에 마커들을 추가합니다
			        clusterer.addMarkers(markers);
			        
			    };
			    
			    
			    
			    
			    
			//----------------------------------------------------------
			
		    //리스트에 띄울 목록 제이슨으로 가져올 준비
			function getLl(message) {
				// 지도 영역정보를 얻어오기
				var bounds = map.getBounds();
				// 영역정보의 남서쪽 정보 얻어오기
				var swLatlng = bounds.getSouthWest();
				// 영역정보의 북동쪽 정보 얻어오기
				var neLatlng = bounds.getNorthEast();
				// 남서쪽,북동쪽 각각의 위도 경도 얻어오기
				var swLat = swLatlng.getLat();
				var swLng = swLatlng.getLng();
				var neLat = neLatlng.getLat();
				var neLng = neLatlng.getLng();
				// 현재 지도 영역에 포함되는 글만 갖고오게 범위가 될 파일을 준비(컨트롤러에가서 JSON이 되고 JSON을 HashMap으로 변경됨)
				
				var lldata = '{"message":"' + message +'","swLat":"'+ swLat +'","swLng":"'+ swLng +'","neLat":"'+neLat+'","neLng":"'+ neLng +'"}';
				//console.log(lldata);
				mapList(lldata);
				
			};
			
			
			
			
			
			
			//리스트에 현재지도영역에 포함되는 글만 가져오는 함수
			function mapList(lldata) {
				console.log("계속!"); 
				console.log("제이슨도착~" + lldata);
			    $("#placesList").scrollTop(0);   // 스크롤 항상 위로 지정
				   $.ajax({
					   url: "${pageContext.request.contextPath}/map2",
					   type: "POST",
					   data: lldata,
					   contentType: "application/json",
					   dataType: "json",
					   success: function(data) {
						     console.log(data);
					    	 var list = document.getElementById("List");
					    	 var itemStr = '';
					    	 //리스트모양만들기(html코드생성)
					    	 for(i = 0; i < data.list.length; i++){ 
						          itemStr += '<div class="info">' +
						                      '<b><small>' + data.list[i].writercode + '</b></small><br>' ;
						           //ebcode라면, wbcode라면 조건 추가해서 밑에 #링크 거는거 판단하게 수정 
						          itemStr += '<span><a href="#">' + data.list[i].title + '</a></span>';  //게시글 연결 
						          itemStr += '<span class="date">' + data.list[i].startdate + '~' + data.list[i].enddate + '</span>' + '</div>';
					    	 }  
					    	 //console.log(itemStr);
					    	 $("#List").html(itemStr);  
					   	},
					    	error: function(errorThrown) {
					    	console.log(errorThrown.statusText);
					    }
				   	});  //ajax
						 
				   var get = document.getElementById("get");  //검색하기버튼
					get.onclick = function() {				   //이 클릭되면 
						asyncSend();						   //검색어에 맞는 위도경도 찾아서 움직임
					}
				};
			
		   
				
			//----------------------------------------------------------
			
			//검색어 제이슨파일 검색 준비
			function getXHR() {
				var req;
				try {
					req = new XMLHttpRequest();
				} catch (e) {
					try {
						req = new ActiveXObject("Msxm12.XMLHTTP");
					} catch (e) {
						req = new ActiveXObject("Microsoft.XMLHTTP");
					}
				}
				return req;
			};
		
			
			
			//검색어 제이슨파일에서 검색
			function asyncSend() {
				var req = getXHR();
		
				req.onreadystatechange = function() {
					if (req.readyState == 4) {
						if (req.status == 200) {
							jsonData = JSON.parse(req.responseText);
							for (i = 0; i < jsonData.city.length; i++) {
								if (jsonData.city[i].읍면동 == $('#q').val()) {    //#q : 검색어 
									lat = jsonData.city[i].위도;
									lng = jsonData.city[i].경도;
									var moveLatLon = new kakao.maps.LatLng(lat, lng);
									panTo(moveLatLon);
									break;
								}
							}
		
						}
					}
				}
				req.open('GET', 'seachJson/city.json');     //이 파일에서 찾겠다.
				req.send(null);
			};
		
			
			//----------------------------------------------------------
			
			
			//지도 중심 부드럽게 변경해주는 함수
			function panTo(moveLatLon) {
				map.panTo(moveLatLon);
			};
			
			
			
		
			
			
									 // 지도가,   '움직이면 함수실행',  움직인 좌표에 맞는 리스트 다시 가져옴
			kakao.maps.event.addListener(map, 'bounds_changed', getLl);
				
			
	 
			//구직탭 클릭시----------------------------------------------------------
			
		
			$("#gujicTab").click(function(){
				
				var message = "2.2";
			    $.ajax({
				 url: "${pageContext.request.contextPath}/map1",
				 type: "POST",
				 data: message,
				 contentType: "application/String",
				 dataType: "json",
				 success:  function(json) {
					      makeMarker(json);
			    		  getLl("2.2");
				 },
				 error: function(errorThrown) {
				 console.log(errorThrown.statusText);
				    }
				})  	
    		});
			
			
			//구인탭 클릭시----------------------------------------------------------	
			
			
			$("#guinTab").click(function(){
				guinStart();
		    });
				
			
			//시작시 구인탭으로 시작하기 위해 빼놓음------------------------------------------
			
			
			function guinStart(){	
			var message = "1.1";
		    $.ajax({
			 url: "${pageContext.request.contextPath}/map1",
			 type: "POST",
			 data: message,
			 contentType: "application/String",
			 dataType: "json",
			 success:  function(json) {
				      makeMarker(json);
		    		  getLl("1.1");
			 },
			 error: function(errorThrown) {
			 console.log(errorThrown.statusText);
			    }
			})  	
		};
			
			
									 
									 
		</script>
		
		
		
		<!-- 리스트를 띄어줄 div -->
		<div id="placesList" style="width:300px;height:450px;border:1px solid black;float:left;overflow-y:scroll;">
			 <h3>이 지역 공고글</h3>
			 <div id="List" style="height: auto; width: 100%; border-top:1px solid black;">
			 </div>
		</div>
		
		<div id="search">
		    <input type="text" name="q" id="q" placeholder="동 검색" size="30">
			<input id="get" type="button" value="검색하기">
		</div>
	
		
		
	</body>
</html>
