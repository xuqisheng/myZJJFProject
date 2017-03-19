package com.corner.core.beans;

public class SpGroupAdvertisment {
    private String id;

    private Integer spGroupId;

    private Integer advertismentId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getSpGroupId() {
        return spGroupId;
    }

    public void setSpGroupId(Integer spGroupId) {
        this.spGroupId = spGroupId;
    }

    public Integer getAdvertismentId() {
        return advertismentId;
    }

    public void setAdvertismentId(Integer advertismentId) {
        this.advertismentId = advertismentId;
    }
}