package map.spring;


import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import map.vo.ArticlesDto;
import map.vo.MapDto;


public class MapDao {
	
	private SqlSessionTemplate sqlSessionTemplae;
	private MapDto mapDto;
	private ArticlesDto articlesDto;
	

	public MapDao(SqlSessionTemplate sqlSessionTemplae) {
		super();
		this.sqlSessionTemplae = sqlSessionTemplae;
	}
	
	public void setMapDto(MapDto dto) {
		this.mapDto = dto;
	}
	
	
	public void setArticlesDto(ArticlesDto dto) {
		this.articlesDto = dto;
	}

	public List<MapDto> selectGuinAll() {
		System.out.println("구인쿼리실행시작");
		List<MapDto> result = sqlSessionTemplae.selectList("selectGuinAll");
		return result;
	}

	
	public List<MapDto> selectGujicAll() {
		System.out.println("구직쿼리실행시작");
		List<MapDto> result = sqlSessionTemplae.selectList("selectGujicAll");
		return result;
	}
	
	
	
	public List<ArticlesDto> selectGuinArticles(HashMap<String, Double> map) {
		System.out.println("구인쿼리실행시작");
		List<ArticlesDto> result = sqlSessionTemplae.selectList("selectGuinArticles", map);
		return result;
	}

	public List<ArticlesDto> selectGujicArticles(HashMap<String, Double> map) {
		System.out.println("구직쿼리실행시작");
		List<ArticlesDto> result = sqlSessionTemplae.selectList("selectGujicArticles", map);
		return result;
	}

	
	
}
