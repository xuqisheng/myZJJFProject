/**   
* @Title: ScmsFreightTplMgServiceImpl.java 
* @Package com.corner.scms.service.sc.impl 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年1月21日 上午10:41:31 
* @version V1.0   
*/

package com.corner.scms.service.sc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.scms.dao.ScmsFreightTplMgMapper;
import com.corner.scms.service.impl.BaseServiceImpl;
import com.corner.scms.service.sc.ScmsFreightTplMgService;

/** 
* @ClassName: ScmsFreightTplMgServiceImpl 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年1月21日 上午10:41:31 
*  
*/
@Service
public class ScmsFreightTplMgServiceImpl extends BaseServiceImpl implements ScmsFreightTplMgService {

	@Autowired
	ScmsFreightTplMgMapper scmsFreightTplMgMapper;
}
