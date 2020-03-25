package popularArticle.spring;

import java.util.List;

import chatMatching.vo.EmployerBoardVO;

public interface PopAtcDao {

	//구인글
	List<EmployerBoardVO> ebPopList();
	//구직글
	List<EmployerBoardVO> wbPopList();
	
	
	
}
