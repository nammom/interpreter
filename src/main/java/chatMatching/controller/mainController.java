package chatMatching.controller;

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
import org.springframework.web.bind.annotation.RequestParam;

import chatMatching.spring.ChatService;

@Controller
public class mainController{

    @RequestMapping(value = {"/main"})
    public String oneChatList(@RequestParam(value="id", required = false) String myuserCode,
    		HttpSession session, final Model model) {

    	session.setAttribute("myuserCode", myuserCode);
  
    	model.addAttribute("myuserCode", myuserCode);//결과 model에 담음

        return "chatMatching/main";
    }
        
}