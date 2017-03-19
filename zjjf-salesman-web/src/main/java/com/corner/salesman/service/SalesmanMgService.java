package com.corner.salesman.service;

import java.util.List;

import com.corner.core.beans.vo.Pager;
import com.corner.salesman.model.SalesmanMg;

/**
 * 业务员服务层接口
 * @author Longx
 *
 */
public interface SalesmanMgService {

	/**
	 * 添加业务员信息
	 * @return
	 * @throws Exception
	 */
	public int addSalesmanMg(SalesmanMg salesman) throws Exception;
	
	/**
	 * 修改业务员信息
	 * @return
	 * @throws Exception
	 */
	public void updateSalesmanMg(SalesmanMg salesman) throws Exception;
	
	/**
	 * 解绑设备ID
	 * @return
	 * @throws Exception
	 */
	public void updateUnbindDeviceId(SalesmanMg salesman) throws Exception;
	
	/**
	 * 删除业务员信息
	 * @return
	 * @throws Exception
	 */
	public int delSalesmanMg(SalesmanMg salesman) throws Exception;
	
	/**
	 * 查询业务员信息
	 * @return
	 * @throws Exception
	 */
	public SalesmanMg findSalesmanMgById(SalesmanMg salesman) throws Exception;
	
	/**
	 * 查询业务员分页方法
	 * @param salesman
	 * @return
	 * @throws Exception
	 */
	public Pager<SalesmanMg> getPageList(SalesmanMg salesman) throws Exception;
	
	/**
	 * 查询设备列表信息分页方法
	 * @param salesman
	 * @return
	 * @throws Exception
	 */
	public Pager<SalesmanMg> getDevicePageList(SalesmanMg salesman) throws Exception;
	
	/**
	 * 查询用户列表分页方法
	 * @param record
	 * @return
	 */
	public Pager<SalesmanMg> getUserPageList(SalesmanMg record) throws Exception;
	
	/**
	 * 查询部门领导分页sql
	 * @param record
	 * @return
	 */
	public Pager<SalesmanMg> getDeptLeaderPageList(SalesmanMg record) throws Exception;
	
	/**
	 * 根据部门编码获取部门领导列表
	 * @param salesman
	 * @return
	 */
	public List<SalesmanMg> getDeptLeaderList(String deptId) throws Exception;
	
	/**
	 * 校验帐号是否已经存在
	 * @param mobile
	 * @return
	 */
	public int chekAccountIsExist(String mobile) throws Exception;
	
	/**
	 * 根据部门ID查询对应部门用户列表
	 * @param deptCode
	 * @return
	 */
	public List<SalesmanMg> getSalesmanList(SalesmanMg salesman) throws Exception;
}
