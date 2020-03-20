package myBlogErEe.spring;

import java.util.List;

import chatMatching.spring.MatchingDao;
import chatMatching.vo.ReviewVo;
import myBlogErEe.vo.CareerVo;
import myBlogErEe.vo.IntroductionVo;

public class MyBlogErEeServiceImpl implements MyBlogErEeService {

	private MyBlogErEeDao myBlogErEeDao;
	
	public MyBlogErEeServiceImpl() {
		super();
	}

	public MyBlogErEeServiceImpl(MyBlogErEeDao myBlogErEeDao){
		this.myBlogErEeDao = myBlogErEeDao;
	}
	
	@Override
	public IntroductionVo getErIntroduction(String myuserCode) {
		String category = "employer";
		return myBlogErEeDao.selectIntroduction2(myuserCode, category);
	}
	
	@Override
	public IntroductionVo getEeIntroduction(String myuserCode) {
		String category = "employee";
		IntroductionVo i = myBlogErEeDao.selectIntroduction2(myuserCode, category);
		return i;
	}
	
	@Override
	public List<CareerVo> getCareer(String introductionCode) {
		return myBlogErEeDao.selectCareer2(introductionCode);
	}  
	
	@Override
	public int getErStar(String myuserCode) {
		String category = "employer";
		return myBlogErEeDao.selectAvgStar2(myuserCode, category);
	}
	
	@Override
	public int getEeStar(String myuserCode) {
		String category = "employee";
		return myBlogErEeDao.selectAvgStar2(myuserCode, category);
	}
	
	@Override
	public List<ReviewVo> getErReview(String myuserCode) {
		String category = "employer";
		return myBlogErEeDao.selectReview2(myuserCode, category);
	}
	
	@Override
	public List<ReviewVo> getEeReview(String myuserCode) {
		String category = "employee";
		return myBlogErEeDao.selectReview2(myuserCode, category);
	}
}
