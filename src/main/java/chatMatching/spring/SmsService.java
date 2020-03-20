package chatMatching.spring;

public interface SmsService {
	public void sendSms(String number, String text);
	public String sendReservationSms(String myUserCode, String text, String date);
	public void cancelSms(String groupId);
}
