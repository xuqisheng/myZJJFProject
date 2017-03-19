package com.corner.core.beans;

import java.util.Date;

public class ERPPhysicsStockLog {
    private String id;

    private String logicStockLogId;

    private String physicsStockId;

    private Byte operationType;

    private Integer operationNum;

    private Date operationTime;

    private Integer stockNum;

    private String remark;

    private Byte status;

    private Boolean isDelete;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getLogicStockLogId() {
        return logicStockLogId;
    }

    public void setLogicStockLogId(String logicStockLogId) {
        this.logicStockLogId = logicStockLogId == null ? null : logicStockLogId.trim();
    }

    public String getPhysicsStockId() {
        return physicsStockId;
    }

    public void setPhysicsStockId(String physicsStockId) {
        this.physicsStockId = physicsStockId == null ? null : physicsStockId.trim();
    }

    public Byte getOperationType() {
        return operationType;
    }

    public void setOperationType(Byte operationType) {
        this.operationType = operationType;
    }

    public Integer getOperationNum() {
        return operationNum;
    }

    public void setOperationNum(Integer operationNum) {
        this.operationNum = operationNum;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public Integer getStockNum() {
        return stockNum;
    }

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }
}