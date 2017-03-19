package com.corner.salesman.dao;

import java.util.List;

import com.corner.salesman.common.persistence.CrudDao;
import com.corner.salesman.common.persistence.annotation.MyBatisDao;
import com.corner.salesman.model.TrackRecord;

@MyBatisDao
public interface TrackRecordMapper extends CrudDao<TrackRecord> {

	/**
	 * 根据用户ID查询该用户轨迹信息列表
	 * @param trackRecord
	 * @return
	 */
    public List<TrackRecord> findTrackRecordByUserId(TrackRecord trackRecord);
    
    
	/**
	 * 根据用户ID+时间查询该用户轨迹坐标信息列表
	 * @param trackRecord
	 * @return
	 */
    public List<TrackRecord> findMyAllDateTrackList(TrackRecord trackRecord);
    
	/**
	 * 根据用户ID查询该用户轨迹明细列表
	 * @param trackRecord
	 * @return
	 */
    public List<TrackRecord> findMyTrackDetailList(TrackRecord trackRecord);
    
    
    /**
     * 根据用户组ID查询该组业务员最新规矩列表
     * @param trackRecord
     * @return
     */
    public List<TrackRecord> findGroupNewTrackByTime(TrackRecord trackRecord);
    
    /**
     * 添加轨迹信息
     * @param trackRecord
     * @return
     */
    public int insertTrackRecord(TrackRecord trackRecord);
    
    /**
     * 添加个人最新轨迹记录到表中
     * @param trackRecord
     * @return
     */
    public int insertTrackRecordNew(TrackRecord trackRecord);
    
    /**
     * 删除业务员个人最新轨迹信息
     * @param trackRecord
     * @return
     */
    public int deleteTrackRecordNewByUserId(TrackRecord trackRecord);
    
    /**
     * 查询业务员个人最新轨迹列表
     * @param trackRecord
     * @return
     */
    public List<TrackRecord> findUserEverydayTrackById(TrackRecord trackRecord);
    
    
    /**
     * 获取数据库中轨迹最新日期
     * @return
     */
    public String getTrackMaxDate();
}