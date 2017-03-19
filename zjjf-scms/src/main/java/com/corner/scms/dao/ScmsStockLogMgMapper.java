package com.corner.scms.dao;

import java.util.List;

import com.corner.core.beans.ScmsStockLog;
import com.corner.scms.beans.ro.StockManagerParamRo;
import com.corner.scms.beans.vo.ScmsStockLogVo;

public interface ScmsStockLogMgMapper {
	
	/**
	 * 根据条件查询所有的库存操作记录
	 * @author longwu at  2015年12月4日下午1:52:33
	 * @email tiezhongtang@izjjf.cn
	 * @param List<ScmsStockLogVo>
	 * @return
	 */
	public List<ScmsStockLogVo> getAllScmsStockLogByParam(StockManagerParamRo stockManagerParam);
	
	/**
	 * 获取所有记录数
	 * @author longwu at  2015年12月4日下午1:52:45
	 * @email tiezhongtang@izjjf.cn
	 * @param int
	 * @return
	 */
	public int getScmsStockLogCount(StockManagerParamRo stockManagerParam);
	
	/**
	 * 插入订单记录
	 * @author longwu at  2015年12月4日下午1:52:45
	 * @email tiezhongtang@izjjf.cn
	 * @param int
	 * @return
	 */
	int insertSelective(ScmsStockLog record);

}
