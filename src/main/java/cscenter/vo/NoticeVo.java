package cscenter.vo;

public class NoticeVo {
	private String no;
	private String title;
	private String content;
	private String regdate;
	private int hit;
	
	
	public NoticeVo() {
	}


	public NoticeVo(String no, String title, String content, String regdate, int hit) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
		this.hit = hit;
	}


	public String getNo() {
		return no;
	}


	public void setNo(String no) {
		this.no = no;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getRegdate() {
		return regdate;
	}


	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}


	public int getHit() {
		return hit;
	}


	public void setHit(int hit) {
		this.hit = hit;
	}



	
	
}
