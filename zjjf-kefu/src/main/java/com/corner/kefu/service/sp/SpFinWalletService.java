/**   
* @Title: SpFinWalletService.java 
* @Package com.corner.kefu.service.sp 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年7月18日 下午5:59:35 
* @version V1.0   
*/

package com.corner.kefu.service.sp;

import java.util.List;

import com.corner.core.beans.FinWallet;
import com.corner.core.beans.RechargeGrade;
import com.corner.core.beans.Supplier;
import com.corner.core.beans.vo.Pager;
import com.corner.kefu.beans.ro.sp.FinWalletMgRo;
import com.corner.kefu.beans.ro.sp.FinWalletRechargeInfoRo;
import com.corner.kefu.beans.ro.sp.FinWalletRo;
import com.corner.kefu.beans.vo.sp.FinWalletLogVo;
import com.corner.kefu.beans.vo.sp.FinWalletRechargeInfoVo;
import com.corner.kefu.beans.vo.sp.FinWalletVo;
import com.corner.kefu.beans.vo.sp.RechargeGradeVo;


/** 
* @ClassName: SpFinWalletService 
* @Description:钱包业务接口
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年7月18日 下午5:59:35 
*  
*/

public interface SpFinWalletService {

	/**
	 * 
	* @Title: getRechargeGradeList 
	* @Description:获取充值梯度列表
	* @param @return
	* @return List<RechargeGradeVo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<RechargeGradeVo> getRechargeGradeList();

	/**
	 * 
	* @Title: getStoreWalletList 
	* @Description:获取钱包明细列表出错
	* @param @param finWalletRo
	* @param @return
	* @return Pager<FinWalletVo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	Pager<FinWalletVo> getStoreWalletList(FinWalletRo finWalletRo);

	/**
	 * 
	* @Title: updateStoreFinWallet 
	* @Description:修改钱包status状态
	* @param @param finWalletRo
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	void updateStoreFinWallet(FinWalletRo finWalletRo);

	Pager<FinWalletRechargeInfoVo> getAllWalletRechargeInfo(FinWalletRechargeInfoRo rechargeInfo);
	
	/**
	 * 
	* @Title: addRechargeGrade 
	* @Description:增加充值梯度
	* @param @param rechargeGrade
	* @param @return
	* @return RechargeGrade    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	void addRechargeGrade(RechargeGrade rechargeGrade);

	/**
	 * @return 
	 * 
	* @Title: addSupplierWallet 
	* @Description:创造批发商钱包
	* @param @param supplier
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	FinWallet addSupplierWallet(Supplier supplier);

	/**
	 * 
	* @Title: deleteRechargeGrade 
	* @Description:删除充值送劵梯度
	* @param @param rechargeGrade
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	void deleteRechargeGrade(RechargeGrade rechargeGrade);
   
	/**
	 * 
	* @Title: getWalletLog 
	* @Description:获得钱包交易记录
	* @param @param finWalletRo
	* @param @return
	* @return Pager<FinWalletLogVo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	Pager<FinWalletLogVo> getWalletLog(FinWalletMgRo finWalletMgRo);


}
