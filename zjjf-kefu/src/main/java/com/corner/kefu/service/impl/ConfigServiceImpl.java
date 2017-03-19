package com.corner.kefu.service.impl;

import com.corner.core.beans.DeliveryConfig;
import com.corner.core.beans.SendTimeConfig;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.ConfigMapper;
import com.corner.core.dao.DeliveryConfigMapper;
import com.corner.core.dao.SendTimeConfigMapper;
import com.corner.core.utils.StringUtil;
import com.corner.kefu.beans.ro.ConfigRo;
import com.corner.kefu.beans.ro.SendTimeConfigRo;
import com.corner.kefu.beans.vo.ConfigVo;
import com.corner.kefu.beans.vo.SendTimeConfigVo;
import com.corner.kefu.dao.ConfigMgMapper;
import com.corner.kefu.dao.SendTimeConfigMgMapper;
import com.corner.kefu.service.ConfigService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class ConfigServiceImpl implements ConfigService {

	@Autowired
	ConfigMgMapper configMgMapper; 
	
	@Autowired
	ConfigMapper configMapper;
	
	@Autowired
	DeliveryConfigMapper deliveryConfigMapper;
	
	@Autowired
	SendTimeConfigMgMapper sendTimeConfigMgMapper;
	
	@Autowired
	SendTimeConfigMapper sendTimeConfigMapper;
	
	/**
	 * 获取所有系统配置信息 
	* @Title
	* @Description: TODO 
	* @param @return
	* @2016年4月14日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	public Pager<ConfigVo> getAllConfig(ConfigRo configRo){
		List<ConfigVo> configVoList =  configMgMapper.getAllConfig(configRo);
		int num = configMgMapper.getAllConfigCount(configRo);
		return new Pager<>(num, configVoList);
	}

	
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
	@Override
	public ModelMsg saveDeliveryConfig(Map<String, Object> paramMap) {
		ModelMsg msg = new ModelMsg();
		msg.setSuccess(false);
		String scopTypeStr = (String) paramMap.get("scopType");//0全部定格 1指定定格  2 排除定格
		String discountStr = (String) paramMap.get("discount");
		String[] spGroupIdArr = (String[]) paramMap.get("spGroupIdArr");
		String[] discountArr = (String[]) paramMap.get("discountArr");
		if(scopTypeStr.equals("0")){//全部定格
			if(StringUtils.isEmpty(discountStr)){
				msg.setMessage("请输入优惠比例!");
				return msg;
			}
			DeliveryConfig config = new DeliveryConfig();
			config.setScopType((byte)0);//全部定格
			config.setDiscount(new BigDecimal(discountStr));
			
			configMgMapper.deleteFromDeliveryConfig();
			deliveryConfigMapper.insertSelective(config);
			
		}else{//指定定格 || 排除定格
			if(spGroupIdArr==null||spGroupIdArr.length==0){
				msg.setMessage("定格id数组为空!");
				return msg;
			}
			if(discountArr==null||discountArr.length==0){
				msg.setMessage("折扣数组为空!");
				return msg;
			}
			if(spGroupIdArr.length!=discountArr.length){
				msg.setMessage("定格id数组长度和折扣数组长度不相同!");
				return msg;
			}
			if(scopTypeStr.equals("2")){//排除定格
				if(StringUtils.isEmpty(discountStr)){
					msg.setMessage("请输入优惠比例!");
					return msg;
				}
			}
			configMgMapper.deleteFromDeliveryConfig();
			for (int i = 0; i < discountArr.length; i++) {
				DeliveryConfig config = new DeliveryConfig();
				config.setScopType((byte)1);//指定定格
				if(scopTypeStr.equals("1")){
					config.setDiscount(new BigDecimal(discountArr[i]));
				}else {
					config.setDiscount(new BigDecimal(discountStr));		
				}
				config.setSpGroupId(Integer.parseInt(spGroupIdArr[i]));
				deliveryConfigMapper.insertSelective(config);	
			}
		}
		msg.setSuccess(true);
		return msg;
	}


	@Override
	public Pager<SendTimeConfigVo> getSendTimeConfig(SendTimeConfigRo sendTimeConfigRo) {
		if(!StringUtil.stringIsNullOrEmpty(sendTimeConfigRo.getName())){
			sendTimeConfigRo.setName(sendTimeConfigRo.getName().trim());
		}
		List<SendTimeConfigVo> list = sendTimeConfigMgMapper.getSendTimeConfig(sendTimeConfigRo);
		int count = sendTimeConfigMgMapper.getSendTimeConfigCount(sendTimeConfigRo);
		return new Pager<SendTimeConfigVo>(count, list);
	}


	@Override
	public SendTimeConfig getSendTimeConfigById(byte id) {
		return sendTimeConfigMapper.selectByPrimaryKey(id);
	}


	@Override
	public boolean addSendTimeConfig(SendTimeConfig sendTimeConfig) {
		int count = sendTimeConfigMapper.insertSelective(sendTimeConfig);
		if(count > 0){
			return true;
		}
		return false;
	}


	@Override
	public boolean updateSendTimeConfig(SendTimeConfig sendTimeConfig) {
		int count = sendTimeConfigMapper.updateByPrimaryKeySelective(sendTimeConfig);
		if(count > 0){
			return true;
		}
		return false;
	}


	@Override
	public void delOrReco(Map<String, Object> map) {
		sendTimeConfigMgMapper.delOrReco(map);
	}

	@Override
	public List<SendTimeConfig> getAllSendTimeConfig() {
		return  sendTimeConfigMgMapper.getAllSendTimeConfig();
	}

}
