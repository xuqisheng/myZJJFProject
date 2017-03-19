package com.corner.salesman.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.corner.salesman.common.config.Global;

/**
 * 百度工具类
 * 
 * @author xuyw
 * @email xyw10000@163.com
 * @date 2014-06-22
 */
public class BaiDuUtil {
  public static String getCity(String lat, String lng) {
    JSONObject obj = getLocationInfo(lat, lng).getJSONObject("result")
        .getJSONObject("addressComponent");
    return obj.getString("city");
  }

  public static JSONObject getLocationInfo(String lat, String lng) {
    String url = "http://api.map.baidu.com/geocoder/v2/?location=" + lat + ","
        + lng + "&output=json&ak="+Global.getConfig("baidu.map.ak")+"&pois=0";
    JSONObject obj = JSONObject.parseObject(HttpUtil.getRequest(url));
    return obj;
  }
  
  public static String getLocationName(String lat, String lng) {
	    String url = "http://api.map.baidu.com/geocoder/v2/?location=" + lat + ","
	        + lng + "&output=json&ak="+Global.getConfig("baidu.map.ak")+"&pois=0";
	    JSONObject obj = JSONObject.parseObject(HttpUtil.getRequest(url));
	    
	    String sematic_description = obj.getJSONObject("result")
	    .getString("sematic_description");
	    return sematic_description;
	  }

  public static void main(String[] args) {
   // System.out.println(BaiDuUtil.getCity("28.694439", "115.939728"));
    //System.out.println(BaiDuUtil.getLocationInfo("28.694439", "115.939728"));
	    /*Double longitude = 113.951472;//经度
	    Double latitude = 22.535981;//纬度
		if(null != longitude && null != latitude){
			BaiDuUtil.getLocationInfo(latitude.toString(), longitude.toString());
		}*/
  }
}