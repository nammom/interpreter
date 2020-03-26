package myPageErEe.spring;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import chatMatching.vo.ReviewVo;
import myBlogErEe.vo.CareerVo;
import myBlogErEe.vo.IntroductionVo;

public class MyPageErEeDaoImpl implements MyPageErEeDao {

	private SqlSessionTemplate sqlSessionTemplate;
	
	public MyPageErEeDaoImpl() {}
	
	public MyPageErEeDaoImpl(SqlSessionTemplate sqlSessionTemplate){
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	
	@Override
	public void deleteIntroduction2(String introductionCode) {
		System.out.println("삭제" +introductionCode);
		sqlSessionTemplate.delete("deleteIntroduction", introductionCode);		
	}
	
	@Override
	public void updateOpenCheck2(String openCheck, String myuserCode, String category) {
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("openCheck", openCheck);
		map.put("myuserCode", myuserCode);
		map.put("category", category);
		System.out.println(map);
		sqlSessionTemplate.update("updateOpenCheck", map);
		
	}
	
	@Override
	public void insertIntro2(HashMap intro) {
		sqlSessionTemplate.insert("InsertIntroduction", intro);
		
	}

	@Override
	public String selectIntroductionCode2(String myuserCode) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("selectIntroCode", myuserCode);
	}
	
	@Override
	public void InsertCareer2(HashMap<String, String> o) {
		sqlSessionTemplate.insert("InsertCareer", o);
		
	}
	
	@Override
	public void updateIntro2(HashMap intro) {
		sqlSessionTemplate.update("updateIntro", intro);
		
	}
	
	@Override
	public void deleteCareer2(String introductionCode) {
		sqlSessionTemplate.delete("deleteCareer", introductionCode);
		
	}
	
	
	
}
