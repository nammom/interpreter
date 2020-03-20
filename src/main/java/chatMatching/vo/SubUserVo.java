package chatMatching.vo;

public class SubUserVo {

	String userCode;
	int point;
	int cfCount;
	int loveCount;
	int warningCount;
	String smsCheck;
	
	public SubUserVo() {}
	
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public int getCfCount() {
		return cfCount;
	}
	public void setCfCount(int cfCount) {
		this.cfCount = cfCount;
	}
	public int getLoveCount() {
		return loveCount;
	}
	public void setLoveCount(int loveCount) {
		this.loveCount = loveCount;
	}
	public int getWarningCount() {
		return warningCount;
	}
	public void setWarningCount(int warningCount) {
		this.warningCount = warningCount;
	}
	public String getSmsCheck() {
		return smsCheck;
	}
	public void setSmsCheck(String smsCheck) {
		this.smsCheck = smsCheck;
	}
	
	
}
