package com.corner.salesman.service;

import com.corner.core.beans.vo.Pager;
import com.corner.salesman.model.DeviceLog;

/**
 * 业务员服务层接口
 * @author Longx
 *
 */
public interface DeviceLogService {

	/**
	 * 查询设备列表信息分页方法
	 * @param deviceLog
	 * @return
	 * @throws Exception
	 */
	public Pager<DeviceLog> getDevicePageList(DeviceLog deviceLog) throws Exception;
}
