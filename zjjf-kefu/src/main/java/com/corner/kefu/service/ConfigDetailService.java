package com.corner.kefu.service;

import com.corner.core.beans.vo.Pager;
import com.corner.kefu.beans.ro.ConfigPayRo;
import com.corner.kefu.beans.ro.ConfigShareRo;
import com.corner.kefu.beans.ro.sp.SpVoucherTempRo;
import com.corner.kefu.beans.vo.ConfigPayVo;
import com.corner.kefu.beans.vo.ConfigShareVo;
import com.corner.kefu.beans.vo.sp.SpVoucherTempVo;

public interface ConfigDetailService {

	
	public ConfigPayVo getSystemConfigDetailById(ConfigPayRo configPayRo);

	public void updateSystemConfigDetailByPayType(ConfigPayRo configPayRo);
	
	/**
	 *  获取邀请配置
	* @Title
	* @Description: TODO 
	* @param @return
	* @2016年4月26日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	public ConfigShareVo getConfigShareById(ConfigShareRo configShareRo);

	public void updateConfigShare(ConfigShareRo configShareRo);
	
	/**
	 * 获取所有优惠券 
	* @Title
	* @Description: TODO 
	* @param @param spVoucherTemp
	* @param @return
	* @2016年4月26日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	public Pager<SpVoucherTempVo> getAllSpVoucherTemp(SpVoucherTempRo spVoucherTempRo);

	public int updateAllGoodsStock(Integer stockNum);
}
