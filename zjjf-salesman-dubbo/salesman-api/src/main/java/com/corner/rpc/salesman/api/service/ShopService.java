package com.corner.rpc.salesman.api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.corner.rpc.salesman.model.Shop;
import com.corner.salesman.commons.persistence.Page;

/**  
 * @desc  敬业者业务层接口
 * 创建时间：2015-1-27 下午5:15:03  
 * @author 元宝  
 * @version 2.2  
 */

public interface ShopService {
	
	/**
	 * 根据ID查询条件查询经验者信息
	 * @param shop
	 * @return
	 * @throws Exception
	 */
	public Shop findShopById(Map<String,Object> paramMap) throws Exception;
	
	/**
	 * 根据查询条件查询经验者信息
	 * @param shop
	 * @return
	 * @throws Exception
	 */
	public List<Shop> findShopList(Shop shop) throws Exception;
	
    /**
     * 添加经营者信息方法
     * @param shop
     * @return
     */
    public int addShopInfo(Shop shop) throws Exception;
    
    /**
     * 修改经营者信息方法
     * @param shop
     * @return
     */
    public int updateShopInfo(Shop shop) throws Exception;
    
    /**
     * 根据业务员ID 查询该业务员的相关门店
     * @param shop
     * @return
     */
    public List<Shop> findShopListByUserId(Shop shop) throws Exception;
    
    /**
     * 根据业务员ID 查询该业务员的相关门店(分页方法)
     * @param shop
     * @return
     */
	public Page<Shop> findShopListByUserId(Page<Shop> page, Shop shop) throws Exception;
	
	/**
	 * 检查对应的定格是否已经存在
	 */
	public int checkShopIsExist(String shopNo) throws Exception;
	
	/**
	 * 根据店铺ID获取对应管理定格和线路名称
	 * @param shopId
	 * @return
	 * @throws Exception
	 */
    public Map<String,String> getSpGroupAndLineName(String shopId) throws Exception;
    
    /**
     * 
     * @param userId
     * @param list
     * @throws Exception
     */
    public void batchSaveShop(String userId,List<Shop> list) throws Exception;
    
    /**
     * 查询店宝新增的客户信息（供同步店宝数据使用）
     * @param shopInfo
     * @return
     */
    public List<Shop> queryAddToShopList(Map<String,Object> paramMap) throws Exception; 
    
    /**
     * 查询客户线路列表
     * @param shopInfo
     * @return
     */
    public List<HashMap<String,Object>> getMyShopLineList(Shop shop) throws Exception;
    
    /**
     * 查询我的客户列表
     * @param shopInfo
     * @return
     */  
    public Page<Shop> getMyShopList(Page<Shop> page, Shop shop) throws Exception;
    
    /**
     * 批量将指定业务员绑定店铺
     * @param shop
     * @throws Exception
     */
    public void bacthBindShop(Shop shop) throws Exception;
    
    /**
     * 根据查询条件获取店铺坐标信息
     * @param shop
     * @return
     */
    public List<HashMap<String,Object>> getShopSiteList(Shop shop) throws Exception;
    
    /**
     * 如果客户经纬度为空，则更新客户坐标信息
     * @param paraMap
     * @return
     */
    public void updateShopCoordinate(Map<String,Object> paraMap) throws Exception;
    
    /**
     * 根据用户ID查询对应业务员负责的店铺
     * @param userId
     * @return
     */
    public List<String> getShopNoList(String userId) throws Exception;
    
    /**
     * 根据部门ID查询对应部门业务员负责全部店铺编码
     * @param deptId
     * @return
     */
    public List<String> getShopNoListByDeptId(String deptId) throws Exception;
    
    /**
     * 获取tbl_shop_t表中全部有效的店铺编码列表
     * @param 
     * @return
     */
    public List<String> getAllShopNoList() throws Exception;
    
    /**
     * 查询已经绑定的业务员的客户关系列表
     * @return
     */
    public Page<Shop> getStoreRelationList(Page<Shop> page, Shop shop) throws Exception;
    
}
