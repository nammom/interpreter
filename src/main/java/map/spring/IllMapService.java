package map.spring;

import org.json.simple.parser.ParseException;

public interface IllMapService {
	
	public abstract String getMaker(String message);
	public abstract String getListItem(String lldata) throws ParseException;
}
