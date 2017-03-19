package com.corner.salesman.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONArray;
import com.corner.salesman.common.config.Global;
import com.corner.salesman.common.utils.Json;
import com.corner.salesman.common.utils.http.HttpUtils;
import com.corner.salesman.common.utils.http.ReponseHandlerString;
import com.corner.salesman.model.AppVersionBean;

/**
 * 公告信息Controller
 * @author 元宝
 * @version 2016-01-26
 */
@Controller
@RequestMapping("/mobile/version")
public class AppVersionController {
	
	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	
	/**
	 * 给原生APP使用，后端做统一管理，不再依赖第三方平台
	 * @param request
	 * @param plant andriod/ios
	 * @return
	 */
	@RequestMapping(value = "/getKDBVersionInfo.do")
	@ResponseBody
	public Object getKDBVersionInfo(HttpServletRequest request,String plant) {
		Json json = new Json();
		try {
			if(StringUtils.isEmpty(plant)){
				json.setCode("500");
				json.setMsg("参数错误plant不能为空!");
				json.setSuccess(false);
			}
			HttpGet get = null;
			if("android".equals(plant)){
				String kdbUpdateUrl_andriod= Global.getConfig("kdbUpdateUrl_android");
				get = new HttpGet(kdbUpdateUrl_andriod);
			}else if("ios".equals(plant)){
				String kdbUpdateUrl_ios= Global.getConfig("kdbUpdateUrl_ios");
				get = new HttpGet(kdbUpdateUrl_ios);
			}else{
				json.setCode("500");
				json.setMsg("参数错误!");
				json.setSuccess(false);
			}
			if(get == null){
				logger.error("buildRequest  response is null");
				//return ResponseUtils.sendMsg(false,"response is null");	
				json.setCode("500");
				json.setMsg("response is null");
				json.setSuccess(false);
			}else{
				CloseableHttpClient httpclient = HttpUtils.getHttpClient();
				String resultString = httpclient.execute(get, new ReponseHandlerString());
				Map map = JSONArray.parseObject(resultString, Map.class);
				AppVersionBean avBean=new AppVersionBean();
				avBean.setName(map.get("name")+"");
				avBean.setVersion(map.get("version")+"");
				avBean.setVersionShort(map.get("versionShort")+"");
				avBean.setChangelog(map.get("changelog")+"");
				avBean.setBuild(map.get("build")+"");
				avBean.setInstallUrl(map.get("installUrl")+"");
				avBean.setUpdatedTime(map.get("updated_at")+"");
				avBean.setUpdateUrl(map.get("update_url")+"");
				json.setMsg("获取应用包更新信息成功!");
				json.setSuccess(true);
				json.setData(avBean);
			}
		} catch (Exception e) {
			logger.error("【版本检查】出现异常",e);
			json.setCode("500");
			json.setMsg("请求异常!");
			json.setSuccess(false);
		}
		return json;
	}
}
