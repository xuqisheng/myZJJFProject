package com.corner.kefu.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.AppItemTag;
import com.corner.core.beans.AppModule;
import com.corner.core.beans.AppModuleCfgMapKey;
import com.corner.core.beans.DBAppConfig;
import com.corner.core.beans.SpGroup;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.AppModuleCfgMapMapper;
import com.corner.core.dao.DBAppConfigMapper;
import com.corner.core.dao.SpGroupMapper;
import com.corner.core.utils.StringUtil;
import com.corner.kefu.beans.ro.DBAppConfigRo;
import com.corner.kefu.beans.vo.DBAppConfigVo;
import com.corner.kefu.beans.vo.sp.SpGroupVo;
import com.corner.kefu.dao.DBAppConfigMgMapper;

@Service
public class DBAppConfigServiceImpl implements DBAppConfigService {
	
	@Autowired
	DBAppConfigMgMapper dBAppConfigMgMapper;
	
	@Autowired
	DBAppConfigMapper dBAppConfigMapper;
	
	@Autowired
	SpGroupMapper spGroupMapper;
	
	@Autowired
	AppModuleCfgMapMapper appModuleCfgMapMapper;
	

	@Override
	public Pager<DBAppConfigVo> getAllDBAppConfig(DBAppConfigRo dBAppConfig) {
		if(!StringUtil.stringIsNullOrEmpty(dBAppConfig.getName())){
			dBAppConfig.setName(dBAppConfig.getName().trim());
		}
		List<DBAppConfigVo> list = dBAppConfigMgMapper.getAllDBAppConfig(dBAppConfig);
		int totalSize = dBAppConfigMgMapper.getAllDBAppConfigCount(dBAppConfig);
		return new Pager<>(totalSize, list);
	}

	@Override
	public DBAppConfig getDBAppConfigById(Integer id) {
		return dBAppConfigMapper.selectByPrimaryKey(id);
	}

	@Override
	public void addAppConfig(DBAppConfig appConfig, Integer[] spGroups,String[] itemTagRadio) {
		//dBAppConfigMapper.insertSelective(appConfig);
		dBAppConfigMgMapper.insertSelectiveReurnId(appConfig);
		//分配定格
		upDateAllocationSpGroup(appConfig.getId(),spGroups);
		
		for(int i=0;i<itemTagRadio.length;i++){
	          AppModuleCfgMapKey appModuleCfgMapKey = new AppModuleCfgMapKey();
	          appModuleCfgMapKey.setAppCfgId(appConfig.getId());
	          appModuleCfgMapKey.setModuleId(itemTagRadio[i]);
	          appModuleCfgMapMapper.insertSelective(appModuleCfgMapKey);
			}
	}
	/**
	 * @Override
	public void addAppConfig(DBAppConfig appConfig,String[] itemTagRadio,Integer[] spGroups) {
		//dBAppConfigMapper.insertSelective(appConfig);
		dBAppConfigMgMapper.insertSelectiveReurnId(appConfig);
		for(int i=0;i<itemTagRadio.length;i++){
          AppModuleCfgMapKey appModuleCfgMapKey = new AppModuleCfgMapKey();
          appModuleCfgMapKey.setAppCfgId(appConfig.getId());
          appModuleCfgMapKey.setModuleId(itemTagRadio[i]);
          appModuleCfgMapMapper.insertSelective(appModuleCfgMapKey);
		}
	}*/

	/**
	 * @Override
	public void updateAppConfig(DBAppConfig appConfig, Integer[] spGroups) {
		dBAppConfigMapper.updateByPrimaryKeySelective(appConfig);
		//分配定格
		upDateAllocationSpGroup(appConfig.getId(),spGroups);
	}*/
	@Override
	public void updateAppConfig(DBAppConfig appConfig,String[] itemTagRadio,Integer spGroups[]) {
		dBAppConfigMapper.updateByPrimaryKeySelective(appConfig);
		//分配定格
		upDateAllocationSpGroup(appConfig.getId(),spGroups);
		dBAppConfigMgMapper.deleteFromAppModelCfgMap(appConfig);
		for(int i=0;i<itemTagRadio.length;i++){
          AppModuleCfgMapKey appModuleCfgMapKey = new AppModuleCfgMapKey();
          appModuleCfgMapKey.setAppCfgId(appConfig.getId());
          appModuleCfgMapKey.setModuleId(itemTagRadio[i]);
          appModuleCfgMapMapper.insertSelective(appModuleCfgMapKey);
		}
	}

	private void upDateAllocationSpGroup(Integer appCfgId, Integer[] spGroups) {
		SpGroup group = null;
		if(spGroups != null && spGroups.length > 0 && appCfgId != null){
			for (Integer spGroupId : spGroups) {
				group = new SpGroup();
				group.setId(spGroupId);
				group.setAppCfgId(appCfgId);
				spGroupMapper.updateByPrimaryKeySelective(group);
			}
		}
	}

	@Override
	public void delOrReco(Map<String, Object> map) {
		dBAppConfigMgMapper.delOrReco(map);
	}

	@Override
	public List<SpGroupVo> getSpGroup(Integer id) {
		// TODO Auto-generated method stub
		return dBAppConfigMgMapper.getSpGroup(id);
	}

	@Override
	public List<DBAppConfigVo> getAllAppConfig() {
		// TODO Auto-generated method stub
		return dBAppConfigMgMapper.getAllAppConfig();
	}

	@Override
	public List<AppItemTag> getAllAppItemTag() {
		return dBAppConfigMgMapper.getAllAppItemTag();
	}

	@Override
	public List<AppModuleCfgMapKey> getCfgMapByConfigId(DBAppConfig appConfig) {
		return dBAppConfigMgMapper.getCfgMapByConfigId(appConfig);
	}

	@Override
	public List<AppModule> getAllAppModules() {
		return dBAppConfigMgMapper.getAllAppModules();
	}


}
