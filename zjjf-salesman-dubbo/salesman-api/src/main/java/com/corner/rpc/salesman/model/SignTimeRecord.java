package com.corner.rpc.salesman.model;

import java.io.Serializable;
import java.util.Date;

public class SignTimeRecord  implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

    private Date startTime;

    private Date outWorkStart;

    private Date outWorkEnd;

    private Date endTime;

    private String userId;

    private String week;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getOutWorkStart() {
        return outWorkStart;
    }

    public void setOutWorkStart(Date outWorkStart) {
        this.outWorkStart = outWorkStart;
    }

    public Date getOutWorkEnd() {
        return outWorkEnd;
    }

    public void setOutWorkEnd(Date outWorkEnd) {
        this.outWorkEnd = outWorkEnd;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week == null ? null : week.trim();
    }
}