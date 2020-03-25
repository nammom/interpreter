package freeboard.spring;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import freeboard.vo.CommentsVo;
import freeboard.vo.FreeBoardVo;
import freeboard.vo.PageReady;
import freeboard.vo.Search;

public class FreeBoardMybatis implements FreeBoardDao {

	private SqlSessionTemplate sqlSessionTemplate;

	public FreeBoardMybatis(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	@Override
	public List<FreeBoardVo> list() {
		System.out.println("서비스에서 요청");
		return sqlSessionTemplate.selectList("freelist");
	}

	@Override
	public int freedelete(String freecode) {
		System.out.println(freecode);
		System.out.println("삭제직전");
		return sqlSessionTemplate.delete("freedelete", freecode);
	}

	@Override
	public int freeupdate(FreeBoardVo vo) {
		return sqlSessionTemplate.update("freeupdate", vo);
	}

	@Override
	public void freeinsert(FreeBoardVo vo) {
		sqlSessionTemplate.insert("freeinsert", vo);
	}

	@Override
	public FreeBoardVo freeselect(String freecode) {
		FreeBoardVo vo = (FreeBoardVo) sqlSessionTemplate.selectOne("freeselect", freecode);
		return vo;
	}

	@Override
	public int updateReadCount(String freecode) {
		return sqlSessionTemplate.update("freeupdateCount", freecode);
	}

	@Override //**이거
	public List<FreeBoardVo> listPage(PageReady pagerd) {
		System.out.println("서비스에서 요청");
		return sqlSessionTemplate.selectList("listPage", pagerd);
	}

	@Override //**이거
	public int listCount() {
		return sqlSessionTemplate.selectOne("listCount");
	}
	
	@Override 
	public List<FreeBoardVo> listSearch(Search sc) {
		return sqlSessionTemplate.selectList("listSearch", sc);
	}
	
	@Override 
	public int countSearch(Search sc) {
		return sqlSessionTemplate.selectOne("countSearch",sc);
	}

	public void commentsInsert(CommentsVo vo) {
		sqlSessionTemplate.insert("commentsInsert", vo);
	}

	public List<CommentsVo> commentsList(String articleCode) {
		return sqlSessionTemplate.selectList("commentsList", articleCode);
	}

	public void commentsUpdate(CommentsVo vo) {
		sqlSessionTemplate.update("commentsUpdate", vo);
	}

	public void commentsDelete(CommentsVo vo) {
		sqlSessionTemplate.delete("commentsDelete",vo);
	}
	
}
