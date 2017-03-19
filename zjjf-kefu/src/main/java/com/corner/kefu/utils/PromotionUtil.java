/**   
* @Title: PromotionUtil.java 
* @Package com.corner.kefu.utils 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年3月30日 下午3:02:09 
* @version V1.0   
*/

package com.corner.kefu.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/** 
* @ClassName: PromotionUtil 
* @Description:促销管理工具类
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年3月30日 下午3:02:09 
*  
*/

public class PromotionUtil {

	/**
	 * 
	* @Title: ruleContenToJsonStr 
	* @Description:满减活动将页面传过来的数据按要求转换成Json格式字符串
	* @param @param payMethodStr
	* @param @param payMethod
	* @param @return
	* @return String    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public static Map<String, Object> ruleContenToJsonStr(String ruleContent,String payMethodStr,int payMethod) {
		
		String minStr = "";
		Map<String, Object>map = new HashMap<String,Object>();
		//替换中文：
		payMethodStr = payMethodStr.replaceAll("：", ":");
		payMethodStr = payMethodStr.replaceAll("；", ";");
		payMethodStr+=";";
		//校验格式
		if(!Pattern.matches("^([1-9]+\\d*:[1-9]+\\d*;)+$", payMethodStr)){
			map.put("flag", false);
			return map;
		}else {
	        payMethodStr = payMethodStr.substring(0, payMethodStr.lastIndexOf(";"));
		}
		if(payMethodStr.split(";").length>1){
			String[] arr = sort(payMethodStr);
			String temp = "";
			for (int i = 0; i < arr.length; i++) {
				temp+=arr[i]+";";
			}
			temp = temp.substring(0,temp.lastIndexOf(";"));
			payMethodStr = temp;
			minStr = arr[0].split(":")[0];
		}else {
			minStr = payMethodStr.split(":")[0];
		}
		ruleContent += "{\"enable\":true,\"paymethod\":"+payMethod+",\"rule\":";
		String rule = "\"";
		rule += payMethodStr;
		rule += "\"}&";
		ruleContent += rule;
		map.put("ruleContent", ruleContent);
		map.put("minStr", minStr);
		map.put("flag", true);
		return map;
	}

	/**
	 * 
	* @Title: sort 
	* @Description:对满减字符串进行排序
	* @param @param payMethodStr
	* @param @return
	* @return String[]    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	private static String[] sort(String payMethodStr) {
		String[] arr = payMethodStr.split(";");
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < arr.length; i++) {
			String item = arr[i];
			list.add(Integer.parseInt(item.split(":")[0]));
		}
		Collections.sort(list);
		List<String> sortList = new ArrayList<String>();
		for (Integer integer : list) {
			for (int i = 0; i < arr.length; i++) {
				if (Integer.parseInt(arr[i].split(":")[0])==integer) {
					sortList.add(arr[i]);
					break;
				}
			}
		}
		String[] arr2 = sortList.toArray(new String[sortList.size()]);
		return arr2;
	}

	/**
	 * 
	* @Title: JsonStrToStr 
	* @Description:将json字符串解析成普通字符串
	* @param @param ruleContent
	* @param @return
	* @param @throws Exception
	* @return String    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	/*public static String JsonStrToStr(String ruleContent) throws Exception{
		String returnStr = "";
		String[] jsonStr = ruleContent.split("&");
		for (int i = 0; i < jsonStr.length; i++) {
			String[] tempArr = jsonStr[i].split(",");
			String payMethod = tempArr[1];
			payMethod = payMethod.split(":")[1];
			returnStr+=payMethod+
		}
		return null;
	}*/
}
