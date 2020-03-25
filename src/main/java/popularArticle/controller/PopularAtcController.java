package popularArticle.controller;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import chatMatching.vo.EmployerBoardVO;
import popularArticle.spring.PopAtcService;

@Controller
public class PopularAtcController {
	
	private PopAtcService popAtcService;

	public void setPopAtcService(PopAtcService popAtcService) {
		this.popAtcService = popAtcService;
	}
	
	
	@RequestMapping(value = "/imsi")
	public String ad() {
		return "popularArticle/popatc";
	}
	
	
	//인기글 받아오기
	
	@ResponseBody
	@RequestMapping(value = "/newPop", produces = "text/json; charset=utf-8")
	public String newPop() {
	 
		List<EmployerBoardVO> eb= popAtcService.getEb();
		List<EmployerBoardVO> wb = popAtcService.getWb();
		  
		JSONObject result = new JSONObject();
		  
		
		JSONObject json1 = new JSONObject();
		JSONArray ebList = new JSONArray();			
		
		//인기 구인글 제이슨
		for(EmployerBoardVO i : eb){ 
			json1 = new JSONObject();
			json1.put("ebcode", i.getEbcode());
		  	json1.put("writerCode", i.getWriterCode());
		  	json1.put("title", i.getTitle());
		  	json1.put("address", i.getAddress());
		  	json1.put("salary", i.getSalary());
		  	ebList.add(json1); 
		}//제이슨 만들기 
		result.put("ebList", ebList);
		 
		//인기 구직글 제이슨 
		JSONArray wbList = new JSONArray();
		for(EmployerBoardVO i : wb){ 
			json1 = new JSONObject();
			json1.put("wbcode", i.getEbcode());
		  	json1.put("writerCode", i.getWriterCode());
		  	json1.put("title", i.getTitle());
		  	json1.put("address", i.getAddress());
		  	json1.put("salary", i.getSalary());
		  	wbList.add(json1); 
		}//제이슨 만들기 
		result.put("wbList", wbList);
		
		return result.toJSONString(); 
	  }
	
	
	
	
	
	
	
	
	

}
