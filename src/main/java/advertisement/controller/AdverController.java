package advertisement.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import advertisement.spring.AdverService;
import advertisement.vo.AdverAppVo;
import advertisement.vo.AdverVo;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AdverController {

	private static final Logger logger = LoggerFactory.getLogger(AdverController.class);

	private AdverService adverService;

	public void setAdverService(AdverService adverService) {
		this.adverService = adverService;
	}

	@RequestMapping(value = "/ad")
	public String ad(Model model) {
		return "advertisement/ad";
	}
	
	@RequestMapping(value = "/adApp")
	public String adApp(@RequestParam(value="category") String category, 
			HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
    	String myuserCode = (String)session.getAttribute("myuserCode");
    	
		if(category == null || myuserCode == null) {
			return "redirect:login";
		}
		model.addAttribute("category", category);
		

		model.addAttribute("userCode", myuserCode);
		
		return "advertisement/adApp";
	}

	@RequestMapping(value = "/adAppSuccess1")
	public String adAppSuccess1(AdverAppVo adverAppVo, Model model){
		String company = "company";
		String userCode = adverAppVo.getUserCode();
		String category = adverAppVo.getAdcategory();
		int point = adverService.getPoint(userCode);

		if((!category.equals(company)) || point < 300) {
			return "redirect:adAppFail";
		}
		if((!category.equals(company)) && adverService.getPoint(userCode) >= 300) {
			adverService.minusPoint(userCode);
		}
		
		adverService.makeAdApp(adverAppVo);
		return "redirect:adAppSuccess";
	}
	
	@RequestMapping(value = "/adAppSuccess2")
	public String adAppSuccess2(MultipartHttpServletRequest multiRequset)
			{

		AdverAppVo adverAppVo = new AdverAppVo();
		adverAppVo.setUserCode(multiRequset.getParameter("userCode"));
		adverAppVo.setAdcategory(multiRequset.getParameter("adcategory"));
		adverAppVo.setEnterprise(multiRequset.getParameter("enterprise"));
		adverAppVo.setLink(multiRequset.getParameter("link"));
		adverAppVo.setAdDate(Integer.parseInt(multiRequset.getParameter("adDate")));
		adverAppVo.setPhone(multiRequset.getParameter("phone"));
		adverAppVo.setEmail(multiRequset.getParameter("email"));
		adverAppVo.setContent(multiRequset.getParameter("content"));
		
		MultipartFile uploadfile = multiRequset.getFile("file");
		
			logger.info("파일 이름: {}", uploadfile.getOriginalFilename());
		    logger.info("파일 크기: {}", uploadfile.getSize());
		
		adverAppVo.setImage(saveFile(uploadfile));
		System.out.println();
		adverService.makeAdApp(adverAppVo);
		
		return  "redirect:adAppSuccess";
	}
	
	private String saveFile(MultipartFile file){
	    // 파일 이름 변경
	    UUID uuid = UUID.randomUUID();
	    
	    int index =	file.getOriginalFilename().lastIndexOf('.');
	    String format = file.getOriginalFilename().substring(index);
	    
	    String saveName = uuid + format;

	    logger.info("saveName: {}",saveName);

	    // 저장할 File 객체를 생성(껍데기 파일)
	    File saveFile = new File("C:\\Users\\user1\\Desktop\\companyLogo",saveName); // 저장할 폴더 이름, 저장할 파일 이름
	    System.out.println("파일저장");

	    try {
	        file.transferTo(saveFile); // 업로드 파일에 saveFile이라는 껍데기 입힘
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	    return saveName;
	}
	
	@RequestMapping(value = "/adAppSuccess")
	public String adAppSuccess() {
		return "advertisement/adAppSuccess";
	}
	
	@RequestMapping(value = "/adAppFail")
	public String adAppFail() {
		return "advertisement/adAppFail";
	}
	 
	@ResponseBody
	@RequestMapping(value = "/newAd", produces = "text/json; charset=utf-8")
	public String newAd() {
	 
		String category = "company";
		List<AdverVo> cAd= adverService.getcAd();
		List<AdverVo> erAd = adverService.getErAd();
		List<AdverVo> eeAd = adverService.getEeAd();
		  
		JSONObject result = new JSONObject();
		  
		JSONArray cAdList = new JSONArray();
		JSONObject json1 = new JSONObject();
		
		for(AdverVo i : cAd){ 
			json1 = new JSONObject();
			json1.put("enterprise", i.getEnterprise());
		  	json1.put("image", i.getImage());
		  	json1.put("link", i.getLink());
		  	cAdList.add(json1); 
		}//제이슨 만들기 
		result.put("cAdList", cAdList);
		  
		JSONArray erAdList = new JSONArray();
		for(AdverVo i : erAd){ 
			json1 = new JSONObject();
		  	json1.put("userCode", i.getUserCode());
		  	json1.put("link", i.getLink());
		  	json1.put("title", i.getTitle());
		  	json1.put("address", i.getAddress());
		  	json1.put("salary", i.getSalary());
		  	erAdList.add(json1); 
		}//제이슨 만들기 
		result.put("erAdList", erAdList);
		
		JSONArray eeAdList = new JSONArray();
		for(AdverVo i : eeAd){ 
			json1 = new JSONObject();
			json1.put("userCode", i.getUserCode());
			json1.put("link", i.getLink());
			json1.put("title", i.getTitle());
		  	json1.put("address", i.getAddress());
		  	json1.put("salary", i.getSalary());
			erAdList.add(json1); 
		}//제이슨 만들기 
		result.put("eeAdList", eeAdList);		  	
		  	
		return result.toJSONString(); 
	  }
	 

}
