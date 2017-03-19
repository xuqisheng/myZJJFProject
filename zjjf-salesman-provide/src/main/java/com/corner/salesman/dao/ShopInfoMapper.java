package com.corner.salesman.dao;

import java.util.HashMap;
import java.util.List;

import com.corner.salesman.common.persistence.annotation.MyBatisDao;
import com.corner.salesman.model.ShopInfo;

@MyBatisDao
public interface ShopInfoMapper {
    int deleteByPrimaryKey(String shopId);

    int insert(ShopInfo record);

    int insertSelective(ShopInfo record);

    ShopInfo selectByPrimaryKey(String shopId);

    int updateByPrimaryKeySelective(ShopInfo record);

    int updateByPrimaryKey(ShopInfo record);
    
    /**
     * 根据业务员ID 查询该业务员的相关门店
     * @param shopInfo
     * @return
     */
    public List<ShopInfo> findShopListByUserId(ShopInfo shopInfo);
    
    /**
     * 根据业务员ID 查询相关门店坐标点
     * @param shopInfo
     * @return
     */
    public List<ShopInfo> findShopCoordinateList(ShopInfo shopInfo);
    
    /**
     * 根据查询条件查询店铺信息列表
     * @param shopInfo
     * @return
     */
    public List<ShopInfo> queryShopList(ShopInfo shopInfo);
    
    /**
     * 根据用户ID查询对应业务员负责的店铺
     * @param userId
     * @return
     */
    //public List<String> getShopNoList(String userId);
}