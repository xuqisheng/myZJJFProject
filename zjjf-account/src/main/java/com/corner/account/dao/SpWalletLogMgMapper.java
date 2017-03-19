package com.corner.account.dao;

import java.util.List;
import java.util.Map;

import com.corner.account.beans.ro.SpWithDrawMgCondition;
import com.corner.core.beans.SpWalletLog;

/**
 * 
* @ClassName: SpWalletLogMgMapper 
* @Description: 批发商钱包交易日志实现接口
* @author 孟星魂	mengxinghun@izjjf.cn
* @date 2016年4月22日 下午2:33:24 
*
 */
public interface SpWalletLogMgMapper {
	/**
	 * 
	* @Title: updateWalletByLog 
	* @Description: 新增批发商钱包操作日志、修改钱包金额 
	* @param: @param spWalletLog
	* @param: @return	设定文件 
	* @return int    返回类型 
	* @author 孟星魂	mengxinghun@izjjf.cn
	 */
	int updateWalletByLog(SpWalletLog spWalletLog);
	
	List<Map<String, Object>> getSpWalletPage(SpWithDrawMgCondition condition);
	Integer getSpWalletPageCount(SpWithDrawMgCondition condition);
	
	List<Map<String, Object>> getSpWalletLogPage(SpWithDrawMgCondition condition);
	Integer getSpWalletLogPageCount(SpWithDrawMgCondition condition);
}
