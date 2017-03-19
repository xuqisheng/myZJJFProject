package com.corner.kefu.service;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.AppItemTag;
import com.corner.core.beans.AppModule;
import com.corner.core.beans.AppModuleCfgMapKey;
import com.corner.core.beans.DBAppConfig;
import com.corner.core.beans.vo.Pager;
import com.corner.kefu.beans.ro.DBAppConfigRo;
import com.corner.kefu.beans.vo.DBAppConfigVo;
import com.corner.kefu.beans.vo.sp.SpGroupVo;

public interface DBAppConfigService {

	Pager<DBAppConfigVo> getAllDBAppConfig(DBAppConfigRo dBAppConfig);

	DBAppConfig getDBAppConfigById(Integer id);

	//void addAppConfig(DBAppConfig appConfig, Integer[] spGroups);
	void addAppConfig(DBAppConfig appConfig, Integer[] spGroups, String[] itemTagRadio);

	//void updateAppConfig(DBAppConfig appConfig, Integer[] spGroups);
	void updateAppConfig(DBAppConfig appConfig, String[] itemTagRadio,Integer spGroups[]);

	void delOrReco(Map<String, Object> map);

	List<SpGroupVo> getSpGroup(Integer id);

	List<DBAppConfigVo> getAllAppConfig();

	List<AppItemTag> getAllAppItemTag();

	List<AppModuleCfgMapKey> getCfgMapByConfigId(DBAppConfig appConfig);

	List<AppModule> getAllAppModules();

}
