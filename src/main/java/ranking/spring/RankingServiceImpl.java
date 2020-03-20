package ranking.spring;

import java.util.List;

import chatMatching.spring.MatchingDao;
import chatMatching.vo.ReviewVo;
import myBlogErEe.vo.CareerVo;
import myBlogErEe.vo.IntroductionVo;
import ranking.vo.RankingVo;

public class RankingServiceImpl implements RankingService {

	private RankingDao rankingDao;
	
	public RankingServiceImpl() {
		super();
	}

	public RankingServiceImpl(RankingDao rankingDao){
		this.rankingDao = rankingDao;
	}
	
	@Override
	public List<RankingVo> getRanking(String category) {
		// TODO Auto-generated method stub
		return rankingDao.selectRanking2(category);
	}
}
