package chatMatching.spring;

import java.util.HashMap;

import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

public class SmsServiceImpl implements SmsService {
	String api_key = "NCSOZZRWOTVMDOLC";
    String api_secret = "GREAEDXPGPP07PZ2JKR40TNX2QCAPVPM";
    Message coolsms = new Message(api_key, api_secret);
    
    private MatchingDao matchingDao;
	
	public SmsServiceImpl() {
		super();
	}

	public SmsServiceImpl(MatchingDao matchingDao){
		this.matchingDao = matchingDao;
	}
	
    @Override
    public void sendSms(String number, String text) {
  
    	HashMap<String, String> params = new HashMap<String, String>();
    		params.put("to", number);
		    params.put("from", "01053531629");
		    params.put("type", "SMS");
		    params.put("text", "인증번호 : " + text);
		    params.put("app_version", "test app 1.2"); 	
		    
		    try {
			      JSONObject obj = (JSONObject) coolsms.send(params);
			      System.out.println("인증문자전송성공:" + obj.toString());
			    } catch (CoolsmsException e) {
			      System.out.println(e.getMessage());
			      System.out.println(e.getCode());
			    }   
    }
    
    @Override
    public String sendReservationSms(String myUserCode, String text, String date) {
    	String number = matchingDao.getPhone(myUserCode);//고객 전화번호 가져오는 메서드  
    	HashMap<String, String> params = new HashMap<String, String>();
    		params.put("to", number);
		    params.put("from", "01053531629");
		    params.put("type", "SMS");
		    params.put("text", text);
		    params.put("app_version", "test app 1.2"); 	
		    params.put("datetime", date);
		    
		    String smsNum = "null";
		    try {
			      JSONObject obj = (JSONObject) coolsms.send(params);
			      obj.get("group_id");
			      smsNum = (String)obj.get("group_id");
			      System.out.println(smsNum);
			      System.out.println("문자예약성공:" + obj.toString());
			    } catch (CoolsmsException e) {
			      System.out.println(e.getMessage());
			      System.out.println(e.getCode());
			    }   
		return smsNum;  
    }
    
    @Override
    public void cancelSms(String groupId) {
    	try {
    		HashMap<String, String> params = new HashMap<String, String>();
    		params.put("group_id", groupId); // group id
		    System.out.println(params.toString());
		   
		    JSONObject obj2 = coolsms.cancel(params);
		    System.out.println("문자예약 취소성공obj2 : "+ obj2.toString());

	   } catch (CoolsmsException e) {
		   	System.out.println(e.getMessage());
		   	System.out.println(e.getCode());
	   }
    	
	}
}
