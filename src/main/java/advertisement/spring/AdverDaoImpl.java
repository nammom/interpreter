package advertisement.spring;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import advertisement.vo.AdverAppVo;
import advertisement.vo.AdverVo;
import chatMatching.vo.ReviewVo;
import myBlogErEe.vo.CareerVo;
import myBlogErEe.vo.IntroductionVo;
import ranking.vo.RankingVo;

public class AdverDaoImpl implements AdverDao {

	private SqlSessionTemplate sqlSessionTemplate;
	
	public AdverDaoImpl() {}
	
	public AdverDaoImpl(SqlSessionTemplate sqlSessionTemplate){
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	
	@Override
	public void insertAdApp2(AdverAppVo adverAppVo) {
		sqlSessionTemplate.insert("insertAdApp", adverAppVo);	
	}
	
	@Override
	public List<AdverVo> selectCad2() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("selectCad");
	}
		
	@Override
	public List<AdverVo> selectErAd2() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("selectErAd");
	}
	
	@Override
	public List<AdverVo> selectEeAd2() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("selectEeAd");
	}
	
	@Override
	public int selectPoint2(String userCode) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("selectPoint", userCode);
	}
	
	@Override
	public void updateAdPoint2(String userCode) {
		sqlSessionTemplate.selectOne("updateAdPoint", userCode);
	}
}
