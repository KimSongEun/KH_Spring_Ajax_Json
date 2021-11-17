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
<button type="button" id="test3">테스트3</button>
<div id="d3"></div>
	<script>
		$("#test3").on("click",
						function() {
							// 컨트롤러로 부터 리스트를 받아서 출력한다 
							$.ajax({
										/*  가지고 들어가는 건 없고 들고 들어오는 데이터만 있다.  */
										url : "test3.do",
										type : "post",
										dataType : "json",
										success : function(data) {
											// 전달받은 data를 JSON 문자열 형태로 바꾼다 
											var jsonStr = JSON.stringify(data);
											// 바꾼 문자열을 json 객체로 변환한다 
											var json = JSON.parse(jsonStr);
											console.log(data);
											console.log(jsonStr);
											console.log(json);
											var values = $("#d3").html();

											for ( var i in json.list) {
												values += json.list[i].userId
														+ ", "
														+ json.list[i].userPwd
														+ ", "
														+ decodeURIComponent(json.list[i].userName)
														+ ", "
														+ json.list[i].age
														+ ", "
														+ json.list[i].email
														+ "<br>";
											}

											// values에 담은 값을 d3이라는 id의 div에 출력한다. 
											$("#d3").html(values);
										},
										error : function(request, status,
												errorData) {
											alert("error code : "
													+ request.status + "\n"
													+ "message : "
													+ request.responseText
													+ "\n" + "error : "
													+ errorData);
										}
									});
						});
	</script>
</body>
</html>