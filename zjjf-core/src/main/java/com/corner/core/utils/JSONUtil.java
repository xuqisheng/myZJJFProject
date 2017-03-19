package com.corner.core.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @ClassName: JSONUtil
 * @Description: TODO(输入一个对象，返回json string)
 * @author luke
 * @email luke@mibodoctor.com
 * @date 2015年2月4日 下午12:31:46
 * 
 */
public class JSONUtil {

	private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static final ObjectMapper objectMapper;

	static {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		objectMapper = new ObjectMapper();
		objectMapper.setDateFormat(dateFormat);
	}
      
    public static <T> T JSONToObject(String json,Class<T> clazz) {  
        try {  
            return objectMapper.readValue(json, clazz);  
        } catch (IOException e) {  
            throw new RuntimeException("将对象转换为json字符时失败!");  
        }  
    }  
	public static String objectToJSONString(Object obj) {
		String result = "";
		try {
			result = objectMapper.writeValueAsString(obj);
		} catch (Exception e) {
			result = "";
		}
		return result;
	}

}
