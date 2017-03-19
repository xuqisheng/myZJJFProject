/**   
* @Title: ScmsPlantProtocoMgServiceImpl.java 
* @Package com.corner.scms.service.sp.impl 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年2月23日 下午2:10:03 
* @version V1.0   
*/

package com.corner.scms.service.sp.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.PlantProtocol;
import com.corner.scms.dao.ScmsPlantProtocolMgMapper;
import com.corner.scms.service.impl.BaseServiceImpl;
import com.corner.scms.service.sp.ScmsPlantProtocoMgService;

/** 
* @ClassName: ScmsPlantProtocoMgServiceImpl 
* @Description:进销存协议接口实现类
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年2月23日 下午2:10:03 
*  
*/
@Service
public class ScmsPlantProtocoMgServiceImpl extends BaseServiceImpl implements ScmsPlantProtocoMgService {
 
	@Autowired
	ScmsPlantProtocolMgMapper scmsPlantProtocolMgMapper;
	
	/**
	 * 
	* Title: getLastPlantProtocol 
	* Description:获取最新协议 
	* @return
	* @throws Exception 
	* @see com.corner.scms.service.sp.ScmsPlantProtocoMgService#getLastPlantProtocol()
	 */
	@Override
	public PlantProtocol getLastPlantProtocol() throws Exception {
		return scmsPlantProtocolMgMapper.getLastPlantProtocol();
	}

}
