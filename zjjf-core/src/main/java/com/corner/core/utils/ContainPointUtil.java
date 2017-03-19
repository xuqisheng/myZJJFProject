package com.corner.core.utils;

public class ContainPointUtil {
	 /// <summary> 
    /// <para>判断点是否在多边形的范围内</para> 
    /// <para>返回值：值为 1 表示点在多边形范围内</para> 
    /// <para>值为 0 表示点在多边形边上</para> 
    /// <para>值为-1 表示点不在多边形范围内</para> 
    /// </summary> 
    /// <param name="point">点坐标长度为 2</param> 
    /// <param name="polyline">多边形节点坐标长度为 2*n其中 n 应大于或等于 3即至少为三角形</param> 
    /// <returns> 
    public int PolygonIsContainPoint(double[] point,Double[] polyline) 
    { 
        int result = -1, count = 0, pointcount = 0, tempI; 
        double maxx = 0, minx = 0, maxy = 0, miny = 0; 
        if (polyline != null) 
        { 
            int i; 
            pointcount = polyline.length/ 2; 
            maxx = minx = polyline[0]; 
            maxy = miny = polyline[1];                     
            for (i = 0; i < pointcount; i++) 
            { 
                tempI = i + i; 
                if (maxx < polyline[tempI]) 
                    maxx = polyline[tempI]; 
                if (minx > polyline[tempI]) 
                    minx = polyline[tempI]; 
                if (maxy < polyline[tempI + 1]) 
                    maxy = polyline[tempI + 1]; 
                if (miny > polyline[tempI + 1]) 
                    miny = polyline[tempI + 1]; 
            } 
        } 
        if (point != null) 
        { 
             
            //首先判断是否在面的外框范围内 
            if (point[0] < minx || point[0] > maxx 
                || point[1] < miny || point[1] > maxy) 
            { 
                return result; 
            } 
            else 
            { 
                int i, j; 
                j = pointcount - 1; 
                double[] point1, point2; 
                double tempValue; 
                for (i = 0; i < pointcount; i++) 
                { 
                    point1 = new double[2]; 
                    point2 = new double[2]; 
                    tempI = i + i; 
                    point1[0] = polyline[tempI]; 
                    point1[1] = polyline[tempI + 1]; 
                    tempI = j + j; 
                    point2[0] = polyline[tempI]; 
                    point2[1] = polyline[tempI + 1]; 
                    if ((point1[0] < point[0] && point2[0] >= point[0]) 
                        || (point2[0] < point[0] && point1[0] >= point[0])) 
                    { 
                        tempValue=point1[1] + (point[0] - point1[0]) / (point2[0] - point1[0]) * (point2[1] - 
point1[1]); 
                        if (tempValue < point[1]) 
                        {                                     
                            count++; 
                        } 
                        else if (tempValue == point[1]) 
                        { 
                            count = -1; 
                            break; 
                        } 
                    }                             
                    j = i; 
                } 
            } 
        } 
        return count == -1 ? 0 : count % 2 == 0 ? -1 : 1;
    } 
    
    private static double EARTH_RADIUS = 6378.137;  
    
    private static double rad(double d) {  
        return d * Math.PI / 180.0;  
    }  
  
    /**
     * 根据二点的经纬度计算距离
     * @author Howe at 2015年2月25日下午4:39:59
     * @Email itzihao@sina.com
     * @Desc
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return
     */
    public double getDistance(double lat1, double lng1, double lat2,  
            double lng2) {  
        double radLat1 = rad(lat1);  
        double radLat2 = rad(lat2);  
        double a = radLat1 - radLat2;  
        double b = rad(lng1) - rad(lng2);  
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)  
                + Math.cos(radLat1) * Math.cos(radLat2)  
                * Math.pow(Math.sin(b / 2), 2)));  
        s = s * EARTH_RADIUS;  
    	s *= 1000;
        s = Math.round(s * 10000) / 10000;  
        return s;  
    }  
}
