package chatMatching.spring;

import java.util.List;

import chatMatching.spring.MatchingDao;
import chatMatching.spring.MatchingService;
import chatMatching.vo.MatchingVo;

public class MatchingServiceImpl implements MatchingService{
	
	private MatchingDao matchingDao;
	
	public MatchingServiceImpl() {
		super();
	}

	public MatchingServiceImpl(MatchingDao matchingDao){
		this.matchingDao = matchingDao;
	}
	
	private SmsService smsService;
	 
	public void setSmsService(SmsService smsService){
		this.smsService = smsService;
	}
	
	@Override
	public List<MatchingVo> getErMatching(String myUserCode) {
		return matchingDao.selectErMatching2(myUserCode);
	}
	
	@Override
	public List<MatchingVo> getEeMatching(String myUserCode) {
		// TODO Auto-generated method stub
		return matchingDao.selectEeMatching2(myUserCode);
	}
	
	@Override
	public void deleteMatching(String matchingCode) {
		System.out.println("그룹아이디 가져오기 " + matchingCode);
		String groupId = matchingDao.selectGroupId2(matchingCode);
		System.out.println(groupId);
		smsService.cancelSms(groupId);
		matchingDao.deleteMatching2(matchingCode);
		
	}
	
	@Override
	public void onMatchangeSms(String myUserCode) {
		String smsCheck = "Y";
		matchingDao.updateSmsCheck2(smsCheck, myUserCode);//SMSCHECK 데이터 Y로 바꾸기
		List<MatchingVo> mList = matchingDao.selectNullMatching2(myUserCode);//groupID가 null인 것 뽑아오기
		for(MatchingVo e : mList) {
			String ebStart = e.getMatchingDate();
        	String date = Integer.parseInt(ebStart.replaceAll("/",""))-1 + "120000";
       		String text = "사장님 " + e.getEmployerCode() + "과 알바생 " + myUserCode +"의 약속이 다음날입니다. 게시글 코드 : " + e.getEbCode();
       		System.out.println(text);
       		String groupId = smsService.sendReservationSms(myUserCode, text, date);
			//for문으로 문자전송
       		matchingDao.updateGroupId2(groupId, e.getMatchingCode());
		}
		
	}
	
	@Override
	public void offMatchangeSms(String myUserCode) {
		String smsCheck = "N";
		matchingDao.updateSmsCheck2(smsCheck, myUserCode);//SMSCHECK 데이터  N 으로 바꾸기
		List<MatchingVo> mList = matchingDao.selectGroupIdMatching2(myUserCode);//groupID가 null인 것 뽑아오기
		for(MatchingVo e : mList) {
			smsService.cancelSms(e.getGroupID());
			matchingDao.updateGroupIdNull2(myUserCode);
		}//groupID가 null이 아닌것 뽑아오기
		//for문으로 문자취소
		//예약 문자취소하기+ groupID 컬럼에 null로 추가
	}
	
	@Override
	public void makeCf(String targetCode, String matchingCode) {
		matchingDao.updateCf2(targetCode);
		matchingDao.updateCfCheck2(matchingCode);
		
	}
	
	@Override
	public void makeReview(String category, String matchingCode, String myuserCode, String targetCode, int star, String content) {
		matchingDao.insertReviewCf2(category, matchingCode, myuserCode, targetCode, star, content);
		matchingDao.updatePoint2(myuserCode);
	}
	


	
}
