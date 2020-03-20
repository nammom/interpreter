package join.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import join.member.Member;
import join.service.JoinServiceImpl;

@Controller
public class JoinController {
	private JoinServiceImpl service;	
	
	public void setService(JoinServiceImpl service) {
		this.service = service;
	}

	//이용약관 페이지 이동
	@RequestMapping("/join/step1")
	public String handleStep1() {
//		System.out.println("소셜로그인 접근");
//		//네이버 로그인 처리 컨트롤러
//		String clientId = "Vagel85RDbRxBGlWkEa3";
//		String clientSecret = "JGuo9SJeEK"; 
//		String code = request.getParameter("code");
//		String state = request.getParameter("state");
//		String redirectURI = URLEncoder.encode("http://localhost:8080/finalproject/join/step1","UTF-8");
//		
//		StringBuffer apiURL = new StringBuffer();
//		apiURL.append("https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&");
//		apiURL.append("client_id=" + clientId);
//		apiURL.append("&client_secret=" + clientSecret);
//		apiURL.append("&redirect_uri=" + redirectURI);
//		apiURL.append("&code=" + code);
//		apiURL.append("&state=" + state);
//		String access_token = "";
//		String refresh_token = ""; //나중에 이용합시다
//		
//		try {
//			
//			  URL url = new URL(apiURL.toString());
//		      HttpURLConnection con = (HttpURLConnection)url.openConnection();
//		      con.setRequestMethod("GET");
//		      int responseCode = con.getResponseCode();
//		      BufferedReader br;
//		      System.out.print("responseCode="+responseCode);
//		      if(responseCode==200) { // 정상 호출
//		        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
//		      } else {  // 에러 발생
//		        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
//		      }
//		      String inputLine;
//		      StringBuffer res = new StringBuffer();
//		      
//		      while ((inputLine = br.readLine()) != null) {
//		        res.append(inputLine);
//		      };
//		      br.close();
//		      
//		      //네아로가 오류 없이 정상 작동 되엇을 경우
//		      if(responseCode==200) {
//		        System.out.println(res.toString());
//		        
//		        JSONParser parsing = new JSONParser();
//		    	Object obj = parsing.parse(res.toString());
//		    	JSONObject jsonObj = (JSONObject)obj;
//		    		        
//		    									//사용자의 정보에 접근에 필요한 토큰
//		    	access_token = (String)jsonObj.get("access_token");
//		    										
//		    	refresh_token = (String)jsonObj.get("refresh_token");
//		    	
//		    	HttpSession session = request.getSession();
//		    	session.setAttribute("access_token", access_token);
//		    	session.setAttribute("refresh_token", refresh_token);
//		    	
//		    	 
//		      }
//		      
//		    } catch (Exception e) {
//		      System.out.println(e);
//		    }

		return "join/step1";
	}
	
	
	//이용약관을 거치지 않고 회원가입 페이지로 직접 이동했을 경우 
	@RequestMapping(value="/join/step2", method=RequestMethod.GET)
	public String handleStep2() {
		
		return "redirect:/join/step1";
	}
	
	//이용약관 동의한 경우 회원가입 페이지로 이동
	@RequestMapping(value="/join/step2", method=RequestMethod.POST)
	public String handleStep2(Model model) {
	
		return "join/step2";		
	}
	
	//회원가입 email중복확인
	@RequestMapping(value="/join/emailCheck", method=RequestMethod.POST)
	@ResponseBody
	public String emailCheck(@RequestBody String email) {
		String result = service.Check("이메일",email);

		return result;
	}
	
	//회원가입 userCode중복확인
	@RequestMapping(value="/join/usercodeCheck", method=RequestMethod.POST)
	@ResponseBody
	public String usercodeCheck(@RequestBody String userCode) {
		String result = service.Check("닉네임",userCode);

		return result;
	}
	
	//인증번호 발송
	@RequestMapping(value="/join/send", method=RequestMethod.POST, produces = "text/json;charset=UTF-8" )
	@ResponseBody
	public String sendSms(@RequestBody String phone, HttpSession session) {
									//최대값,최소값
		int num = (int)(Math.random()*9999)+1000;
		
		String random = Integer.toString(num);
		System.out.println("요청한 폰번호: " + phone+ ", 발송된 인증버노:" + random);
		
		//인증문자보내는 메서드(random); 
//		SmsService.sendSm(phone , random);
		session.setAttribute("random", random);
		session.setAttribute("phone", phone);
		return "인증번호가 발송되었습니다.";
	}
	
	//발송된 인증번호 검사
	@RequestMapping(value="/join/CheckRan", method=RequestMethod.POST)
	@ResponseBody
	public String CheckRan(@RequestBody String userRan, HttpSession session) {
		//사용자가 입력한 인증번호와 세션에 저장된 발송된 인증번호 비교
		System.out.println("사용자가 입력한 인증번호:"+userRan);
		if(userRan.equals(session.getAttribute("random"))) {
			String phone =(String)session.getAttribute("phone");
			System.out.println(phone);
			Member member = service.selectUser("휴대폰번호", (String)session.getAttribute("phone"));
			session.setAttribute("myEmail", member.getEmail());
			return "0";
		}else {
			return "1";
		}
		
	}
	
	//회원가입페이지에서 입력한 값 전달받음
	@RequestMapping(value="/join/step3", method=RequestMethod.POST)
	public String handleStep3(@ModelAttribute("user")Member member,Model model) {
		System.out.println(member.toString());		
		service.userInsert(member);
		
		model.addAttribute("myuserCode", member.getUsercode());
		return "join/step3";
	}
	
//	@RequestMapping(value="/join/step3", method=RequestMethod.GET)
//	public String handleStep3() {
//		return "join/step3";
//	}
	
}
