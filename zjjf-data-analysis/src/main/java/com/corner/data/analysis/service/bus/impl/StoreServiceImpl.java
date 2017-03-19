package com.corner.data.analysis.service.bus.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.vo.Pager;
import com.corner.data.analysis.beans.vo.StoreVo;
import com.corner.data.analysis.dao.StoreMgMapper;
import com.corner.data.analysis.service.bus.StoreService;

/**
 * ClassName: StoreServiceImpl
 * 
 * @Description: 店铺信息业务逻辑层
 * @author 元宝
 * @date 2016年01月8日
 */
@Service
public class StoreServiceImpl implements StoreService {
	
	@Autowired
	private StoreMgMapper storeMapper;

	/**
	 * @Description: 店铺信息查询方法
	 * @param storeVo
	 * @return 
	 * @author 元宝	yuanbao@izjjf.cn
	 */
	@Override
	public List<StoreVo> findStoreList(StoreVo storeVo) throws Exception {
		return storeMapper.findStoreList(storeVo);
	}
	
	/**
	 * @Description: 店铺信息分页查询方法
	 * @param storeVo
	 * @return 
	 * @author 元宝	yuanbao@izjjf.cn
	 */
	@Override
	public Pager<StoreVo> findPagerList(StoreVo storeVo) throws Exception {
		List<StoreVo> list = storeMapper.getStoreList(storeVo);
		int size = storeMapper.getStoreListSize(storeVo);
		return new Pager<StoreVo>(size,list);
	}
}
