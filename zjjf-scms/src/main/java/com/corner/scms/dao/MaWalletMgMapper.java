/**   
* @Title: MaWalletMgMapper.java 
* @Package com.corner.scms.dao 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年3月7日 下午6:10:19 
* @version V1.0   
*/

package com.corner.scms.dao;

import java.util.Map;

import com.corner.core.beans.MaWallet;
import com.corner.core.beans.MaWalletLog;
import com.corner.core.beans.ScOrderInfo;

/** 
* @ClassName: MaWalletMgMapper 
* @Description:经销商钱包类
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年3月7日 下午6:10:19 
*  
*/

public interface MaWalletMgMapper {

	/**
	 * 
	* @Title: addManagerWallet 
	* @Description:经销商加钱
	* @param @param map
	* @param @throws Exception
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	void addManagerWallet(Map<String, Object> map) throws Exception;

	/**
	 * 
	* @Title: selectById 
	* @Description:获取经销商钱包
	* @param @param managerId
	* @param @return
	* @param @throws Exception
	* @return MaWallet    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	MaWallet selectById(String managerId)throws Exception;

	/**
	 * 
	* @Title: selectByOrderId 
	* @Description:根据汇总订单订单号查询交易流水
	* @param @param scOrderInfo
	* @param @return
	* @param @throws Exception
	* @return MaWalletLog    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	MaWalletLog selectByOrderId(ScOrderInfo scOrderInfo) throws Exception;

}
