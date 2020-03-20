package chatMatching.vo;

import java.sql.Timestamp;

public class EmployerBoardVO {
	private String ebcode;
	private String writerCode;
	private String regDate;
	private String title;
	private String address;
	private String start;
	private String end;
	private String workTime;
	private int salary;
	private String job;
	private int age;
	private String content;
	private String deadline;
	private int count;
	private char timeover;
	private char matching;
	
	public EmployerBoardVO() {}
	
	public EmployerBoardVO(String title, String address, String start, String end, String workTime, int salary,
			String job, int age, String content, String deadline) {
		super();
		this.title = title;
		this.address = address;
		this.start = start;
		this.end = end;
		this.workTime = workTime;
		this.salary = salary;
		this.job = job;
		this.age = age;
		this.content = content;
		this.deadline = deadline;
		this.count = 0;
	}

	public String getEbcode() {
		return ebcode;
	}

	public void setEbcode(String ebcode) {
		this.ebcode = ebcode;
	}

	public String getWriterCode() {
		return writerCode;
	}

	public void setWriterCode(String writerCode) {
		this.writerCode = writerCode;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getWorkTime() {
		return workTime;
	}

	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public char getTimeover() {
		return timeover;
	}

	public void setTimeover(char timeover) {
		this.timeover = timeover;
	}

	public char getMatching() {
		return matching;
	}

	public void setMatching(char matching) {
		this.matching = matching;
	}

}

