package cscenter.spring;

import java.util.HashMap;
import java.util.List;

import cscenter.vo.MemberVo;
import cscenter.vo.NoticeVo;
import cscenter.vo.QnaRequestVo;




public interface CsCenterDao {

	//공지리스트
	public abstract List<NoticeVo> noticeList();
	//공지글 하나
	public abstract NoticeVo noticeselect(String seq);
	//공지글 카운팅
	public abstract int updateReadCount(String seq);
	
	
	//1:1문의 등록
	public abstract void qnainsert(QnaRequestVo vo);
	//닉네임 확인
	public abstract MemberVo userCheck(String userCode);
	
	//첨부파일이름 등록
	public abstract void fileNameinsert(HashMap<String,String> fileinsert);
	
	//최종 시퀀스 확인
	public abstract int currval();
	
}
