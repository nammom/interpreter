package map.vo;

public class ArticlesDto {
	
	private int ebcode;
	private int wbcode;
	private String title;
	private String writercode; 
	private String start; 
	private String end;
	
	public ArticlesDto() {

	}


	public ArticlesDto(int ebcode, int wbcode, String title, String writercode, String start, String end) {
		super();
		this.ebcode = ebcode;
		this.wbcode = wbcode;
		this.title = title;
		this.writercode = writercode;
		this.start = start;
		this.end = end;
	}



	public int getEbcode() {
		return ebcode;
	}



	public void setEbcode(int ebcode) {
		this.ebcode = ebcode;
	}



	public int getWbcode() {
		return wbcode;
	}



	public void setWbcode(int wbcode) {
		this.wbcode = wbcode;
	}



	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}


	public String getWritercode() {
		return writercode;
	}


	public void setWritercode(String writercode) {
		this.writercode = writercode;
	}
	
	
	
	
}
