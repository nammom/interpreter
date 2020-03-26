package popularArticle.spring;

import java.util.List;

import chatMatching.vo.EmployerBoardVO;

public interface PopAtcService {
	
	//인기 구인글 가져오기
	List<EmployerBoardVO> getEb();
	//인기 구직글 가져오기 
	List<EmployerBoardVO> getWb();

}
