package ranking.spring;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import chatMatching.vo.ReviewVo;
import myBlogErEe.vo.CareerVo;
import myBlogErEe.vo.IntroductionVo;
import ranking.vo.RankingVo;

public class RankingDaoImpl implements RankingDao {

	private SqlSessionTemplate sqlSessionTemplate;
	
	public RankingDaoImpl() {}
	
	public RankingDaoImpl(SqlSessionTemplate sqlSessionTemplate){
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	@Override
	public List<RankingVo> selectRanking2(String category) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("selectRanking", category);
	}
	
}
