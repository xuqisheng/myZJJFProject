package com.corner.auth.dao.mg;

import com.corner.auth.beans.ro.UserRo;
import com.corner.auth.beans.vo.UserVo;

import java.util.List;



public interface CustomerServiceMgMapper {
	public List<UserVo> getUserListPage(UserRo command);
	public Integer getUserListPageCount(UserRo command);
	public Integer insertUserRoleMap(UserRo command);
}
