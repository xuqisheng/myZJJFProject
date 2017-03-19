/**   
* @Title: SpVoucherGradedServiceImpl.java 
* @Package com.corner.kefu.service.sp.impl 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年5月16日 下午5:10:01 
* @version V1.0   
*/

package com.corner.scms.service.sp.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.SpVoucherActive;
import com.corner.scms.beans.vo.SpVoucherGradedVo;
import com.corner.scms.dao.SpVoucherGradedMgMapper;
import com.corner.scms.service.sp.SpVoucherGradedService;

/** 
* @ClassName: SpVoucherGradedServiceImpl 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年5月16日 下午5:10:01 
*  
*/
@Service
public class SpVoucherGradedServiceImpl implements SpVoucherGradedService {

	@Autowired
	SpVoucherGradedMgMapper spVoucherGradedMgMapper;

	/**
	 * 
	* @Title: getListByActiveId 
	* @Description:根据活动id获取该活动的优惠劵梯度
	* @param @param spVoucherActive
	* @param @return
	* @param @throws Exception
	* @return List<SpVoucherGradedVo>    返回类型
	*@author 海灵子
	* @throws
	 */
	@Override
	public List<SpVoucherGradedVo> getListByActiveId(SpVoucherActive spVoucherActive)  {
		return spVoucherGradedMgMapper.getListByActiveId(spVoucherActive);
	}
}
