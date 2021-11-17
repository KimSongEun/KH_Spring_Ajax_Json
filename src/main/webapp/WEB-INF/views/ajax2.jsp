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
<button type="button" id="test2">테스트2</button>
<div id="d2"></div>
	<script>
	/* 전달되는 값이 없다. 여기말한건지 ajax1 akfgksrjswl qhrl */
	$("#test2").on("click", function(){ 
		 // test2.do 로부터 json 객체를 리턴받아, div 에 출력한다 
		 $.ajax({ 
		 url : "test2.do", 
		 type : "post", 
		 dataType : "json", /* 아래의 data 모양이 json일 것이다.  */
		 success : function(data){ 
		  // 두 console의 값이 다르다. 
		  console.log("data : " + data); // String에 연결되어서 data도 String으로 인지하고 표현해주기 때문, 그래서 표현이 잘 안된다. 
		  console.log(data); // 혼자 독단적으로 뿌려주면 된다. 그래서 크롬이 위의 것은 못뿌려주겠어서 그냥 object로 나온 것이고 이렇게 해야 제대로 나온다. 
		 // 전달받은 JSONObject에 담은 Value를 Key로 접근하여 출력한다 
		 $("#d2").html("번호 : " + data.no 
		 + "<br>제목 : " + data.title 
/* 		 + "<br>작성자 : " + decodeURIComponent(data.writer) 
		 + "<br>내용 : " + decodeURIComponent(data.content.replace(/\+/g, " ")));  */
		 + "<br>작성자 : " + data.writer
		 + "<br>내용 : " + decodeURIComponent(data.content.replace(/\+/g, " "))); 
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