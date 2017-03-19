package com.corner.task.dao.mg;

import com.corner.task.beans.SpWalletLog;

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
}
