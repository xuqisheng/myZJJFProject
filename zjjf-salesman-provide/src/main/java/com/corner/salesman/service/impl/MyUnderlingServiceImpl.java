package com.corner.salesman.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.corner.salesman.common.utils.JedisUtils;
import com.corner.salesman.commons.utils.Constants;
import com.corner.salesman.dao.DeptMapper;
import com.corner.salesman.dao.MyUnderlingMapper;
import com.corner.salesman.dao.UserDeptMapper;
import com.corner.salesman.dao.UserMapper;
import com.corner.salesman.model.Department;
import com.corner.salesman.model.MyUnderling;
import com.corner.salesman.model.User;
import com.corner.salesman.service.MyUnderlingService;
/**
 * @描述： 我的下属业务层实现
 * @author Longx
 * @创建时间  2016-01-26
 */
@Service("myUnderlingService")
@Transactional(readOnly = true)
public class MyUnderlingServiceImpl implements MyUnderlingService {
	
	@Autowired
	private MyUnderlingMapper underlingMapper;
	@Autowired
	private UserDeptMapper userDeptMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private DeptMapper deptMapper;

	@Override
	public MyUnderling findMyUnderlingList(MyUnderling underling) throws Exception {
		
		List<User> userList = null;
		List<Department> deptList = null;
		List<Department> nvaList = null;
		//根据取部门ID
		String deptId = underling.getDeptId();
		//0、根据token获取当前用户所在部门ID
		String token = underling.getToken();
		User user = (User)JedisUtils.getObject(Constants.TOKEN_PREFIX_KEY+token);
		if(null == user){
			user = userMapper.findUserInfoById(underling.getUserId());
		}
		
		//根据用户ID粗略校验，如果在管理映射表中有用户可以查询具体的数据信息，否则则能看到自己
		int num = userDeptMapper.checkIsLeader(underling.getUserId());
		
		if(num>0){
			//获取当前部门下的人员列表
			userList = underlingMapper.findMyUnderlingList(deptId);
			//原来的逻辑是该部门下方没有任何人就查询当前登录人的（此部分逻辑在v1.1.0版本去除）
	    	/*if(userList.isEmpty()){
	    		userList = underlingMapper.findMyselfUserList(underling.getUserId());
	    	}*/
			//获取当前部门下的子部门列表
			deptList = underlingMapper.findMyUnderlingDeptList(deptId);
			//设置部门人员数量
			if(null != deptList && !deptList.isEmpty()){
				for(Department deptVO : deptList){
					int total = underlingMapper.findMyUnderlingTatol(deptVO.getDeptId());
					deptVO.setTotal(total);
				}
			}else{
				//当数据库查询获得的子部门为空的时候，构造一个空的list给app好判断处理
				deptList = new ArrayList<Department>();
			}
			//获取菜单导航数据信息列表
			//根据当前部门ID获取导航菜单列表
			nvaList = new ArrayList<Department>();
			setDeptNav(deptId, user.getDeptId(), nvaList);
			//3、将查询的部门list倒序即可
			Collections.reverse(nvaList);
		}else{
			//没有部门管理权限的时候（仅仅有菜单权限时），只能看到自己的数据
			userList = underlingMapper.findMyselfUserList(underling.getUserId());
			if(null != user){
				nvaList = new ArrayList<Department>();
				Department deptVo = new Department();
				deptVo.setDeptId(deptId);
				deptVo.setDeptName(user.getDeptName());
				nvaList.add(deptVo);
			}
		}
		
		return new MyUnderling(userList, deptList,nvaList);
	}
	
	/**
	 * 根据部门的ID查询当前部门到本部门的导航菜单
	 * @param deptId
	 * @param uDeptId
	 * @param nvaList
	 */
	public void setDeptNav(String deptId,String uDeptId,List<Department> nvaList){
		//1、根据当前部门ID查询父级部门信息
		 Department deptVo = deptMapper.findDeptById(deptId);
		 
		//2、取部门ID同当前用户部门ID比较，如果相等则已经到顶级部门，不相等则继续递归直到查询到用户所在部门为止
		 if(null != deptVo){
			 String cDeptId = deptVo.getDeptId();
			 String cDeptPid = deptVo.getDeptPid();
			 if(uDeptId.equals(cDeptId)){
				 nvaList.add(deptVo);
			 }else{
				 nvaList.add(deptVo);
				 setDeptNav(cDeptPid, uDeptId, nvaList);
			 }
		 }
	}
	
	/**
	 * 根据用户ID查询自己部门下的用户
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<User> getDeptUserList(String userId) throws Exception{
		//根据用户ID获取部门ID
		return underlingMapper.findDeptUserList(userId);
	}

}
