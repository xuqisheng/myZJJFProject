package com.corner.scms.service.sp.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.ScmsStockLog;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.PlantItemMapper;
import com.corner.core.dao.ScmsStockLogMapper;
import com.corner.core.utils.StringUtil;
import com.corner.scms.beans.ro.StockManagerParamRo;
import com.corner.scms.beans.vo.ScmsStockLogVo;
import com.corner.scms.dao.ScmsStockLogMgMapper;
import com.corner.scms.service.sp.ScmsStockLogMgService;
@Service
public class ScmsStockLogMgServiceImpl implements ScmsStockLogMgService {

	//core工程里面的PlantItemMapper-dao层接口对象
	@Autowired
	private ScmsStockLogMapper scmsStockLogCore;
	//本工程里面的ScmsStockLogMgMapper-dao层接口对象
	@Autowired
	private ScmsStockLogMgMapper scmsStockLogMgMapper;
	@Autowired
	private PlantItemMapper plantItemMapper;
	
	
	
	/**
	 * 获取所有记录数
	 * @author longwu at  2015年12月4日下午1:52:45
	 * @email tiezhongtang@izjjf.cn
	 * @param int
	 * @return
	 */
	@Override
	public Pager<ScmsStockLogVo> getAllStockLog(StockManagerParamRo stockManagerParam) {
		List<ScmsStockLogVo> ScmsStockLogVoList = scmsStockLogMgMapper.getAllScmsStockLogByParam(stockManagerParam);
		int count = scmsStockLogMgMapper.getScmsStockLogCount(stockManagerParam);
		return new Pager<ScmsStockLogVo>(count, ScmsStockLogVoList);
	}

	@Override
	public int addStockLog(ScmsStockLog scmsStockLog) {
		scmsStockLog.setId(StringUtil.getUUID());
		scmsStockLog.setAddTime(new Date());
		scmsStockLog.setIsDelete(false);
		scmsStockLog.setStatus(Byte.parseByte("1"));
		return scmsStockLogMgMapper.insertSelective(scmsStockLog);
		
	}

}
