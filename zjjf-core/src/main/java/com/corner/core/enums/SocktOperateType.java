package com.corner.core.enums;
/**
 *
 */
public enum SocktOperateType {
	/*# 1001-订单业务:10011-下单，10012-提单，10013-打单，10014-派单，10015-派单中，10016-送达 ，10018-取消订单；
	 # 1006-修正业务:10064-报损单，10065-报溢单
  	 # 1007-销售业务:10076-销售出库 ,10077-销售退货
  	 # 1008-采购业务:10085-采购入库 ,10086-采购入库(修正出入库使用) ,10087-采购退货
	 # 2001-初始化业务:20011-仓库初始化业务(批量),20012-仓库初始化业务(单个)）
	 # 1009-线下订单业务:线下打单10093
	*/
	Operate_10011("下单" , 10011),
	Operate_10012("提单" , 10012),
	Operate_10013("打单" , 10013),
	Operate_10014("派单" , 10014),
	Operate_10015("派单中" , 10015),
	Operate_10016("送达" , 10016),
	Operate_10018("取消订单" , 10018),
	Operate_10064("报损单" , 10064),
	Operate_10065("报溢单" , 10065),
	Operate_10075("销售出库出库" , 10075),
	Operate_10076("销售出库送达" , 10076),
	Operate_10077("销售退货" , 10077),
	Operate_10085("采购入库" , 10085),
	Operate_10086("采购入库(修正出入库使用)" , 10086),
	Operate_10087("采购退货" , 10087),
	Operate_20011("仓库初始化业务(批量)" , 20011),
	Operate_20012("仓库初始化业务(单个)" , 20012),
	Operate_10093("线下打单" , 10093);



	private String name;
	private int index;

	private SocktOperateType(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
