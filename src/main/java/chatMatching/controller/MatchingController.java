package chatMatching.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import chatMatching.spring.ChatService;
import chatMatching.spring.MatchingService;
import chatMatching.spring.SmsService;
import chatMatching.vo.EmployerBoardVO;

@Controller
public class MatchingController
{
    private ChatService chatService;
 
	public void setChatService(ChatService chatService){
		this.chatService = chatService;
	}
	
	private SmsService smsService;
	 
	public void setSmsService(SmsService smsService){
		this.smsService = smsService;
	}
   
    @RequestMapping(value = {"/matching/{employeeCode}"})
    public String viewMatching(@PathVariable String employeeCode,
    		HttpServletRequest request, final Model model) {
    	//내코드 toCode는 임시로 :"U1"으로 함
    	
    	HttpSession session = request.getSession();

    	String myuserCode = (String)session.getAttribute("myuserCode");
	
		/*
		 * System.out.println("사장님게시글 메서드 employeeCode : " + employeeCode);
		 * System.out.println("myuserCode:" + myuserCode);
		 */
		session.setAttribute("employeeCode", employeeCode);
		session.setAttribute("fromCode", employeeCode);
 	
    	List<EmployerBoardVO> employerBoardVoList = chatService.selectEb(myuserCode);//1:1채팅 목록을 가져오는 서비스(userCode)
    	
    	model.addAttribute("employerBoardVoList", employerBoardVoList);//결과 model에 담음

        return "chatMatching/matching";
    }
    
    @ResponseBody
    @RequestMapping(value = {"/makeMatching"})
    public void makeMatching(@RequestBody HashMap<String, Object> map, HttpServletRequest request ) {
    	String groupId = "null";
    	HttpSession session = request.getSession();
    	
    	String myuserCode = (String)session.getAttribute("myuserCode");
    	String employeeCode = (String)session.getAttribute("employeeCode");
    	    	
    	String ebCode = (String)map.get("ebCode"); 
    	System.out.println(ebCode);
    	String smsCheck = chatService.selectSmsCheck(employeeCode);
    	System.out.println("smsCheck :" +smsCheck);
       	if(smsCheck.equals("Y")) {    		
       		String ebStart = (String)map.get("ebStart");
        	String date = Integer.parseInt(ebStart.replaceAll("/",""))-1 + "120000";
       		String text = "사장님 " + myuserCode + "과 알바생 " + employeeCode +"의 약속이 다음날입니다. 게시글 코드 : " + ebCode;
       		System.out.println(text);
       		groupId = smsService.sendReservationSms(employeeCode, text, date);
    	}
       	else {
       		groupId = "null";
       	}
    	chatService.insertMatching(myuserCode,employeeCode,ebCode,groupId);//매칭테이블에 값넣는 서비스 
    	//CalendarAddDto cal = chatService.getOneErMatching(ebCode);
    	//String scheduleCategory = "매칭";
    	//String backColor = "";
    	//String fontColor = "";
    	//String allDayCheck = "";
    	
    	//cal.setScheduleCategory(scheduleCategory);
    	//cal.setBackColor(backColor);
    	//cal.setFontColor(fontColor);
    	//cal.setAllDayCheck(allDayCheck);
    	
    	//calendarService.addService(cal);
    }
	

	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping(value = {"/matching/{employeeCode}"}) public String
	 * sendMatching(@RequestBody HashMap<String, Object> map, HttpServletRequest
	 * request) {
	 * 
	 * HttpSession session = request.getSession(); String employeeCode =
	 * (String)session.getAttribute("employeeCode");
	 * session.setAttribute("fromCode", employeeCode); String ebCode =
	 * (String)map.get("ebCode"); String ebURL = "게시글 경로 " + ebCode;
	 * echo.sendMatching(ebURL); }
	 */
    
    
}