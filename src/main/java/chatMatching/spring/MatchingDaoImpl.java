package chatMatching.spring;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import chatMatching.vo.MatchingVo;
import chatMatching.vo.ReviewVo;

public class MatchingDaoImpl implements MatchingDao{
	
	private SqlSessionTemplate sqlSessionTemplate;
	
	public MatchingDaoImpl() {}
	
	public MatchingDaoImpl(SqlSessionTemplate sqlSessionTemplate){
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	
	
	@Override
	public List<MatchingVo> selectErMatching2(String myUserCode) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("selectErMatching", myUserCode);
	}
	
	@Override
	public List<MatchingVo> selectEeMatching2(String myUserCode) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("selectEeMatching", myUserCode);
	}
	
	@Override
	public void deleteMatching2(String matchingCode) {
		sqlSessionTemplate.delete("deleteMatching", matchingCode);		
	}
	
	@Override
	public void updateMatchingCheck2(String ebCode) {
		sqlSessionTemplate.update("updateMatchingCheck", ebCode);
		
	}
	
	@Override
	public void updateSmsCheck2(String smsCheck, String myUserCode) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("smsCheck", smsCheck);
		map.put("myUserCode", myUserCode);
		sqlSessionTemplate.update("updateSmsCheck", map);
		
	}
	
	@Override
	public void updateGroupId2(String groupId, String matchingCode) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("groupId", groupId);
		map.put("matchingCode", matchingCode);
		sqlSessionTemplate.update("updateGroupId", map);
		
	}
	
	@Override
	public void updateGroupIdNull2(String myUserCode) {
		sqlSessionTemplate.update("updateGroupIdNull", myUserCode);
		
	}
	
	@Override
	public List<MatchingVo> selectNullMatching2(String myUserCode) {
		return sqlSessionTemplate.selectList("selectNullMatching", myUserCode);
	}
	
	@Override
	public List<MatchingVo> selectGroupIdMatching2(String myUserCode) {
		return sqlSessionTemplate.selectList("selectGroupIdMatching", myUserCode);
	}
	
	@Override
	public String getPhone(String myUserCode) {
		return sqlSessionTemplate.selectOne("selectPhone", myUserCode);
	}
	
	@Override
	public void updateCf2(String targetCode) {
		sqlSessionTemplate.update("updateCf", targetCode);		
	}
	
	@Override
	public void insertReviewCf2(String category, String matchingCode, String myuserCode, String targetCode, int star, String content) {
		ReviewVo reviewVo = new ReviewVo(category, matchingCode, myuserCode, targetCode, star, content);
		
		sqlSessionTemplate.insert("insertReview", reviewVo);
		
	}
	
	@Override
	public void updatePoint2(String myuserCode) {
		sqlSessionTemplate.update("updatePoint", myuserCode);		
	}
	
	@Override
	public String selectGroupId2(String matchingCode) {
		return sqlSessionTemplate.selectOne("selectGroupId", matchingCode);
	}

	@Override
	public void updateCfCheck2(String matchingCode) {
		sqlSessionTemplate.update("updateCfCheck", matchingCode);	
		
	}
	

	
}
