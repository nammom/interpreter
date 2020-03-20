package myBlogErEe.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import chatMatching.vo.ReviewVo;
import myBlogErEe.spring.MyBlogErEeService;
import myBlogErEe.vo.CareerVo;
import myBlogErEe.vo.IntroductionVo;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MyBlogErEeController {
	
	private static final Logger logger = LoggerFactory.getLogger(MyBlogErEeController.class);

    private MyBlogErEeService myBlogErEeService;
    
	public void setMyBlogErEeService(MyBlogErEeService myBlogErEeService){
		this.myBlogErEeService = myBlogErEeService;
	}
	

	@RequestMapping(value = "/myBlogEr")
	public String home(
			HttpServletRequest request,// HttpServletResponse response,
			Locale locale, Model model) {
		//!! 구현해야할것 ! 나중에는 세션에서 id 꺼냄
		HttpSession session = request.getSession();
		
		if(session.getAttribute("myuserCode") == null) {
		System.out.println("로그인회원아님"); return "login"; }
		 
		String myuserCode = (String)session.getAttribute("myuserCode");
		
		IntroductionVo erIntro = myBlogErEeService.getErIntroduction(myuserCode);
		List<ReviewVo> review = myBlogErEeService.getErReview(myuserCode);
		int avgStar = myBlogErEeService.getErStar(myuserCode);
		//System.out.println(erIntro.getContents());
		System.out.println(avgStar);
		model.addAttribute("myuserCode", myuserCode);		
		model.addAttribute("erIntro", erIntro);//model에 담음
		model.addAttribute("review", review);
		model.addAttribute("avgStar", avgStar);
		
		return "myBlogErEe/myBlogEr";
	}
	
	@RequestMapping(value = "/myBlogEe")
	public String myBlogEe(HttpServletRequest request,Model model) {
		//!! 구현해야할것 ! 나중에는 세션에서 id 꺼냄
		HttpSession session = request.getSession();
		
		if(session.getAttribute("myuserCode") == null) {
		System.out.println("로그인회원아님"); return "login"; }

		List<ReviewVo> review = new ArrayList<ReviewVo>();
		List<CareerVo> career = new ArrayList<CareerVo>();
		IntroductionVo eeIntro = new IntroductionVo();
		
		String myuserCode = (String)session.getAttribute("myuserCode");
		eeIntro = myBlogErEeService.getEeIntroduction(myuserCode);	
		if(eeIntro != null) {
			career = myBlogErEeService.getCareer(eeIntro.getIntroductionCode());
			System.out.println(eeIntro.getContents());//e.getIntroductionCode()
		}
		review = myBlogErEeService.getEeReview(myuserCode);
		int avgStar = myBlogErEeService.getEeStar(myuserCode);
		
		//System.out.println(eeIntro.getContents());
		System.out.println(avgStar);
		
		model.addAttribute("myuserCode", myuserCode);
		model.addAttribute("eeIntro", eeIntro);//model에 담음
		model.addAttribute("career", career);
		model.addAttribute("review", review);
		model.addAttribute("avgStar", avgStar);
		return "myBlogErEe/myBlogEe";
	}
	

	
}
