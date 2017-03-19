/**
 * @author winston at 2014年12月31日下午2:00:05
 * @Email 330262107@qq.com
 * 
 */
package com.corner.core.utils.message.tools;

import java.util.HashMap;
import java.util.List;

/**
 * @author winston at 2014年12月31日下午2:00:05
 * @Email 330262107@qq.com
 */

public class UMengMessageVo {
	// 参考链接 http://dev.umeng.com/message/android/api-server-doc/api-doc-v20
	/**ios android 公用的参数start**/
	// 多个alias以逗号隔开  要保证唯一 否则很多设备都会出现信息
	private String alias;
	//corner
	private String alias_type;
    //Groupcast
	private List<String> tags;
	
	/**ios android 公用的参数start**/
	
	/**android 参数start**/
	// 通知栏提示文字
	private String ticker;
	// 通知标题
	private String title;
	// 通知文字描述
	private String text;
	/*
	 * 点击"通知"的后续行为，默认为打开app。 值可以为: "go_app": 打开应用 "go_url": 跳转到URL
	 * "go_activity": 打开特定的activity "go_custom": 用户自定义内容。
	 */
	private String after_open;
	// 与after_open 为go_activity 组合使用
	private String activity;
	// 与after_open 为go_url 组合使用
	private String url;
	// 需要传递的参数
	private HashMap<String, String> params;
	/*
	 * 与after_open 为go_custom 组合使用 自定义打开类型 activity url chatMessage 设为"true"
	 * 为聊天消息，如果在前台则不显示通知栏
	 */
	private HashMap<String, String> custom;
	/**android 参数end**/
	/**ios 参数start**/
	private String alert;
	/**ios 参数end**/
	

	/**
	 * @return the activity
	 */
	public String getActivity() {
		return activity;
	}

	/**
	 * @return the alert
	 */
	public String getAlert() {
		return alert;
	}

	/**
	 * @param alert the alert to set
	 */
	public void setAlert(String alert) {
		this.alert = alert;
	}

	/**
	 * @param activity
	 *            the activity to set
	 */
	public void setActivity(String activity) {
		this.activity = activity;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * @param alias
	 *            the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * @return the alias_type
	 */
	public String getAlias_type() {
		return alias_type;
	}

	/**
	 * @param alias_type
	 *            the alias_type to set
	 */
	public void setAlias_type(String alias_type) {
		this.alias_type = alias_type;
	}

	/**
	 * @return the ticker
	 */
	public String getTicker() {
		return ticker;
	}

	/**
	 * @param ticker
	 *            the ticker to set
	 */
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the after_open
	 */
	public String getAfter_open() {
		return after_open;
	}

	/**
	 * @param after_open
	 *            the after_open to set
	 */
	public void setAfter_open(String after_open) {
		this.after_open = after_open;
	}

	/**
	 * @return the params
	 */
	public HashMap<String, String> getParams() {
		return params;
	}

	/**
	 * @param params
	 *            the params to set
	 */
	public void setParams(HashMap<String, String> params) {
		this.params = params;
	}

	/**
	 * @return the custom
	 */
	public HashMap<String, String> getCustom() {
		return custom;
	}

	/**
	 * @param custom
	 *            the custom to set
	 */
	public void setCustom(HashMap<String, String> custom) {
		this.custom = custom;
	}

	/**
	 * @return the tags
	 */
	public List<String> getTags() {
		return tags;
	}

	/**
	 * @param tags the tags to set
	 */
	public void setTags(List<String> tags) {
		this.tags = tags;
	}

}
