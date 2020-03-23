package mypage.service;

import java.util.HashMap;

import org.mindrot.jbcrypt.BCrypt;

import join.member.Member;
import join.service.JoinDao;

public class MypageServiceImpl implements MypageService {
	JoinDao joinDao;

	
	public MypageServiceImpl() {
	}
	
	public void setJoinDao(JoinDao joinDao) {
		this.joinDao = joinDao;
	}

	//회원정보 변경
	@Override
	public void updateInfo(Member member) {
		joinDao.updateInfo(member);
	}

	//비밀번호 변경
	@Override
	public void updatePwd(HashMap map) {
		String hashPwd = BCrypt.hashpw((String)map.get("newPassword"), BCrypt.gensalt());
		System.out.println(hashPwd);
		//해싱된 비밀번호로 map변경
		map.put("newPassword", hashPwd);
		
		joinDao.updatePwd(map);
		
	}
	
	//회원 탈퇴
	@Override
	public void deleteAccout(String email) {
		joinDao.deleteAccout(email);		
	}

}
