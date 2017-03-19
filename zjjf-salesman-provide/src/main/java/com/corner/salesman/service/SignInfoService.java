package com.corner.salesman.service;

import java.util.List;

import com.corner.salesman.common.utils.Json;
import com.corner.salesman.commons.persistence.Page;
import com.corner.salesman.model.SignInfo;

/**  
 * @desc  用户业务层接口
 * 创建时间：2015-1-27 下午5:15:03  
 * @author 元宝  
 * @version 2.2  
 */

public interface SignInfoService {
	
	/**
	 * 根据用户ID查询该用户签到信息列表
	 * @param signInfo
	 * @return
	 */
    //public List<SignInfo> findSigntListByUserId(SignInfo signInfo) throws Exception;
    
	/**
	 * 我的签到列表(分页方法)
	 * @param signInfo
	 * @return
	 */
    public Page<SignInfo> findMySigntList(Page<SignInfo> page, SignInfo signInfo) throws Exception;
    
	/**
	 * 我的签到明细列表(分页方法)
	 * @param signInfo
	 * @return
	 */
    public Page<SignInfo> findMySigntDetailList(Page<SignInfo> page, SignInfo signInfo) throws Exception;
    
    /**
     * 根据用户组ID查询该组签到的信息列表
     * @param signInfo
     * @return
     */
    public List<SignInfo> findSigntListByGroupId(SignInfo signInfo) throws Exception;
    
	/**
	 * 根据时间查询当前所有外勤签到列表
	 * @param signInfo
	 * @return
	 */
    public List<SignInfo> findOutWorkSignList(SignInfo signInfo) throws Exception;
    
    
    /**
     * 根据用户组ID查询该组签到的信息列表
     * @param signInfo
     * @param appSignInfo
     * @return
     */
    //public List<SignInfo> findSigntListByGroupId(SignInfo signInfo,AppSignInfo appSignInfo) throws Exception;
    
	/**
	 * 根据用户ID查询该用户签到信息列表(分页方法)
	 * @param signInfo
	 * @return
	 */
    //public Page<SignInfo> findSigntListByGroupId(Page<SignInfo> page, SignInfo signInfo) throws Exception;
    
    /**
     * 添加签到信息
     * @param signInfo
     * @return
     */
    public int addSignInfo(SignInfo signInfo) throws Exception;
    
    public Json addSignData(SignInfo signInfo) throws Exception;
    
    /**
     * 检查用户是否已经签到过
     * @param signInfo
     * @return
     */
    public int checkUserIsSignt(SignInfo signInfo) throws Exception;
}
