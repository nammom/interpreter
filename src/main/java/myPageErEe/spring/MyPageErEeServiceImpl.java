package myPageErEe.spring;

import java.util.HashMap;
import java.util.List;

import chatMatching.spring.MatchingDao;
import chatMatching.vo.ReviewVo;
import myBlogErEe.vo.CareerVo;
import myBlogErEe.vo.IntroductionVo;

public class MyPageErEeServiceImpl implements MyPageErEeService {

	private MyPageErEeDao myPageErEeDao;
	
	public MyPageErEeServiceImpl() {
		super();
	}

	public MyPageErEeServiceImpl(MyPageErEeDao myPageErEeDao){
		this.myPageErEeDao = myPageErEeDao;
	}
	
	@Override
	public void deleteIntro(String introductionCode) {
		myPageErEeDao.deleteIntroduction2(introductionCode);
		
	}
	
	@Override
	public void updateOpenCheck(String openCheck, String myuserCode, String category) {
		myPageErEeDao.updateOpenCheck2(openCheck, myuserCode, category);
	}

	@Override
	public void editIntro(HashMap intro) {
		myPageErEeDao.updateIntro2(intro);	
	}
	
	@Override
	public void deleteCareer(String introductionCode) {
		myPageErEeDao.deleteCareer2(introductionCode);
		
	}
	
	@Override
	public void writeIntro(HashMap intro) {
		myPageErEeDao.insertIntro2(intro);	
		
	}
	
	@Override
	public String selectIntroductionCode(String myuserCode) {
		// TODO Auto-generated method stub
		return myPageErEeDao.selectIntroductionCode2(myuserCode);	
	}
	
	@Override
	public void InsertCareer(HashMap<String, String> o) {
		myPageErEeDao.InsertCareer2(o);	
		
	}
}
