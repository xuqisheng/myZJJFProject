package com.corner.kefu.dao;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.AppItemTag;
import com.corner.core.beans.AppModule;
import com.corner.kefu.beans.vo.AppItemTagVo;
import com.corner.kefu.beans.vo.AppModuleVo;

public interface AppModuleMgMapper {

//	public List<AppModuleVo> getAllAppModule();

	public AppItemTag getAppModuleDetailById(String id);

	public int checkSkipObjectId(Map<String, Object> map);

	public List<AppItemTag> getImage();

	public List<AppModuleVo> getAdAllAppModule();

	public List<AppItemTag> getAllItemTag();

	public List<AppModuleVo> getAppModuleList();
	
	public List<AppModule> getAppModuleByAppItemTagId(String id);

	public int updateDelOrRecoModule(Map<String, Object> map);

	public int updateDelOrRecoTag(Map<String, Object> map);

	public List<AppItemTagVo> getAllAppTag();

	public int insertSelectiveReturnId(AppModule appModule);

	public void saveMoudelAndTag(Map<String, Object> paramMap);


	public List<Map<String, Object>> getAppModuleTagMapList(AppModule appModule);

	public void deleteFromTagMap(AppModule appModule);

}
