<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<button type="button" id="test6">테스트6</button>
<div id="d6"></div>
<br>
<button type="button" id="testMyPick">테스트MyPick</button>
<div id="d6"></div>

<!-- frm1은 action method 없음. why~ ajax 전송예정 -->
	<form id="frm1">
		<input type="text" name="name">
		<input type="text" name="age">
	</form>
	<button type="button" id= "frmaction">테스트frm</button>
	
	
	<script>
	
	$("#frmaction").on("click", function(){
		/* form 태그 안쪽으로 가, 그리고 그 안에 name으로 되어있는 것들을 직렬화 시켜줘. 이렇게 해주면 form태그 안의 것들이 자바스크립트 오브젝트 모양으로 툭 튀어나온다. */
		var dataquery = $("#frm1").serialize();
		console.log(dataquery);
		$.ajax({
			url : "test8.do",
			data : dataquery, 
			type : "post",
			success : function(data){
				console.log(data);
			},
			error : function(request, status, errorData){ 
				 alert("error code : " + request.status + "\n" 
				 + "message : " + request.responseText + "\n" 
				 + "error : " + errorData); 
				 } 
		});
	});
	
	$("#testMyPick").on("click", function(){ 
		 // 자바스크립트에서 jsonArray 객체를 만들어서, 서버 컨트롤러로 보내기 
		 var jArray = [{"name" : "이 이", "age" : 30 }, 
		 {"name" : "신사임당", "age" : 47}, 
		 {"name" : "황진이", "age" : 25}]; 
		 
		 $.ajax({ 
		 url : "test7.do", 
		 data : JSON.stringify(jArray), 
		 type : "post", 
		 contentType : "application/json; charset=utf-8", 
		 dataType : "json",
		 success : function(result){ 
	    	  console.log("7: "+ result);
	    	  console.log(result);
		 alert("전송성공!"); 
		 var values = $("#d6").html(); 
		 for(var i in jArray){ 
		 values += "이름 : " + jArray[i].name + ", 나이 : " + jArray[i].age + "<br>"; 
		 } 
		 
		 $("#d6").html(values); 
		 }, 
		 error : function(request, status, errorData){ 
		 alert("error code : " + request.status + "\n" 
		 + "message : " + request.responseText + "\n" 
		 + "error : " + errorData); 
		 } 
		 }); 
		});
	
	$("#test6").on("click", function(){ 
		 // 자바스크립트에서 jsonArray 객체를 만들어서, 서버 컨트롤러로 보내기 
		 var jArray = [{"name" : "이 이", "age" : 30 }, 
		 {"name" : "신사임당", "age" : 47}, 
		 {"name" : "황진이", "age" : 25}]; 
		 
		 $.ajax({ 
		 url : "test6.do", 
		 data : JSON.stringify(jArray), 
		 type : "post", 
		 contentType : "application/json; charset=utf-8", 
		 success : function(result){ 
			 console.log("6 : " + result);
		 alert("전송성공!"); 
		 var values = $("#d6").html(); 
		 for(var i in jArray){ 
		 values += "이름 : " + jArray[i].name + ", 나이 : " + jArray[i].age + "<br>"; 
		 } 
		 
		 $("#d6").html(values); 
		 }, 
		 error : function(request, status, errorData){ 
		 alert("error code : " + request.status + "\n" 
		 + "message : " + request.responseText + "\n" 
		 + "error : " + errorData); 
		 } 
		 }); 
		});
	</script>
</body>
</html>