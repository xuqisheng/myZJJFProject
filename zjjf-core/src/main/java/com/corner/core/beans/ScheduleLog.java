package com.corner.core.beans;

import java.util.Date;

public class ScheduleLog {
    private Integer id;

    private String logName;

    private Integer intId;

    private String stringId;

    private Date dateOne;

    private Date dateTwo;

    private Date dateThree;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName == null ? null : logName.trim();
    }

    public Integer getIntId() {
        return intId;
    }

    public void setIntId(Integer intId) {
        this.intId = intId;
    }

    public String getStringId() {
        return stringId;
    }

    public void setStringId(String stringId) {
        this.stringId = stringId == null ? null : stringId.trim();
    }

    public Date getDateOne() {
        return dateOne;
    }

    public void setDateOne(Date dateOne) {
        this.dateOne = dateOne;
    }

    public Date getDateTwo() {
        return dateTwo;
    }

    public void setDateTwo(Date dateTwo) {
        this.dateTwo = dateTwo;
    }

    public Date getDateThree() {
        return dateThree;
    }

    public void setDateThree(Date dateThree) {
        this.dateThree = dateThree;
    }
}