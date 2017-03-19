package com.corner.kefu.enums.sku;

public enum SKUPublishIsShow {
	YES("显示", 1), NO("不显示", 0);

	private String name;

	private Integer index;

	SKUPublishIsShow(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public static String getName(int index) {
		for (SKUPublishIsShow skuPublishIsShow : SKUPublishIsShow.values()) {
			if(skuPublishIsShow.getIndex().intValue() == index){
				return skuPublishIsShow.getName();
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

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

}
