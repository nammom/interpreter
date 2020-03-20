package join.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import join.member.Member;
import join.service.JoinService;
import join.service.JoinServiceImpl;

@Controller
public class LoginController {
	private JoinServiceImpl service;
	
	public void setService(JoinServiceImpl service) {
		this.service = service;
	}

	//로그인
	@RequestMapping(value="/login/login", method=RequestMethod.GET)
	public String login() {
		
		return "login/login";	
	}
	
	//로그아웃
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "home";
	}
	
	@RequestMapping(value="/login/login", method=RequestMethod.POST)
	@ResponseBody
	public String login(@RequestBody HashMap<String,String> map, HttpServletRequest req, Model model) {
		String check = service.userCheck(map.get("email"), map.get("password"));
		
		if(check.equals("0")) {
			HttpSession session = req.getSession();
			Member member = service.selectUser("이메일",map.get("email"));
			session.setAttribute("myuserCode", member.getUsercode());
			session.setAttribute("myEmail", member.getEmail());
			model.addAttribute("myuserCode", member.getUsercode());
		}
		return check;
	}
	
	//비밀번호 찾기
	@RequestMapping(value="/login/searchPwd", method=RequestMethod.GET)
	public String searchPwd() {
		return "login/searchPwd";
	}
	
	//아이디 & 비밀번호 찾기
	@RequestMapping(value="/login/searchEmailPwd", method=RequestMethod.GET)
	public String searchEmailPwd() {
		return "login/searchEmailPwd";
	}
	
	@RequestMapping(value="/login/searchEmailPwd", method=RequestMethod.POST)
	@ResponseBody
	public String searchEmailPwd(@RequestBody String phoneSc, Model model, HttpSession ss) {
		System.out.println(phoneSc);
		ss.setAttribute("aaa", "dsaf");
		//Member member = service.selectUser("휴대폰번호", phone);
		return "{\"myEmail\":\""+phoneSc+"\"}";
	}
	
	//아이디 찾기완료.
	@RequestMapping(value="/login/searchEmailComplite", method=RequestMethod.GET)
	public String searchEmailComplite() {
		return "login/searchEmailComplite";
	}
	
	//홈으로
	@RequestMapping("/home")
	public String home() {
		return "home";
	}

}
