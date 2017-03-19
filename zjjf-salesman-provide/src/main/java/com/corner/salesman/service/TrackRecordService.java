package com.corner.salesman.service;

import java.util.List;

import com.corner.salesman.commons.persistence.Page;
import com.corner.salesman.model.TrackRecord;

/**  
 * @desc  业务员轨迹业务层接口
 * 创建时间：2015-1-27 下午5:15:03  
 * @author 元宝  
 * @version 2.2  
 */

public interface TrackRecordService {
	/**
	 * 根据用户ID查询该用户轨迹信息列表
	 * @param trackRecord
	 * @return
	 */
    public List<TrackRecord> findTrackRecordByUserId(TrackRecord trackRecord) throws Exception;
    
	/**
	 * 根据用户ID+时间查询该用户轨迹坐标信息列表
	 * @param trackRecord
	 * @return
	 */
    public List<TrackRecord> findMyAllDateTrackList(TrackRecord trackRecord) throws Exception;
    
	/**
	 * 根据用户ID查询该用户轨迹明细列表
	 * @param trackRecord
	 * @return
	 */
    public Page<TrackRecord> findMyTrackDetailList(Page<TrackRecord> page, TrackRecord trackRecord) throws Exception;
    
	/**
	 * 根据用户ID查询该用户轨迹信息列表(分页方法)
	 * @param trackRecord
	 * @return
	 */
	public Page<TrackRecord> findTrackRecordPage(Page<TrackRecord> page, TrackRecord trackRecord) throws Exception;
    
    /**
     * 根据用户组ID查询该组业务员最新规矩列表
     * @param trackRecord
     * @return
     */
    public List<TrackRecord> findGroupNewTrackByTime(TrackRecord trackRecord) throws Exception;
    
	/**
	 * 根据用户组ID查询该用户轨迹信息列表(分页方法)
	 * @param trackRecord
	 * @return
	 */
	public Page<TrackRecord> findGroupNewTrackByTime(Page<TrackRecord> page, TrackRecord trackRecord) throws Exception;
    
    /**
     * 添加轨迹信息
     * @param trackRecord
     * @return
     */
    public int addTrackRecord(TrackRecord trackRecord) throws Exception;
    
    /**
     * 批量添加轨迹信息
     * @param trackRecord
     * @return
     */
    public void addBatchTrackRecord(TrackRecord trackRecord) throws Exception;
    
    /**
     * 查询业务员个人最新轨迹列表
     * @param trackRecord
     * @return
     */
	public Page<TrackRecord> findUserEverydayTrackById(Page<TrackRecord> page, TrackRecord trackRecord) throws Exception;
}
