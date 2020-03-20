package myBlogErEe.spring;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import chatMatching.vo.ReviewVo;
import myBlogErEe.vo.CareerVo;
import myBlogErEe.vo.IntroductionVo;

public class MyBlogErEeDaoImpl implements MyBlogErEeDao {

	private SqlSessionTemplate sqlSessionTemplate;
	
	public MyBlogErEeDaoImpl() {}
	
	public MyBlogErEeDaoImpl(SqlSessionTemplate sqlSessionTemplate){
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	@Override
	public IntroductionVo selectIntroduction2(String myuserCode, String category) {
		
		System.out.println("DAO : " +myuserCode+ "," + category);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("myuserCode", myuserCode);
		map.put("category", category);
		
		return sqlSessionTemplate.selectOne("selectIntroduction", map);
	}
	
	@Override
	public List<CareerVo> selectCareer2(String introductionCode) {
		return sqlSessionTemplate.selectList("selectCareer", introductionCode);
	}
	
	@Override
	public int selectAvgStar2(String myuserCode, String category) {
		System.out.println("DAO : " +myuserCode+ "," + category);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("myuserCode", myuserCode);
		map.put("category", category);
		
		return sqlSessionTemplate.selectOne("selectAvgStar", map);
	}
	
	@Override
	public List<ReviewVo> selectReview2(String myuserCode, String category) {
		System.out.println("DAO : " +myuserCode+ "," + category);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("myuserCode", myuserCode);
		map.put("category", category);
		
		return sqlSessionTemplate.selectList("selectReview", map);
	}
	
	
}
