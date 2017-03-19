package com.corner.kefu.service;

import java.util.Map;

import com.corner.core.beans.vo.Pager;
import com.corner.kefu.beans.ro.SystemVersionRo;
import com.corner.kefu.beans.vo.SystemVersionVo;

public interface SystemVersionService {

	
	public Pager<SystemVersionVo> getAllVersionInfo(SystemVersionRo versionRo);

	public SystemVersionVo getVersionInfoById(Map<String, Object> map);

	public void updateVersionInfoById(SystemVersionRo versionRo);

	public void addVersionInfo(SystemVersionRo versionRo);

	public void updateVersionInfoStatusById(SystemVersionRo versionRo);
}
