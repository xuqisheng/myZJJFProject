package com.corner.kefu.enums.sku;

public enum SKUGoodType {
    COMMONGOODS("普通商品",0),
    SUITGOODS("套装商品",3);
   
	private String name;
	
	private Byte index;
	
	SKUGoodType(String name, int index){
		this.name = name;
		this.index = (byte)index;
	}

	public static String getName(Byte index) {
		for (SKUGoodType skuGoodType : SKUGoodType.values()) {
			if(index.intValue() == skuGoodType.getIndex().intValue()){
				return skuGoodType.getName();
			}
		}
		return "";
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
