package com.corner.salesman.dao;

import com.corner.salesman.common.persistence.annotation.MyBatisDao;
import com.corner.salesman.model.AppMonitorLogs;
/**
 * 监控记录app状况接口
 * @author yuanbao
 * 
 */
@MyBatisDao
public interface AppMonitorLogsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AppMonitorLogs record);

    int insertSelective(AppMonitorLogs record);

    AppMonitorLogs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppMonitorLogs record);

    int updateByPrimaryKeyWithBLOBs(AppMonitorLogs record);

    int updateByPrimaryKey(AppMonitorLogs record);
}