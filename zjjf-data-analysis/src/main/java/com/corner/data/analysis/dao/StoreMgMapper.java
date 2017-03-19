package com.corner.data.analysis.dao;

import java.util.List;

import com.corner.data.analysis.beans.vo.StoreVo;
/**
 * ClassName: StoreMapper
 * 
 * @Description: 店铺信息 模型层接口
 * @author 元宝
 * @date 2016年01月8日
 */
public interface StoreMgMapper {
	
	/**
	 * @Description: 店铺信息查询列表方法
	 * @param statisVo
	 * @return 
	 * @author 元宝	yuanbao@izjjf.cn
	 */
	public List<StoreVo> findStoreList(StoreVo orderVo);

	/**
	 * @Description: 店铺信息查询列表方法
	 * @param statisVo
	 * @return 
	 * @author 元宝	yuanbao@izjjf.cn
	 */
	public List<StoreVo> getStoreList(StoreVo orderVo);
	
	/**
	 * @Title: getPageListSize 
	 * @Description: 店铺信息查询总数方法
	 * @param orderVo
	 * @return int   返回类型
	 * @author 元宝	yuanbao@izjjf.cn
     */
	public int getStoreListSize(StoreVo orderVo);
	
}
