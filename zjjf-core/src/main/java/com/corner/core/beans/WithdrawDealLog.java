package com.corner.core.beans;

import java.util.Date;

public class WithdrawDealLog {
    private Integer id;

    private Integer withDrawId;

    private Short status;

    private Date dealTime;

    private String dealerId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWithDrawId() {
        return withDrawId;
    }

    public void setWithDrawId(Integer withDrawId) {
        this.withDrawId = withDrawId;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
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
}