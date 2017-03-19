package com.corner.core.beans;

import java.util.Date;

public class SpWithdrawDealLog {
    private Long id;

    private Long withDrawId;

    private Byte objectStatus;

    private String objectDate;

    private Date dealTime;

    private String dealerId;

    private String dealerName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWithDrawId() {
        return withDrawId;
    }

    public void setWithDrawId(Long withDrawId) {
        this.withDrawId = withDrawId;
    }

    public Byte getObjectStatus() {
        return objectStatus;
    }

    public void setObjectStatus(Byte objectStatus) {
        this.objectStatus = objectStatus;
    }

    public String getObjectDate() {
        return objectDate;
    }

    public void setObjectDate(String objectDate) {
        this.objectDate = objectDate == null ? null : objectDate.trim();
    }

    public Date getDealTime() {
        return dealTime;
    }

    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }

    public String getDealerId() {
        return dealerId;
    }

    public void setDealerId(String dealerId) {
        this.dealerId = dealerId == null ? null : dealerId.trim();
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName == null ? null : dealerName.trim();
    }
}