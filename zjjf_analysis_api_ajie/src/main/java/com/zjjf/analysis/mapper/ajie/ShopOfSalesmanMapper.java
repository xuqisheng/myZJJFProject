package com.zjjf.analysis.mapper.ajie;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zjjf.analysis.beans.analysis.saleman.ShopOfSalesman;

public interface ShopOfSalesmanMapper {
	
	List<String> getShopBySalesmanIds(@Param("salesmanIds") String salesmanIds);
	
    int deleteByPrimaryKey(String shopId);

    int insert(ShopOfSalesman record);

    int insertSelective(ShopOfSalesman record);

    ShopOfSalesman selectByPrimaryKey(String shopId);

    int updateByPrimaryKeySelective(ShopOfSalesman record);

    int updateByPrimaryKey(ShopOfSalesman record);
}