package com.corner.kefu.dao;

import java.util.List;

import com.corner.kefu.beans.ro.ConfigShareRo;
import com.corner.kefu.beans.ro.sp.SpVoucherTempRo;
import com.corner.kefu.beans.vo.ConfigShareVo;
import com.corner.kefu.beans.vo.sp.SpVoucherTempVo;

public interface ConfigShareMgMapper {

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
	public List<SpVoucherTempVo> getAllSpVoucherTemp(SpVoucherTempRo spVoucherTempRo);
	public int getAllSpVoucherTempCount(SpVoucherTempRo spVoucherTempRo);
}
