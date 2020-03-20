package join.member;


public class Member {
	private String usercode;
	private String email;
	private String password;
	private String birthdate;
	private String phone;
	private String regiDate;
	
	public Member() {
	}

	public Member(String usercode, String email, String password, String birthdate, String phone, String regiDate) {
		super();
		this.usercode = usercode;
		this.email = email;
		this.password = password;
		this.birthdate = birthdate;
		this.phone = phone;
		this.regiDate = regiDate;
	}

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRegiDate() {
		return regiDate;
	}

	public void setRegiDate(String regiDate) {
		this.regiDate = regiDate;
	}

	@Override
	public String toString() {
		return "Member [usercode=" + usercode + ", email=" + email + ", password=" + password + ", birthdate="
				+ birthdate + ", phone=" + phone + ", regiDate=" + regiDate + "]";
	}
	
	

}
