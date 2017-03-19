package com.corner.salesman.dao;

import java.util.HashMap;
import java.util.List;

import com.corner.salesman.common.persistence.annotation.MyBatisDao;
import com.corner.salesman.model.UserDept;
@MyBatisDao
public interface UserDeptMapper {
    int insert(UserDept record);

    int insertSelective(UserDept record);
    
    int updateByPrimaryKey(UserDept record);
    
    String findDeptIdByUserId(String userId);
    
    /**
     * 根据部门ID获取部门用户总数
     * @param deptId
     * @return
     */
    int getDeptUserTotal(String deptId);
    
    /**
     * 检查当前用户是否是领导(粗略校验)
     * @param record
     * @return
     */
    int checkIsLeader(String userId);
    
    /**
     * 根据部门ID查询部门对应领导人ID
     * @param deptId
     * @return
     */
    List<UserDept> findLeaderListByDeptId(String deptId);
    
    /**
     * 获取我所在的区域编码
     * @param userId
     * @return
     */
    public HashMap<String,String> getMyInArea(String userId);
}