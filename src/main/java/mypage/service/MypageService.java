package mypage.service;

import java.util.HashMap;

import join.member.Member;

public interface MypageService {
	void updateInfo(Member member);
	void updatePwd(HashMap map);
}
