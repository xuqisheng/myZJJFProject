/**   
* @Title: FinWalletLogMgMapper.java 
* @Package com.corner.kefu.dao.sp 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年7月21日 下午1:57:47 
* @version V1.0   
*/

package com.corner.kefu.dao.sp;

import java.util.List;

import com.corner.kefu.beans.ro.sp.FinWalletMgRo;
import com.corner.kefu.beans.vo.sp.FinWalletLogVo;

/** 
* @ClassName: FinWalletLogMgMapper 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年7月21日 下午1:57:47 
*  
*/

public interface FinWalletLogMgMapper {

	/**
	 * 
	* @Title: getWalletLog 
	* @Description:查询钱包交易明细
	* @param @param finWalletMgRo
	* @param @return
	* @return List<FinWalletLogVo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<FinWalletLogVo> getWalletLog(FinWalletMgRo finWalletMgRo);

	/**
	 * 
	* @Title: getCountWalletLog 
	* @Description:查询钱包交易明细总量
	* @param @param finWalletMgRo
	* @param @return
	* @return Integer    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	Integer getCountWalletLog(FinWalletMgRo finWalletMgRo);


}
