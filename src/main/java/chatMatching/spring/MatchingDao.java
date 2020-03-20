package chatMatching.spring;

import java.util.List;

import chatMatching.vo.MatchingVo;

public interface MatchingDao {
	public List<MatchingVo> selectErMatching2(String myUserCode); //내가 사장님일때 매칭 리스트 가져오기  
	public List<MatchingVo> selectEeMatching2(String myUserCode); //내가 알바생일때 매칭 리스트 가져오기  
	public void deleteMatching2(String matchingCode); //매칭취소시 삭제 
	public void updateMatchingCheck2(String ebCode); //매칭 취소시 해당 게시글 매칭여부 N으로 변경
	public void updateSmsCheck2(String smsCheck, String myUserCode); //SMSCHECK 데이터 Y or N 으로 바꾸기
	public void updateGroupId2(String groupId, String matchingCode); //예약 문자취소하기 or전송+ groupID 컬럼에 null or 값  추가
	public void updateGroupIdNull2(String myUserCode);
	public List<MatchingVo> selectNullMatching2(String myUserCode); 
	public List<MatchingVo> selectGroupIdMatching2(String myUserCode);
	public String getPhone(String myUserCode);
	public void updateCf2(String targetCode);
	public void insertReviewCf2(String category, String matchingCode, String myuserCode, String targetCode, int star, String content);
	public void updatePoint2(String myuserCode);
	public String selectGroupId2(String matchingCode);
	public void updateCfCheck2(String matchingCode);


	
}
