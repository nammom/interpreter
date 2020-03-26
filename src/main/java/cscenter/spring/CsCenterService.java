package cscenter.spring;

import java.util.HashMap;
import java.util.List;

import cscenter.vo.MemberVo;
import cscenter.vo.NoticeVo;
import cscenter.vo.QnaRequestVo;


public interface CsCenterService {
	
	//공지사항
	public abstract List<NoticeVo> list(); 
	public abstract NoticeVo read(String seq);
	
	
	//1:1문의
	public abstract void write(QnaRequestVo vo);
	//첨부파일 이름 등록
	public abstract void filewrite(HashMap<String,String> fileinsert);
	//닉네임확인
	public abstract MemberVo Check(String userCode);
	//최종 시퀀스 확인
	public abstract int currval();
	

}
