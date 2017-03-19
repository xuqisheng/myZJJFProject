package com.corner.kefu.service;

import com.corner.core.beans.SendTimeConfig;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.kefu.beans.ro.ConfigRo;
import com.corner.kefu.beans.ro.SendTimeConfigRo;
import com.corner.kefu.beans.vo.ConfigVo;
import com.corner.kefu.beans.vo.SendTimeConfigVo;

import java.util.List;
import java.util.Map;

public interface ConfigService {

	public Pager<ConfigVo> getAllConfig(ConfigRo configRo);

	/**
	 * 
	* @Title: saveDeliveryConfig 
	* @Description:保存配送方式
	* @param @param paramMap
	* @param @return
	* @return ModelMsg    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public ModelMsg saveDeliveryConfig(Map<String, Object> paramMap);

	public Pager<SendTimeConfigVo> getSendTimeConfig(SendTimeConfigRo sendTimeConfigRo);

	public SendTimeConfig getSendTimeConfigById(byte id);

	public boolean addSendTimeConfig(SendTimeConfig sendTimeConfig);

	public boolean updateSendTimeConfig(SendTimeConfig sendTimeConfig);

	public void delOrReco(Map<String, Object> map);

    List<SendTimeConfig> getAllSendTimeConfig();
}
