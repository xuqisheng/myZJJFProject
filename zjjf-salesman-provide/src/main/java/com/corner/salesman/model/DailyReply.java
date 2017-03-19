package com.corner.salesman.model;

import com.corner.salesman.common.persistence.BaseEntity;

public class DailyReply extends BaseEntity<DailyReply> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer type;

    private String reportId;

    private String commentId;//日报评论Id
    private String comment;

    private String postBy;//发布者
    
    private String replyBy;//回复者(创建者是回复者)

    private String createBy;

    private String createTime;
    
    private String timePoint;
    
    private String commentTotal;

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId == null ? null : reportId.trim();
    }

	public String getCommentTotal() {
		return commentTotal;
	}

	public void setCommentTotal(String commentTotal) {
		this.commentTotal = commentTotal;
	}

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

	public String getPostBy() {
		return postBy;
	}

	public void setPostBy(String postBy) {
		this.postBy = postBy;
	}

	public String getReplyBy() {
		return replyBy;
	}

	public void setReplyBy(String replyBy) {
		this.replyBy = replyBy;
	}

	public String getTimePoint() {
		return timePoint;
	}

	public void setTimePoint(String timePoint) {
		this.timePoint = timePoint;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}