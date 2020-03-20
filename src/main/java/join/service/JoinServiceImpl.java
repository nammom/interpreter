package join.service;

import java.util.HashMap;

import org.mindrot.jbcrypt.BCrypt;

import join.member.Member;

public class JoinServiceImpl implements JoinService {
	private JoinDao joinDao;
	
	public JoinServiceImpl() {

	}
	
	public void setJoinDao(JoinDao joinDao) {
		this.joinDao = joinDao;
	}
	
	@Override
	public String Check(String title,String data) {
		Member member = null;
		
		if(title.equals("이메일")) {
			member = joinDao.emailCheck(data);
		}else if(title.equals("닉네임")) {
			member = joinDao.usercodeCheck(data);
		}
		
		String check = "";
		if(member == null) {
			check ="1";
			System.out.println("check : "+check);
			return check;
		}else {
			check ="0";
			System.out.println("check : "+check);
			return check;
		}
	}
	
	//회원가입
	@Override
	public void userInsert(Member member) {
		String hashPwd = BCrypt.hashpw(member.getPassword(), BCrypt.gensalt());
		member.setPassword(hashPwd);
		joinDao.userInsert(member);		
	}

	//이메일&패스워드 검증
	@Override
	public String userCheck(String email, String pwd) {
		Member member = joinDao.emailCheck(email);
		String check = "";
		//검색한 이메일의 회원이 있는지 확인
		if(member == null) {
			//회원정보가 없을때
			return check = "1";
		}else {
			if(BCrypt.checkpw(pwd, member.getPassword())) {
				/*BCrypt.checkpw(pwd, member.getPassword())*/
				//회원의 정보가 일치할때
				return check ="0";
			}else {
				//회원정보의 비밀번호가 다를때
				return check ="2";
			}
		}
	}
	
	//회원정보 검색
	@Override
	public Member selectUser(String title, String data) {
		Member member = null;

		if (title.equals("이메일")) {
			member = joinDao.emailCheck(data);
		} else if (title.equals("닉네임")) {
			member = joinDao.usercodeCheck(data);
		} else if(title.equals("휴대폰번호")) {
			member = joinDao.phoneCheck(data);
		}

		return member;
	}

}
