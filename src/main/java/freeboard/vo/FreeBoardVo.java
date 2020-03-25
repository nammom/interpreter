package freeboard.vo;

public class FreeBoardVo {
	
	private int num;
	private String freecode;
	private String writercode;
	private String title;
	private String content;
	private String regdate;
	private int hit;
	
	public FreeBoardVo() {
		
	}

	

	public FreeBoardVo(int num, String freecode, String writercode, String title, String content, String regdate,
			int hit) {
		super();
		this.num = num;
		this.freecode = freecode;
		this.writercode = writercode;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
		this.hit = hit;
	}



	public String getFreecode() {
		return freecode;
	}

	public void setFreecode(String freecode) {
		this.freecode = freecode;
	}

	public String getWritercode() {
		return writercode;
	}

	public void setWritercode(String writercode) {
		this.writercode = writercode;
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

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}


	public int getNum() {
		return num;
	}


	public void setNum(int num) {
		this.num = num;
	}
	
	

	
}
