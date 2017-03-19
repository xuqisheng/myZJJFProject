package com.corner.salesman.dao;

import java.util.List;

import com.corner.salesman.model.AppMonitorLogs;

public interface AppMonitorLogsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AppMonitorLogs record);

    int insertSelective(AppMonitorLogs record);

    AppMonitorLogs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppMonitorLogs record);

    int updateByPrimaryKeyWithBLOBs(AppMonitorLogs record);

    int updateByPrimaryKey(AppMonitorLogs record);
    
    List<AppMonitorLogs> getAppLogPageList(AppMonitorLogs record);
    
    int getAppLogPageSize(AppMonitorLogs record);
    
	/**
	 * 查询导出的app日志记录信息
	 * @param appLogs
	 * @return
	 * @throws Exception
	 */
	public List<AppMonitorLogs> findAppLogList(AppMonitorLogs appLogs);
}