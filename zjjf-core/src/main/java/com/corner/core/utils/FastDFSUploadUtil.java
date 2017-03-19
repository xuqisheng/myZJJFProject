/**
 * 
 */
package com.corner.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.corner.core.config.PropertieNameConts;
import com.corner.core.config.UploadKeys;
import com.corner.core.enums.UploadType;
import com.corner.core.utils.fastdfs.FastDFSFile;
import com.corner.core.utils.fastdfs.FileManager;

/**
 * 
* @ClassName: FastDFSUploadUtil
* @Description: TODO(这里用一句话描述这个类的作用)
* @author luke
* @email   luke@mibodoctor.com  
* @date 2015年2月25日 上午11:44:55
*
 */
public class FastDFSUploadUtil {

	private static Logger logger = LoggerFactory.getLogger(FastDFSUploadUtil.class);

	public static String getReadPath(UploadType uploadType) {
		return PropertiesCacheUtil.getValue(UploadKeys.FastdfsPath, PropertieNameConts.UPLOAD);
	}
	public static String getReadPath() {
		return PropertiesCacheUtil.getValue(UploadKeys.FastdfsPath, PropertieNameConts.UPLOAD);
	}

	/**
	 * 获取文件扩展名
	 * 
	 * @param fileName
	 * @return
	 */
	private static String getExt(String fileName) {
		// 获取上传文件类型的扩展名,先得到.的位置，再截取从.的下一个位置到文件的最后，最后得到扩展名
		String ext = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
		if (ext.contains("?")) {
			ext = ext.substring(0, ext.indexOf("?"));
		}
		// 对扩展名进行小写转换
		return ext.toLowerCase();
	}

	/**
	 * 检查上传文件类型是否在我的配置允许中
	 * 
	 * @param imgFile
	 *            上传文件类型
	 * @return 通过 true 不通过 flase
	 */
	public static boolean checkType(MultipartFile imgFile) {
		String type = PropertiesCacheUtil.getValue(UploadKeys.DOCTOR_REGISTER_UPLOAD_TYPE, PropertieNameConts.UPLOAD);
		String fileName = imgFile.getOriginalFilename();
		// 获取上传文件类型的扩展名,先得到.的位置，再截取从.的下一个位置到文件的最后，最后得到扩展名
		String ext = getExt(fileName);
		if (type.indexOf(ext) != -1) {
			return true;
		}
		logger.info(fileName);
		return false;
	}

	public static String saveFile(MultipartFile imgFile, UploadType uploadType) throws IOException {
		String ext = getExt(imgFile.getOriginalFilename());
		FastDFSFile fastDFSFile = new FastDFSFile(imgFile.getOriginalFilename(), imgFile.getBytes(), ext);
		return FileManager.upload(fastDFSFile);
	}

	public static String saveFile(File imgFile) throws IOException {
		String ext = getExt(imgFile.getName());
		FileInputStream fis = new FileInputStream(imgFile);
		byte[] file_buff = null;
		if(fis != null){
	    	int len = fis.available();
	    	file_buff = new byte[len];
	    	fis.read(file_buff);
	    }
		FastDFSFile fastDFSFile = new FastDFSFile(imgFile.getName(), file_buff, ext);
		return FileManager.upload(fastDFSFile);
	}

}
