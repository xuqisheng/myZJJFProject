package com.corner.auth.service.impl;

import com.corner.auth.beans.CustomerService;
import com.corner.auth.beans.msg.ModelMsg;
import com.corner.auth.beans.ro.UserRo;
import com.corner.auth.beans.vo.Pager;
import com.corner.auth.beans.vo.UserVo;
import com.corner.auth.dao.CustomerServiceMapper;
import com.corner.auth.dao.mg.CustomerServiceMgMapper;
import com.corner.auth.service.CustomerServiceService;
import com.corner.auth.utils.BeanUtil;
import com.corner.auth.utils.StringUtil;
import com.corner.auth.utils.safe.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class CustomerServiceServiceImpl implements CustomerServiceService {

	@Autowired
	CustomerServiceMapper customerServiceMapper;
	@Autowired
	CustomerServiceMgMapper customerServiceMgMapper;
	@Override
	public Pager<UserVo> getUserListPage(UserRo command) {
		List<UserVo> list =customerServiceMgMapper.getUserListPage(command);
		int result = customerServiceMgMapper.getUserListPageCount(command);
		return new Pager<UserVo>( result , list);
	}

	@Override
	public ModelMsg insertUser(UserRo command) throws Exception {
		command.setCreateTime(new Date());
		command.setUpdateTime(new Date());
		command.setPassword(StringUtil.stringIsNullOrEmpty(command.getPassword()) ? null : MD5.StringToMd5(command.getPassword()));
		int result = customerServiceMapper.insertSelective(BeanUtil.toObject(CustomerService.class, command));
		if(result == 0)
			   throw new Exception("新增数据错误");
		result = customerServiceMgMapper.insertUserRoleMap(command);
		return new ModelMsg(true, "新增成功");
	}

	@Override
	public ModelMsg updateUser(UserRo command) throws Exception {
		command.setUpdateTime(new Date());
		command.setPassword(StringUtil.stringIsNullOrEmpty(command.getPassword()) ? null : MD5.StringToMd5(command.getPassword()));
		int result = customerServiceMapper.updateByPrimaryKeySelective(BeanUtil.toObject(CustomerService.class, command));
		if(result == 0)
			   throw new Exception("修改数据错误");
		if(command.getRoleIds() == null || command.getRoleIds().length == 0){
			return new ModelMsg(true, "修改成功");
		}
		result = customerServiceMgMapper.insertUserRoleMap(command);
		return new ModelMsg(true, "修改成功");
	}

	@Override
	public ModelMsg deleteUser(String id) throws Exception {
		CustomerService command = new CustomerService();
		command.setUpdateTime(new Date());
		command.setIsDelete(true);
		int result = customerServiceMapper.updateByPrimaryKeySelective(command);
		if(result == 0)
			   throw new Exception("删除数据错误");
		return new ModelMsg(true, "删除成功");
	}
	@Override
	public CustomerService getUserById(String id) {
		return customerServiceMapper.selectByPrimaryKey(id);
	}
}
