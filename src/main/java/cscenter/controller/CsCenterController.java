package cscenter.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import cscenter.spring.CsCenterService;
import cscenter.vo.MemberVo;
import cscenter.vo.NoticeVo;
import cscenter.vo.QnaRequestVo;

@Controller
@SessionAttributes({ "noticeVo", "qnaRequestVo" })
//noticeVo, qnaRequestVo키로 저장된 attribute는 세션객체에 저장 됨
public class CsCenterController {

	private CsCenterService csCenterService;
	//HttpServletRequest request;

	public void setCsCenterService(CsCenterService csCenterService) {
		this.csCenterService = csCenterService;
	}

	// 공지 목록
	@RequestMapping(value = "/noticeList")
	public String list(Model model) {
		System.out.println("여기");
		List<NoticeVo> a = csCenterService.list();
		for(NoticeVo o : a) {
			System.out.println(o.getTitle());
		}
		model.addAttribute("noticeList", csCenterService.list());
		
		System.out.println("저기");
		return "cscenter/noticeList";
	}

	// 특정 공지글 보기
	@RequestMapping(value = "/noticeList/read/{seq}")
	public String read(Model model, @PathVariable String seq) {
		model.addAttribute("NoticeVo", csCenterService.read(seq));
		System.out.println("글번호" + seq);
		return "cscenter/noticeRead";
	}

	
	
	
	// 1:1새 글 작성을 위한 요청을 처리
	@RequestMapping(value = "/qnaStep1", method = RequestMethod.GET)
	public String write(HttpServletRequest request, Model model) {
		
		//HttpSession session = request.getSession();
		//String id = (String) session.getAttribute("myuserCode");
		String id = "qqww101";
		model.addAttribute("myuserCode", id);
		if(!id.isEmpty()) {
			return "cscenter/qnaStep1";
		} else {
			return "login";
		}
	}

	
	//가입 닉네임인지 확인 
		@RequestMapping(value="/cs/usercodeCheck", method=RequestMethod.POST)
		@ResponseBody
		public String usercodeCheck(@RequestBody String userCode) {
			MemberVo result = csCenterService.Check(userCode);
			String a = "";
			if(result == null) {
				a = "0";
			}else {
				a = "1";
			}
			return a;
		}
	
	
	
	
	// 1:1 글 등록을 위한 요청을 처리 다중파일업로드가능
	@RequestMapping(value = "/qnaStep2", method = RequestMethod.POST)
	public String write(@ModelAttribute("qna")QnaRequestVo vo, @RequestParam("uploadFile") List<MultipartFile> file) {
		System.out.println("vo도착" + vo.toString());
		System.out.println("파일도착" + file.toString());
		
		csCenterService.write(vo);
			
		System.out.println("for문 끝");	
		int currval = csCenterService.currval();
		String qno = "Q"+ currval;
		
		List<MultipartFile> files = file;
		
		for(int i = 0; i < files.size(); i++) {
			MultipartFile f = files.get(i);
			UUID uuid = UUID.randomUUID(); //랜덤이름 만들기
			if(!f.getOriginalFilename().isEmpty()) {    //파일을 첨부하지 않아도 무조건 인자로 빈 파일이 넘어오기 때문에 조건 추가 
			System.out.println("첨부파일이 있다면");
			int index =   f.getOriginalFilename().lastIndexOf('.'); 
			String format = f.getOriginalFilename().substring(index);
			String saveName = uuid + format;
			//저장하기전 경로랑 이름지정
			File saveFile = new File("D:\\dev\\file\\",saveName); // 저장할 폴더 이름, 저장할 파일 이름
			String fileName = saveFile.getName();
			
			try {
				FileCopyUtils.copy(f.getBytes(),saveFile);	//진짜 물리적 저장은 여기서
			} catch (IOException e) {
				e.printStackTrace();
			} 
			
			HashMap<String,String> fileinsert = new HashMap<>();
			fileinsert.put("qno", qno);
			fileinsert.put("fileName", fileName);
			
			csCenterService.filewrite(fileinsert);
			
			}
		}

			return "cscenter/qnaStep2";
	}

	
	
	//이용약관
		@RequestMapping(value = "/tos")
		public String tos() {
			return "cscenter/tos";
		}
	
	
	
	
}
