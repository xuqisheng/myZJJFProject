package com.corner.kefu.service;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.AppItemTag;
import com.corner.core.beans.AppModule;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.kefu.beans.vo.AppItemTagVo;
import com.corner.kefu.beans.vo.AppModuleVo;

public interface AppModuleService {

//	public List<AppModuleVo> getAllAppModule();

	public AppItemTag getAppModuleDetailById(String id);

	public ModelMsg addModuleDetail(AppItemTag moduleDetail);

	public ModelMsg updateModuleDetail(AppItemTag moduleDetail);

	public List<AppItemTag> getImage();

	public boolean updateImage(String[] ids, String[] picUrls);

	public List<AppModuleVo> getAdAllAppModule();

	public List<AppItemTag> getAllItemTag();

	public List<AppModuleVo> getAppModuleList();

	public AppModule getAppModuleById(String id);

	public ModelMsg addModule(Map<String, Object> paramMap);

	public ModelMsg updateModule(Map<String, Object> paramMap);

	public boolean updateDelOrRecoModule(String id, Boolean isDelete);

	public boolean updateDelOrRecoTag(String id, Boolean isDelete);

	public List<AppItemTagVo> getAllAppTag();

	public List<Map<String, Object>> getAppModuleTagMapList(AppModule appModule);



}
