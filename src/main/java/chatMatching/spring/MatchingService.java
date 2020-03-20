package chatMatching.spring;

import java.util.List;

import chatMatching.vo.MatchingVo;

public interface MatchingService {
	
	public List<MatchingVo> getErMatching(String myUserCode);
	public List<MatchingVo> getEeMatching(String myUserCode);
	public void deleteMatching(String matchingCode);
	public void onMatchangeSms(String myUserCode);
	public void offMatchangeSms(String myUserCode);
	public void makeCf(String targetCode, String matchingCode);
	public void makeReview(String category, String matchingCode, String myuserCode, String targetCode, int star, String content);


	
}
