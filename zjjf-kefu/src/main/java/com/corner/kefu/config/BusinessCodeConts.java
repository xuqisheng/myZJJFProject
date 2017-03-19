package com.corner.kefu.config;

/**
 * 业务返回码，处理本工程 Controll和Service的错误码
 * @ClassName: BusinessCodeConts
 * @Description: 
 * @author 小武
 * @version 七彩虹版本
 * @date 2016年8月24日 下午5:11:48
 *
 */
public interface BusinessCodeConts{
	//000000 表示所有业务处理成功
	String allbusiness_ok = "000000";
	
	//100000-199999 代表系统基础服务错误，比如 省市区、分类、品牌、商品库
	String cat_11001 = "11001";  //查询基础商品库分类错误
	
	//200000-299999 代表业务界面错误
	
	
	
}