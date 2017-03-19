package com.corner.salesman.commons.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * 对象操作工具类, 继承org.apache.commons.lang3.ObjectUtils类
 * @author ThinkGem
 * @version 2014-6-29
 */
public class ObjectUtils extends org.apache.commons.lang3.ObjectUtils {

	/**
	 * 注解到对象复制，只复制能匹配上的方法。
	 * @param annotation
	 * @param object
	 */
	public static void annotationToObject(Object annotation, Object object){
		if (annotation != null){
			Class<?> annotationClass = annotation.getClass();
			Class<?> objectClass = object.getClass();
			for (Method m : objectClass.getMethods()){
				if (StringUtils.startsWith(m.getName(), "set")){
					try {
						String s = StringUtils.uncapitalize(StringUtils.substring(m.getName(), 3));
						Object obj = annotationClass.getMethod(s).invoke(annotation);
						if (obj != null && !"".equals(obj.toString())){
							if (object == null){
								object = objectClass.newInstance();
							}
							m.invoke(object, obj);
						}
					} catch (Exception e) {
						// 忽略所有设置失败方法
					}
				}
			}
		}
	}
	
	/**
	 * 序列化对象
	 * @param object
	 * @return
	 */
	public static byte[] serialize(Object object) {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			if (object != null){
				baos = new ByteArrayOutputStream();
				oos = new ObjectOutputStream(baos);
				oos.writeObject(object);
				return baos.toByteArray();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 反序列化对象
	 * @param bytes
	 * @return
	 */
	public static Object unserialize(byte[] bytes) {
		ByteArrayInputStream bais = null;
		try {
			if (bytes != null && bytes.length > 0){
				bais = new ByteArrayInputStream(bytes);
				ObjectInputStream ois = new ObjectInputStream(bais);
				return ois.readObject();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*** 
     * 判断 String 是否是 int 
     *  
     * @param input 
     * @return 
     */  
    public static boolean isInteger(String input){  
        Matcher mer = Pattern.compile("^[+-]?[0-9]+$").matcher(input);  
        return mer.find();  
    }
    
	/** 
     * 格式化金额         
     * @param s 
     * @param len 
     * @return 
     */  
    public static String formatMoney(String s, int len)   
    {  
        if (s == null || s.length() < 1) {  
            return "";  
        }  
        NumberFormat formater = null;  
        double num = Double.parseDouble(s);  
        if (len == 0) {  
            formater = new DecimalFormat("###,###");  
  
        } else {  
            StringBuffer buff = new StringBuffer();  
            buff.append("###,###.");  
            for (int i = 0; i < len; i++) {  
                buff.append("#");  
            }  
            formater = new DecimalFormat(buff.toString());  
        }  
        String result = formater.format(num);  
        if(result.indexOf(".") == -1)  
        {  
            result = "￥" + result + ".00";  
        }  
        else  
        {  
            result = "￥" + result;  
        }  
        return result;  
    }  
    
	/** 
     * 格式化金额         
     * @param s 
     * @param len 
     * @return 
     */  
    public static String formatMoney(String s)   
    {  
        if (s == null || s.length() < 1) {  
            return "";  
        }  
        NumberFormat formater = null;  
        double num = Double.parseDouble(s);  
        formater = new DecimalFormat("###");  
        String result = formater.format(num);  
        return result;  
    } 
    
    /*public static void main(String[] args) {
		System.err.println(ObjectUtils.isInteger("55o"));
	}*/
}
