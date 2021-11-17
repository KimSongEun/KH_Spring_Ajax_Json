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
<button type="button" id="test4">테스트4</button>
<div id="d4"></div>
	<script>
		$("#test4").on("click", function(){ 
		 // 컨트롤러에서 맵 객체를 jsonView를 사용해 json 객체로 리턴받아서 출력 처리 
		 $.ajax({ 
		 url : "test4.do", 
		 type : "post", 
		 dataType : "json", 
		 success : function(data){ 
			 console.log(data);
		 $("#d4").html("받은 맵 안의 samp 객체 정보 확인<br>" 
		 + "이름 : " + decodeURIComponent(data.samp.name) 
		 + ", 나이 : " + data.samp.age); 
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