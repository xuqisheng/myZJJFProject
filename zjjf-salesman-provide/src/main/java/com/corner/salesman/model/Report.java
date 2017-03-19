package com.corner.salesman.model;

import java.util.ArrayList;
import java.util.List;

import com.corner.salesman.common.persistence.DataEntity;

public class Report extends DataEntity<Report> {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String reportId;
	
	private String tmplId;

    private String remark;

    private String picUrl;

    private String week;

    private String content;
    
    private String timePoint;
    
    private String postName;
    
    private String tmplName;//日志模板名称
    
    private String queryType;//该属性标识是否清空消息队列【0：正常刷新（清除）;1:发布刷新（不清除）
    
    private String participant;//查阅关系人
    
	List<DailyReply> replyList = null;//回复列表信息
	
    @SuppressWarnings("rawtypes")
	private List fieldList= null;//报告字段列表
    
    @SuppressWarnings("rawtypes")
	private List picList = new ArrayList();//图片列表
    
    public List getPicList() {
		return picList;
	}

	public void setPicList(List picList) {
		this.picList = picList;
	}

	public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId == null ? null : reportId.trim();
    }

    public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week == null ? null : week.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

	public List<DailyReply> getReplyList() {
		return replyList;
	}

	public void setReplyList(List<DailyReply> replyList) {
		this.replyList = replyList;
	}

	@SuppressWarnings("rawtypes")
	public List getFieldList() {
		return fieldList;
	}

	@SuppressWarnings("rawtypes")
	public void setFieldList(List fieldList) {
		this.fieldList = fieldList;
	}

	public String getTimePoint() {
		return timePoint;
	}

	public void setTimePoint(String timePoint) {
		this.timePoint = timePoint;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public String getTmplName() {
		return tmplName;
	}

	public void setTmplName(String tmplName) {
		this.tmplName = tmplName;
	}

	public String getTmplId() {
		return tmplId;
	}

	public void setTmplId(String tmplId) {
		this.tmplId = tmplId;
	}

	public String getParticipant() {
		return participant;
	}

	public void setParticipant(String participant) {
		this.participant = participant;
	}
    
}