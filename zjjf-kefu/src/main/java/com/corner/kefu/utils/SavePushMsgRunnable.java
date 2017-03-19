/**   
 * @Title: SavePushMsgRunnable.java 
 * @Package com.corner.pc.web.controller.util 
 * @Description:
 * @author 杨开泰  yangkaitai@izjjf.cn   
 * @date 2015年12月16日 上午11:07:10 
 * @version V1.0   
 */
package com.corner.kefu.utils;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.corner.core.beans.SpPushMsg;
import com.corner.core.dao.SpPushMsgMapper;
import com.corner.kefu.dao.sp.SpSpMsgMgMapper;

/**
 * @ClassName: SavePushMsgRunnable
 * @Description:保存推送消息
 * @author 杨开泰 yangkaitai@izjjf.cn
 * @date 2015年12月16日 上午11:07:10
 */
public class SavePushMsgRunnable implements Runnable {
	private static Logger logger = LoggerFactory.getLogger(SavePushMsgRunnable.class);

	private Map<String, Object> map = null;
 
	//SpMsgDao spMsgDao;
	
	public SavePushMsgRunnable() {
		super();
	}

	public SavePushMsgRunnable(Map<String, Object> map) {
		super();
		this.map = map;
	}

	@Override
	public void run() {
		/**
		 * 1.插入SpPushMsg表
		 * 2.维护SpPushMsgMap关系
		 */
		logger.info("保存推送消息开始==================================");
		SpPushMsgMapper spMsgDao = (SpPushMsgMapper) map.get("spPushMsgMapper");
		SpSpMsgMgMapper spSpMsgMgMapper = (SpSpMsgMgMapper) map.get("spSpMsgMgMapper");
		if(!(Boolean)map.get("flag")){
			spMsgDao.insertSelective((SpPushMsg)map.get("spPushMsg"));
		}else {
            spMsgDao.updateByPrimaryKeySelective((SpPushMsg)map.get("spPushMsg"));			
		}
		spSpMsgMgMapper.saveSpPushMsgMap(map);
		logger.info("保存推送消息结束==================================");
	}

}
