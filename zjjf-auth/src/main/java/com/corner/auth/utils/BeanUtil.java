package com.corner.auth.utils;
 
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
 
/**
 * 
* @ClassName: BeanUtil 
* @Description: 对象转换Util
* @date 2016年1月9日 下午2:14:11 
 */
public class BeanUtil {
 
    public static BeanUtil m = new BeanUtil();
 
    static {
        // 在封装之前 注册转换器
        ConvertUtils.register(new DateTimeConverter(), java.util.Date.class);
    }
    /**
     * 
    * @Title: toMap 
    * @Description: 实体转换为Map 返回Map无空串
    * @param	Object obj	
    * @return	设定文件 
    * @return Map<String,Object>    返回类型 
    * @author 孟星魂	mengxinghun@izjjf.cn
     */
    public static Map<String, Object> toMapNotNull(Object obj){
    	return toMap(obj, false);
    }
    /**
     * 
    * @Title: toMap 
    * @Description: 实体转换为Map 返回Map有空串
    * @param	Object obj	
    * @return	设定文件 
    * @return Map<String,Object>    返回类型 
    * @author 孟星魂	mengxinghun@izjjf.cn
     */
    public static Map<String, Object> toMapHaveNull(Object obj){
    	return toMap(obj, true);
    }
    /**
     * 
    * @Title: toMap 
    * @Description: 实体转换为Map 
    * @param	Object obj	
    * @param	haveNull	是否有NULL值	有传true	无传false
    * @return	设定文件 
    * @return Map<String,Object>    返回类型 
    * @author 孟星魂	mengxinghun@izjjf.cn
     */
	private static Map<String, Object> toMap(Object obj , boolean haveNull){
    	try {
    		if(obj == null)  
                return null;      
            Map<String, Object> map = new HashMap<String, Object>();   
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());    
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();    
            for (PropertyDescriptor property : propertyDescriptors) {    
                String key = property.getName();    
                if (key.compareToIgnoreCase("class") == 0) {   
                    continue;  
                }
                Method getter = property.getReadMethod();
                if(!haveNull){
                	if(getter == null || getter.invoke(obj) == null)
                		continue;
                }
                Object value = getter!=null ? getter.invoke(obj) : null;  
                map.put(key, value);
            }    
            return map;  
		} catch (Exception e) {
			e.printStackTrace();
		} 
    	return null;
    }
	
    /**
     * 将Map对象转换成指定对象
     * @param clazz，指定的转换后的对象。
     * @param map，被转换信息的map。
     * @return clazz参数指定的对象。
     * @since 1.0.0
     */
    public static <E> E toObject(Class<E> clazz, Map<String, Object> fromMap) {
 
        if (clazz == null || fromMap == null)
            return null;
 
        E obj = null;
        try {
            obj = clazz.newInstance();
            BeanUtils.populate(obj, fromMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
 
    /**
     * 将对象转换成指定的对象。
     * @param clazz，指定转换成的对象。
     * @param obj，被转换的对象。
     * @return clazz参数指定转换成的对象。
     * @since 1.0.0 
     */
    public static <E> E toObject(Class<E> clazz, Object obj) {
        if (clazz == null || obj == null)
            return null;
        E dest = null;
        try {
            dest = clazz.newInstance();
            BeanUtils.copyProperties(dest, obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dest;
    }
 
    /**
     * 将原始数据的List转换成指定对象的List。
     * @param clazz，指定转换成的对象。
     * @param objList，原始数据List集合。
     * @return List list内每个对象元素为clazz参数指定的对象。
     * @since 1.0.0 
     */
    public static <E> List<E> toObject(Class<E> clazz, List<?> objList) {
        List<E> ret = new ArrayList<E>();
        if (clazz == null || objList == null || objList.size() == 0)
            return ret;
 
        for (Object obj : objList) {
            E e = toObject(clazz, obj);
            ret.add(e);
        }
        return ret;
    }
}