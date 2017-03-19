package com.corner.core.beans;

public class TagItemMapKey {
    private String item_id;

    private Integer tag_id;

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id == null ? null : item_id.trim();
    }

    public Integer getTag_id() {
        return tag_id;
    }

    public void setTag_id(Integer tag_id) {
        this.tag_id = tag_id;
    }
}