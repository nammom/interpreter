package chatMatching.controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.json.simple.JSONObject;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import chatMatching.spring.ChatService;
import chatMatching.vo.ChatVo;

public class EchoHandler extends TextWebSocketHandler{
   
	public EchoHandler() {
		System.out.println("웹소켓 객체 생성");
	}

	private ChatService chatService;
	 
	public void setChatService(ChatService chatService){
		this.chatService = chatService;
	}
	
	private Map<String, WebSocketSession> users = 
			new ConcurrentHashMap<String, WebSocketSession>();
	
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> map = session.getAttributes();
		System.out.println(session.getId() + "웹 소켓 접속자 : " + map.get("myuserCode"));
		users.put((String)map.get("myuserCode"), session);
		System.out.println("========================");
		System.out.println(users.toString());
		System.out.println("========================");
	}
	
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println("메시지 전송됨");
		Map<String, Object> map = session.getAttributes();
		String myuserCode = (String)map.get("myuserCode");
		String fromCode = (String)map.get("fromCode");//이부분 조심 fromCode이지만 채팅받는사람
		String chatContent = message.getPayload();
		System.out.println(myuserCode + "가 " + fromCode +"에게 "+ chatContent + "전송");

    	ChatVo o = chatService.sendChat(myuserCode, fromCode, chatContent);//채팅 테이블에 insert 
    	
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
    	
		JSONObject json = new JSONObject();
		json.put("myuserCode",o.getFromCode());
        json.put("chatContent", o.getChatContent());
        json.put("chatTime", o.getChatTime());
         
         
        String result =  json.toJSONString();
        
    	System.out.println(result);
    	    	
    	
    	users.get(myuserCode).sendMessage(new TextMessage(result));
    	System.out.println("전송성공");
    	
		if(users.get(fromCode) != null) {
			users.get(fromCode).sendMessage(new TextMessage(result));
		}
		

    }

	public void sendMatching(String ebURL) {}
	
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
	
		 users.remove(session.getId()); System.out.println("세션삭제");
	
	}


}