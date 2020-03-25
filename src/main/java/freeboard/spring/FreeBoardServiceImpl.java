package freeboard.spring;

import java.util.List;

import org.springframework.stereotype.Service;

import freeboard.vo.CommentsVo;
import freeboard.vo.FreeBoardVo;
import freeboard.vo.PageReady;
import freeboard.vo.Search;

@Service
public class FreeBoardServiceImpl implements FreeBoardService {
	
	private FreeBoardMybatis freeBoardDao;
	
	public void setFreeBoardDao(FreeBoardMybatis freeBoardDao) {
		this.freeBoardDao = freeBoardDao;
	}

	@Override
	public List<FreeBoardVo> freeList() {
		return freeBoardDao.list();
	}

	@Override
	public FreeBoardVo freeRead(String freecode) {
		freeBoardDao.updateReadCount(freecode);
		return freeBoardDao.freeselect(freecode);
	}

	@Override
	public void write(FreeBoardVo vo) {
		freeBoardDao.freeinsert(vo);
	}

	
	@Override
	public void delete(String freecode) {
		System.out.println("삭제요청");
		freeBoardDao.freedelete(freecode);
	}

	@Override
	public void edit(FreeBoardVo vo) {
		freeBoardDao.freeupdate(vo);
	}
	
	@Override
	public List<FreeBoardVo> listPage(PageReady pagerd) {
		return freeBoardDao.listPage(pagerd);
	}
	
	@Override
	public int listCount() {
		return freeBoardDao.listCount();
	}
	
	
	@Override
	public List<FreeBoardVo> listSearch(Search sc) {
		return freeBoardDao.listSearch(sc);
	}
	
	@Override
	public int countSearch(Search sc) {
		return freeBoardDao.countSearch(sc);
	}
	
	@Override
	public void commentsInsert(CommentsVo vo) {
		freeBoardDao.commentsInsert(vo);
	}
	
	@Override
	public List<CommentsVo> commentsList(String articleCode) {
		return freeBoardDao.commentsList(articleCode);
	}
	
	@Override
	public void commentsUpdate(CommentsVo vo) {
		freeBoardDao.commentsUpdate(vo);
	}
	
	@Override
	public void commentsDelete(CommentsVo vo) {
		freeBoardDao.commentsDelete(vo);
	}
	
}
