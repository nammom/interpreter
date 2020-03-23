package join.service;

import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;

import join.member.Member;

public class JoinDao {
	private SqlSessionTemplate sqlSessionTemplate;

	public JoinDao() {
	
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	//email로 select
	public Member emailCheck(String data) {		 
		return sqlSessionTemplate.selectOne("emailSelect", data);
	}
	
	//닉네임으로 select
	public Member usercodeCheck(String data) {
		return sqlSessionTemplate.selectOne("usercodeCheck", data);
	}
	
	//휴대폰번호로 select
	public Member phoneCheck(String data) {
		return sqlSessionTemplate.selectOne("phoneSelect",data);
	}
	
	//회원정보 insert
	public void userInsert(Member member) {
		sqlSessionTemplate.insert("userInsert", member);
	}
	
	//회원정보 update
	public void updateInfo(Member member) {
		sqlSessionTemplate.update("updateInfo", member);
	}
	
	//비밀번호 update
	public void updatePwd(HashMap map) {
		sqlSessionTemplate.update("updatePwd",map);
	}
	
	//회원 delete
	public void deleteAccout(String email) {
		sqlSessionTemplate.delete("deleteAccount", email);
	}
	
}
