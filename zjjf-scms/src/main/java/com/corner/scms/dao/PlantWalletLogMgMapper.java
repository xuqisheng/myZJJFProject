package com.corner.scms.dao;

import com.corner.core.beans.PlantWalletLog;

/**
 * 
* @ClassName: PlantWalletLogMgMapper 
* @Description:平台钱包交易日志实现接口
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年3月11日 下午5:49:11 
*
 */
public interface PlantWalletLogMgMapper {
	int updateWalletByLog(PlantWalletLog plantWalletLog);
}
