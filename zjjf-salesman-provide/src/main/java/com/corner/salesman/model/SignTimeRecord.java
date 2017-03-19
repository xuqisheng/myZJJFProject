package com.corner.salesman.model;

import com.corner.salesman.common.persistence.BaseEntity;


public class SignTimeRecord  extends BaseEntity<SignInfo> {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

    private String startTime;

    private String outWorkStart;

    private String outWorkEnd;

    private String endTime;
    
    private String type;
    
    private String typeName;
    
	private String week;//星期

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getOutWorkStart() {
		return outWorkStart;
	}

	public void setOutWorkStart(String outWorkStart) {
		this.outWorkStart = outWorkStart;
	}

	public String getOutWorkEnd() {
		return outWorkEnd;
	}

	public void setOutWorkEnd(String outWorkEnd) {
		this.outWorkEnd = outWorkEnd;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}