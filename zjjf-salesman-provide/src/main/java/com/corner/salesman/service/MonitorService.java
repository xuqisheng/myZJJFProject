package com.corner.salesman.service;

import com.corner.salesman.model.Monitor;

import java.util.HashMap;
import java.util.List;

/**  
 * @desc  消息监控业务层接口
 * 创建时间：2015-1-27 下午5:15:03  
 * @author 元宝  
 * @version 2.2  
 */

public interface MonitorService {
	
	/**
	 * 获取用户首页消息列表数据
	 * @param monitor
	 * @return
	 */
    public List<HashMap<String,Object>> getMyNewsList(Monitor monitor) throws Exception;
    
}
