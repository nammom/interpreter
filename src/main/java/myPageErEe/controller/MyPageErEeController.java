package myPageErEe.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import myBlogErEe.spring.MyBlogErEeService;
import myBlogErEe.vo.CareerVo;
import myBlogErEe.vo.IntroductionVo;
import myPageErEe.spring.MyPageErEeService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MyPageErEeController {
	
	private static final Logger logger = LoggerFactory.getLogger(MyPageErEeController.class);

    private MyPageErEeService myPageErEeService;
    
	public void setMyPageErEeService(MyPageErEeService myPageErEeService){
		this.myPageErEeService = myPageErEeService;
	}
	
    private MyBlogErEeService myBlogErEeService;
    
	public void setMyBlogErEeService(MyBlogErEeService myBlogErEeService){
		this.myBlogErEeService = myBlogErEeService;
	}
	
    //완성
	@RequestMapping(value = "/myPageEr")
	public String myPageEr(
			HttpSession session, Locale locale, Model model) {

		if(session.getAttribute("myuserCode") == null) {
		System.out.println("로그인회원아님"); return "login"; }
		 
		String myuserCode = (String)session.getAttribute("myuserCode");
		IntroductionVo erIntro = myBlogErEeService.getErIntroduction(myuserCode);
		String category = "employer";
		
		model.addAttribute("myuserCode", myuserCode);		
		model.addAttribute("intro", erIntro);//model에 담음
		model.addAttribute("category", category);
		
		return "myPageErEe/myPageEe";
	}
	
    //완성
	@RequestMapping(value = "/myPageEe")
	public String myPageEe(HttpSession session, Model model) {

		if(session.getAttribute("myuserCode") == null) {
		System.out.println("로그인회원아님"); return "login"; }
		
		List<CareerVo> career = new ArrayList<CareerVo>();
		IntroductionVo eeIntro = new IntroductionVo();
		
		String myuserCode = (String)session.getAttribute("myuserCode");
		eeIntro = myBlogErEeService.getEeIntroduction(myuserCode);	
		if(eeIntro != null) {
			career = myBlogErEeService.getCareer(eeIntro.getIntroductionCode());
			System.out.println(eeIntro.getContents());//e.getIntroductionCode()
		}
		String category = "employee";
		
		model.addAttribute("myuserCode", myuserCode);
		model.addAttribute("intro", eeIntro);//model에 담음
		model.addAttribute("career", career);
		model.addAttribute("category", category);
		
		return "myPageErEe/myPageEe";
	}
	
	
    @RequestMapping(value = "/writeIntro/{category}", method = RequestMethod.GET)
    public String writeIntro(@PathVariable String category,
    		@RequestParam("open") String openCheck,
    		HttpSession session, Model model) {
    	
    	String myuserCode = (String) session.getAttribute("myuserCode");
    	model.addAttribute("category", category);//결과 model에 담음
    	model.addAttribute("myuserCode", myuserCode);
    	model.addAttribute("openCheck", openCheck);
        return "myPageErEe/writeIntro";
    }
    
    @ResponseBody
    @RequestMapping(value = "/writeSuccess/{category}")
    public String writeSuccess(@RequestBody List<HashMap<String,String>> introVo,
    							@PathVariable String category,
    							HttpSession session) {
    	
    	HashMap intro = introVo.get(0);
    	myPageErEeService.writeIntro(intro);
    	
    	if(category.equals("employee")) {
    		String myuserCode = (String)session.getAttribute("myuserCode");
    		String introCode = myPageErEeService.selectIntroductionCode(myuserCode);
    	
    		if(introVo.size() > 1) {
    			
            	introVo.remove(0);
            	List<HashMap<String,String>> careerList = introVo;
            
            	myPageErEeService.deleteCareer(introCode);
            	
            	for(HashMap<String,String> o : careerList) {
            		o.put("introductionCode", introCode);
            		System.out.println(o);
        			myPageErEeService.InsertCareer(o);
            	}	
        	}
    		return "myPageEe";
    	}
    	else {
    		return "myPageEr";
    	}
    }
    
    @RequestMapping(value = "/editIntro/{category}", method = RequestMethod.GET)
    public String editIntro(@PathVariable String category, 
    		HttpSession session, Model model) {
	
    	String myuserCode = (String)session.getAttribute("myuserCode");
    	
    	if(category.equals("employee")) {
    		IntroductionVo eeIntro = myBlogErEeService.getEeIntroduction(myuserCode);
    		String[] workspaceList = eeIntro.getWorkspace().split(" ");
    		
    		model.addAttribute("intro", eeIntro); //기존 소개서 담기
    		model.addAttribute("workspaceList", workspaceList);
    		
    		if(eeIntro != null) {
    			List<CareerVo> career = myBlogErEeService.getCareer(eeIntro.getIntroductionCode());
    			System.out.println(eeIntro.getContents());//e.getIntroductionCode()
    			model.addAttribute("career", career);
    		}
    			
    	}else {
    		IntroductionVo erIntro = myBlogErEeService.getErIntroduction(myuserCode);
    		String[] workspaceList = erIntro.getWorkspace().split(" ");
    		
    		model.addAttribute("intro", erIntro);
    		model.addAttribute("workspaceList", workspaceList);
    	}
    	
    	model.addAttribute("category", category);//결과 model에 담음
    	
        return "myPageErEe/editIntro";
    }
    
    @ResponseBody
    @RequestMapping(value = "/editSuccess/{category}", method = RequestMethod.POST)
    public String editSuccess(@PathVariable String category, 
    		@RequestBody List<HashMap<String,String>> introVo, 
    		HttpSession session) {

    	HashMap intro = introVo.get(0);
    	myPageErEeService.editIntro(intro);
    
    	if(category.equals("employee")) {
    		String myuserCode = (String)session.getAttribute("myuserCode");
    		String introCode = myPageErEeService.selectIntroductionCode(myuserCode);
    	
    		if(introVo.size() > 1) {
    			
            	introVo.remove(0);
            	List<HashMap<String,String>> careerList = introVo;
            
            	myPageErEeService.deleteCareer(introCode);
            	
            	for(HashMap<String,String> o : careerList) {
            		o.put("introductionCode", introCode);
            		System.out.println(o);
        			myPageErEeService.InsertCareer(o);
            	}	
        	}
    		return "myPageEe";
    	}
    	else {
    		return "myPageEr";
    	}
    	
    }
    
    //완성
	@ResponseBody
	@RequestMapping(value = "/deleteIntro/{category}", method = RequestMethod.POST)
	public void deleteIntro( @RequestBody String introductionCode, @PathVariable String category) {
		System.out.println(introductionCode);
		myPageErEeService.deleteIntro(introductionCode);
		
		if(category.equals("employee")) {
			myPageErEeService.deleteCareer(introductionCode);
		}
	}
	    
    //완성
	 @ResponseBody
	 @RequestMapping(value = {"/updateOpenCheck/{category}"})
	 public void updateOpenCheck(@RequestBody String openCheck, @PathVariable String category,
	    						HttpServletRequest request ) {
		 System.out.println(openCheck);
	    HttpSession session = request.getSession();   	
	    String myuserCode = (String)session.getAttribute("myuserCode");
	    	
	    myPageErEeService.updateOpenCheck(openCheck, myuserCode, category);
	 }
	

	
}
