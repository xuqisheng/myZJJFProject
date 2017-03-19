package com.corner.task.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.task.beans.SKUActive;
import com.corner.task.beans.msg.ModelMsg;
import com.corner.task.beans.vo.SystemInfoVo;
import com.corner.task.config.SessionConfig;
import com.corner.task.dao.mg.SKUActiveMgMapper;
import com.corner.task.service.SKUActiveTaskService;
import com.corner.task.util.HttpRequestUtils;
import com.corner.task.util.JSONUtil;

@Service
public class SKUActiveTaskServiceImpl implements SKUActiveTaskService {
	private static final Logger log = LoggerFactory.getLogger(SKUActiveTaskService.class);
	@Autowired
	SKUActiveMgMapper skuActiveMgMapper;
	static final String key_SKUActiveTaskAuth="SKUActiveTaskAuth";
	static final String key_SKUActiveTaskAuthURL = "SKUActiveTaskAuthURL";
	
	static String url = "http://127.0.0.1:18080/zjjf-kefu/keFu/SKUActive/taskSKUActive.do";
	
	static String urlTaskAllSKUActive = "/zjjf-kefu/keFu/SKUActive/taskSKUActive.do";
	static String urlTaskEffecSKUActive = "/zjjf-kefu/keFu/SKUActive/taskEffecSKUActive.do";
	static String urlTaskInvalidSKUActive = "/zjjf-kefu/keFu/SKUActive/taskInvalidSKUActive.do";
	
	public String queryKefuHost(){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("id", key_SKUActiveTaskAuthURL);
		List<SystemInfoVo> list = skuActiveMgMapper.selectSKUActiveTaskAuth(param);
		if(null == list || list.size() == 0 || list.get(0) == null 
				|| list.get(0).getContent() == null || list.get(0).getContent().length() == 0){
			log.error("缺少配置信息;SystemInfo表，id=SKUActiveTaskAuthURL,content表示参数路径");
			return null;
		}
		String host = list.get(0).getContent();
		log.debug("task query kefu service host:" + host);
		return host;
	}
	
	public String queryKefuAuth(){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("id", key_SKUActiveTaskAuth);
		List<SystemInfoVo> list = skuActiveMgMapper.selectSKUActiveTaskAuth(param);
		if(null == list || list.size() == 0 || list.get(0) == null 
				|| list.get(0).getContent() == null || list.get(0).getContent().length() == 0){
			log.error("缺少配置信息;SystemInfo表，id=key_SKUActiveTaskAuth,content表示参数路径");
			return null;
		}
		String auth = list.get(0).getContent();
		return auth;
	}
	
	public ModelMsg taskSKUActive(){
		log.info("taskSKUActive ...............................................................................");
		Map<String,Object> param = new HashMap<String,Object>();
		List<SKUActive> allList = skuActiveMgMapper.getAllSKUActive(param);
		List<SKUActive> schedulerList = new ArrayList<SKUActive>();
		Date cur = new Date();
		for(SKUActive one:allList){
			final SKUActive copy = new SKUActive();
			BeanUtils.copyProperties(one, copy);
			//已审核,已经生效的任务需要启动定时任务处理。
			if(status_confirm.equals(one.getStatus().intValue()) || status_effec.equals(one.getStatus().intValue())){
				if(status_confirm.equals(one.getStatus().intValue())){
					if(one.getStartTime().compareTo(cur) < 0){
						//应该启动则立即启动
						taskEffecNow(copy);
					}else{
						//倒计时启动
						if(!SessionConfig.skuActiveMap.containsKey(copy.getId() + "_S")){
							SessionConfig.skuActiveMap.put(copy.getId() + "_S", copy);
							final Timer timerS = new Timer();
					    	final TimerTask taskS = new TimerTask() {
								@Override
								public void run() {
									SessionConfig.skuActiveMap.remove(copy.getId() + "_S");
									taskEffecNow(copy);
									timerS.cancel();
								}
							};
					    	timerS.schedule(taskS, copy.getStartTime());
						}
					}
				}else if(status_effec.equals(one.getStatus().intValue())){
					if(one.getEndTime().compareTo(cur) < 0){
						//应该停止则立即停止
						taskInvalidNow(copy);
					}else{
						//倒计时停止
						if(!SessionConfig.skuActiveMap.containsKey(copy.getId() + "_E")){
							SessionConfig.skuActiveMap.put(copy.getId() + "_E", copy);
							final Timer timerE = new Timer();
					    	final TimerTask taskE = new TimerTask() {
								@Override
								public void run() {
									SessionConfig.skuActiveMap.remove(copy.getId() + "_E");
									taskInvalidNow(copy);
									timerE.cancel();
								}
							};
					    	timerE.schedule(taskE, copy.getEndTime());
						}
					}
				}
			}
		}
		//计划生效所有的数据
		if(schedulerList != null && schedulerList.size() > 0){
			schedulerSKUActive(schedulerList);
		}
		ModelMsg msg = new ModelMsg(true,"处理单品促销活动任务成功");
		log.info("taskSKUActive end!");
		return msg;
	}
	
	public void schedulerSKUActive(final List<SKUActive> schedulerList){
		Runnable r = new Runnable() {
			@Override
			public void run() {
				try {
					timerTaskActive(schedulerList);
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		};
		r.run();
	}
	
	/**
	 * 模糊生效所有的
	 * @Title: taskAllSKUActive
	 * @date 2016年9月22日  下午2:11:50
	 * @author 小武
	 * @version  七彩虹
	 */
	public void taskAllSKUActive(){
		Runnable r = new Runnable() {
			@Override
			public void run() {
				String host = queryKefuHost();
				String auth = queryKefuAuth();
				Object object = HttpRequestUtils.httpPost("http://" + host + urlTaskAllSKUActive + "?auth=" + auth, null);
				log.info(JSONUtil.objectToJSONString(object)); 
			}
		};
		r.run();
	}
	/**
	 * 立即失效活动
	 * @Title: taskInvalidNow
	 * @date 2016年9月22日  下午2:11:20
	 * @author 小武
	 * @version  七彩虹
	 * @param one
	 */
	public void taskInvalidNow(final SKUActive one){
		Runnable r = new Runnable() {
			@Override
			public void run() {
				String host = queryKefuHost();
				String auth = queryKefuAuth();
				Object object = HttpRequestUtils.httpPost("http://" + host + urlTaskInvalidSKUActive + "?auth=" + auth+ "&id=" + one.getId(), null);
				log.info(JSONUtil.objectToJSONString(object));
				SessionConfig.skuActiveMap.remove(one.getId());
			}
		};
		r.run();
	}
	/**
	 * 立即生效活动
	 * @Title: taskEffecNow
	 * @date 2016年9月22日  下午2:11:36
	 * @author 小武
	 * @version  七彩虹
	 * @param one
	 */
	public void taskEffecNow(final SKUActive one){
		Runnable r = new Runnable() {
			@Override
			public void run() {
				String host = queryKefuHost();
				String auth = queryKefuAuth();
				Object object = HttpRequestUtils.httpPost("http://" + host + urlTaskEffecSKUActive + "?auth=" + auth+ "&id=" + one.getId(), null);
				log.info(JSONUtil.objectToJSONString(object)); 
				SessionConfig.skuActiveMap.remove(one.getId());
			}
		};
		r.run();
	}
	
	public ModelMsg effecSKUActive(){
		Object object = HttpRequestUtils.httpPost(url + "?auth=123", null);
		Map map2 = JSONUtil.JSONToObject(JSONUtil.objectToJSONString(object), Map.class);
		log.info(JSONUtil.objectToJSONString(map2));
		return null;
	}
	
	public ModelMsg invalidSKUActive(){
		Object object = HttpRequestUtils.httpPost(url + "?auth=123", null);
		Map map2 = JSONUtil.JSONToObject(JSONUtil.objectToJSONString(object), Map.class);
		log.info(JSONUtil.objectToJSONString(map2));
		return null;
	}
	
	public void timerTaskActive(List<SKUActive> listActive){
		
		for(final SKUActive active:listActive){
			if(SessionConfig.skuActiveMap.containsKey(active.getId())){
				continue;
			}
			SessionConfig.skuActiveMap.put(active.getId(), active);
	    	Date runDate = null;
	    	if(status_confirm.equals(active.getStatus().intValue())){
	    		runDate = active.getStartTime();
	    	}else if(status_effec.equals(active.getStatus().intValue())){
	    		runDate = active.getEndTime();
	    	}
	    	final Timer timer = new Timer();
	    	final TimerTask task = new TimerTask() {
				@Override
				public void run() {
					if(status_confirm.equals(active.getStatus().intValue())){
						taskEffecNow(active);
			    	}else if(status_effec.equals(active.getStatus().intValue())){
			    		taskInvalidNow(active); 
			    	}
					timer.cancel();
				}
			};
	    	timer.schedule(task, runDate);
		}
	}
}
