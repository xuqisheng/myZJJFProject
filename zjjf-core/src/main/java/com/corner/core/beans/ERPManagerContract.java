package com.corner.core.beans;

import java.math.BigDecimal;
import java.util.Date;

public class ERPManagerContract {
    private String id;

    private String erpManagerId;

    private String code;

    private Date startTime;

    private Date endTime;

    private Integer dayNum;

    private BigDecimal usePlatFormFee;

    private BigDecimal useTecFee;

    private BigDecimal generalizeFee;

    private BigDecimal logisticsFee;

    private BigDecimal margin;

    private BigDecimal penalty;

    private String remark;

    private Date addTime;

    private Date updateTime;

    private Boolean isDelete;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getErpManagerId() {
        return erpManagerId;
    }

    public void setErpManagerId(String erpManagerId) {
        this.erpManagerId = erpManagerId == null ? null : erpManagerId.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getDayNum() {
        return dayNum;
    }

    public void setDayNum(Integer dayNum) {
        this.dayNum = dayNum;
    }

    public BigDecimal getUsePlatFormFee() {
        return usePlatFormFee;
    }

    public void setUsePlatFormFee(BigDecimal usePlatFormFee) {
        this.usePlatFormFee = usePlatFormFee;
    }

    public BigDecimal getUseTecFee() {
        return useTecFee;
    }

    public void setUseTecFee(BigDecimal useTecFee) {
        this.useTecFee = useTecFee;
    }

    public BigDecimal getGeneralizeFee() {
        return generalizeFee;
    }

    public void setGeneralizeFee(BigDecimal generalizeFee) {
        this.generalizeFee = generalizeFee;
    }

    public BigDecimal getLogisticsFee() {
        return logisticsFee;
    }

    public void setLogisticsFee(BigDecimal logisticsFee) {
        this.logisticsFee = logisticsFee;
    }

    public BigDecimal getMargin() {
        return margin;
    }

    public void setMargin(BigDecimal margin) {
        this.margin = margin;
    }

    public BigDecimal getPenalty() {
        return penalty;
    }

    public void setPenalty(BigDecimal penalty) {
        this.penalty = penalty;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }
}