package com.corner.salesman.common.utils;
import java.util.HashMap;
import java.util.Map;
/**
 * 地图距离计算工具类
 * 
 * @author Administrator
 *
 */
public class MapDistance { 
       
    private static double EARTH_RADIUS = 6378.137; 
   
    private static double rad(double d) { 
        return d * Math.PI / 180.0; 
    }
    
   /** 
    * 基于余弦定理求两经纬度距离 
    * @param lon1 第一点的精度 
    * @param lat1 第一点的纬度 
    * @param lon2 第二点的精度 
    * @param lat3 第二点的纬度 
    * @return 返回的距离，单位km 
    * */  
   public static double LantitudeLongitudeDist(String lng1Str, String lat1Str,String lng2Str, String lat2Str) {  
	   Double lat1 = Double.parseDouble(lat1Str);
       Double lng1 = Double.parseDouble(lng1Str);
       Double lat2 = Double.parseDouble(lat2Str);
       Double lng2 = Double.parseDouble(lng2Str);
       
	   
       double radLat1 = rad(lat1);  
       double radLat2 = rad(lat2);  
 
       double radLon1 = rad(lng1);  
       double radLon2 = rad(lng2);  
 
       if (radLat1 < 0)  
           radLat1 = Math.PI / 2 + Math.abs(radLat1);// south  
       if (radLat1 > 0)  
           radLat1 = Math.PI / 2 - Math.abs(radLat1);// north  
       if (radLon1 < 0)  
           radLon1 = Math.PI * 2 - Math.abs(radLon1);// west  
       if (radLat2 < 0)  
           radLat2 = Math.PI / 2 + Math.abs(radLat2);// south  
       if (radLat2 > 0)  
           radLat2 = Math.PI / 2 - Math.abs(radLat2);// north  
       if (radLon2 < 0)  
           radLon2 = Math.PI * 2 - Math.abs(radLon2);// west  
       double x1 = EARTH_RADIUS * Math.cos(radLon1) * Math.sin(radLat1);  
       double y1 = EARTH_RADIUS * Math.sin(radLon1) * Math.sin(radLat1);  
       double z1 = EARTH_RADIUS * Math.cos(radLat1);  
 
       double x2 = EARTH_RADIUS * Math.cos(radLon2) * Math.sin(radLat2);  
       double y2 = EARTH_RADIUS * Math.sin(radLon2) * Math.sin(radLat2);  
       double z2 = EARTH_RADIUS * Math.cos(radLat2);  
 
       double d = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)+ (z1 - z2) * (z1 - z2));  
       //余弦定理求夹角  
       double theta = Math.acos((EARTH_RADIUS * EARTH_RADIUS + EARTH_RADIUS * EARTH_RADIUS - d * d) / (2 * EARTH_RADIUS * EARTH_RADIUS));  
       double dist = theta * EARTH_RADIUS;  
       dist = dist * 1000;      //换算成米
       return dist;  //得到千米数
   }
   
   /** 
    * 基于余弦定理求两经纬度距离 
    * @param lon1 第一点的精度 
    * @param lat1 第一点的纬度 
    * @param lon2 第二点的精度 
    * @param lat3 第二点的纬度 
    * @return 返回的距离，单位km 
    * */  
   public static double LantitudeLongitudeDist(Double lng1, Double lat1,Double lng2, Double lat2) {  
	   
       double radLat1 = rad(lat1);  
       double radLat2 = rad(lat2);  
 
       double radLon1 = rad(lng1);  
       double radLon2 = rad(lng2);  
 
       if (radLat1 < 0)  
           radLat1 = Math.PI / 2 + Math.abs(radLat1);// south  
       if (radLat1 > 0)  
           radLat1 = Math.PI / 2 - Math.abs(radLat1);// north  
       if (radLon1 < 0)  
           radLon1 = Math.PI * 2 - Math.abs(radLon1);// west  
       if (radLat2 < 0)  
           radLat2 = Math.PI / 2 + Math.abs(radLat2);// south  
       if (radLat2 > 0)  
           radLat2 = Math.PI / 2 - Math.abs(radLat2);// north  
       if (radLon2 < 0)  
           radLon2 = Math.PI * 2 - Math.abs(radLon2);// west  
       double x1 = EARTH_RADIUS * Math.cos(radLon1) * Math.sin(radLat1);  
       double y1 = EARTH_RADIUS * Math.sin(radLon1) * Math.sin(radLat1);  
       double z1 = EARTH_RADIUS * Math.cos(radLat1);  
 
       double x2 = EARTH_RADIUS * Math.cos(radLon2) * Math.sin(radLat2);  
       double y2 = EARTH_RADIUS * Math.sin(radLon2) * Math.sin(radLat2);  
       double z2 = EARTH_RADIUS * Math.cos(radLat2);  
 
       double d = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)+ (z1 - z2) * (z1 - z2));  
       //余弦定理求夹角  
       double theta = Math.acos((EARTH_RADIUS * EARTH_RADIUS + EARTH_RADIUS * EARTH_RADIUS - d * d) / (2 * EARTH_RADIUS * EARTH_RADIUS));  
       double dist = theta * EARTH_RADIUS;  
       dist = dist * 1000;      //换算成米
       return dist;  //得到千米数
   }
     
    /**
     * 获取当前用户一定距离以内的经纬度值
     * 单位米 return minLat
     * 最小经度 minLng
     * 最小纬度 maxLat
     * 最大经度 maxLng
     * 最大纬度 minLat
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map getAround(String latStr, String lngStr, String raidus) {
        Map map = new HashMap();
         
        Double latitude = Double.parseDouble(latStr);// 传值给经度
        Double longitude = Double.parseDouble(lngStr);// 传值给纬度
 
        Double degree = (24901 * 1609) / 360.0; // 获取每度
        double raidusMile = Double.parseDouble(raidus);
         
        Double mpdLng = Double.parseDouble((degree * Math.cos(latitude * (Math.PI / 180))+"").replace("-", ""));
        Double dpmLng = 1 / mpdLng;
        Double radiusLng = dpmLng * raidusMile;
        //获取最小经度
        Double minLat = longitude - radiusLng;
        // 获取最大经度
        Double maxLat = longitude + radiusLng;
         
        Double dpmLat = 1 / degree;
        Double radiusLat = dpmLat * raidusMile;
        // 获取最小纬度
        Double minLng = latitude - radiusLat;
        // 获取最大纬度
        Double maxLng = latitude + radiusLat;
         
        map.put("minLat", minLat+"");
        map.put("maxLat", maxLat+"");
        map.put("minLng", minLng+"");
        map.put("maxLng", maxLng+"");
         
        return map;
    }
     
    public static void main(String[] args) {
        //济南国际会展中心经纬度：117.11811  36.68484
        //趵突泉：117.00999000000002  36.66123
        double lng1 = 113.931239;
  	    double lat1 = 22.537754;
  	    double lng2 = 113.930323;
  	    double lat2 = 22.533661;
//        System.out.println(LantitudeLongitudeDist(lng1+"",lat1+"",lng2+"",lat2+""));
        System.out.println(LantitudeLongitudeDist(lng1,lat1,lng2,lat2));
         
        //System.out.println(getAround("117.11811", "36.68484", "13000"));
        //117.01028712333508(Double), 117.22593287666493(Double),
        //36.44829619896034(Double), 36.92138380103966(Double)
         
    }
     
}