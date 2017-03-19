package com.corner.salesman.commons.push;

import java.util.HashMap;
import java.util.List;

public class UMengMsg {

	private String deviceToken; //设备token
	private String ticker;// 通知栏提示文字
	private String title;// 通知标题
	private String text;// 通知文字描述
	private String alert;
	private String builderId;
	private String icon;
	private String largeIcon;
	private String img;
	private String customField;
	private String playVibrate;
	private String displayType;// 消息弹出模式： message；消息提醒模式：notification
	private String url;// 与after_open 为go_url 组合使用
	//customizedcast.setAlias("alias", "alias_type");
	//customizedcast.setAlias("ff8080815307dd760153080881150008", "userId");
	private String alias; // 多个alias以逗号隔开  要保证唯一 否则很多设备都会出现信息
	private String alias_type; //别名字段类型
	private List<String> tags;//指定的组关键列表字段
	/*
	 * 点击"通知"的后续行为，默认为打开app。 值可以为: "go_app": 打开应用 "go_url": 跳转到URL
	 * "go_activity": 打开特定的activity "go_custom": 用户自定义内容。
	 */
	private String after_open;
	// 与after_open 为go_activity 组合使用
	private String activity;
	// 需要传递的参数
	private HashMap<String, String> params;
	/*
	 * 与after_open 为go_custom 组合使用 自定义打开类型 activity url chatMessage 设为"true"
	 * 为聊天消息，如果在前台则不显示通知栏
	 */
	private HashMap<String, String> custom;
	
	
	public String getCustomField() {
		return customField;
	}
	public void setCustomField(String customField) {
		this.customField = customField;
	}
	public String getDisplayType() {
		return displayType;
	}
	public void setDisplayType(String displayType) {
		this.displayType = displayType;
	}
	public String getDeviceToken() {
		return deviceToken;
	}
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getBuilderId() {
		return builderId;
	}
	public void setBuilderId(String builderId) {
		this.builderId = builderId;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getLargeIcon() {
		return largeIcon;
	}
	public void setLargeIcon(String largeIcon) {
		this.largeIcon = largeIcon;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getPlayVibrate() {
		return playVibrate;
	}
	public void setPlayVibrate(String playVibrate) {
		this.playVibrate = playVibrate;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAlert() {
		return alert;
	}
	public void setAlert(String alert) {
		this.alert = alert;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getAlias_type() {
		return alias_type;
	}
	public void setAlias_type(String alias_type) {
		this.alias_type = alias_type;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public String getAfter_open() {
		return after_open;
	}
	public void setAfter_open(String after_open) {
		this.after_open = after_open;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public HashMap<String, String> getParams() {
		return params;
	}
	public void setParams(HashMap<String, String> params) {
		this.params = params;
	}
	public HashMap<String, String> getCustom() {
		return custom;
	}
	public void setCustom(HashMap<String, String> custom) {
		this.custom = custom;
	}
	
	
}
