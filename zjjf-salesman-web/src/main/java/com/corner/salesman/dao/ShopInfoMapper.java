package com.corner.salesman.dao;

import java.util.List;

import com.corner.salesman.model.ShopInfo;

public interface ShopInfoMapper {
    int deleteByPrimaryKey(String shopId);

    int insert(ShopInfo record);

    int insertSelective(ShopInfo record);

    ShopInfo selectByPrimaryKey(String shopId);

    int updateByPrimaryKeySelective(ShopInfo record);

    int updateByPrimaryKey(ShopInfo record);
    
    int updateBindSpgroupShop(ShopInfo record);
    
    int deleteShopById(String shopId);

	/**
	 * 查询店铺信息
	 * @param record
	 * @return
	 */
    public List<ShopInfo> getShopPageList(ShopInfo record);
    
	/**
	 * 查询店铺信息总数
	 * @param record
	 * @return
	 */
	public int getShopPageSize(ShopInfo record);
	
    public List<ShopInfo> getChoicePageList(ShopInfo record);
    
    public int getChoicePageSize(ShopInfo record);
	
	public int checkShopIsExist(String shopNo);
}