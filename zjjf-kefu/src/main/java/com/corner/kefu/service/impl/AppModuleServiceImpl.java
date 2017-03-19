package com.corner.kefu.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.AppItemTag;
import com.corner.core.beans.AppModule;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.dao.AppItemTagMapper;
import com.corner.core.dao.AppModuleMapper;
import com.corner.core.dao.AppModuleTagMapMapper;
import com.corner.core.utils.StringUtil;
import com.corner.kefu.beans.vo.AppItemTagVo;
import com.corner.kefu.beans.vo.AppModuleVo;
import com.corner.kefu.dao.AppModuleMgMapper;
import com.corner.kefu.service.AppModuleService;

@Service
public class AppModuleServiceImpl implements AppModuleService {

	@Autowired
	AppModuleMapper appModuleMapper;
	@Autowired
	AppModuleMgMapper appModuleMgMapper;
	@Autowired
	AppItemTagMapper appModuleDetailMapper;
	@Autowired
	AppModuleTagMapMapper appModuleTagMapMapper;
	
//	@Override
//	public List<AppModuleVo> getAllAppModule() {
//		return appModuleMgMapper.getAllAppModule();
//	}
	@Override
	public AppItemTag getAppModuleDetailById(String id) {
		return appModuleMgMapper.getAppModuleDetailById(id);
	}
	
	public boolean checkSkipObjectId(AppItemTag moduleDetail){
		Map<String, Object> map = new HashMap<>();
		map.put("appModuleId", moduleDetail.getAppModuleId());
		map.put("skipObjectId", moduleDetail.getSkipObjectId());
		int count = appModuleMgMapper.checkSkipObjectId(map);	
		if(count>0){
			return true;
		}else{
			return false;
		}
	}
	@Override
	public ModelMsg addModuleDetail(AppItemTag moduleDetail) {
		//查询目标有没有重复的
//		if(checkSkipObjectId(moduleDetail)){
//			return new ModelMsg(false, "此模块已存在对应的目标对象");
//		}
		if(StringUtil.stringIsNullOrEmpty(moduleDetail.getSkipObjectId())){
			moduleDetail.setSkipObjectId("0");
		}
		String id = StringUtil.getUUID();
		//插入数据
		moduleDetail.setId(id);
		moduleDetail.setAddTime(new Date());
		moduleDetail.setUpdateTime(new Date());
		int num = appModuleDetailMapper.insertSelective(moduleDetail);
		if(num>0){
			return new ModelMsg(true, "添加成功");
		}else{
			return new ModelMsg(false, "添加失败");
		}
	}
	@Override
	public ModelMsg updateModuleDetail(AppItemTag moduleDetail) {
		if(StringUtil.stringIsNullOrEmpty(moduleDetail.getSkipObjectId())){
			moduleDetail.setSkipObjectId("0");
		}
		moduleDetail.setUpdateTime(new Date());
		int num = appModuleDetailMapper.updateByPrimaryKeySelective(moduleDetail);
		if(num>0){
			return new ModelMsg(true, "编辑成功");
		}else{
			return new ModelMsg(false, "编辑失败");
		}
	}
	@Override
	public List<AppItemTag> getImage() {
		return appModuleMgMapper.getImage();
	}
	@Override
	public boolean updateImage(String[] ids, String[] picUrls) {
		AppItemTag detail = null;
		for (int i = 0; i < ids.length; i++) {
			detail = new AppItemTag();
			detail.setUpdateTime(new Date());
			detail.setId(ids[i]);
			detail.setPicUrl(picUrls[i]);
			appModuleDetailMapper.updateByPrimaryKeySelective(detail);
		}
		return true;
	}
	@Override
	public List<AppModuleVo> getAdAllAppModule() {
		return appModuleMgMapper.getAdAllAppModule();
	}
	@Override
	public List<AppItemTag> getAllItemTag() {
		return appModuleMgMapper.getAllItemTag();
	}
	@Override
	public List<AppModuleVo> getAppModuleList() {
		// TODO Auto-generated method stub
		return appModuleMgMapper.getAppModuleList();
	}
	@Override
	public AppModule getAppModuleById(String id) {
		return appModuleMapper.selectByPrimaryKey(id);
	}
	@Override
	public ModelMsg addModule(Map<String, Object> paramMap) {
		AppModule appModule = (AppModule) paramMap.get("appModule");
		String[] itemTagRadio = (String[]) paramMap.get("itemTagRadio");
		
		appModule.setAddTime(new Date());
		appModule.setUpdateTime(new Date());
		appModule.setId(StringUtil.getUUID());
	    int num = appModuleMapper.insertSelective(appModule);
		//int num = appModuleMgMapper.insertSelectiveReturnId(appModule);
		if(itemTagRadio!=null&&itemTagRadio.length>0){
			for (String itemTagId : itemTagRadio) {
				paramMap.put("moduleId", appModule.getId());
				paramMap.put("tagId", itemTagId);
				appModuleMgMapper.saveMoudelAndTag(paramMap);
			}
		}
		
		if(num > 0){
			return new ModelMsg(true, "添加成功");
		}else{
			return new ModelMsg(true, "添加失败");
		}
	}
	@Override
	public ModelMsg updateModule(Map<String, Object> paramMap) {
		AppModule appModule = (AppModule) paramMap.get("appModule");
		String[] itemTagRadio = (String[]) paramMap.get("itemTagRadio");
		appModule.setUpdateTime(new Date());
		int num = appModuleMapper.updateByPrimaryKeySelective(appModule);
		
		appModuleMgMapper.deleteFromTagMap(appModule);
		if(itemTagRadio!=null&&itemTagRadio.length>0){
			for (String itemTagId : itemTagRadio) {
				paramMap.put("moduleId", appModule.getId());
				paramMap.put("tagId", itemTagId);
				appModuleMgMapper.saveMoudelAndTag(paramMap);
			}
		}
		
		if(num > 0){
			return new ModelMsg(true, "编辑成功");
		}else{
			return new ModelMsg(true, "编辑失败");
		}
	}
	@Override
	public boolean updateDelOrRecoModule(String id, Boolean isDelete) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("isDelete", isDelete);
		int num = appModuleMgMapper.updateDelOrRecoModule(map);
		if(num > 0){
			return true;
		}
		return false;
	}
	@Override
	public boolean updateDelOrRecoTag(String id, Boolean isDelete) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("isDelete", isDelete);
		int num = appModuleMgMapper.updateDelOrRecoTag(map);
		if(num > 0){
			return true;
		}
		return false;
	}

	@Override
	public List<AppItemTagVo> getAllAppTag() {
		// TODO Auto-generated method stub
		List<AppItemTagVo> list = appModuleMgMapper.getAllAppTag();
		for (Iterator<AppItemTagVo> iterator = list.iterator(); iterator.hasNext();) {
			AppItemTagVo appItemTagVo =  iterator.next();
			List<AppModule>  listAppModule = appModuleMgMapper.getAppModuleByAppItemTagId(appItemTagVo.getId());
			String names = "";
			if(listAppModule !=null && !listAppModule.isEmpty()){
				for (int i = 0; i < listAppModule.size(); i++) {
					names += listAppModule.get(i).getName();
					if(i < (listAppModule.size()-1)){
						names +="，";
					}
				}				
			}
			appItemTagVo.setModelName(names);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getAppModuleTagMapList(AppModule appModule) {
		return appModuleMgMapper.getAppModuleTagMapList(appModule);
	}

}
