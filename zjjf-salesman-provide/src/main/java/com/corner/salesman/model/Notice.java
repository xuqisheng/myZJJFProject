package com.corner.salesman.model;

import java.util.List;

import com.corner.salesman.common.persistence.DataEntity;

public class Notice extends DataEntity<Notice> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;//会议ID
	private String groupId;//所属组
	private String place;//会议场所
	private String meetingTime;//会议时间
	private String subject;//会员主题
	private String content;//会议内容
	private String assigns;//指派人（供app收取指定人所用，多个userid以逗号分割）
	private String userId;//用户ID（登陆人在用户ID）
	private String participant;//参与者
	private int total;//部门人数
    private String queryType;//该属性标识是否清空消息队列【0：正常刷新（清除）;1:发布刷新（不清除）】
    private String sendType;//发送类型(1：所有人；2：我的部门；3：运营中心所有主管；)
	private String noticeId;//报告ID
	private String picUrl;//图片路径
    @SuppressWarnings("rawtypes")
	private List picList = null;//图片列表
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getMeetingTime() {
		return meetingTime;
	}
	public void setMeetingTime(String meetingTime) {
		this.meetingTime = meetingTime;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAssigns() {
		return assigns;
	}
	public void setAssigns(String assigns) {
		this.assigns = assigns;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getParticipant() {
		return participant;
	}
	public void setParticipant(String participant) {
		this.participant = participant;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getQueryType() {
		return queryType;
	}
	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}
	public String getSendType() {
		return sendType;
	}
	public void setSendType(String sendType) {
		this.sendType = sendType;
	}
	@SuppressWarnings("rawtypes")
	public List getPicList() {
		return picList;
	}
	@SuppressWarnings("rawtypes")
	public void setPicList(List picList) {
		this.picList = picList;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

}
