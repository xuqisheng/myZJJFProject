package com.corner.kefu.dao.sp;

import java.util.List;

import com.corner.core.beans.RechargeGrade;
import com.corner.kefu.beans.ro.sp.FinWalletRo;
import com.corner.kefu.beans.vo.sp.FinWalletVo;

public interface SpFinWalletMgMapper {

	/**
	 * 
	* @Title: getStoreWalletList 
	* @Description:分页查询小店钱包列表
	* @param @param finWalletRo
	* @param @return
	* @return List<FinWalletVo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<FinWalletVo> getStoreWalletList(FinWalletRo finWalletRo);

	/**
	 * 
	* @Title: getCountStoreWalletList 
	* @Description:获取小店钱包总数
	* @param @param finWalletRo
	* @param @return
	* @return Integer    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	Integer getCountStoreWalletList(FinWalletRo finWalletRo);

	/**
	 * 
	* @Title: addRechargeGrade 
	* @description: 增加充值梯度
	* @param @param rechargeGrade
	* @param @return
	* @return RechargeGrade    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	void addRechargeGrade(RechargeGrade rechargeGrade);

}
