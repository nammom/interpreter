package freeboard.spring;

import java.util.List;

import freeboard.vo.CommentsVo;
import freeboard.vo.FreeBoardVo;
import freeboard.vo.PageReady;
import freeboard.vo.Search;



public interface FreeBoardService {
	
	
		//자유게시판 리스트 
		public abstract List<FreeBoardVo> freeList();
		//게시글 하나
		public abstract FreeBoardVo freeRead(String freecode);
		
		
		//게시글 등록
		public abstract void write(FreeBoardVo vo);
		//게시글 삭제
		public abstract void delete(String freecode);
		//게시글 수정
		public abstract void edit(FreeBoardVo vo);

		//리스트 페이징 **이거 
		public abstract List<FreeBoardVo> listPage(PageReady pagerd);
		// 총 게시물 카운팅 **이거
		public abstract int listCount();
		
		//목록 + 페이징 + 검색  
		public abstract List<FreeBoardVo> listSearch(Search sc);
		//검색 결과 갯수  
		public abstract int countSearch(Search sc);
		
		//댓글등록
		public abstract void commentsInsert(CommentsVo vo);
		//댓글리스트 가져오기
		public abstract List<CommentsVo> commentsList(String articleCode);
		//댓글수정
		public abstract void commentsUpdate(CommentsVo vo);
		//댓글삭제
		public abstract void commentsDelete(CommentsVo vo);
	
	
	
}
