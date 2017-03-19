package com.corner.rpc.salesman.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.corner.rpc.salesman.model.Shop;

public interface ShopMapper {
    int deleteByPrimaryKey(String shopId);

    int insert(Shop record);

    int insertSelective(Shop record);

    Shop selectByPrimaryKey(Map<String,Object> paramMap);

    int updateByPrimaryKeySelective(Shop record);

    int updateByPrimaryKey(Shop record);
    
    int checkShopIsExist(String ShopId);
    
    List<HashMap<String,Object>> getMyShopLineList(String ShopId);
    
    List<Shop> getMyShopList(Shop shop);
    
    //List<Shop> getMyTotalPlansShopList(Shop shop);
    
    int bacthBindShop(Shop shop);
    
    /**
     * 根据业务员ID 查询该业务员的相关门店
     * @param shopInfo
     * @return
     */
    public List<Shop> findShopListByUserId(Shop shop);
    
    /**
     * 根据业务员ID 查询相关门店坐标点
     * @param shopInfo
     * @return
     */
    //public List<Shop> findShopCoordinateList(Shop shop);
    
    /**
     * 根据查询条件查询店铺信息列表
     * @param shopInfo
     * @return
     */
    //public List<Shop> queryShopList(Shop shop);
    
    /**
     * 查询店宝新增的客户信息（供同步店宝数据使用）
     * @param shopInfo
     * @return
     */
    public List<Shop> queryAddToShopList(Map<String,Object> paramMap); 
    
	/**
	 * 根据店铺ID获取对应管理定格和线路名称
	 * @param shopId
	 * @return
	 * @throws Exception
	 */
    public Map<String,String> getSpGroupAndLineName(String shopId);
    
    /**
     * 根据线路ID获取线路店铺信息
     * @param lineId
     * @return
     */
    public List<HashMap<String,Object>> getShopByLineId(Map<String,Object> paramMap);
    
    /**
     * 根据查询条件获取店铺坐标信息
     * @param shop
     * @return
     */
    public List<HashMap<String,Object>> getShopSiteList(Shop shop);
    
    /**
     * 如果客户经纬度为空，则更新客户坐标信息
     * @param paraMap
     * @return
     */
    public int updateShopCoordinate(Map<String,Object> paraMap);
    
    /**
     * 根据用户ID查询对应业务员负责的店铺
     * @param userId
     * @return
     */
    public List<String> getShopNoList(String userId);
    
	/**
     * 根据部门ID查询对应部门业务员负责全部店铺编码
     * @param deptId
     * @return
     */
    public List<String> getShopNoListByDeptId(String deptId);
    
    /**
     * 获取tbl_shop_t表中全部有效的店铺编码列表
     * @param 
     * @return
     */
    public List<String> getAllShopNoList();
    
    /**
     * 查询已经绑定的业务员的客户关系列表
     * @return
     */
    public List<Shop> getStoreRelationList(Shop shop);
    
}