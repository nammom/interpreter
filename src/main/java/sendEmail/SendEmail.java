package sendEmail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
	public static void main(String[] args) {
		  String server = "smtp.naver.com";
		  String sendID = "ghsodb";
		  String sendPW = "rlap0814";
		  String sendMailAddr = "ghsodb@naver.com";
		  int smtpPort=465;
		  
		  String recEmailAddr="ghsodb@naver.com";
		  String sub = "안녕";
		  String cont = "내용임";
		  
		  Properties props = System.getProperties();
		  
		  props.put("mail.smtp.host", server);
		  props.put("mail.smtp.port", smtpPort);
		  props.put("mail.smtp.auth", "true");
		  props.put("mail.smtp.ssl.enable", "true");
		  props.put("mail.smtp.ssl.trust", server);
		  
		  Session session = Session.getDefaultInstance(props, new Authenticator(){
		   protected PasswordAuthentication getPasswordAuthentication(){
		    return new PasswordAuthentication(sendID, sendPW);
		   }
		  });
		  
		  session.setDebug(true);
		  
		  Message mimeMessage = new MimeMessage(session);
		  try {
		   mimeMessage.setFrom(new InternetAddress( sendMailAddr ));
		   mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recEmailAddr));
		   mimeMessage.setSubject(sub);
		   mimeMessage.setText(cont);
		   Transport.send(mimeMessage);
		  } catch (AddressException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  } catch (MessagingException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  }
	}
}
