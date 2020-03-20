package chatMatching.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import chatMatching.spring.ChatService;
import chatMatching.vo.ChatVo;

@Controller
public class ChatController
{
    private static final Logger logger;
    
    static {
        logger = LoggerFactory.getLogger(ChatListController.class);
    }
    
    private ChatService chatService;
    
	public void setChatService(ChatService chatService){
		this.chatService = chatService;
	}
    
    //EchoHandler 의존주입
    
    @RequestMapping(value = {"/chat/{fromCode}"})
    public String oneChatList(@PathVariable String fromCode,
    		HttpServletRequest request, HttpServletResponse response, 
    		Locale locale, final Model model) {
    	//내코드 toCode는 임시로 :"U1"으로 함
    	
    	HttpSession session = request.getSession();

    	if(session.getAttribute("myuserCode") == null) {
    		System.out.println("로그인회원아님");
    		return "chatMatching/login";
    	}
    	
    	System.out.println("로그인회원");
    	String myuserCode = (String)session.getAttribute("myuserCode");
      	System.out.println(fromCode);
    	session.setAttribute("fromCode", fromCode);
    	System.out.println("myuserCode:" + myuserCode);
    	List<ChatVo> oneList = chatService.getOneChatList(myuserCode, fromCode);//1:1채팅 목록을 가져오는 서비스(userCode)
    	List<ChatVo> oneChatVoList = new ArrayList<ChatVo>();
    	
    	for(ChatVo o : oneList) {
    		int hour = Integer.parseInt(o.getChatTime().substring(0, 2));
    		String min = o.getChatTime().substring(2);
    		System.out.println(o.getChatTime());
    		System.out.println(hour);
    		if(hour > 12) {
    			System.out.println("오후 -12");
    			hour -= 12;	
    			o.setChatTime("오후 " + hour + min);	
    		}else if(hour == 12) {
    			o.setChatTime("오후 " + hour + min);
    		}
      		else {
    			System.out.println("오전");
    			o.setChatTime("오전 " + hour + min);
    		}
    		oneChatVoList.add(o);
    		System.out.println(o.getChatContent()+ o.getChatTime());
    	}
    	
    	model.addAttribute("oneChatVoList", oneChatVoList);//결과 model에 담음
    	model.addAttribute("employeeCode", fromCode);
        return "chatMatching/chat";
    }
    
    @RequestMapping(value = {"/roadMap"})
    public String oneChatList() {
    	return "chatMatching/roadMap"; 
    }
    
}