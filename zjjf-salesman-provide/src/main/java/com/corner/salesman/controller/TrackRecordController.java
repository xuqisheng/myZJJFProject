package com.corner.salesman.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.salesman.common.persistence.AppPage;
import com.corner.salesman.common.utils.AppJson;
import com.corner.salesman.common.utils.BaiDuUtil;
import com.corner.salesman.common.utils.Encodes;
import com.corner.salesman.common.utils.Json;
import com.corner.salesman.commons.persistence.Page;
import com.corner.salesman.model.TrackRecord;
import com.corner.salesman.service.TrackRecordService;

/**  
 * 创建时间：2015-1-28 下午1:17:27  
 * @author andy  
 * @version 2.2  
 */
@Controller
@RequestMapping("/mobile/track")
public class TrackRecordController {
	
	private static final Logger logger = LoggerFactory.getLogger(TrackRecordController.class);

	@Autowired
	private TrackRecordService trackRecordService;	
	        
	/**
	 * 添加轨迹信息
	 * @param trackRecord
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"addTrackRecord"})
	public Object addTrackRecord(TrackRecord trackRecord, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			//logger.info("提交新增轨迹信息为：{}", JSON.toJSON(trackRecord));
			boolean bool = false;
			//对app端转码的中文进行解码处理
			String positionName = trackRecord.getPositionName();
			//如果获取位置名称不为空，着进行转码处理
			if(StringUtils.isNotBlank(positionName)){
				positionName = Encodes.urlDecode(positionName);
				trackRecord.setPositionName(positionName);
			}else{
			//如果为空，则根据当前经纬度反地理编码获得对应位置名称
			    Double longitude = trackRecord.getLongitude();//经度
			    Double latitude = trackRecord.getLatitude();//纬度
				if(null != longitude && null != latitude){
					try{
						positionName = BaiDuUtil.getLocationName(latitude.toString(), longitude.toString());
					}catch (Exception e) {
						logger.error("反地理编码失败！");
					}
					//positionName = positionName==null?"":positionName;
					if(StringUtils.isNotBlank(positionName)){
						trackRecord.setPositionName(positionName);
					}
					
					bool = true;
				}
			}
			
			trackRecordService.addTrackRecord(trackRecord);
			json.setMsg("添加轨迹信息成功！");
			json.setSuccess(true);
			if(bool){
				Map<String,String> dataMap = new HashMap<String,String>();
				dataMap.put("positionName", positionName);
				json.setData(dataMap);
			}
		} catch (Exception e) {
			json.setMsg("添加轨迹信息异常！");
			json.setSuccess(false);
			json.setCode("500");
			logger.info("添加轨迹信息异常：{}",e.getMessage());
		}
		return json;
	}
	
	/**
	 * 添加轨迹信息
	 * @param trackRecord
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"addBatchTrackRecord"})
	public Object addBatchTrackRecord(TrackRecord trackRecord, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			String batchTrackStr = trackRecord.getBatchTrackStr();
			String deviceType = trackRecord.getDeviceType()==2?"IOS":"Android";
			logger.info("设备类型为：{}批量上传轨迹信息：{}",deviceType,batchTrackStr);
			
			trackRecordService.addBatchTrackRecord(trackRecord);
			json.setMsg("批量添加轨迹信息成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("批量添加轨迹信息异常！");
			json.setSuccess(false);
			json.setCode("500");
			logger.info("批量添加轨迹信息异常：{}",e.getMessage());
		}
		return json;
	}
	
	
	/**
	 * 获取个人轨迹列表信息
	 * @param trackRecord
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getMyTrackList"})
	public Object getMyTrackList(TrackRecord trackRecord, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			 Page<TrackRecord> page = trackRecordService.findTrackRecordPage(new Page<TrackRecord>(request, response), trackRecord);
			 AppPage<TrackRecord> target = new AppPage<TrackRecord>();
			 BeanUtils.copyProperties(page, target);
			 json.setData(target);
			 json.setMsg("获取个人轨迹列表信息成功！");
			 json.setSuccess(true);
			//logger.info("获取个人轨迹列表信息为：{}", JSONArray.toJSONString(json));
		} catch (Exception e) {
			json.setMsg("获取个人轨迹列表信息异常！");
			json.setSuccess(false);
			json.setCode("500");
			logger.info("获取个人轨迹列表信息异常：{}",e.getMessage());
		}
		return json;
	}
	
	/**
	 * 获取个人轨迹列表信息
	 * @param trackRecord
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getMyTrackDetailList"})
	public Object getMyTrackDetailList(TrackRecord trackRecord, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			 Page<TrackRecord> page = trackRecordService.findMyTrackDetailList(new Page<TrackRecord>(request, response), trackRecord);
			 AppPage<TrackRecord> target = new AppPage<TrackRecord>();
			 BeanUtils.copyProperties(page, target);
			 json.setData(target);
			 json.setMsg("获取个人轨迹列表信息成功！");
			 json.setSuccess(true);
			//logger.info("获取个人轨迹列表信息为：{}", JSONArray.toJSONString(json));
		} catch (Exception e) {
			json.setMsg("获取个人轨迹列表信息异常！");
			json.setSuccess(false);
			json.setCode("500");
			logger.info("获取个人轨迹列表信息异常：{}",e.getMessage());
		}
		return json;
	}
	
	/**
	 * 获取组员轨迹列表信息（组ID+时间）
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getGroupTrackList"})
	public Object getGroupTrackList(TrackRecord trackRecord, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			 Page<TrackRecord> page = trackRecordService.findGroupNewTrackByTime(new Page<TrackRecord>(request, response), trackRecord);
			 AppPage<TrackRecord> target = new AppPage<TrackRecord>();
			 BeanUtils.copyProperties(page, target);
			 json.setData(target);
			 /*List<TrackRecord> trackList = trackRecordService.findGroupNewTrackByTime(trackRecord);
			 AppObject<TrackRecord> trackObj = new AppObject<TrackRecord>();
			 trackObj.setList(trackList);
			 json.setData(trackObj);*/
			 json.setMsg("获取组员轨迹列表信息成功！");
			 json.setSuccess(true);
			//logger.info("获取组员轨迹列表信息为：{}", JSONArray.toJSONString(json));
		} catch (Exception e) {
			json.setMsg("获取组员轨迹列表信息异常！");
			json.setSuccess(false);
			json.setCode("500");
			logger.info("获取组员轨迹列表信息异常：{}",e.getMessage());
		}
		return json;
	}
	
	/**
	 * 获取组员每一天最新的轨迹列表
	 * @param trackRecord
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getMyEverydayTrack"})
	public Object getMyEverydayTrack(TrackRecord trackRecord, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			 Page<TrackRecord> page = trackRecordService.findUserEverydayTrackById(new Page<TrackRecord>(request, response), trackRecord);
			 AppPage<TrackRecord> target = new AppPage<TrackRecord>();
			 BeanUtils.copyProperties(page, target);
			 json.setData(target);
			 json.setMsg("获取业务员每天轨迹列表成功！");
			 json.setSuccess(true);
			//logger.info("获取业务员每天轨迹列表信息为：{}", JSONArray.toJSONString(json));
		} catch (Exception e) {
			json.setMsg("获取业务员每天轨迹列表信息异常！");
			json.setSuccess(false);
			json.setCode("500");
			logger.info("获取业务员每天轨迹列表信息异常：{}",e.getMessage());
		}
		return json;
	}
	
	/**
	 * 获取个人全天的轨迹坐标列表信息
	 * @param trackRecord
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getMyAllDateTrackList"})
	public Object getMyAllDateTrackList(TrackRecord trackRecord, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			 List<TrackRecord> list = trackRecordService.findMyAllDateTrackList(trackRecord);
			 AppJson trackObj = new AppJson();
			 trackObj.setList(list);
			 json.setData(trackObj);
			 json.setMsg("获取个人轨迹列表信息成功！");
			 json.setSuccess(true);
			//logger.info("获取个人轨迹列表信息为：{}", JSONArray.toJSONString(json));
		} catch (Exception e) {
			json.setMsg("获取个人轨迹列表信息异常！");
			json.setSuccess(false);
			json.setCode("500");
			logger.info("获取个人轨迹列表信息异常：{}",e.getMessage());
		}
		return json;
	}
	
}
