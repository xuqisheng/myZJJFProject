package com.corner.kefu.dao;

import com.corner.core.beans.SendTimeConfig;
import com.corner.kefu.beans.ro.SendTimeConfigRo;
import com.corner.kefu.beans.vo.SendTimeConfigVo;

import java.util.List;
import java.util.Map;

public interface SendTimeConfigMgMapper {

	List<SendTimeConfigVo> getSendTimeConfig(SendTimeConfigRo sendTimeConfigRo);

	int getSendTimeConfigCount(SendTimeConfigRo sendTimeConfigRo);

	void delOrReco(Map<String, Object> map);

    List<SendTimeConfig> getAllSendTimeConfig();

}
