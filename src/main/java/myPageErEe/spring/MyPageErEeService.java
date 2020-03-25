package myPageErEe.spring;

import java.util.HashMap;
import java.util.List;

import chatMatching.vo.ReviewVo;
import myBlogErEe.vo.CareerVo;
import myBlogErEe.vo.IntroductionVo;

public interface MyPageErEeService {

	void deleteIntro(String introductionCode);

	void updateOpenCheck(String openCheck, String myuserCode, String category);

	void editIntro(HashMap intro);

	void deleteCareer(String introductionCode);

	void writeIntro(HashMap intro);

	String selectIntroductionCode(String myuserCode);

	void InsertCareer(HashMap<String, String> o);



}
