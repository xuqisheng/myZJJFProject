package com.corner.kefu.utils;

import java.io.File;

/**
 * 
* @ClassName: PathUtil 
* @Description:路径工具类
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2015年12月27日 下午9:20:02 
*
 */
public class PathUtil {

	
	/**
	 * 
	* @Title: getClasspath 
	* @Description: 获取classPath
	* @param @return
	* @return String
	* @author 杨开泰  yangkaitai@izjjf.cn
	* @throws
	 */
	public static String getClasspath(){
		String path = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../").replaceAll("file:/", "").replaceAll("%20", " ").trim();	
		if(path.indexOf(":") != 1){
			path = File.separator + path;
		}
		return path;
	}
	
	public static void main(String[] args) {
		System.out.println(PathUtil.getClasspath());
	}
}
