package com.corner.salesman.dao;

import com.corner.salesman.common.persistence.annotation.MyBatisDao;
import com.corner.salesman.model.Daily;

import java.util.List;

/**
 * 日报接口
 * @author yuanbao
 * 
 */
@MyBatisDao
public interface DailyMapper {
	
    int deleteByPrimaryKey(String reportId);

    int insert(Daily record);

    int insertSelective(Daily record);

    Daily selectByPrimaryKey(String reportId);

    int updateByPrimaryKeySelective(Daily record);

    int updateByPrimaryKey(Daily record);
    
    /**
     * 根据条件查询日报列表信息
     * @param record
     * @return
     */
    List<Daily> findDailyList(Daily record);
    
    /**
     * 根据条件查询我的日报列表信息
     * @param record
     * @return
     */
    List<Daily> findMyDailyList(Daily record);
    
}