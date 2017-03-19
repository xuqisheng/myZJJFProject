package com.corner.core.enums;

import java.util.HashMap;

/**
 * 库存类型
 */
public enum StockType {
	STOCK("正常库存", 1),
	SALES_STOCK("促销库存", 2),
	SAFE_STOCK("安全库存", 3),
	SEEDS_TOCK("炒货库存", 4),
	DEAD_STOCK("积压库存", 5);

	private String name;
	private Byte index;

	StockType(String name, int index) {
		this.name = name;
		this.index = (byte)index;
	}
	
	
	public static final HashMap<Byte, StockType> map = new HashMap<Byte, StockType>();
	static{
         if(map.size()==0){
        	 map.put(STOCK.getIndex(), STOCK);
        	 map.put(SALES_STOCK.getIndex(), SALES_STOCK);
        	 map.put(SAFE_STOCK.getIndex(), SAFE_STOCK);
        	 map.put(SEEDS_TOCK.getIndex(), SEEDS_TOCK);
        	 map.put(DEAD_STOCK.getIndex(), DEAD_STOCK);
         }
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Byte getIndex() {
		return index;
	}

	public void setIndex(Byte index) {
		this.index = index;
	}
	
}
