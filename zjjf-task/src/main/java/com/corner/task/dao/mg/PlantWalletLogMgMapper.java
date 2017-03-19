package com.corner.task.dao.mg;

import com.corner.task.beans.PlantWalletLog;

/**
 * 
* @ClassName: PlantWalletLogMgMapper 
* @Description: 平台钱包交易日志实现接口 
* @author 孟星魂	mengxinghun@izjjf.cn
* @date 2016年4月22日 下午2:34:28 
*
 */
public interface PlantWalletLogMgMapper {
	int updateWalletByLog(PlantWalletLog  plantWalletLog);
}
