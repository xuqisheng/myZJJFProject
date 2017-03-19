package com.corner.salesman.dao;

import java.util.List;

import com.corner.salesman.common.persistence.CrudDao;
import com.corner.salesman.common.persistence.annotation.MyBatisDao;
import com.corner.salesman.model.Department;
import com.corner.salesman.model.User;

@MyBatisDao
public interface MyUnderlingMapper extends CrudDao<User> {
    
    /**
     * 根据部门ID查询自己所在部门下的子部门
     * @param deptId
     * @return
     */
    public List<Department> findMyUnderlingDeptList(String deptId);
    
    /**
     * 根据部门ID查询对应部门总人数
     * @param deptId
     * @return
     */
    public int findMyUnderlingTatol(String deptId);
    
    /**
     * 根据部门ID查询自己所在部门的下属
     * @param deptId
     * @return
     */
    public List<User> findMyUnderlingList(String deptId);
    
    /**
     * 选择用户ID查询自己的数据信息
     * @param user
     * @return
     */
    public List<User> findMyselfUserList(String userId);
    
    /**
     * 根据部门ID查询对应用户信息
     * @param deptId
     * @return
     */
    public List<User> findDeptUserList(String userId);
    
}