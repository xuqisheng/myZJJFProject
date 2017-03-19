package com.corner.rpc.shop.model;
/**
 * 优惠劵模板
 */
import java.math.BigDecimal;
import java.util.Date;

public class SpVoucherTemp {
    private Integer id;

    private Integer activeId;

    private String ruleName;

    private String ruleRemark;

    private String ruleMsg;

    private Byte billType;

    private BigDecimal useMoney;

    private Byte useDay;

    private BigDecimal startPrice;

    private Byte useItemFlag;

    private String mgItems;

    private Byte payType;

    private String payStr;

    private Byte creatStatus;

    private Byte creatPub;

    private Integer ordId;

    private Date addTime;

    private Date updateTime;

    private Byte status;

    private Boolean isDelete;

    private String remark;

    private String useItemIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getActiveId() {
        return activeId;
    }

    public void setActiveId(Integer activeId) {
        this.activeId = activeId;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName == null ? null : ruleName.trim();
    }

    public String getRuleRemark() {
        return ruleRemark;
    }

    public void setRuleRemark(String ruleRemark) {
        this.ruleRemark = ruleRemark == null ? null : ruleRemark.trim();
    }

    public String getRuleMsg() {
        return ruleMsg;
    }

    public void setRuleMsg(String ruleMsg) {
        this.ruleMsg = ruleMsg == null ? null : ruleMsg.trim();
    }

    public Byte getBillType() {
        return billType;
    }

    public void setBillType(Byte billType) {
        this.billType = billType;
    }

    public BigDecimal getUseMoney() {
        return useMoney;
    }

    public void setUseMoney(BigDecimal useMoney) {
        this.useMoney = useMoney;
    }

    public Byte getUseDay() {
        return useDay;
    }

    public void setUseDay(Byte useDay) {
        this.useDay = useDay;
    }

    public BigDecimal getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(BigDecimal startPrice) {
        this.startPrice = startPrice;
    }

    public Byte getUseItemFlag() {
        return useItemFlag;
    }

    public void setUseItemFlag(Byte useItemFlag) {
        this.useItemFlag = useItemFlag;
    }

    public String getMgItems() {
        return mgItems;
    }

    public void setMgItems(String mgItems) {
        this.mgItems = mgItems == null ? null : mgItems.trim();
    }

    public Byte getPayType() {
        return payType;
    }

    public void setPayType(Byte payType) {
        this.payType = payType;
    }

    public String getPayStr() {
        return payStr;
    }

    public void setPayStr(String payStr) {
        this.payStr = payStr == null ? null : payStr.trim();
    }

    public Byte getCreatStatus() {
        return creatStatus;
    }

    public void setCreatStatus(Byte creatStatus) {
        this.creatStatus = creatStatus;
    }

    public Byte getCreatPub() {
        return creatPub;
    }

    public void setCreatPub(Byte creatPub) {
        this.creatPub = creatPub;
    }

    public Integer getOrdId() {
        return ordId;
    }

    public void setOrdId(Integer ordId) {
        this.ordId = ordId;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getUseItemIds() {
        return useItemIds;
    }

    public void setUseItemIds(String useItemIds) {
        this.useItemIds = useItemIds == null ? null : useItemIds.trim();
    }
}