package myBlogErEe.spring;

import java.util.List;

import chatMatching.vo.ReviewVo;
import myBlogErEe.vo.CareerVo;
import myBlogErEe.vo.IntroductionVo;

public interface MyBlogErEeDao {

	IntroductionVo selectIntroduction2(String myuserCode, String category);

	List<CareerVo> selectCareer2(String introductionCode);

	int selectAvgStar2(String myuserCode , String category);

	List<ReviewVo> selectReview2(String myuserCode , String category);

}
