package com.corner.data.analysis.service.bus;

import java.util.List;

import com.corner.core.beans.vo.Pager;
import com.corner.data.analysis.beans.vo.StoreVo;

/**
 * ClassName: StoreService
 * 
 * @Description: 店铺信息业务层接口
 * @author 元宝
 * @date 2016年01月8日
 */
public interface StoreService {
	/**
	 * @Description: 店铺信息查询列表
	 * @param storeVo
	 * @return 
	 * @author 元宝	yuanbao@izjjf.cn
	 */
	public List<StoreVo> findStoreList(StoreVo storeVo) throws Exception;
	
	/**
	 * @Description: 店铺信息分页查询方法
	 * @param storeVo
	 * @return 
	 * @author 元宝	yuanbao@izjjf.cn
	 */
	public Pager<StoreVo> findPagerList(StoreVo storeVo) throws Exception;
}
