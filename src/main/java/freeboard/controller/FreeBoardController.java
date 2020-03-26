package freeboard.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import freeboard.spring.FreeBoardService;
import freeboard.vo.CommentsVo;
import freeboard.vo.FreeBoardVo;
import freeboard.vo.PageMaker;
import freeboard.vo.Search;

@Controller
public class FreeBoardController {

	private FreeBoardService freeBoardService;

	String fileRoot = "d:\\dev\\new\\";// 저장될 외부 파일 경로
	
	public FreeBoardController() {
		System.out.println("FreeBoardController생성");
	}
	public void setFreeBoardService(FreeBoardService freeBoardService) {
		this.freeBoardService = freeBoardService;
	}

	/*
	 * // 자유게시판 글목록
	 * 
	 * @RequestMapping(value = "/freeList") public String list(HttpServletRequest
	 * request, Model model) {
	 * 
	 * // HttpSession session = request.getSession(); //
	 * session.setAttribute("myuserCode", "인터헬씨");
	 * 
	 * System.out.println("컨트롤러실행"); List<FreeBoardVo> a =
	 * freeBoardService.freeList(); for (FreeBoardVo o : a) {
	 * System.out.println(o.getFreecode()); }
	 * 
	 * String id = "인터헬씨"; model.addAttribute("myuserCode", id);
	 * model.addAttribute("freeList", freeBoardService.freeList());
	 * System.out.println("뷰로 가기 직전"); return "freeList"; }
	 */

	
	
	/*
	 * // 자유게시판 글목록 페이징
	 * 
	 * @RequestMapping(value = "/freeList") public String
	 * listPage(HttpServletRequest request, PageReady pagerd, Model model) {
	 * 
	 * // HttpSession session = request.getSession(); //
	 * session.setAttribute("myuserCode", "인터헬씨");
	 * 
	 * System.out.println("컨트롤러실행"); List<FreeBoardVo> a =
	 * freeBoardService.listPage(pagerd); for (FreeBoardVo o : a) {
	 * System.out.println(o.getFreecode()); }
	 * 
	 * String id = "인터헬씨"; model.addAttribute("myuserCode", id);
	 * model.addAttribute("freeList", freeBoardService.listPage(pagerd));
	 * 
	 * PageMaker pageMaker = new PageMaker(); pageMaker.setPagerd(pagerd);
	 * pageMaker.setTotalCount(freeBoardService.listCount());
	 * model.addAttribute("pageMaker", pageMaker);
	 * 
	 * System.out.println("뷰로 가기 직전"); return "freeList"; }
	 */

	
	
	// 글목록 + 페이징 + 검색   **이거
		@RequestMapping(value = "/freeList")
		public String listPage(HttpServletRequest request,@ModelAttribute("sc") Search sc, Model model) {

			// HttpSession session = request.getSession();
			// session.setAttribute("myuserCode", "인터헬씨");

			System.out.println("컨트롤러실행");
			List<FreeBoardVo> a = freeBoardService.listSearch(sc);
			for (FreeBoardVo o : a) {
				System.out.println(o.getFreecode());
			}

			String id = "인터헬씨";
			model.addAttribute("myuserCode", id);
			model.addAttribute("freeList", freeBoardService.listSearch(sc));
			
			PageMaker pageMaker = new PageMaker(); 
			pageMaker.setPagerd(sc);
			pageMaker.setTotalCount(freeBoardService.countSearch(sc));
			model.addAttribute("pageMaker", pageMaker);
			
			System.out.println("뷰로 가기 직전");
			return "freeboard/freeList";
		}
	
	
	// 특정 게시글 보기
	@RequestMapping(value = "/freeList/read/{freecode}")
	public String read(Model model, @PathVariable String freecode) {
		String id = "인터헬씨";
		model.addAttribute("myuserCode", id);
		model.addAttribute("freeBoardVo", freeBoardService.freeRead(freecode));
		return "freeboard/freeRead";
	}

	
	
	// 새글 작성을 위한 요청을 처리
	@RequestMapping(value = "/freeWrite", method = RequestMethod.GET)
	public String write(HttpServletRequest request, Model model) {

//			HttpSession session = request.getSession();
//			String id = (String) session.getAttribute("myuserCode");
		String id = "인터헬씨";
		model.addAttribute("myuserCode", id);
		if (!id.isEmpty()) {
			return "freeboard/freeWrite";
		} else {
			return "login";
		}
	}

	
	
	// 새 글 등록을 위한 요청을 처리
	@RequestMapping(value = "/freeWrite", method = RequestMethod.POST)
	public String write(@ModelAttribute("newFree") FreeBoardVo vo) {
		System.out.println("vo도착");
		freeBoardService.write(vo);
		return "redirect:/freeList";
	}

	
	
	// (에이젝스로)이미지 업로드 했을 때, 외부 리소스 경로에 파일 업로드
	@RequestMapping(value = "/uploadSummernoteImageFile", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile) {
		System.out.println("여기!");

		JSONObject jsonObject = new JSONObject();

		String originalFileName = multipartFile.getOriginalFilename(); // 오리지날 파일명
		String extension = originalFileName.substring(originalFileName.lastIndexOf(".")); // 파일 확장자

		String savedFileName = UUID.randomUUID() + extension; // 저장될 파일 명 (UUID는 파일명의 중복 방지를 위해 랜덤 생성)

		File targetFile = new File(fileRoot + savedFileName);

		try {
			InputStream fileStream = multipartFile.getInputStream();
			FileUtils.copyInputStreamToFile(fileStream, targetFile); // 파일 저장
			jsonObject.put("url", "/interHealthy/summernoteImage/" + savedFileName);
			jsonObject.put("responseCode", "success");

		} catch (IOException e) {
			FileUtils.deleteQuietly(targetFile); // 저장된 파일 삭제
			jsonObject.put("responseCode", "error");
			e.printStackTrace();
		}

		System.out.println("response String : " + jsonObject.toString());

		return jsonObject.toString();
	}

	
	
	// 글 수정
	@RequestMapping(value = "/edit/{freecode}", method = RequestMethod.GET)
	public String edit(@PathVariable String freecode, Model model) {
		FreeBoardVo vo = freeBoardService.freeRead(freecode);
		model.addAttribute("freeBoardVo", vo);
		return "freeboard/freeEdit";
	}

	@RequestMapping(value = "/edit/{freecode}", method = RequestMethod.POST)
	public String edit(@ModelAttribute("edit") FreeBoardVo vo) {
		System.out.println("수정할 vo도착");
		freeBoardService.edit(vo);
		return "redirect:/freeList";
	}

	
	
	// 글 삭제
//		@RequestMapping(value="/delete/{freecode}", method=RequestMethod.GET)
//		public String delete(@PathVariable String freecode, Model model){
//			model.addAttribute("freecode", freecode);
//			return "/board/delete";
//		}

	
	
	@RequestMapping(value = "/delete/{freecode}", method = RequestMethod.POST)
	public String delete2(@PathVariable String freecode, Model model) {
		freeBoardService.delete(freecode);
		return "redirect:/freeList";
	}
	
	
	
	// 댓글 등록
		@RequestMapping("/freeboard/addComment")
		@ResponseBody
		public String addComment(HttpServletRequest request, @RequestBody CommentsVo vo) throws Exception {
			
			HttpSession session = request.getSession();
			session.setAttribute("myuserCode", "인터헬씨");

			vo.setUserCode((String) session.getAttribute("myuserCode"));
			
			
			
			freeBoardService.commentsInsert(vo);

			return "success";
		}

		// 댓글 리스트 읽어오기
		@RequestMapping(value = "/freeboard/commentList", produces = "text/json;charset=UTF-8")
		@ResponseBody
		public ResponseEntity commentList(@RequestBody String articleCode, HttpServletRequest request, Model model) throws Exception {
			HttpSession session = request.getSession();
			session.setAttribute("myuserCode", "인터헬씨");
			model.addAttribute("myuserCode", "인터헬씨");

			
			HttpHeaders responseHeaders = new HttpHeaders();
	        ArrayList<HashMap> hmlist = new ArrayList<HashMap>();
	        
			List<CommentsVo> vo = freeBoardService.commentsList(articleCode);
			
			// 해당 게시물 댓글
			if (vo.size() > 0) {
				for (int i = 0; i < vo.size(); i++) {
					HashMap hm = new HashMap();
					hm.put("num", vo.get(i).getNum());
					System.out.println(hm.get("num"));
					hm.put("userCode", vo.get(i).getUserCode());
					hm.put("comment", vo.get(i).getComments());
					hm.put("registDate", vo.get(i).getRegistDate());

					hmlist.add(hm);
				}

			}

			JSONArray json = new JSONArray(hmlist);
			return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
		}
		
		
		//수정한 댓글 데이터베이스에 입력
		@RequestMapping("/freeboard/editComment")
		@ResponseBody
		public String modifyCmt(HttpServletRequest request, @RequestBody CommentsVo vo) throws Exception {
			
			HttpSession session = request.getSession();
			session.setAttribute("myuserCode", "인터헬씨");

			vo.setUserCode((String) session.getAttribute("myuserCode"));
			

			freeBoardService.commentsUpdate(vo);

			return "success";
		}
		
		//댓글 삭제하기
		
		@RequestMapping("/freeboard/deleteCmt")
		@ResponseBody
		public String deleteCmt(HttpServletRequest request, @RequestBody CommentsVo vo) throws Exception {
			
			freeBoardService.commentsDelete(vo);
			
			return "success";
		}
	
	
	
}
