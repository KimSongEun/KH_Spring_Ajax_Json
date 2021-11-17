package com.mycompany.ajaxjson.controller;

import java.awt.image.SampleModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.mycompany.ajaxjson.HomeController;
import com.mycompany.ajaxjson.model.domain.Sample;
import com.mycompany.ajaxjson.model.domain.User;

@Controller
public class TestAjaxController {
	// 누군가 수정할 수 없도록 static final, Logger은 log4j 것을 임포트하는데 이게 인터페이스라 LoggerFactory라는
	// 클래스로 new
	// logger을 계속 new로 쓸 것이냐? 아니라는 것이다. 그래서 특별한 영역에 생성하고 가는 것이다. singleton 이야기 해야함
	// 1개의 인스턴스를 만들어두고 필요할 때 가져가서 쓰면돼, 그래서 new해서 여러개 생성되지 않도록 여기서도 new를 쓰지 않는다.
	// getLogger는 String 쓰는 getLogger와 Class 쓰는 getLogger 가 있다.
	// 여기선 class를 써본다. 여기에 클래스 이름을 작성하면 해당 클래스 안에있는 log를 싹 모아서 해줄게 라는 것이다.
	private static final Logger logger = LoggerFactory.getLogger(TestAjaxController.class);
	// 기존에 만들어져있는 것에 setter로 TestAjaxController.class 이걸 세팅해준다는 이야기이다.
	// 싱글톤으로 클래스 하나 만들어두고 쓰는 것이다.

	@RequestMapping(value="ajax1", method=RequestMethod.GET)
	public ModelAndView ajax1(ModelAndView mv) {
		mv.setViewName("ajax1");
		return mv;
	}
	@RequestMapping(value="ajax1", method=RequestMethod.POST)
	public String ajax1Data() {
//		Map<String, Integer> map = new HashMap<String, Integer>();
//		map.put("k1", 1);
		JSONObject job = new JSONObject();
		job.put("k1",1);
		String result = job.toJSONString();
		return result;
	}
	
	@RequestMapping("ajax2")
	public String ajax2() {
		return "ajax2";
	}

	@RequestMapping("ajax3")
	public String ajax3() {
		return "ajax3";
	}

	@RequestMapping("ajax4")
	public String ajax4() {
		return "ajax4";
	}

	@RequestMapping("ajax5")
	public String ajax5() {
		return "ajax5";
	}

	@RequestMapping("ajax6")
	public String ajax6() {
		return "ajax6";
	}

	@RequestMapping(value = "test1.do", method = RequestMethod.POST) // 파라미터가 하나가 아니라서 이름도 적어서 들어옴
	// test2.do와 같은 방식 하는법 (아래의 '예전 servlet과 같은 방법' 수정)
	@ResponseBody
	public String test1Method(@RequestParam("name") String name, 
			                  @RequestParam("age") int age, 
			                  Sample vo, HttpServletResponse response, HttpSession session) 
							  throws IOException { // 리턴을 String으로 하면 그걸 view 페이지로 인식한다.
		String result = "";
		if (vo.getName().equals("신사임당")) {
			session.setAttribute("samp", vo);
			result = "ok";
		} else {
			result = "fail";
		}
		return result;
	}

	// 예전 servlet 과 같은 방법
//	public void test1Method(Sample vo, HttpServletResponse response, HttpSession session) throws IOException { // 리턴을 String으로 하면 그걸 view 페이지로 인식한다. 
//		// System.out.println("[sekim] test1Method vo : " + vo); // 내가 뿌린 로그인걸 티내기 위해서  이렇게 뿌려주기도함
//		response.setContentType("text/html; charset=utf-8");
//		// PrintWriter 객체를 생성하여 통신에 대한 응답 결과를 전달한다.
//		PrintWriter out = response.getWriter(); // ajax의 result에 채워지는 부분
//
//		System.out.println("vo : " + vo); // 지금 request.getParameter가 없으므로 vo에 실려서 왔다는 이야기이고 그래서 vo에 age도 넣어야한다. (jsp 파일에 따라)
//		if (vo.getName().equals("신사임당")) { // 이걸보고 String name을 vo에 넣어야겠구나 생각
//			session.setAttribute("samp", vo); // 전달되지 않음. 화면 reload 되어야 인지하므로 ajax로는 전달되지 않음. (forward나 redirect로만 전달된다. refresh가 안되면 화면에 안나타난다는 것이다.) 
//			out.append("ok");
//			out.flush();
//		} else {
//			out.append("fail");
//			out.flush();
//		}
//		out.close();
//	}
//------------------------------------------------------------------------------------//
	@RequestMapping(value = "test2.do", method = RequestMethod.POST)
	@ResponseBody // 결과를 response 객체에 담아서 보내는 어노테이션 (append와 out까지 해서 ajax로 전달해주는 어노테이션)
	public String test2Method(HttpServletResponse response) throws IOException {
		response.setContentType("application/json; charset=utf-8"); // 이건 filter 넣어도 써줘야한다.

		JSONObject job = new JSONObject(); // simple Json 것 임포트!! 아래의 toJSONString을 쓰기위해!
		// Map 형식의 JSONObject 객체를 생성하여 출력할 값을 Key와 Value 형태로 담는다
		job.put("no", 123);
		job.put("title", "test return json object");
		// 한글 전송 시 깨질 우려가 있으므로, URLEncoder로 UTF-8방식의 인코딩을 처리한다
//		job.put("writer", URLEncoder.encode("홍길동", "utf-8"));
//		job.put("content", URLEncoder.encode("json 객체를 뷰로 리턴하는 테스트", "utf-8"));
		// filter 넣었으니 없앤다.
		job.put("writer", "홍길동");
		job.put("content", "json 객체를 뷰로 리턴하는 테스트");
		// JSONObject 를 string 형태로 리턴한다.
		return job.toJSONString(); // {"no" : 123, "title" : "test~~"} <- 이게 data에 들어간다.
	}

//------------------------------------------------------------------------------------//
	@RequestMapping(value = "test3.do", method = RequestMethod.POST)
	public void test3Method(HttpServletResponse response) throws IOException {
		// logger은 인스턴스명으로 이미 생성이 되어있어야한다.
		logger.info("test3Method() run..."); // INFO: 라고 console창에 뜬다. 만약 여기에 error을 찍으면 색상도 바뀌고 중요한 문구처럼 찍는다. 뒤쪽의 문구가
												// 무엇이냐에 따라서 작성하는 것이다. 보통 error, warn, info를 사용한다.
		// error // warn : 에러까지는 아니고 경고 나타낼 때 // info : 순수하게 정보 나타낼 때 // debug // trace
		// //<- logger에 있는 5개의 메소드. 실무가면 이 이름은 아닐 것이지만 비슷한 의미의 4~5가지가 있다. 보통 4가지!!
		// debug와 trace를 묶어서 한가지!, 실제 release되는 버전에서는 debug는 안보이게 하고 간다.
		// log4j에 쓰여있는 레벨
		// FATAL(빡 죽을때) - ERROR(오류) - WARN(오류 우려되는 부분) - INFO(상태변화정보메시지) - DEBUG - TRACE
		logger.error("❗에러메시지❗");
		logger.warn("warn메시지");
		logger.debug("debug메시지");

		// List를 json 배열로 만들어서, 뷰로 리턴 처리한다.
		ArrayList<User> list = new ArrayList<User>();
		list.add(new User("u1234", "p1234", "홍길동", 25, "h1234@kh.org")); // cunstructor 필요
		list.add(new User("u2345", "p2345", "박문수", 33, "p2345@kh.org"));
		list.add(new User("u3456", "p3456", "장영실", 45, "j3456@kh.org"));

		// 전송용 최종 json 객체
		JSONObject sendJson = new JSONObject();
		// JSONArray 객체를 생성하여 JSONObject 객체를 하나씩 담는다
		JSONArray jarr = new JSONArray();

		// list 를 jarr 에 저장 처리
		for (User user : list) {
			// user 정보 저장할 json 객체 선언
			JSONObject juser = new JSONObject();
			juser.put("userId", user.getUserId());
			juser.put("userPwd", user.getUserPwd());
			juser.put("userName", URLEncoder.encode(user.getUserName(), "utf-8"));
			juser.put("age", user.getAge());
			juser.put("email", user.getEmail());
			jarr.add(juser);
		}
		// 전송할 객체 배열을 JSONObject에 담아 한 번에 전달한다
		sendJson.put("list", jarr);
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(sendJson.toJSONString());
		out.flush();
		out.close();
	}

	// ------------------------------------------------------------------------------------//
	@RequestMapping(value = "test4.do", method = RequestMethod.POST)
	public ModelAndView test4Method(ModelAndView mv) throws UnsupportedEncodingException {
		logger.info("test4Method() run...");
		Sample samp = new Sample();
		// map 객체를 ModelAndView 에 담아서 리턴한다
		 System.out.println("samp : " + samp); 
//		 samp.setName(URLEncoder.encode(samp.getName(), "utf-8")); 기존에 있는 이름을 꺼내서 써서 이부분을 수정한다.
		 samp.setName(URLEncoder.encode("김송은", "utf-8"));
		Map<String, Sample> map = new HashMap<String, Sample>();
		 map.put("samp", samp); 

		mv.addAllObjects(map);
		// 뷰지정 : jsonView를 빈으로 등록하고, 해당 객체의 id를 뷰이름으로 지정해야 함
		mv.setViewName("jsonView"); // 이 이름을 쓴다고 해서 이 이름의 jsp 파일이 있는 것이 아니다. servlet-context.xml에서 jsonView 라는 클래스로 인스턴스가 만들어져있고 그것으로 리턴하는거고, 그걸 viewresolve로 등록시켜놓은 것이다. 

		return mv; // ajax 는 json 객체로 받음
	}

//------------------------------------------------------------------------------------//
	@RequestMapping(value = "test5.do", method = RequestMethod.POST)
	public ResponseEntity<String> test5Method(HttpServletRequest request, @RequestBody String param) throws Exception {
		logger.info("test5Method() run...");
		request.setCharacterEncoding("utf-8");

		// 전송온 문자열을 json 객체로 변환 처리
		JSONParser parser = new JSONParser();
		JSONObject job = (JSONObject) parser.parse(param);

		String name = (String) job.get("name");
		int age = ((Long) job.get("age")).intValue();

		System.out.println("name : " + name + ", age : " + age);

		// 정상 완료됨을 클라이언트로 성공값을 보내야 함
		return new ResponseEntity<String>("successResult", HttpStatus.OK);
	}

//------------------------------------------------------------------------------------//
	@RequestMapping(value = "test6.do", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseEntity<String> test6Method(HttpServletRequest request, @RequestBody String param) throws Exception {
//		logger.info("test6Method() run...");
//		request.setCharacterEncoding("utf-8");
//
//		System.out.println("param : " + param);
//		JSONArray jarr = (JSONArray) new JSONParser().parse(param);
//
//		System.out.println("jarr : " + jarr.size());
//
//		for (int i = 0; i < jarr.size(); i++) {
//			JSONObject job = (JSONObject) jarr.get(i);
//			String name = (String) job.get("name");
//			int age = ((Long) job.get("age")).intValue();
//
//			System.out.println("name : " + name + ", age : " + age);
//		}
//
//		// 정상적으로 처리가 되었다면, 클라이언트로 성공값을 보내야 한다
//		return new ResponseEntity<String>("success", HttpStatus.OK);
//	}
	@ResponseBody
	public String test6Method(HttpServletRequest request, @RequestBody String param) throws Exception {
		logger.info("test6Method() run...");
		request.setCharacterEncoding("utf-8");

		System.out.println("param : " + param);
		JSONArray jarr = (JSONArray) new JSONParser().parse(param);

		System.out.println("jarr : " + jarr.size());

		for (int i = 0; i < jarr.size(); i++) {
			JSONObject job = (JSONObject) jarr.get(i);
			String name = (String) job.get("name");
			int age = ((Long) job.get("age")).intValue();

			System.out.println("name : " + name + ", age : " + age);
		}

		// 정상적으로 처리가 되었다면, 클라이언트로 성공값을 보내야 한다
		return "successResult";
	}
	
	@RequestMapping(value = "test7.do", method = RequestMethod.POST, produces="text/plain;charset=UTF-8") // gson 쓸때는 produces를 추가해줘야 제대로 나간다.
	@ResponseBody
//	public Map<String, Object> test7Method(@RequestBody String param) throws Exception {
	public String test7Method(@RequestBody String param) throws Exception {
		logger.info("test6Method() run...");
		System.out.println("param : " + param);
//		param : [{"name":"이 이","age":30},{"name":"신사임당","age":47},{"name":"황진이","age":25}]
		
		Gson gson = new Gson();
		
		// 방법1 항상 OK
		Sample[] reqVoArray = gson.fromJson(param,  Sample[].class);
		List<Sample> reqVoList = Arrays.asList(reqVoArray); // 자바코드
		System.out.println(reqVoList);
//		[Sample [name=이 이, age=30], Sample [name=신사임당, age=47], Sample [name=황진이, age=25]]
		
		// 방법 2 안될수도 있네요~ : 이 방법은 지금 다운받은 버전에선 작동을 안하는 것으로 에상한다. 그래서 지금은 안된다. 
		// List<Sample> reqVoList = gson.fromJson(param.toString(), new TypeToken<List<Sample>>() {}.getType());
		
		// List
		ArrayList<User> list = new ArrayList<User>();
		list.add(new User("u1234", "p1234", "홍길동", 25, "h1234@kh.org")); // cunstructor 필요
		list.add(new User("u2345", "p2345", "박문수", 33, "p2345@kh.org"));
		list.add(new User("u3456", "p3456", "장영실", 45, "j3456@kh.org"));
		// List + 각종 data를 채운 Map 생성
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("volist", list);
		map1.put("startNum", 1);
		map1.put("endNum", 15);
		
//		return map1;
		return gson.toJson(map1);
	}
	
	@RequestMapping(value="test8.do", method=RequestMethod.POST)
	@ResponseBody
	public String test8Method(@RequestBody String param, @RequestParam("name") String name) {
		System.out.println(param);
		System.out.println(name);
		return "ok";
	}
}
