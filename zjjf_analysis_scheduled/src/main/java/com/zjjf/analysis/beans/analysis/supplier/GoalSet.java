package com.zjjf.analysis.beans.analysis.supplier;

import java.util.Date;

public class GoalSet {
    private Integer id;

    private Integer year;

    private Integer month;

    private String saleGoal;

    private String terminalGoal;

    private Integer createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getSaleGoal() {
        return saleGoal;
    }

    public void setSaleGoal(String saleGoal) {
        this.saleGoal = saleGoal == null ? null : saleGoal.trim();
    }

    public String getTerminalGoal() {
        return terminalGoal;
    }

    public void setTerminalGoal(String terminalGoal) {
        this.terminalGoal = terminalGoal == null ? null : terminalGoal.trim();
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}