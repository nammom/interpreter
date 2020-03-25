package popularArticle.spring;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import chatMatching.vo.EmployerBoardVO;

public class PopAtcMybatis implements PopAtcDao  {
	
	private SqlSessionTemplate sqlSessionTemplate;
	

	public PopAtcMybatis() {
		
	}

	public PopAtcMybatis(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	//구인글
	@Override
	public List<EmployerBoardVO> ebPopList() {
		return sqlSessionTemplate.selectList("ebPopList");
	}
	
	//구직글
	@Override
	public List<EmployerBoardVO> wbPopList() {
		return sqlSessionTemplate.selectList("wbPopList");
	}
	

}
