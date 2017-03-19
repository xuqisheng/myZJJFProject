package com.corner.salesman.model;

import com.corner.salesman.common.persistence.DataEntity;

public class UserNotice extends DataEntity<UserNotice> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String noticeId;//会议ID
	private String userId;//所属组
	private String groupId;//用户组

	public String getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

}
