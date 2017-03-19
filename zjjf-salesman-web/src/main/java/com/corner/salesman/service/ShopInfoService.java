package com.corner.salesman.service;

import java.util.Map;

import com.corner.core.beans.vo.Pager;
import com.corner.salesman.model.ShopInfo;

/**
 * 客户数据接口
 * @author Administrator
 *
 */
public interface ShopInfoService {
	
    
	/**
	 * 查询客户分页方法
	 * @param shopVO
	 * @return
	 * @throws Exception
	 */
	public Pager<ShopInfo> getShopInfoPageList(ShopInfo shopVO) throws Exception;
	
	/**
	 * 查询选择客户划分路线使用分页方法
	 * @param shopVO
	 * @return
	 * @throws Exception
	 */
	public Pager<ShopInfo> getChoicePageList(ShopInfo shopVO) throws Exception;
	
	/**
	 * 添加客户信息方法
	 * @param shopVO
	 * @return
	 * @throws Exception
	 */
	public int addShopInfo(ShopInfo shopVO) throws Exception;
	
	/**
	 * 修改客户信息方法
	 * @param shopVO
	 * @return
	 * @throws Exception
	 */
	public int updateShopInfo(ShopInfo shopVO) throws Exception;
	
	public int updateBindSpgroupShop(ShopInfo record) throws Exception;
	
	/**
	 * 删除客户信息方法
	 * @param shopVO
	 * @return
	 * @throws Exception
	 */
	public int deleteShopInfo(ShopInfo shopVO) throws Exception;
	
	/**
	 * 根据ID查询客户信息
	 * @param shopId
	 * @return
	 * @throws Exception
	 */
	public ShopInfo findShopInfoById(String shopId) throws Exception;
	
	/**
	 * 检查新增客户ID是否已经存在
	 * @param shopId
	 * @return
	 */
	public int checkShopIsExist(String shopNo) throws Exception;

	/**
	 * 为excel解析准备转换信息
	 * @return
	 * @throws Exception
	 */
	//public Map<String,Object> getShopTypeMap()throws Exception;
	/**
	 * 批量同步店宝店铺信息到阿街系统
	 * @param shopVO
	 * @throws Exception
	 */
	public void batchSyncShop(ShopInfo shopVO) throws Exception;
}
