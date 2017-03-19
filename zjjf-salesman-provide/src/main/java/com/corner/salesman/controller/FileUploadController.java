package com.corner.salesman.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.salesman.common.utils.Json;
import com.corner.salesman.common.utils.UploadUtils;


/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/mobile/fileUpload")
public class FileUploadController{

	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

	/**
	 * 
	* @Title: uploadTest
	* @Author yuanbao@mibodoctor.com
	* @Description: 文件上传接口
	* @param @param request
	* @param @return
	* @param @throws IOException    设定文件
	* @return Object    返回类型
	* @throws
	 */ 
	@RequestMapping(value = "/upload.do")
	@ResponseBody
	public Object uploadFile(HttpServletRequest request) throws IOException {
		Json json = null;
		
		try {
			json = UploadUtils.uploadAppFile(request);
			if(null == json){
				json = new Json();
				json.setMsg("图片上传失败！");
				json.setSuccess(false);
				json.setCode("500");
			}
		} catch (Exception e) {
			logger.error("图片上传失败",e);
			json = new Json();
			json.setMsg("图片上传失败！");
			json.setSuccess(false);
			json.setCode("500");
		}
		return json;
	}
	
}
