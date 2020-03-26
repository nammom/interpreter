package myPageErEe.spring;

import java.util.HashMap;
import java.util.List;

import chatMatching.vo.ReviewVo;
import myBlogErEe.vo.CareerVo;
import myBlogErEe.vo.IntroductionVo;

public interface MyPageErEeDao {

	void deleteIntroduction2(String introductionCode);

	void updateOpenCheck2(String openCheck, String myuserCode, String category);

	void updateIntro2(HashMap intro);

	void deleteCareer2(String introductionCode);

	void insertIntro2(HashMap intro);

	String selectIntroductionCode2(String myuserCode);

	void InsertCareer2(HashMap<String, String> o);


}
