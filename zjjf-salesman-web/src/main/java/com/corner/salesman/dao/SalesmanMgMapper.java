package com.corner.salesman.dao;

import java.util.List;

import com.corner.salesman.model.CustomerService;
import com.corner.salesman.model.SalesmanMg;

public interface SalesmanMgMapper {
    int deleteByPrimaryKey(String id);

    int insert(SalesmanMg record);

    int insertSelective(SalesmanMg record);

    SalesmanMg selectByPrimaryKey(String id);
    
    SalesmanMg findSalesmanById(SalesmanMg record);

    int updateByPrimaryKeySelective(SalesmanMg record);

    int updateByPrimaryKey(SalesmanMg record);
    
	List<SalesmanMg> getPageList(SalesmanMg record);

	int getPageListSize(SalesmanMg record);
	
	List<SalesmanMg> getDevicePageList(SalesmanMg record);

	int getDevicePageListSize(SalesmanMg record);
	
	/**
	 * 查询部门领导分页sql
	 * @param record
	 * @return
	 */
	List<SalesmanMg> getDeptLeaderPageList(SalesmanMg record);
	/**
	 * 查询部门领导分页总数sql
	 * @param record
	 * @return
	 */
	int getDeptLeaderPageSize(SalesmanMg record);
	
	
	/**
	 * 获取用户列表
	 * @param record
	 * @return
	 */
	List<SalesmanMg> getUserPageList(SalesmanMg record);
	/**
	 * 获取用户列表总数
	 * @param record
	 * @return
	 */
	int getUserPageSize(SalesmanMg record);
	
	/**
	 * 解绑设备ID
	 * @return
	 * @throws Exception
	 */
	public int updateUnbindDeviceId(SalesmanMg salesman);
	
	/**
	 * 解绑时将解绑信息insert到设备日志表中
	 * @param salesman
	 * @return
	 */
	public int insertDeviceLog(SalesmanMg salesman);
	
	/**
	 * 根据部门编码获取部门领导列表
	 * @param salesman
	 * @return
	 */
	public List<SalesmanMg> getDeptLeaderList(String deptCode);
	
	/**
	 * 校验帐号是否已经存在
	 * @param mobile
	 * @return
	 */
	public int chekAccountIsExist(String mobile);
	
	/**
	 * 根据部门ID查询对应部门用户列表
	 * @param deptCode
	 * @return
	 */
	public List<SalesmanMg> getSalesmanList(SalesmanMg salesman);
	
	public CustomerService getUserByCustomerServiceCredential(String credential);
}