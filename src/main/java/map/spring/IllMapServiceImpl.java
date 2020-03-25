package map.spring;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import map.vo.ArticlesDto;
import map.vo.MapDto;



@Service
public class IllMapServiceImpl implements IllMapService {
	private MapDao mapDao;
	
	public void setMapDao(MapDao mapDao) {
		this.mapDao = mapDao;
	}
	
	
	@Override
	public String getMaker(String message) {
		
		List<MapDto> ls;
		
		if(message.equals("2.0")) {
			 ls = mapDao.selectGujicAll();
		} else { 
			 ls = mapDao.selectGuinAll();
		}
		
		
		JSONArray jarray = new JSONArray();
		
		for(MapDto i : ls){
			JSONObject json = new JSONObject();	
			//System.out.println(i.getAddr());
			json.put("address",i.getAddress());
			json.put("lat", i.getLat());
			json.put("lng", i.getLng());
			jarray.add(json);
		}
		
		JSONObject json = new JSONObject();	
		json.put("positions", jarray);
		System.out.println("마커컨트롤러 :" +json.toJSONString());
		
		return json.toJSONString();

	}
	
	
	@Override
	public String getListItem(String lldata) throws ParseException {
		//String을 JSON으로 변환
		System.out.println("컨트롤러에서 제이슨도착" + lldata);
		JSONParser parser = new JSONParser();
		JSONObject jsonObj = (JSONObject) parser.parse(lldata);
		System.out.println("제이슨값:" + jsonObj);
		
		double message = 0.0;
		try{
			message = Double.parseDouble((String)jsonObj.get("message"));
		}catch(Exception e) {
			System.out.println("message 값 없음");
		}
		
		//JSON을 HashMap으로 변환
		  HashMap<String, Double> map = null;
		  try {
			map = (HashMap<String, Double>) new ObjectMapper().readValue(jsonObj.toJSONString(), Map.class) ;
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		  
		List<ArticlesDto> ls;
		if(message == 2.2) {
			System.out.println("2.2버전");
			ls = mapDao.selectGujicArticles(map);
		} else {
			System.out.println("기타 버전");
			ls = mapDao.selectGuinArticles(map);
		}
		
		System.out.println("쿼리결과값 : " + ls.toString());
		JSONArray jarray = new JSONArray();
		
		for(ArticlesDto i : ls){
			JSONObject json = new JSONObject();	
			json.put("title", i.getTitle());
			json.put("writercode",i.getWritercode());
			json.put("startdate", i.getStart());
			json.put("enddate", i.getEnd());
			jarray.add(json);
		}
		
		JSONObject json = new JSONObject();	
		json.put("list", jarray);
		System.out.println("목록컨트롤러 :" +  json.toJSONString());
		
		return json.toJSONString();
			
	}


   
}
