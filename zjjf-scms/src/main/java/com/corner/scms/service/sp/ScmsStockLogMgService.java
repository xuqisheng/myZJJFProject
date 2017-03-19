package com.corner.scms.service.sp;

import java.util.List;

import com.corner.core.beans.ScmsStockLog;
import com.corner.core.beans.vo.Pager;
import com.corner.scms.beans.ro.StockManagerParamRo;
import com.corner.scms.beans.vo.ScmsStockLogVo;

public interface ScmsStockLogMgService {
	/**
	 * 获取所有记录数
	 * @author longwu at  2015年12月4日下午3:02:23
	 * @email tiezhongtang@izjjf.cn
	 * @param Pager<ScmsStockLogVo>
	 * @return
	 */
	public Pager<ScmsStockLogVo> getAllStockLog(StockManagerParamRo stockManagerParam);
	
	public int addStockLog(ScmsStockLog scmsStockLog);

}
