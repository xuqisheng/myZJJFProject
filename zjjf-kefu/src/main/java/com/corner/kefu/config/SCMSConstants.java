 package com.corner.kefu.config;

/**
 * 
* @ClassName: SCMSConstants 
* @Description: 公用常量定义
* @author 孟星魂	mengxinghun@izjjf.cn
* @date 2016年1月20日 上午10:59:35 
*
 */
public final class SCMSConstants {
	/**--------------------------------页面跳转定义	Begin--------------------------------**/
	/**经销商商品目录**/
	private static String DEALER_GOODS="dealer-goods/";
	/**经销商商品添加界面**/
	public static String DEALER_GOODS_GOODS_ADD = DEALER_GOODS+"goods-add";
	/**经销商商品编辑界面**/
	public static String DEALER_GOODS_GOODS_EDIT = DEALER_GOODS+"goods-edit";
	/**经销商商品起购量**/
	public static String DEALER_GOODS_DELIVERY_MINNUM =DEALER_GOODS+"goods-delivery-minnum";
	/**经销商商品管理首页**/
	public static String DEALER_GOODS_INDEX =DEALER_GOODS+"index";
	/**经销商商品列表**/
	public static String DEALER_GOODS_GOODS =DEALER_GOODS+"goods";
	
	
	/**客户目录**/
	private static String CUSTOMER= "customer/";
	/**经销商列表**/
	public static String DEALER = CUSTOMER+ "dealer";
	/**经销商添加**/
	public static String DEALER_ADD =CUSTOMER +"dealer-add";
	/**经销商编辑**/
	public static String DEALER_EDIT =CUSTOMER +"dealer-edit";
	/**商铺待审核**/
	public static String SHOP_UNAUDITED =CUSTOMER +"shop-unaudited";
	/**商铺管理**/
	public static String SHOP =CUSTOMER +"shop";
	/**商铺详情**/
	public static String SHOP_DETAIL =CUSTOMER +"shop-detail";
	/**商铺详情**/
	public static String SUPPLIER =CUSTOMER +"supplier";
	/**商铺详情**/
	public static String SUPPLIER_EDIT =CUSTOMER +"supplier-edit";
	
	
	/**订单目录**/
	private static String ORDER = "order/"; 
	/**批发商汇总订单**/
	public static String ORDER_DEALER_SUMMARY = ORDER + "dealer-summary";
	/**批发商汇总订单明细**/
	public static String DEALER_SUMMARY_DETAIL = ORDER + "dealer-summary-detail"; 
	/**批发商汇总订单打印**/
	public static String DEALER_SUMMARY_PRINT = ORDER + "dealer-summary-print"; 
	
	
	/**联合采购商品目录**/
	private static String DEALER_GROUP = "dealer-group/"; 
	/**联合采购商品列表**/
	public static String DEALER_GROUP_MANAGE = DEALER_GROUP + "manage";
	/**联合采购商品列表编辑**/
	public static String DEALER_GROUP_MANAGE_GOODS_SP = DEALER_GROUP + "manage-goods-sp";
	/**联合采购商品列表**/
	public static String DEALER_GROUP_MANAGE_GOODS = DEALER_GROUP + "manage-goods";
	/**联合采购商品添加编辑**/
	public static String DEALER_GROUP_MANAGE_SP_ADD = DEALER_GROUP + "manage-sp-add";
	/**联合采购批发商列表**/
	public static String GROUP_GOODS_MANAGE_SP = DEALER_GROUP + "manage-sp";
	
	
	/**批发商定格目录**/
	public static String SUPPLIER_GROUP = "supplier-group/";
	/**批发商定格管理**/
	public static String SUPPLIER_GROUP_INDEX = SUPPLIER_GROUP + "index";
	/**批发商定格管理-关联便利店**/
	public static String SUPPLIER_GROUP_MANAGE_STORE = SUPPLIER_GROUP + "manage-store";
	/**批发商定格管理-关联批发商**/
	public static String SUPPLIER_GROUP_DETAIL = SUPPLIER_GROUP + "detail";
	/**--------------------------------页面跳转定义	End--------------------------------**/
	
	/**--------------------------------返回错误定义	Begin--------------------------------**/
	/**缺少必要参数**/
	public static String IS_NOT_NULL = "缺少必要参数";
	/** 查无数据*/
    public static String NOT_FOUND = "查无数据";
    /**总笔数不匹配*/
    public static String TOTAL_CNT_NOT_MATCH = "总笔数不匹配";
    /**总金额不匹配*/
    public static String TOTAL_AMT_NOT_MATCH = "总金额不匹配";
	
	/**--------------------------------页面跳转定义	End--------------------------------**/
}
