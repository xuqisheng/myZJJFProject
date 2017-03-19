package com.corner.kefu.dao;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.AppItemTag;
import com.corner.core.beans.AppModule;
import com.corner.core.beans.AppModuleCfgMapKey;
import com.corner.core.beans.DBAppConfig;
import com.corner.kefu.beans.ro.DBAppConfigRo;
import com.corner.kefu.beans.vo.DBAppConfigVo;
import com.corner.kefu.beans.vo.sp.SpGroupVo;

public interface DBAppConfigMgMapper {

	List<DBAppConfigVo> getAllDBAppConfig(DBAppConfigRo dBAppConfig);

	int getAllDBAppConfigCount(DBAppConfigRo dBAppConfig);

	void delOrReco(Map<String, Object> map);

	List<SpGroupVo> getSpGroup(Integer id);

	List<DBAppConfigVo> getAllAppConfig();

	List<AppItemTag> getAllAppItemTag();

	void deleteFromAppModelCfgMap(DBAppConfig appConfig);

	List<AppModuleCfgMapKey> getCfgMapByConfigId(DBAppConfig appConfig);

	void insertSelectiveReurnId(DBAppConfig appConfig);


	List<AppModule> getAllAppModules();
	
}
