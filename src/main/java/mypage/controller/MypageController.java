package mypage.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import join.member.Member;
import join.service.JoinService;
import mypage.service.MypageService;

@Controller
public class MypageController {
	JoinService joinService;
	MypageService mypageService;

	public MypageController() {
	}
	
	public void setMypageService(MypageService mypageService) {
		this.mypageService = mypageService;
	}
	public void setJoinService(JoinService joinService) {
		this.joinService = joinService;
	}

	//개인정보수정 비밀번호 일치 확인
	@RequestMapping(value="/mypage/checkPwd" , method=RequestMethod.GET)
	public String checkPwd() {
		return "mypage/checkPwd";
	}
	
	@RequestMapping(value="/mypage/checkPwd" , method=RequestMethod.POST)
	@ResponseBody
	public String checkPwd(@RequestBody String password, HttpSession session) {
		String email = (String)session.getAttribute("myEmail");
		String check = joinService.userCheck(email, password);
		return check;
	}
	
	//회원정보 수정 페이지
	@RequestMapping(value="/mypage/updateInfo" , method=RequestMethod.GET)
	public String updateInfo(HttpSession session,Model model) {
		String email = (String)session.getAttribute("myEmail");
		Member member = joinService.selectUser("이메일",email);
		
		model.addAttribute("member",member);
		model.addAttribute("year", (member.getBirthdate()).substring(0, 4));
		model.addAttribute("month", (member.getBirthdate()).substring(6, 7));
		model.addAttribute("day", (member.getBirthdate()).substring(9, 10));
		return "mypage/updateInfo";
	}
	
	//회원정보 수정
	@RequestMapping(value="/mypage/updateInfo" , method=RequestMethod.POST)
	public String updateInfo(Member member,HttpSession session) {
		member.setEmail((String)session.getAttribute("myEmail"));
		mypageService.updateInfo(member);
		return "mypage/updateComplite";
	}
	
	//비밀번호 변경 페이지
	@RequestMapping(value="/mypage/updatePwd" , method=RequestMethod.GET)
	public String updatePwd() {
		return "mypage/updatePwd";
	}
	
	@RequestMapping(value="/mypage/updatePwd" , method=RequestMethod.POST)
	@ResponseBody
	public String updatePwd(@RequestBody HashMap<String,String> map, HttpSession session) {
		System.out.println("패스워드 수정 data옴");
		String userEmail = (String)session.getAttribute("myEmail");		
		//map에 email 추가
		map.put("email", userEmail);
		String check ="";
		
		//현재비밀번호가 없이 비밀번호 변경할때
		if(map.get("oldPassword") == null) {
			//비밀번호를 업데이트하는 구문
			mypageService.updatePwd(map);
			//업데이트 성공
			check ="0";
			return check;
		//현재비밀번호가 있는 비밀번호 변경할때
		}else {
			check = joinService.userCheck(userEmail, (String)map.get("oldPassword"));
			//현재 비밀번호가 일치하는지 확인 
			if(check.equals("0")) {
				//비밀번호 업데이트
				mypageService.updatePwd(map);
				return check;
			//현재 비밀번호가 일치하지 않는경우
			}else {
				return check;
			}
		}
	}
	
	@RequestMapping(value="/mypage/updatePwdComplite" , method=RequestMethod.GET)
	public String updatePwdComplite() {
		return "mypage/updatePwdComplite"; 
	}
}
