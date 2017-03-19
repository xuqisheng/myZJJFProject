package com.corner.salesman.service.impl;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.corner.core.beans.vo.Pager;
import com.corner.salesman.dao.DepartmentMapper;
import com.corner.salesman.dao.DeptManagerMapper;
import com.corner.salesman.dao.SpGroupDataMapper;
import com.corner.salesman.dao.UserDeptMapper;
import com.corner.salesman.model.Department;
import com.corner.salesman.model.DeptManager;
import com.corner.salesman.service.DepartmentService;
@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	private DepartmentMapper deptMapper;
	@Autowired
	private DeptManagerMapper deptManagerMapper;
	@Autowired
	private UserDeptMapper userDeptMapper;
	@Autowired
	private SpGroupDataMapper spGroupDataMapper;
	
	@Override
	public List<Department> findDeptList() throws Exception {
		return deptMapper.findDeptList();
	}
	
	/**
	 * 查询部门分页方法
	 * @param department
	 * @return
	 * @throws Exception
	 */
	public Pager<Department> getDeptPageList(Department department) throws Exception{
		
		List<Department> list = deptMapper.getDeptPageList(department);
		int size = deptMapper.getDeptPageSize(department);
		//根据部门ID查询部门所有的领导信息回填list到界面展示
		for (Department dept : list) {
			Integer id = dept.getId();
			if(null != id){
				String leaders = deptMapper.getDeptLeaders(id);
				dept.setLeaders(leaders);
			}
		}
		
		return new Pager<Department>(size, list);
	}
	
	/**
	 * 添加部门信息方法
	 * @param department
	 * @return
	 * @throws Exception
	 */
	public int addDepartment(Department deptVo) throws Exception{
		String deptId = deptVo.getDeptId();
		String leaderIds = deptVo.getLeaderId();
		addDeptManager(deptId, leaderIds);
		return deptMapper.insertSelective(deptVo);
	}
	
	/**
	 * 添加部门与领导人的关系映射
	 * @param deptCode
	 * @param leaderIds
	 * @throws Exception
	 */
	public void addDeptManager(String deptId,String leaderIds) throws Exception{
		//将部门原有领导删除，在insert当前的领导即可
		deptManagerMapper.deleteDeptManager(deptId);
		if(StringUtils.isNotBlank(leaderIds)){
			//将部门领导按照逗号切割保存数据库
			String[] leaderIdItem = leaderIds.split(",");
			//将部门领导按照逗号切割保存数据库
			for(String leaderId : leaderIdItem){
				DeptManager deptManager = new DeptManager();
				deptManager.setDeptId(deptId);
				deptManager.setLeaderId(leaderId);
				deptManagerMapper.insert(deptManager);
			}
		}
	}
	
	/**
	 * 修改部门信息方法
	 * @param department
	 * @return
	 * @throws Exception
	 */
	public int updateDepartment(Department deptVo) throws Exception{
		String deptId = deptVo.getDeptId();
		String leaderIds = deptVo.getLeaderId();
		addDeptManager(deptId, leaderIds);
		
		Integer provinceId = deptVo.getProvinceId();
		Integer cityId = deptVo.getCityId();
		Integer areaId = deptVo.getAreaId();
		
		Integer oldProvinceId = deptVo.getOldProvinceId();
		Integer oldCityId = deptVo.getOldCityId();
		Integer oldAreaId = deptVo.getOldAreaId();
		
		String strProvinceId = provinceId == null?"":provinceId.toString();
		String strCityId = cityId==null?"":cityId.toString();
		String strAreaId = areaId==null?"":areaId.toString();
		
		String strOldProvinceId = oldProvinceId == null?"":oldProvinceId.toString();
		String strOldCityId = oldCityId==null?"":oldCityId.toString();
		String strOldAreaId = oldAreaId==null?"":oldAreaId.toString();
		
		//1.如果部门绑定区域信息有改变时，删除原来绑定的定格信息
		if(!strProvinceId.equals(strOldProvinceId) || !strCityId.equals(strOldCityId) || !strAreaId.equals(strOldAreaId)){
			//删除原来部门绑定定格的用户
			spGroupDataMapper.deleteSpgBusAgentByDept(deptVo.getDeptId());
			//删除对应部门所定义的路线与定格
			spGroupDataMapper.deleteSpGroupByDept(deptVo.getDeptId());
		}
		
		return deptMapper.updateByPrimaryKeySelective(deptVo);
	}
	
	/**
	 * 删除部门信息方法
	 * @param department
	 * @return
	 * @throws Exception
	 */
	public int deleteDepartment(Department deptVo) throws Exception{
		
		String deptId = deptVo.getDeptId();
		if(StringUtils.isNotBlank(deptId)){
			//删除对应部门所定义的路线与定格
			spGroupDataMapper.deleteSpgBusAgentByDept(deptVo.getDeptId());
			spGroupDataMapper.deleteSpGroupByDept(deptVo.getDeptId());
			//1.删除部门领导关系
			deptManagerMapper.deleteDeptManager(deptVo.getDeptId());
			//2.删除用户与部门的关系
			userDeptMapper.deleteUserDeptByDeptId(deptVo.getDeptId());
		}
		
		//3.删除部门
		return deptMapper.updateByPrimaryKeySelective(deptVo);
	}
	
	/**
	 * 根据ID查询部门信息
	 * @param department
	 * @return
	 * @throws Exception
	 */
	public Department findDepartmentById(String deptId) throws Exception{
		return deptMapper.selectByPrimaryKey(deptId);
	}
	
	/**
	 * 检查新增部门ID是否已经存在
	 * @param deptId
	 * @return
	 */
	public int checkDeptIsExist(String deptId) throws Exception{
		return deptMapper.checkDeptIsExist(deptId);
	}
	
	/**
	 * 检查该用户是否为部门管理者，如果是则返回部门名称
	 * @param userId
	 * @return
	 */
	public String checkIsDeptLeader(String userId) throws Exception{
		return deptMapper.checkIsDeptLeader(userId);
	}

}
