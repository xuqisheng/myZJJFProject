package com.corner.kefu.dao;

import java.util.List;
import java.util.Map;

import com.corner.kefu.beans.ro.SystemVersionRo;
import com.corner.kefu.beans.vo.SystemVersionVo;

public interface SystemVersionMgMapper {
	
	public List<SystemVersionVo> getAllVersionInfo(SystemVersionRo versionRo);
	public int getAllVersionInfoCount(SystemVersionRo versionRo);
	
	public SystemVersionVo getVersionInfoById(Map<String, Object> map);
}
