package com.corner.kefu.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
* @ClassName: FileUpload 
* @Description:上传文件
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2015年12月27日 下午9:46:33 
*
 */
public class FileUpload {

	
	/**
	 * 
	* @Title: fileUp 
	* @Description:
	* @param @param file 文件对象
	* @param @param filePath 上传路径
	* @param @param fileName 文件名
	* @param @return
	* @return String
	* @author 杨开泰  yangkaitai@izjjf.cn
	* @throws
	 */
	public static String fileUp(MultipartFile file, String filePath, String fileName){
		String extName = ""; // 扩展名格式：
		try {
			if (file.getOriginalFilename().lastIndexOf(".") >= 0){
				extName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			}
			copyFile(file.getInputStream(), filePath, fileName+extName).replaceAll("-", "");
		} catch (IOException e) {
			//System.out.println(e);
		}
		return filePath+fileName+extName;
	}
	
	/**
	 * 写文件到当前目录的upload目录中
	* @Title: copyFile 
	* @Description:
	* @param @param in
	* @param @param dir
	* @param @param realName
	* @param @return
	* @param @throws IOException
	* @return String
	* @author 杨开泰  yangkaitai@izjjf.cn
	* @throws
	 */
	private static String copyFile(InputStream in, String dir, String realName)
			throws IOException {
		File file = new File(dir, realName);
		if (!file.exists()) {
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			file.createNewFile();
		}
		FileUtils.copyInputStreamToFile(in, file);
		return realName;
	}
}
