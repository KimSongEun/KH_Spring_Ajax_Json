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
<button type="button" id="test5">테스트5</button>
<div id="d5"></div>
	<script>
	$("#test5").on("click", function(){ 
		 // 자바스크립트에서 json 객체를 생성해 서버 컨트롤러로 전송한다 
		 var job = new Object(); 
		 job.name = "강감찬"; 
		 job.age = 33; 
		 
		 $.ajax({ 
		 url : "test5.do", 
		 data : JSON.stringify(job),
		 type : "post", 
		 contentType : "application/json; charset=utf-8", 
		 success : function(result){ 
			 console.log(result); // success로 들어옴
		 alert("전송 성공!"); 
		 $("#d5").html("전송한 json 객체의 값 : " + job.name + ", " + job.age); 
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