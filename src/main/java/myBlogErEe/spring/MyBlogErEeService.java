package myBlogErEe.spring;

import java.util.List;

import chatMatching.vo.ReviewVo;
import myBlogErEe.vo.CareerVo;
import myBlogErEe.vo.IntroductionVo;

public interface MyBlogErEeService {

	IntroductionVo getErIntroduction(String myuserCode);

	IntroductionVo getEeIntroduction(String myuserCode);

	List<CareerVo> getCareer(String introductionCode);

	int getErStar(String myuserCode);

	int getEeStar(String myuserCode);
	
	List<ReviewVo> getEeReview(String myuserCode);

	List<ReviewVo> getErReview(String myuserCode);

	

}
