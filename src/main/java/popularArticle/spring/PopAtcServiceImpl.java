package popularArticle.spring;

import java.util.List;

import org.springframework.stereotype.Service;

import chatMatching.vo.EmployerBoardVO;

@Service
public class PopAtcServiceImpl implements PopAtcService {
	
	private PopAtcMybatis popAtcDao;

	public void setPopAtcDao(PopAtcMybatis popAtcDao) {
		this.popAtcDao = popAtcDao;
	}
	
	//구인글
	@Override
	public List<EmployerBoardVO> getEb() {
		return popAtcDao.ebPopList();
	}
	
	
	//구직글
	@Override
	public List<EmployerBoardVO> getWb() {
		return popAtcDao.wbPopList();
	}

}
