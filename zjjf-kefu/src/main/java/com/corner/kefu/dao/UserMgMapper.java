/**   
* @Title: UserMgMapper.java 
* @Package com.corner.kefu.dao 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年4月17日 下午1:35:01 
* @version V1.0   
*/

package com.corner.kefu.dao;

import java.util.List;

import com.corner.core.beans.FinWallet;
import com.corner.core.beans.User;

/** 
* @ClassName: UserMgMapper 
* @Description: 
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年4月17日 下午1:35:01 
*  
*/

public interface UserMgMapper {

	/**
	 * 
	* @Title: getUserIdByStoreIdArr 
	* @Description:通过店铺id数组 查询对应的店铺User id 集合 
	* @param @param storeIdArr
	* @param @return    设定文件 
	* @return List<String>    返回类型 
	* @author 杨开泰   yangkaitai@izjjf.cn
	* @throws
	 */
	List<String> getUserIdByStoreIdArr(String[] storeIdArr);

	/**
	 * 
	* @Title: getUserByWalletId 
	* @Description:根据钱包id获取User集合
	* @param @param finWallet
	* @param @return
	* @return List<User>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<User> getUserByWalletId(FinWallet finWallet);


}
