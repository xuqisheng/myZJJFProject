package com.corner.core.enums;

/**
 * 库存编号
 */
public enum StockNo {
	DEFAULT_STOCK("正常库存", 0),
	LOCK_STOCK("锁定库存", 10),
	EXAMINE_STOCK("检查库存", 20),
	TRANSPORT_STOCK("在途库存", 30),
	SEND_STOCK("送达库存", 40);
	private String name;
	private int index;
	StockNo(String name, int index) {
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
