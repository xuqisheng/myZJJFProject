/**   
* @Title: ScmsSpWalletLogMgServiceImpl.java 
* @Package com.corner.scms.service.sp.impl 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年3月21日 上午10:32:45 
* @version V1.0   
*/

package com.corner.scms.service.sp.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.SpWalletLog;
import com.corner.core.dao.SpWalletLogMapper;
import com.corner.scms.service.impl.BaseServiceImpl;
import com.corner.scms.service.sp.ScmsSpWalletLogMgService;

/** 
* @ClassName: ScmsSpWalletLogMgServiceImpl 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年3月21日 上午10:32:45 
*  
*/
@Service
public class ScmsSpWalletLogMgServiceImpl extends BaseServiceImpl implements ScmsSpWalletLogMgService {

	@Autowired
	SpWalletLogMapper spWalletLogMapper;

	@Override
	public void insertSelective(SpWalletLog spWalletLog) throws Exception {
		spWalletLogMapper.insertSelective(spWalletLog);
	}

}
