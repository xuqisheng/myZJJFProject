package com.corner.kefu.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.Config;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.ConfigMapper;
import com.corner.core.dao.ConfigPayMapper;
import com.corner.core.dao.ConfigShareMapper;
import com.corner.kefu.beans.ro.ConfigPayRo;
import com.corner.kefu.beans.ro.ConfigShareRo;
import com.corner.kefu.beans.ro.sp.SpVoucherTempRo;
import com.corner.kefu.beans.vo.ConfigPayVo;
import com.corner.kefu.beans.vo.ConfigShareVo;
import com.corner.kefu.beans.vo.sp.SpGroupVo;
import com.corner.kefu.beans.vo.sp.SpVoucherTempVo;
import com.corner.kefu.dao.ConfigPayMgMapper;
import com.corner.kefu.dao.ConfigShareMgMapper;
import com.corner.kefu.dao.sp.SpGroupMgMapper;
import com.corner.kefu.dao.sp.SpPlantItemMgMapper;
import com.corner.kefu.service.ConfigDetailService;
@Service
public class ConfigDetailServiceImpl implements ConfigDetailService {

	@Autowired
	ConfigPayMgMapper configPayMgMapper;
	@Autowired
	ConfigPayMapper configPayMapper;
	@Autowired
	SpGroupMgMapper spGroupMgMapper;
	@Autowired
	ConfigMapper configMapper;
	@Autowired
	ConfigShareMgMapper configShareMgMapper;
	@Autowired
	ConfigShareMapper configShareMapper;
	@Autowired
	SpPlantItemMgMapper spPlantItemMgMapper;
	
	/**
	 * 根据id获取支付方式
	* @Title
	* @Description: TODO 
	* @param @param systemConfigDetailRo
	* @param @return
	* @2016年4月14日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	public ConfigPayVo getSystemConfigDetailById(ConfigPayRo ConfigPayRo){
		ConfigPayVo configPayVo = configPayMgMapper.getSystemConfigDetailById(ConfigPayRo);
		if(configPayVo != null && configPayVo.getSelectType()==1){
			if(configPayVo.getSpGroupIds() != null && !"".equals(configPayVo.getSpGroupIds()) && !"0".equals(configPayVo.getSpGroupIds())){
				SpGroupVo spGroupVo = null;
				Map<String, Object> map = null;
				if( configPayVo.getSpGroupIds().contains(",")){
					String[] spGroupIdstr = configPayVo.getSpGroupIds().split(",");
					for (int i = 0; i < spGroupIdstr.length; i++) {
						if(spGroupIdstr[i] != null && !"".equals(spGroupIdstr[i])){
							map = new HashMap<String, Object>();
							map.put("id", Integer.parseInt(spGroupIdstr[i].trim()));
							spGroupVo = spGroupMgMapper.getSpGroupAndAreaById(map);
							if(spGroupVo != null){
								configPayVo.getSpGroupList().add(spGroupVo);
							}
						}
					}
				}else{
					map = new HashMap<String, Object>();
					map.put("id", Integer.parseInt(configPayVo.getSpGroupIds().trim()));
					spGroupVo = spGroupMgMapper.getSpGroupAndAreaById(map);
					if(spGroupVo != null){
						configPayVo.getSpGroupList().add(spGroupVo);
					}
				}
			}
		}
		return configPayVo;
	}


	/**
	 * 更具支付方式修改
	* @Title
	* @Description: TODO 
	* @param @param systemConfigDetailRo
	* @2016年4月15日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@Override
	public void updateSystemConfigDetailByPayType(ConfigPayRo configPayRo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("payType",configPayRo.getPayType());
		map.put("configId", configPayRo.getConfigId());
		//查询支付方式是否存在（存在就修改，不存在就添加）
		int count = configPayMgMapper.getSystemConfigDetailByPayType(map);
		if(count>0){
			//修改
			configPayRo.setUpdateTime(new Date());
			configPayMgMapper.updateSystemConfigDetailByPayType(configPayRo);
		}else{
			//添加
			configPayRo.setAddTime(new Date());
			configPayRo.setUpdateTime(new Date());
			configPayMapper.insertSelective(configPayRo);
		}
		Config config = new Config();
		config.setId(configPayRo.getConfigId());
		config.setUpdateTime(new Date());
		configMapper.updateByPrimaryKeySelective(config);
	}


	/**
	 *  获取邀请配置
	* @Title
	* @Description: TODO 
	* @param @return
	* @2016年4月26日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@Override
	public ConfigShareVo getConfigShareById(ConfigShareRo configShareRo) {
		// TODO Auto-generated method stub
		return configShareMgMapper.getConfigShareById(configShareRo);
	}


	@Override
	public void updateConfigShare(ConfigShareRo configShareRo) {
		// TODO Auto-generated method stub
		int num = 0;
		if(configShareRo.getId() != null){
			num = configShareMapper.updateByPrimaryKeySelective(configShareRo);
		}else{
			num = configShareMapper.insertSelective(configShareRo);
		}
		if(num>0){
			Config config = new Config();
			config.setId(configShareRo.getConfigId());
			config.setUpdateTime(new Date());
			configMapper.updateByPrimaryKeySelective(config);
		}
	}


	@Override
	public Pager<SpVoucherTempVo> getAllSpVoucherTemp(SpVoucherTempRo spVoucherTempRo) {
		// TODO Auto-generated method stub
		List<SpVoucherTempVo> SpVoucherTempList = configShareMgMapper.getAllSpVoucherTemp(spVoucherTempRo);
		int num = configShareMgMapper.getAllSpVoucherTempCount(spVoucherTempRo);
		return new Pager(num, SpVoucherTempList);
	}


	@Override
	public int updateAllGoodsStock(Integer stockNum) {
		// TODO Auto-generated method stub
		return spPlantItemMgMapper.updateAllGoodsStock(stockNum);
	}
}
