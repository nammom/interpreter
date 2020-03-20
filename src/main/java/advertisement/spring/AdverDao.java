package advertisement.spring;

import java.util.List;

import advertisement.vo.AdverAppVo;
import advertisement.vo.AdverVo;
import chatMatching.vo.ReviewVo;
import myBlogErEe.vo.CareerVo;
import myBlogErEe.vo.IntroductionVo;
import ranking.vo.RankingVo;

public interface AdverDao {

	void insertAdApp2(AdverAppVo adverAppVo);

	List<AdverVo> selectCad2();

	List<AdverVo> selectErAd2();

	List<AdverVo> selectEeAd2();

	int selectPoint2(String userCode);

	void updateAdPoint2(String userCode);


}
