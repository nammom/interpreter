package chatMatching.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import chatMatching.spring.ChatService;
import chatMatching.vo.ChatVo;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ChatListController {
	
	private static final Logger logger = LoggerFactory.getLogger(ChatListController.class);

    private ChatService chatService;
    
	public void setChatService(ChatService chatService){
		this.chatService = chatService;
	}
	
	@ResponseBody
	@RequestMapping(value = "/newChatList", produces = "text/json; charset=utf-8")
	public String home(HttpSession session) {
		
		System.out.println("newChatList ajax");
		String myuserCode = (String)session.getAttribute("myuserCode");
		List<ChatVo> allChatVoList = chatService.getAllChatList(myuserCode);//채팅목록을 가져오는 서비스
		JSONArray jarray = new JSONArray();
	      for(ChatVo i : allChatVoList){
	            JSONObject json = new JSONObject();
	            json.put("myuserCode", i.getUserCode() ); 
	            json.put("fromCode", i.getFromCode() );
	            json.put("toCode",i.getToCode());
	            json.put("chatContent", i.getChatContent());
	            json.put("chatTime", i.getChatTime());
	            json.put("readCheck", i.getReadCheck());
	            
	            jarray.add(json);
	         }//제이슨 만들기 
	      	JSONObject json = new JSONObject();   
	         json.put("newChatList", jarray);
	         System.out.println("제이슨 newChatList만들기 성공");
		//제이슨 보내기 
		return json.toJSONString();
	}
	@RequestMapping(value = "/chatList")
	public String chatList(HttpSession session, Locale locale, Model model) {
		
		 if(session.getAttribute("myuserCode") == null) {
		 System.out.println("로그인회원아님"); return "login"; }
		 
		 System.out.println("로그인회원");
		 
		String myuserCode = (String)session.getAttribute("myuserCode");
		    	System.out.println(myuserCode);
		List<ChatVo> allChatVoList = chatService.getAllChatList(myuserCode);//채팅목록을 가져오는 서비스
		/* List<String> anotherUser = chatService.getAllanotherUser(allChatVoList); */
		for(ChatVo o : allChatVoList) {
			System.out.println(o.getFromCode() +" : " + o.getChatContent() + o.getChatTime());
		}
	
		model.addAttribute("allChatVoList", allChatVoList);//model에 담음
		model.addAttribute("myuserCode", myuserCode);
		return "chatMatching/chatList";
	}
	
	 @RequestMapping(value = {"/delete/{fromCode}"})
	    public ModelAndView oneChatList(@PathVariable String fromCode,
	    		HttpSession session, 
	    		Locale locale, final Model model) {

	    	String myuserCode = (String)session.getAttribute("myuserCode");
	      	System.out.println(fromCode);

	    	chatService.deleteChat(myuserCode, fromCode);//1:1채팅 목록을 가져오는 서비스(userCode)
	    	List<ChatVo> oneChatVoList = new ArrayList<ChatVo>();
	    	

	        return new ModelAndView (new RedirectView("/springChat/chatList"));
	    }
	    
	
}
