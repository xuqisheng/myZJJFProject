package com.corner.salesman.service;

import java.util.HashMap;
import java.util.List;

import com.corner.salesman.commons.persistence.Page;
import com.corner.salesman.model.ShopInfo;

/**  
 * @desc  敬业者业务层接口
 * 创建时间：2015-1-27 下午5:15:03  
 * @author 元宝  
 * @version 2.2  
 */

public interface ShopInfoService {
	
	/**
	 * 根据ID查询条件查询经验者信息
	 * @param shopInfo
	 * @return
	 * @throws Exception
	 */
	public ShopInfo findShopInfoById(String shopId) throws Exception;
	
	/**
	 * 根据查询条件查询经验者信息
	 * @param shopInfo
	 * @return
	 * @throws Exception
	 */
	public List<ShopInfo> findShopInfoList(ShopInfo shopInfo) throws Exception;
	
    /**
     * 添加经营者信息方法
     * @param shopInfo
     * @return
     */
    public int addShopInfoInfo(ShopInfo shopInfo) throws Exception;
    
    /**
     * 修改经营者信息方法
     * @param shopInfo
     * @return
     */
    public int updateShopInfoInfo(ShopInfo shopInfo) throws Exception;
    
    /**
     * 根据业务员ID 查询该业务员的相关门店
     * @param shopInfo
     * @return
     */
    public List<ShopInfo> findShopListByUserId(ShopInfo shopInfo) throws Exception;
    
    /**
     * 根据业务员ID 查询该业务员的相关门店(分页方法)
     * @param shopInfo
     * @return
     */
	public Page<ShopInfo> findShopListByUserId(Page<ShopInfo> page, ShopInfo shopInfo) throws Exception;
	
    /**
     * 根据业务员ID 查询相关门店坐标点
     * @param shopInfo
     * @return
     */
    public List<ShopInfo> findShopCoordinateList(ShopInfo shopInfo) throws Exception;
    
    /**
     * 根据查询条件查询店铺信息列表(分页方法)
     * @param page
     * @param shopInfo
     * @return
     * @throws Exception
     */
    public Page<ShopInfo> queryShopList(Page<ShopInfo> page, ShopInfo shopInfo) throws Exception;
    
    /**
     * 根据用户ID查询对应业务员负责的店铺
     * @param userId
     * @return
     */
    //public List<String> getShopNoList(String userId) throws Exception;
}
