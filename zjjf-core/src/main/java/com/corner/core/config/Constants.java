 package com.corner.core.config;

/**
 * 
* @ClassName: Constants 
* @Description: 公用常量定义
* @author 孟星魂	mengxinghun@izjjf.cn
* @date 2016年1月20日 上午10:59:35 
*
 */
public final class Constants {
	
	//客服状态
	public static final short SCMS_KEFU_SATUS_1= 1;//未审核
	public static final short SCMS_KEFU_SATUS_2= 2;//已审核
	public static final short SCMS_KEFU_SATUS_3= 3;//已提交
	//订单状态
	public static final short SCMS_ORDER_SATUS_1= 1;//已下单
	public static final short SCMS_ORDER_SATUS_2= 2;//已派单
	public static final short SCMS_ORDER_SATUS_3= 3;//已提单
	public static final short SCMS_ORDER_SATUS_4= 4;//已打印
	public static final short SCMS_ORDER_SATUS_5= 5;//已送达
	public static final short SCMS_ORDER_SATUS_6= 6;//取消的订单
	//经销商状态
	public static final short SCMS_MANAGER_SATUS_1= 1;//未派送
	public static final short SCMS_MANAGER_SATUS_2= 2;//配送中
	public static final short SCMS_MANAGER_SATUS_3= 3;//确认收货
	//仓库状态
	public static final short SCMS_WAREHOUSE_SATUS_1= 1;//待入库
	public static final short SCMS_WAREHOUSE_SATUS_2= 2;//已入库
	public static final short SCMS_WAREHOUSE_SATUS_3= 3;//配送中
	public static final short SCMS_WAREHOUSE_SATUS_4= 4;//确认收货
	//财务审核状态
	public static final short SCMS_FINANCE_SATUS_1= 1;//未审核
	public static final short SCMS_FINANCE_SATUS_2= 2;//已审核(待付款)
	public static final short SCMS_FINANCE_SATUS_3= 3;//已打印结算单
	public static final short SCMS_FINANCE_SATUS_4= 4;//已付款
	
//	/**（子订单）已下单**/
//	public static final short SCMS_ORDER_SATUS_1= 1;
//	/**（子订单）已付款，未配送**/
//	public static final short SCMS_ORDER_SATUS_2= 2;
//	/**（子订单）待收货，配送中**/
//	public static final short SCMS_ORDER_SATUS_3= 3;
//	/**（子订单）已收货**/
//	public static final short SCMS_ORDER_SATUS_4= 4;
//	/**（子订单）取消的订单**/
//	public static final short SCMS_ORDER_SATUS_5= 5;
//	/**(合单) 未提交**/
//	public static final short SCMS_ORDER_SATUS_6= 6;
//	/**(合单) 已提交,未付款**/
//	public static final short SCMS_ORDER_SATUS_7= 7;
//	/**(合单) 财务已付款**/
//	public static final short SCMS_ORDER_SATUS_8= 8;
//	/**(合单) 经销商未发货**/
//	public static final short SCMS_ORDER_SATUS_9= 9;
//	/**(合单) 经销商配送中**/
//	public static final short SCMS_ORDER_SATUS_10= 10;
//	/**(合单) 经销商配送完成,仓库未派送**/
//	public static final short SCMS_ORDER_SATUS_11= 11;
//	/**(合单) 仓库派送中**/
//	public static final short SCMS_ORDER_SATUS_12= 12;
//	/**(合单) 确认收货**/
//	public static final short SCMS_ORDER_SATUS_13= 13;
//	/**图片服务域名**/
//	public static final String  pictureUri = "http://www.izjjf.cn/"; 
}
