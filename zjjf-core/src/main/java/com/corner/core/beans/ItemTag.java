package com.corner.core.beans;

import java.util.Date;

public class ItemTag {
    private Integer tag_id;

    private String tag_name;

    private String tag_img;

    private Short tag_ordId;

    private Date tag_add_time;

    private Byte tag_statue;

    private Boolean tag_isDelete;

    public Integer getTag_id() {
        return tag_id;
    }

    public void setTag_id(Integer tag_id) {
        this.tag_id = tag_id;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name == null ? null : tag_name.trim();
    }

    public String getTag_img() {
        return tag_img;
    }

    public void setTag_img(String tag_img) {
        this.tag_img = tag_img == null ? null : tag_img.trim();
    }

    public Short getTag_ordId() {
        return tag_ordId;
    }

    public void setTag_ordId(Short tag_ordId) {
        this.tag_ordId = tag_ordId;
    }

    public Date getTag_add_time() {
        return tag_add_time;
    }

    public void setTag_add_time(Date tag_add_time) {
        this.tag_add_time = tag_add_time;
    }

    public Byte getTag_statue() {
        return tag_statue;
    }

    public void setTag_statue(Byte tag_statue) {
        this.tag_statue = tag_statue;
    }

    public Boolean getTag_isDelete() {
        return tag_isDelete;
    }

    public void setTag_isDelete(Boolean tag_isDelete) {
        this.tag_isDelete = tag_isDelete;
    }
}