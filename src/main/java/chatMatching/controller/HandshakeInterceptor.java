package chatMatching.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

public class HandshakeInterceptor extends HttpSessionHandshakeInterceptor {

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Before Handshake");

		HttpServletRequest req = ((ServletServerHttpRequest) request).getServletRequest();
		HttpSession session = req.getSession();
		System.out.println(session.getAttribute("fromCode") + ":" + session.getAttribute("myuserCode"));

		attributes.put("fromCode",session.getAttribute("fromCode"));
		attributes.put("myuserCode",session.getAttribute("myuserCode"));
		return super.beforeHandshake(request, response, wsHandler, attributes);
	}

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception ex) {
		// TODO Auto-generated method stub
		System.out.println("After Handshake");
		super.afterHandshake(request, response, wsHandler, ex);
	}
}
