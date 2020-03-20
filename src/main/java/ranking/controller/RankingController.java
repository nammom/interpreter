package ranking.controller;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ranking.spring.RankingService;
import ranking.vo.RankingVo;

/**
 * Handles requests for the application home page.
 */
@Controller
public class RankingController {

	private static final Logger logger = LoggerFactory.getLogger(RankingController.class);

	private RankingService rankingService;

	public void setRankingService(RankingService rankingService) {
		this.rankingService = rankingService;
	}

	@RequestMapping(value = "/ranking")
	public String ranking(Model model) {
		String category = "employee";
		List<RankingVo> eeRanking = rankingService.getRanking(category);
		category = "employer";
		List<RankingVo> erRanking = rankingService.getRanking(category);
		model.addAttribute("eeRanking", eeRanking);
		model.addAttribute("erRanking", erRanking);
		return "ranking/ranking";
	}

	
	  
	  @ResponseBody
	  @RequestMapping(value = "/newRanking", produces = "text/json; charset=utf-8")
	  public String newRanking() {
	 
		  String category = "employee";
		  List<RankingVo> eeRanking = rankingService.getRanking(category);
		  category = "employer";
		  List<RankingVo> erRanking = rankingService.getRanking(category);
		  
		  JSONObject json = new JSONObject();
		  
		  JSONArray newEe = new JSONArray();
		  
		  for(RankingVo i : eeRanking){ 
			  JSONObject json1 = new JSONObject();
		  		json1.put("myuserCode", i.getUsercode());
		  		json1.put("rankNum", i.getRankNum());
		  		json1.put("usercode", i.getUsercode());
		  		json1.put("avg", i.getAvg());
		  		json1.put("count", i.getCount());
		  		newEe.add(json1); 
		  }//제이슨 만들기 
		  json.put("newEe", newEe);
		  
		  JSONArray newEr = new JSONArray();
		  for(RankingVo i : erRanking){ 
			  JSONObject json1 = new JSONObject();
		  		json1.put("myuserCode", i.getUsercode());
		  		json1.put("rankNum", i.getRankNum());
		  		json1.put("usercode", i.getUsercode());
		  		json1.put("avg", i.getAvg());
		  		json1.put("count", i.getCount());
		  		newEr.add(json1); 
		  }//제이슨 만들기 
		  json.put("newEr", newEr);
		  
	  return json.toJSONString(); 
	  }
	 

}
