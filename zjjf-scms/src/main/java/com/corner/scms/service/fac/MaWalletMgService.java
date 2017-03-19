/**   
* @Title: MaWalletMgService.java 
* @Package com.corner.scms.service.fac 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年3月7日 下午6:08:04 
* @version V1.0   
*/

package com.corner.scms.service.fac;

import java.util.Map;

import com.corner.core.beans.MaWallet;

/** 
* @ClassName: MaWalletMgService 
* @Description:经销商钱包业务类
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年3月7日 下午6:08:04 
*  
*/

public interface MaWalletMgService {

	void addManagerWallet(Map<String, Object> map) throws Exception;

	MaWallet selectById(String managerId) throws Exception;

	void insertSelective(MaWallet maWallet) throws Exception;

}
