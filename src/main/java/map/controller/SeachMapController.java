package map.controller;


import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import map.spring.IllMapService;


@Controller 
public class SeachMapController {
	private IllMapService illMapService;


	public void setIllMapService(IllMapService illMapService) {
		this.illMapService = illMapService;
	}
	
	
	
	@RequestMapping("/map")
	public String map() {
		return "map/seachMap";
	}
	
	

	//이 요청은  마커를 찍기위한 경로
	@RequestMapping(value="/map1", produces = "text/json; charset=utf-8")
	@ResponseBody
	public String getMaker(@RequestBody String message) {
		String guin = "1.0";
		String data = "";
		if (message.equals("2.0")) {
			 data = illMapService.getMaker(message);
		} else {
			data = illMapService.getMaker(guin);
		}
	
		return data;
	}
	
	


	
	//이 요청은  리스트를 만들기 위한 준비
		@RequestMapping(value="/map2", produces = "text/json; charset=utf-8")
		@ResponseBody
		public String getListItem(@RequestBody String lldata) throws ParseException {
			System.out.println("뷰에서 제이슨도착" + lldata);

			String data = illMapService.getListItem(lldata);
			
			return data;
			
		}
		
	
	
	
	
	
	
	
}
