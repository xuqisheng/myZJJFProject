package zjjf_analysis_scheduled;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Testxxx {
	public static void main(String[] args) {
		List<Map<String, String>> mapList = new ArrayList<Map<String,String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("salemanId", "A123");
		map.put("raseNum", "-2");
		mapList.add(map);
		map.put("salemanId", "B123");
		map.put("raseNum", "2");
		System.out.println(map);
		mapList.add(map);
		
		System.out.println(mapList);
		
	}
}
