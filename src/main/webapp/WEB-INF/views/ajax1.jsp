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
<button id="test1" type="button">버튼1</button>
<div id="responseVal">[[ ${samp }]]</div>
	<script>
	$(function() {
		$.ajax({
			url : "ajax1",
			type : "post",
			dataType : "json",
			success:function(data){
				console.log(data);
				
				// click event
				
			},
			 error : function(request, status, errorData){
				 /* 거의 공식 */
			 alert("error code : " + request.status + "\n" 
					 + "message : " + request.responseText + "\n" 
					 + "error : " + errorData); 
					 } 
	});
		
		$("#test1").on("click", function(){ 
			$.ajax({ 
			 url : "test1.do",  
			 data : { 
				 /* vo에 실려질 것들 */
				 name : "신사임당", /* 예전에는 request.getparameter("name")으로 꺼냄 */
				 age : 47 /* name, age와 같은 key에는 ""이걸 써도되고 안써도 된다.   */
				 }, 
			 type : "post", 
			 success : function(result){ 
				 console.log("result : " + result);
				 console.log("smap : " + "${samp}")
				 // ajax 결과를 바로 보고 싶으면 js page refresh하는 코드를 써야한다. 근데 이러면 ajax를 쓰는 이유가 없어짐
			 if(result == "ok"){ /* 참고로 JS에서 equals는 없다. */
			 alert("전송 성공!"); 
			 } else 
			 alert("전송 실패!"); 
			 }, 
			 error : function(request, status, errorData){
				 /* 거의 공식 */
			 alert("error code : " + request.status + "\n" 
					 + "message : " + request.responseText + "\n" 
					 + "error : " + errorData); 
					 } 
					 }); 
					}); 
	</script>
</body>
</html>