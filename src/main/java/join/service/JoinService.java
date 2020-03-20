package join.service;

import java.util.HashMap;

import join.member.Member;

public interface JoinService {
	String Check(String title,String data);
	void userInsert(Member member);
	String userCheck(String email, String pwd);
	Member selectUser(String title, String data);
}
