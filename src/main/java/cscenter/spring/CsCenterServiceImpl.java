package cscenter.spring;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import cscenter.vo.MemberVo;
import cscenter.vo.NoticeVo;
import cscenter.vo.QnaRequestVo;


@Service
public class CsCenterServiceImpl implements CsCenterService {
	
	private CsCenterMybatis csCenterDao;
	
	public void setCsCenterDao(CsCenterMybatis csCenterDao) {
		this.csCenterDao = csCenterDao;
	}

	
	//공지사항 목록 가져오기
	@Override
	public List<NoticeVo> list() {
		System.out.println("컨트롤러에서 요청도착");
		return csCenterDao.noticeList();
	}

	//공지사항 글보기
	@Override
	public NoticeVo read(String seq) {
		csCenterDao.updateReadCount(seq);
		return csCenterDao.noticeselect(seq);
	}

	//1:1문의 게시글 올리기
	@Override
	public void write(QnaRequestVo vo) {
		csCenterDao.qnainsert(vo);
	}
	
	@Override
	public MemberVo Check(String userCode) {
		return csCenterDao.userCheck(userCode);
	}
	
	@Override
	public void filewrite(HashMap<String,String> fileinsert) {
		csCenterDao.fileNameinsert(fileinsert);
	}
	
	@Override
	public int currval() {
		return csCenterDao.currval();
	}
	
	
	
}
