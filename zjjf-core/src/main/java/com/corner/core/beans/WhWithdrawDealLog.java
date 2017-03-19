package com.corner.core.beans;

import java.util.Date;

public class WhWithdrawDealLog {
    private Long id;

    private Long whWithDrawId;

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

    public Long getWhWithDrawId() {
        return whWithDrawId;
    }

    public void setWhWithDrawId(Long whWithDrawId) {
        this.whWithDrawId = whWithDrawId;
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