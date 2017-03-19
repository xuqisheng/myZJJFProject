package com.corner.salesman.dao;

import java.util.List;

import com.corner.salesman.common.persistence.CrudDao;
import com.corner.salesman.common.persistence.annotation.MyBatisDao;
import com.corner.salesman.model.SignInfo;

@MyBatisDao
public interface SignInfoMapper extends CrudDao<SignInfo> {

	/**
	 * 根据用户ID查询该用户签到信息列表
	 * @param signInfo
	 * @return
	 */
    public List<SignInfo> findSigntListByUserId2(SignInfo signInfo);
    
	/**
	 * 根据用户ID查询该我的签到列表
	 * @param signInfo
	 * @return
	 */
    public List<SignInfo> findMySigntList(SignInfo signInfo);
    
	/**
	 * 根据时间查询当前所有外勤签到列表
	 * @param signInfo
	 * @return
	 */
    public List<SignInfo> findOutWorkSignList(SignInfo signInfo);
    
	/**
	 * 根据用户ID查询该用户签到明细列表
	 * @param signInfo
	 * @return
	 */
    public List<SignInfo> findMySigntDetailList(SignInfo signInfo);
    
    /**
     * 根据用户组ID查询该组签到的信息列表
     * @param signInfo
     * @return
     */
    public List<SignInfo> findSigntListByGroupId(SignInfo signInfo);
    
    /**
     * 添加签到信息
     * @param signInfo
     * @return
     */
    public int insertSignInfo(SignInfo signInfo);
    
    /**
     * 获取数据库中签到最新日期
     * @return
     */
    public String getSigntMaxDate();
    
    /**
     * 检查用户是否已经签到过
     * @param signInfo
     * @return
     */
    public int checkUserIsSignt(SignInfo signInfo);
    
    /**
     * 保存当天的拜访记录
     * @param signInfo
     * @return
     */
    public int insertShopVisitRecord(SignInfo signInfo);
    
    /**
     * 保存当天的拜访记录
     * @param signInfo
     * @return
     */
    public int updateShopVisitRecord(SignInfo signInfo);
    
}