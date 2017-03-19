package com.corner.auth.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.auth.beans.Admin;
import com.corner.auth.beans.msg.ModelMsg;
import com.corner.auth.beans.ro.UserRo;
import com.corner.auth.beans.vo.Pager;
import com.corner.auth.beans.vo.UserVo;
import com.corner.auth.dao.AdminMapper;
import com.corner.auth.dao.mg.AdminMgMapper;
import com.corner.auth.service.AdminService;
import com.corner.auth.utils.BeanUtil;
import com.corner.auth.utils.StringUtil;
import com.corner.auth.utils.safe.MD5;
@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminMapper adminMapper;
	@Autowired
	AdminMgMapper adminMgMapper;
	@Override
	public Pager<UserVo> getUserListPage(UserRo command) {
		List<UserVo> list =adminMgMapper.getUserListPage(command);
		int result = adminMgMapper.getUserListPageCount(command);
		return new Pager<UserVo>( result , list);
	}

	@Override
	public ModelMsg insertUser(UserRo command) throws Exception {
		command.setCreateTime(new Date());
		command.setUpdateTime(new Date());
		command.setPassword(StringUtil.stringIsNullOrEmpty(command.getPassword()) ? null : MD5.StringToMd5(command.getPassword()));
		int result = adminMapper.insertSelective(BeanUtil.toObject(Admin.class, command));
		if(result == 0)
			   throw new Exception("新增数据错误");
		result = adminMgMapper.insertUserRoleMap(command);
		return new ModelMsg(true, "新增成功");
	}

	@Override
	public ModelMsg updateUser(UserRo command) throws Exception {
		command.setUpdateTime(new Date());
		command.setPassword(StringUtil.stringIsNullOrEmpty(command.getPassword()) ? null : MD5.StringToMd5(command.getPassword()));
		Admin admin = BeanUtil.toObject(Admin.class, command);
		int result = adminMapper.updateByPrimaryKeySelective(admin);
		if(result == 0)
			   throw new Exception("修改数据错误");
		if(command.getRoleIds() == null || command.getRoleIds().length == 0){
			return new ModelMsg(true, "修改成功");
		}
		result = adminMgMapper.insertUserRoleMap(command);
		return new ModelMsg(true, "修改成功");
	}

	@Override
	public ModelMsg deleteUser(String id) throws Exception {
		Admin command = new Admin();
		command.setIsDelete(true);
		int result = adminMapper.updateByPrimaryKeySelective(command);
		if(result == 0)
			   throw new Exception("删除数据错误");
		return new ModelMsg(true, "删除成功");
	}
	@Override
	public Admin getUserById(String id) {
		return adminMapper.selectByPrimaryKey(id);
	}
}
