package sendEmail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SendEmailController {
	
	@RequestMapping(value="/login/sendEmail", method=RequestMethod.POST)
	public void sendEmail(@RequestBody String userEamil) {
		System.out.println("data옴"+userEamil);
		//보내는 사람 메일 설정 
		String host = "smtp.naver.com";
		final String user = "ghsodb";  		
		final String password = "rlap0814";
		final String userEmail = "ghsodb@naver.com";
		int port = 465;
		
		//받는사람 메일 주소
		String to = userEamil;
		
		//Properties -> map의 속성을 가진 파일 입출력 클래스
		//SMTP 서버 정보 설정
		Properties props = System.getProperties();//new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.ssl.enable", "true"); //enable   
		props.put("mail.smtp.ssl.trust", host);

		
		//사용자 이름, 암호, 메일 서버 등의 정보를 관리
		Session session = Session.getDefaultInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		
		session.setDebug(true); //for debug
		// 메세지 작성
		  try {
			  //메일 메세지의 내용과 관련된 추상 클래스	
		   MimeMessage message = new MimeMessage(session);
		   //보내는사람 이메일
		   message.setFrom(new InternetAddress(userEmail));
		   //받는사람 이메일
		   message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));

		   // 제목
		   message.setSubject("메일제목");
		   
		   // 메일내용
		   message.setText("메일내용");

		   //메일 메세지를 MTA로 전송하는 기능을 하는 클래스 
		   Transport.send(message);
		   System.out.println("메일 전송완료!!");

		  } catch (MessagingException e) {
		   e.printStackTrace();
		  }
	}
}
