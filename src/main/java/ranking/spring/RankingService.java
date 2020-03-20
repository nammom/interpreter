package ranking.spring;

import java.util.List;

import chatMatching.vo.ReviewVo;
import myBlogErEe.vo.CareerVo;
import myBlogErEe.vo.IntroductionVo;
import ranking.vo.RankingVo;

public interface RankingService {

	List<RankingVo> getRanking(String category);

}
