package chatMatching.vo;

import java.sql.Date;

public class ChatVo
{
	String userCode;
	int chatNum;
    String fromCode;
    String toCode;
    String chatContent;
    String chatTime;
    int readCheck;
    
    
    
    public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
    public int getChatNum() {
        return this.chatNum;
    }
    
    public void setChatNum(final int chatNum) {
        this.chatNum = chatNum;
    }

    
    public String getFromCode() {
        return this.fromCode;
    }
    
	public void setFromCode(final String fromCode) {
        this.fromCode = fromCode;
    }
    
    public String getToCode() {
        return this.toCode;
    }
    
    public void setToCode(final String toCode) {
        this.toCode = toCode;
    }
    
    public String getChatContent() {
        return this.chatContent;
    }
    
    public void setChatContent(final String chatContent) {
        this.chatContent = chatContent;
    }
    
    public String getChatTime() {
        return this.chatTime;
    }
    
    public void setChatTime(final String chatTime) {
        this.chatTime = chatTime;
    }
    
    public int getReadCheck() {
        return this.readCheck;
    }
    
    public void setReadCheck(final int readCheck) {
        this.readCheck = readCheck;
    }
}