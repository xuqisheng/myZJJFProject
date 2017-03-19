package com.corner.core.beans;

public class SpGroupAd2BordMapKey {
    private Integer adBordId;

    private String spGroupAdId;

    public Integer getAdBordId() {
        return adBordId;
    }

    public void setAdBordId(Integer adBordId) {
        this.adBordId = adBordId;
    }

    public String getSpGroupAdId() {
        return spGroupAdId;
    }

    public void setSpGroupAdId(String spGroupAdId) {
        this.spGroupAdId = spGroupAdId == null ? null : spGroupAdId.trim();
    }
}