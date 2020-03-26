package freeboard.spring;

import java.util.List;

import freeboard.vo.CommentsVo;
import freeboard.vo.FreeBoardVo;
import freeboard.vo.PageReady;
import freeboard.vo.Search;

public interface FreeBoardDao {

		
		
		//자유게시판 리스트
		public abstract List<FreeBoardVo> list();
		//자유게시판 게시글 삭제
		public abstract int freedelete(String freecode);
		//자유게시판 게시글 수정
		public abstract int freeupdate(FreeBoardVo vo);
		//자유게시판 게시글 등록
		public abstract void freeinsert(FreeBoardVo vo);
		//자유게시판 게시글 하나
		public abstract FreeBoardVo freeselect(String freecode);
		//조회수 카운팅
		public abstract int updateReadCount(String freecode);
		//페이징
		public abstract List<FreeBoardVo> listPage(PageReady pagerd);
		//총 게시물 카운트
		public abstract int listCount();
		
		//목록 + 페이징 + 검색  **이거
		public abstract List<FreeBoardVo> listSearch(Search sc);
		//검색 결과 갯수  **이거
		public abstract int countSearch(Search sc);
		
		//댓글등록
		public void commentsInsert(CommentsVo vo);
		//댓글리스트
		public List<CommentsVo> commentsList(String articleCode);
		//댓글수정
		public void commentsUpdate(CommentsVo vo);
		//댓글삭제
		public void commentsDelete(CommentsVo vo);
}
