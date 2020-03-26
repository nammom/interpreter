package cscenter.spring;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import cscenter.vo.MemberVo;
import cscenter.vo.NoticeVo;
import cscenter.vo.QnaRequestVo;



public class CsCenterMybatis implements CsCenterDao {

	private SqlSessionTemplate sqlSessionTemplate;
	
	public CsCenterMybatis(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	@Override
	public List<NoticeVo> noticeList() {
		System.out.println("서비스에서 불렀어");
		return sqlSessionTemplate.selectList("noticeList");
	}

	@Override
	public NoticeVo noticeselect(String seq) {
		NoticeVo vo = (NoticeVo) sqlSessionTemplate.selectOne("noticeselect", seq);
		return vo;
	}

	@Override
	public int updateReadCount(String seq) {
		return sqlSessionTemplate.update("updateCount", seq);
	}

	@Override
	public void qnainsert(QnaRequestVo vo) {
		if(vo.getComplain() != null ) {
		    sqlSessionTemplate.insert("complaininsert", vo);
	    } else {
	    	sqlSessionTemplate.insert("qnainsert", vo);
	    }
	}

	public MemberVo userCheck(String userCode) {
		return sqlSessionTemplate.selectOne("usercodeCheck", userCode);
	}

	public void fileNameinsert(HashMap<String,String> fileinsert) {
		sqlSessionTemplate.insert("fileNameinsert", fileinsert);
	}

	public int currval() {
		return sqlSessionTemplate.selectOne("currval");
	}
	
	
	
	
}
