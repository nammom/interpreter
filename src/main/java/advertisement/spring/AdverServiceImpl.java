package advertisement.spring;

import java.util.List;

import advertisement.vo.AdverAppVo;
import advertisement.vo.AdverVo;
import chatMatching.spring.MatchingDao;
import chatMatching.vo.ReviewVo;
import myBlogErEe.vo.CareerVo;
import myBlogErEe.vo.IntroductionVo;
import ranking.vo.RankingVo;

public class AdverServiceImpl implements AdverService {

	private AdverDao adverDao;
	
	public AdverServiceImpl() {
		super();
	}

	public AdverServiceImpl(AdverDao adverDao){
		this.adverDao = adverDao;
	}

	@Override
	public void makeAdApp(AdverAppVo adverAppVo) {
		adverDao.insertAdApp2(adverAppVo);
	}
	
	@Override
	public List<AdverVo> getcAd() {
		return adverDao.selectCad2();
	}

	@Override
	public List<AdverVo> getErAd() {
		// TODO Auto-generated method stub
		return adverDao.selectErAd2();
	}
	
	@Override
	public List<AdverVo> getEeAd() {
		// TODO Auto-generated method stub
		return adverDao.selectEeAd2();
	}
	
	@Override
	public int getPoint(String userCode) {
		// TODO Auto-generated method stub
		return adverDao.selectPoint2(userCode);
	}
	
	@Override
	public void minusPoint(String userCode) {
		adverDao.updateAdPoint2(userCode);
	}

}
