/**   
* @Title: SystemInfoServiceImpl.java 
* @Package com.corner.kefu.service.impl 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年3月24日 上午10:46:39 
* @version V1.0   
*/

package com.corner.kefu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.SystemInfo;
import com.corner.core.dao.SystemInfoMapper;
import com.corner.kefu.dao.SystemInfoMgMapper;
import com.corner.kefu.service.SystemInfoService;

/** 
* @ClassName: SystemInfoServiceImpl 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年3月24日 上午10:46:39 
*  
*/
@Service
public class SystemInfoServiceImpl extends BaseServiceImpl implements SystemInfoService {
 
	@Autowired
	SystemInfoMgMapper systemInfoMgMapper;
	
	@Autowired
	SystemInfoMapper systemInfoMapper;

	@Override
	public SystemInfo getKDB_Price_Task(String key) throws Exception {
		return systemInfoMapper.selectByPrimaryKey(key);
	}

	@Override
	public int updateSystemInfo(SystemInfo systemInfo){
		return systemInfoMapper.updateByPrimaryKeySelective(systemInfo);
	}

	@Override
	public SystemInfo getSystemInfo(String systemKey) {
		return systemInfoMapper.selectByPrimaryKey(systemKey);
	}
}
