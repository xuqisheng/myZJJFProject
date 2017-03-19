package com.corner.core.beans;

import java.math.BigDecimal;

public class PaySuccessRecord {
    private Long id;

    private String outTradeNO;

    private String otherTradeNO;

    private BigDecimal totelFee;

    private String notifyId;

    private String notifyTime;

    private String subject;

    private String tradeState;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOutTradeNO() {
        return outTradeNO;
    }

    public void setOutTradeNO(String outTradeNO) {
        this.outTradeNO = outTradeNO == null ? null : outTradeNO.trim();
    }

    public String getOtherTradeNO() {
        return otherTradeNO;
    }

    public void setOtherTradeNO(String otherTradeNO) {
        this.otherTradeNO = otherTradeNO == null ? null : otherTradeNO.trim();
    }

    public BigDecimal getTotelFee() {
        return totelFee;
    }

    public void setTotelFee(BigDecimal totelFee) {
        this.totelFee = totelFee;
    }

    public String getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(String notifyId) {
        this.notifyId = notifyId == null ? null : notifyId.trim();
    }

    public String getNotifyTime() {
        return notifyTime;
    }

    public void setNotifyTime(String notifyTime) {
        this.notifyTime = notifyTime == null ? null : notifyTime.trim();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public String getTradeState() {
        return tradeState;
    }

    public void setTradeState(String tradeState) {
        this.tradeState = tradeState == null ? null : tradeState.trim();
    }
}