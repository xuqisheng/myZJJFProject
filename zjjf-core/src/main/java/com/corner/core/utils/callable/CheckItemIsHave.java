package com.corner.core.utils.callable;

/**
 * 库存操作存储过程、
 *
 * 调用实体
 */
public class CheckItemIsHave {
    public CheckItemIsHave(String whId, Integer itemId, Integer typeMg) {
		super();
		this.whId = whId;
		this.itemId = itemId;
		this.typeMg = typeMg;
	}

	private String whId;
    private Integer itemId;
    private Integer typeMg;

    public String getWhId() {
        return whId;
    }

    public void setWhId(String whId) {
        this.whId = whId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getTypeMg() {
        return typeMg;
    }

    public void setTypeMg(Integer typeMg) {
        this.typeMg = typeMg;
    }
}
