package com.corner.auth.dao.mg;

import java.util.List;

import com.corner.auth.beans.ro.UserRo;
import com.corner.auth.beans.vo.UserVo;



public interface AdminMgMapper {
	public List<UserVo> getUserListPage(UserRo command);
	public Integer getUserListPageCount(UserRo command);
	public Integer insertUserRoleMap(UserRo command);
}
