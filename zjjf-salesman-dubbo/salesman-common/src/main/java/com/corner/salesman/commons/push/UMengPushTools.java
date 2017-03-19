package com.corner.salesman.commons.push;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.corner.salesman.commons.push.android.AndroidBroadcast;
import com.corner.salesman.commons.push.android.AndroidCustomizedcast;
import com.corner.salesman.commons.push.android.AndroidFilecast;
import com.corner.salesman.commons.push.android.AndroidGroupcast;
import com.corner.salesman.commons.push.android.AndroidUnicast;
import com.corner.salesman.commons.push.ios.IOSBroadcast;
import com.corner.salesman.commons.push.ios.IOSCustomizedcast;
import com.corner.salesman.commons.push.ios.IOSFilecast;
import com.corner.salesman.commons.push.ios.IOSGroupcast;
import com.corner.salesman.commons.push.ios.IOSUnicast;

public class UMengPushTools {
	private static final String appkey_Android = "57c634cae0f55a8e3f003602";
	private static final String appMasterSecret_Android = "assehz7pfghzmgpwozig5hjaowxrxaem";
	private static final String appkey_IOS = "57b2857767e58ea70d000ad4";
	private static final String appMasterSecret_IOS = "dkocv5966sjd1c7v2kp9cygzr46dcnrk";

	private static UMengPushTools instance;

	public String getTimeStamp(){
		return Integer.toString((int) (System.currentTimeMillis() / 1000));
	}
	// 同步方法，以免单例多线程环境下出现异常
	public synchronized static UMengPushTools getInstance() {
		if (instance == null) {
			instance = new UMengPushTools();
		}
		return instance;
	}
	
	/**
	 * 拜访任务未完成提醒消息推送公共的方法
	 * @param alias
	 */
	public void sendVisitWarnCustomizedcast(String alias,String title) throws Exception {
		UMengMsg umengMsgVo = new UMengMsg();
		umengMsgVo.setAlias(alias);
		umengMsgVo.setAlias_type("userId");//发送阿街用户
		umengMsgVo.setTicker(title);
		umengMsgVo.setTitle("阿街提醒");
		umengMsgVo.setText(title);
		umengMsgVo.setAlert(title);
		umengMsgVo.setAfter_open("go_app");
		umengMsgVo.setDisplayType("notification");
		umengMsgVo.setCustomField("拜访计划未完成提醒");
		//Android的消息提醒模式推送
		sendAndroidCustomizedcast(umengMsgVo);
		//IOS的消息提醒模式推送
		sendIOSCustomizedcast(umengMsgVo);
		
		//消息弹出模式
		umengMsgVo.setDisplayType("message");
		sendAndroidCustomizedcast(umengMsgVo);
	}
	
	
	/**
	 * 拜访计划变动消息推送公共的方法
	 * @param alias
	 */
	public void sendSignCustomizedcast(String alias,String type) throws Exception {
		UMengMsg umengMsgVo = new UMengMsg();
		umengMsgVo.setAlias(alias);
		umengMsgVo.setAlias_type("userId");//发送阿街用户
		umengMsgVo.setTicker("九点钟剑出鞘，英雄，记得签到哦！");
		umengMsgVo.setTitle("签到提醒");
		if("1".equals(type)){
			umengMsgVo.setText("九点钟剑出鞘，英雄，记得签到哦！");
			umengMsgVo.setAlert("九点钟剑出鞘，英雄，记得签到哦！");
		}
		if("2".equals(type)){
			umengMsgVo.setText("忙碌一天您辛苦啦，英雄，记得签退哦！");
			umengMsgVo.setAlert("忙碌一天您辛苦啦，英雄，记得签退哦！");
		}
		umengMsgVo.setAfter_open("go_app");
		umengMsgVo.setDisplayType("notification");
		umengMsgVo.setCustomField("签到提醒");
		//Android的消息提醒模式推送
		sendAndroidCustomizedcast(umengMsgVo);
		//IOS的消息提醒模式推送
		sendIOSCustomizedcast(umengMsgVo);
		
		//消息弹出模式
		umengMsgVo.setDisplayType("message");
		sendAndroidCustomizedcast(umengMsgVo);
	}
	
	/**
	 * 签到拜访消息推送公共的方法
	 * @param alias
	 * @param bool(false:表示当天没有安排拜访路线；true：表示当天有拜访路线；)
	 */
	public void sendSignVisitCustomizedcast(String alias,String lineName,boolean bool) throws Exception {
		UMengMsg umengMsgVo = new UMengMsg();
		umengMsgVo.setAlias(alias);
		umengMsgVo.setAlias_type("userId");//发送阿街用户
		umengMsgVo.setTitle("拜访提醒");
		
		if(bool){
			umengMsgVo.setText("您今天拜访"+lineName+"，祝你旗开得胜！");
			umengMsgVo.setAlert("您今天拜访"+lineName+"，祝你旗开得胜！");
			umengMsgVo.setTicker("您今天拜访"+lineName+"，祝你旗开得胜！");
		}else{
			umengMsgVo.setText("糟糕，您今天的拜访计划路线尚未安排！");
			umengMsgVo.setAlert("糟糕，您今天的拜访计划路线尚未安排！");
			umengMsgVo.setTicker("糟糕，您今天的拜访计划路线尚未安排！");
		}

		umengMsgVo.setAfter_open("go_app");
		umengMsgVo.setDisplayType("notification");
		umengMsgVo.setCustomField("拜访提醒");
		//Android的消息提醒模式推送
		sendAndroidCustomizedcast(umengMsgVo);
		//IOS的消息提醒模式推送
		sendIOSCustomizedcast(umengMsgVo);
		
		//消息弹出模式
		umengMsgVo.setDisplayType("message");
		sendAndroidCustomizedcast(umengMsgVo);
	}
	
	/**
	 * 拜访计划变动消息推送公共的方法
	 * @param alias
	 */
	public void sendPlansCustomizedcast(String alias) throws Exception {
		UMengMsg umengMsgVo = new UMengMsg();
		umengMsgVo.setAlias(alias);
		umengMsgVo.setAlias_type("userId");//发送阿街用户
		umengMsgVo.setTicker("您的拜访计划有变动！");
		umengMsgVo.setTitle("拜访计划");
		umengMsgVo.setText("您的拜访计划有变动！");
		umengMsgVo.setAlert("您的拜访计划有变动！");
		umengMsgVo.setAfter_open("go_app");
		umengMsgVo.setDisplayType("notification");
		umengMsgVo.setCustomField("拜访计划变动");
		//Android的消息提醒模式推送
		sendAndroidCustomizedcast(umengMsgVo);
		//IOS的消息提醒模式推送
		sendIOSCustomizedcast(umengMsgVo);
		
		//消息弹出模式
		umengMsgVo.setDisplayType("message");
		sendAndroidCustomizedcast(umengMsgVo);
	}
	
	/**
	 * 日报消息推送公共的方法
	 * @param alias
	 */
	public void sendReportCustomizedcast(String alias,String ticker) throws Exception {
		UMengMsg umengMsgVo = new UMengMsg();
		umengMsgVo.setAlias(alias);
		umengMsgVo.setAlias_type("userId");//发送阿街用户
		umengMsgVo.setTicker(ticker);
		umengMsgVo.setTitle("阿街日志提醒");
		umengMsgVo.setText(ticker);
		umengMsgVo.setAlert(ticker);
		umengMsgVo.setAfter_open("go_app");
		umengMsgVo.setDisplayType("notification");
		umengMsgVo.setCustomField("阿街日志提醒");
		//Android的消息提醒模式推送
		sendAndroidCustomizedcast(umengMsgVo);
		//IOS的消息提醒模式推送
		sendIOSCustomizedcast(umengMsgVo);
		
		//消息弹出模式
		umengMsgVo.setDisplayType("message");
		sendAndroidCustomizedcast(umengMsgVo);
	}
	
	/**
	 * 公告消息推送公共的方法
	 * @param alias
	 */
	public void sendNoticeCustomizedcast(String alias) throws Exception {
		UMengMsg umengMsgVo = new UMengMsg();
		umengMsgVo.setAlias(alias);
		umengMsgVo.setAlias_type("userId");//发送阿街用户
		umengMsgVo.setTicker("与您有关的公告消息！");
		umengMsgVo.setTitle("阿街公告提醒");
		umengMsgVo.setText("与您有关的公告消息！");
		umengMsgVo.setAlert("与您有关的公告消息！");
		umengMsgVo.setAfter_open("go_app");
		umengMsgVo.setDisplayType("notification");
		umengMsgVo.setCustomField("阿街公告提醒");
		//消息提醒模式推送
		sendAndroidCustomizedcast(umengMsgVo);
		//IOS的消息提醒模式推送
		sendIOSCustomizedcast(umengMsgVo);
		
		//消息弹出模式
		umengMsgVo.setDisplayType("message");
		sendAndroidCustomizedcast(umengMsgVo);
	}
	
	/**
	 * 公告消息撤销推送公共的方法
	 * @param alias
	 */
	public void sendUndoNoticeCustomizedcast(String alias) throws Exception {
		UMengMsg umengMsgVo = new UMengMsg();
		umengMsgVo.setAlias(alias);
		umengMsgVo.setAlias_type("userId");//发送阿街用户
		umengMsgVo.setTicker("公告撤销提醒");
		umengMsgVo.setTitle("公告撤销提醒");
		umengMsgVo.setText("公告发送者撤销了一天与您相关的公告！");
		umengMsgVo.setAlert("公告发送者撤销了一天与您相关的公告！");
		umengMsgVo.setAfter_open("go_app");
		umengMsgVo.setDisplayType("notification");
		umengMsgVo.setCustomField("公告撤销提醒");
		//IOS的消息提醒模式推送
		sendIOSCustomizedcast(umengMsgVo);
		//消息弹出模式
		umengMsgVo.setDisplayType("message");
		sendAndroidCustomizedcast(umengMsgVo);
	}
	
	/**
	 * 评论消息推送公共的方法
	 * @param alias
	 */
	public void sendCommentCustomizedcast(String alias) throws Exception {
		UMengMsg umengMsgVo = new UMengMsg();
		umengMsgVo.setAlias(alias);
		umengMsgVo.setAlias_type("userId");//发送阿街用户
		umengMsgVo.setTicker("与您相关的日志评论！");
		umengMsgVo.setTitle("阿街评论提醒");
		umengMsgVo.setText("与您相关的日志评论！");
		umengMsgVo.setAlert("与您相关的日志评论！");
		umengMsgVo.setAfter_open("go_app");
		umengMsgVo.setDisplayType("notification");
		umengMsgVo.setCustomField("阿街评论提醒");
		//消息提醒模式推送
		sendAndroidCustomizedcast(umengMsgVo);
		//IOS的消息提醒模式推送
		sendIOSCustomizedcast(umengMsgVo);
		
		//消息弹出模式
		umengMsgVo.setDisplayType("message");
		sendAndroidCustomizedcast(umengMsgVo);
	}
	
	/**
	 * 提醒消息推送公共的方法
	 * @param alias
	 */
	public void sendAlertCustomizedcast(String alias) throws Exception {
		UMengMsg umengMsgVo = new UMengMsg();
		umengMsgVo.setAlias(alias);
		umengMsgVo.setAlias_type("userId");//发送阿街用户
		umengMsgVo.setTicker("您今天的日志尚未提交！");
		umengMsgVo.setTitle("阿街消息提醒");
		umengMsgVo.setText("您今天的日志尚未提交！");
		umengMsgVo.setAlert("您今天的日志尚未提交！");
		umengMsgVo.setAfter_open("go_app");
		umengMsgVo.setDisplayType("notification");
		umengMsgVo.setCustomField("阿街消息提醒");
		//消息提醒模式推送
		sendAndroidCustomizedcast(umengMsgVo);
		//IOS的消息提醒模式推送
		sendIOSCustomizedcast(umengMsgVo);
		
		//消息弹出模式
		umengMsgVo.setDisplayType("message");
		sendAndroidCustomizedcast(umengMsgVo);
	}
	
	/**
	 * 全部人发送 for android
	 * 
	 * @author winston at 2014年12月31日上午10:43:28
	 * @Email 330262107@qq.com
	 * @throws Exception
	 */
	public void sendAndroidBroadcast(UMengMsg umengMsg) throws Exception {
		AndroidBroadcast broadcast = new AndroidBroadcast(appkey_Android,appMasterSecret_Android);
		broadcast.setAppMasterSecret(appMasterSecret_Android);
		broadcast.setPredefinedKeyValue("appkey", appkey_Android);
		broadcast.setPredefinedKeyValue("timestamp", getTimeStamp());
		broadcast.setPredefinedKeyValue("ticker", umengMsg.getTicker());
		broadcast.setPredefinedKeyValue("title", umengMsg.getTitle());
		broadcast.setPredefinedKeyValue("text", umengMsg.getText());
		broadcast.setPredefinedKeyValue("after_open", umengMsg.getAfter_open());
		if (umengMsg.getActivity() != null) {
			broadcast.setPredefinedKeyValue("activity", umengMsg.getActivity());
		}
		if (umengMsg.getUrl() != null) {
			broadcast.setPredefinedKeyValue("url", umengMsg.getUrl());
		}
		broadcast.setPredefinedKeyValue("display_type", "notification");
		broadcast.setPredefinedKeyValue("production_mode", "true");
		HashMap<String, String> custom = umengMsg.getCustom();
		if (custom != null) {
			broadcast.setPredefinedKeyValue("custom", custom);
		}
		HashMap<String, String> params = umengMsg.getParams();
		if (params != null) {
			Set<String> keySet = params.keySet();
			for (String key : keySet) {
				String value = params.get(key);
				broadcast.setExtraField(key, value);
			}
		}
		//发送消息对象
		PushClient.getClientInstance().send(broadcast);
	}

	/**
	 * 单发或者群发 for android
	 * @author winston at 2014年12月31日下午1:50:01
	 * @Email 330262107@qq.com
	 * @throws Exception
	 */
	public void sendAndroidCustomizedcast(UMengMsg umengMsgVo) throws Exception {
		AndroidCustomizedcast customizedcast = new AndroidCustomizedcast(appkey_Android,appMasterSecret_Android);
		/*customizedcast.setAppMasterSecret(appMasterSecret_Android);
		customizedcast.setPredefinedKeyValue("appkey", appkey_Android);*/
		customizedcast.setPredefinedKeyValue("timestamp", getTimeStamp());
		customizedcast.setPredefinedKeyValue("alias", umengMsgVo.getAlias());
		customizedcast.setPredefinedKeyValue("alias_type", umengMsgVo.getAlias_type());
		customizedcast.setPredefinedKeyValue("ticker", umengMsgVo.getTicker());
		customizedcast.setPredefinedKeyValue("title", umengMsgVo.getTitle());
		customizedcast.setPredefinedKeyValue("text", umengMsgVo.getText());
		customizedcast.setPredefinedKeyValue("after_open", umengMsgVo.getAfter_open());
		if (umengMsgVo.getActivity() != null) {
			customizedcast.setPredefinedKeyValue("activity", umengMsgVo.getActivity());
		}
		if (umengMsgVo.getUrl() != null) {
			customizedcast.setPredefinedKeyValue("url", umengMsgVo.getUrl());
		}
		//customizedcast.setPredefinedKeyValue("largeIcon", "jifen");
		// customizedcast.setPredefinedKeyValue("icon", "jifen");

		// customizedcast.setPredefinedKeyValue("img",
		// "http://192.168.3.108:8080/MiBo-Web/app/framework/images/mibo_push_notification_default_large_icon.png");
		customizedcast.setPredefinedKeyValue("display_type", umengMsgVo.getDisplayType());
		customizedcast.setPredefinedKeyValue("builder_id", "0");
		customizedcast.setPredefinedKeyValue("production_mode", "true");
		//如果是消息弹出模式，custom不能为空
		String customField = umengMsgVo.getCustomField();
		if(StringUtils.isNotBlank(customField)){
			customizedcast.setPredefinedKeyValue("custom", customField);
		}
		
		HashMap<String, String> custom = umengMsgVo.getCustom();
		if (custom != null) {
			customizedcast.setPredefinedKeyValue("custom", custom);
		}
		HashMap<String, String> params = umengMsgVo.getParams();
		if (params != null) {
			Set<String> keySet = params.keySet();
			for (String key : keySet) {
				String value = params.get(key);
				customizedcast.setExtraField(key, value);
			}
		}
		//发送消息对象
		PushClient.getClientInstance().send(customizedcast);
	}

	/**
	 * 
	 * 单发或者群发 for IOS
	 * 
	 * @author winston at 2015年2月10日下午2:13:04
	 * @Email 330262107@qq.com
	 * @param umengMsgVo
	 * @throws Exception
	 */
	public void sendIOSCustomizedcast(UMengMsg umengMsgVo) throws Exception {
		IOSCustomizedcast customizedcast = new IOSCustomizedcast(appkey_IOS,appMasterSecret_IOS);
		/*customizedcast.setAppMasterSecret(appMasterSecret_IOS);
		customizedcast.setPredefinedKeyValue("appkey", appkey_IOS);*/
		customizedcast.setPredefinedKeyValue("timestamp",getTimeStamp());
		customizedcast.setPredefinedKeyValue("alias", umengMsgVo.getAlias());
		customizedcast.setPredefinedKeyValue("alias_type", umengMsgVo.getAlias_type());
		customizedcast.setPredefinedKeyValue("alert", umengMsgVo.getAlert());
		customizedcast.setPredefinedKeyValue("badge", 0);
		//customizedcast.setPredefinedKeyValue("play_sound", true);// 可选 收到通知是否发出声音,默认为"true"
		//customizedcast.setPredefinedKeyValue("sound", "chime");// 自定义通知声音:
		customizedcast.setPredefinedKeyValue("sound", "default");
		customizedcast.setPredefinedKeyValue("production_mode", "true");
		HashMap<String, String> params = umengMsgVo.getParams();
		if (params != null) {
			Set<String> keySet = params.keySet();
			for (String key : keySet) {
				String value = params.get(key);
				customizedcast.setCustomizedField(key, value);
			}
		}
		//发送消息对象
		PushClient.getClientInstance().send(customizedcast);
	}

	/**
	 * 全部人发送 for IOS
	 * 
	 * @author winston at 2015年2月10日下午2:18:12
	 * @Email 330262107@qq.com
	 * @param umengMsgVo
	 * @throws Exception
	 */
	public void sendIOSBroadcast(UMengMsg umengMsgVo) throws Exception {
		IOSBroadcast broadcast = new IOSBroadcast(appkey_IOS,appMasterSecret_IOS);
		broadcast.setPredefinedKeyValue("timestamp", getTimeStamp());
		broadcast.setPredefinedKeyValue("alert", umengMsgVo.getAlert());
		broadcast.setPredefinedKeyValue("badge", 0);
		//broadcast.setPredefinedKeyValue("sound", "chime");
		// TODO set 'production_mode' to 'true' if your app is under production
		// mode
		broadcast.setPredefinedKeyValue("production_mode", "true");
		// Set customized fields
		HashMap<String, String> params = umengMsgVo.getParams();
		if (params != null) {
			Set<String> keySet = params.keySet();
			for (String key : keySet) {
				String value = params.get(key);
				broadcast.setCustomizedField(key, value);
			}
		}
		//发送消息对象
		PushClient.getClientInstance().send(broadcast);
	}

	public void sendAndroidUnicast() throws Exception {
		AndroidUnicast unicast = new AndroidUnicast(appkey_Android,appMasterSecret_Android);
		unicast.setPredefinedKeyValue("timestamp", getTimeStamp());
		// TODO Set your device token
		unicast.setPredefinedKeyValue("device_tokens", "AkbGfkvzX2x-TcuU41szHjjHRGQWbyXY_qBjtjdqX12c");
		// unicast.setPredefinedKeyValue("device_tokens", "htc");
		unicast.setPredefinedKeyValue("ticker", "Android unicast ticker");

		unicast.setPredefinedKeyValue("largeIcon", "R.drawable.mibo_push_notification_default_large_icon");
		unicast.setPredefinedKeyValue("icon", "R.drawable.mibo_push_notification_default_small_icon");
		// unicast.setPredefinedKeyValue("img",
		// "http://192.168.3.108:8080/MiBo-Web/app/framework/images/mibo_push_notification_default_large_icon.png");
		unicast.setPredefinedKeyValue("title", "中文的title1111111");
		unicast.setPredefinedKeyValue("text", "Android unicast text");
		unicast.setPredefinedKeyValue("after_open", "go_app");
		unicast.setPredefinedKeyValue("display_type", "notification");
		unicast.setPredefinedKeyValue("builder_id", "0");
		// unicast.setPredefinedKeyValue("alias", "lenovo");

		// unicast.setPredefinedKeyValue("display_type", "message");
		// unicast.setPredefinedKeyValue("custom", "123232");
		// TODO Set 'production_mode' to 'false' if it's a test device.
		// For how to register a test device, please see the developer doc.
		unicast.setPredefinedKeyValue("production_mode", "false");
		// Set customized fields
		unicast.setExtraField("test", "helloworld");
		//发送消息对象
		PushClient.getClientInstance().send(unicast);
	}

	/**
	 * 可以按tag发送，比如tag为用户或者商铺
	 * 
	 * @author winston at 2015年2月12日下午2:01:16
	 * @Email 330262107@qq.com
	 * @param umengMsgVo
	 * @throws Exception
	 */
	public void sendAndroidGroupcast(UMengMsg umengMsgVo) throws Exception {
		AndroidGroupcast groupcast = new AndroidGroupcast(appkey_Android,appMasterSecret_Android);
		groupcast.setPredefinedKeyValue("timestamp", getTimeStamp());
		groupcast.setPredefinedKeyValue("ticker", umengMsgVo.getTicker());
		groupcast.setPredefinedKeyValue("title", umengMsgVo.getTitle());
		groupcast.setPredefinedKeyValue("text", umengMsgVo.getText());
		groupcast.setPredefinedKeyValue("after_open", umengMsgVo.getAfter_open());
		JSONObject filterJson = new JSONObject();
		JSONObject whereJson = new JSONObject();
		JSONArray tagArray = new JSONArray();
		for (String tag : umengMsgVo.getTags()) {
			JSONObject testTag = new JSONObject();
			testTag.put("tag", tag);
			tagArray.put(testTag);
		}
		whereJson.put("and", tagArray);
		filterJson.put("where", whereJson);
		System.out.println(filterJson.toString());
		groupcast.setPredefinedKeyValue("filter", filterJson);
		if (umengMsgVo.getActivity() != null) {
			groupcast.setPredefinedKeyValue("activity", umengMsgVo.getActivity());
		}
		if (umengMsgVo.getUrl() != null) {
			groupcast.setPredefinedKeyValue("url", umengMsgVo.getUrl());
		}
		groupcast.setPredefinedKeyValue("display_type", "notification");
		groupcast.setPredefinedKeyValue("production_mode", "true");
		HashMap<String, String> custom = umengMsgVo.getCustom();
		if (custom != null) {
			groupcast.setPredefinedKeyValue("custom", custom);
		}
		HashMap<String, String> params = umengMsgVo.getParams();
		if (params != null) {
			Set<String> keySet = params.keySet();
			for (String key : keySet) {
				String value = params.get(key);
				groupcast.setExtraField(key, value);
			}
		}
		//发送消息对象
		PushClient.getClientInstance().send(groupcast);
	}

	/**
	 * 可以按tag发送，比如tag为用户或者商铺
	 * 
	 * @author winston at 2015年2月12日下午2:01:16
	 * @Email 330262107@qq.com
	 * @param umengMsgVo
	 * @throws Exception
	 */

	public void sendIOSGroupcast(UMengMsg umengMsgVo) throws Exception {
		IOSGroupcast groupcast = new IOSGroupcast(appkey_IOS,appMasterSecret_IOS);
		groupcast.setPredefinedKeyValue("timestamp", getTimeStamp());
		/*
		 * TODO Construct the filter condition: "where": { "and": [
		 * {"tag":"iostest"} ] }
		 */
		JSONObject filterJson = new JSONObject();
		JSONObject whereJson = new JSONObject();
		JSONArray tagArray = new JSONArray();
		for (String tag : umengMsgVo.getTags()) {
			JSONObject testTag = new JSONObject();
			testTag.put("tag", tag);
			tagArray.put(testTag);
		}
		whereJson.put("and", tagArray);
		filterJson.put("where", whereJson);
		//System.out.println(filterJson.toString());
		// Set filter condition into rootJson
		groupcast.setPredefinedKeyValue("filter", filterJson);
		groupcast.setPredefinedKeyValue("alert", umengMsgVo.getAlert());
		groupcast.setPredefinedKeyValue("badge", 0);
		//groupcast.setPredefinedKeyValue("sound", "chime");
		// TODO set 'production_mode' to 'true' if your app is under production
		// mode
		groupcast.setPredefinedKeyValue("production_mode", "true");
		//发送消息对象
		PushClient.getClientInstance().send(groupcast);
	}

	public void sendAndroidFilecast() throws Exception {
		AndroidFilecast filecast = new AndroidFilecast(appkey_Android,appMasterSecret_Android);
		filecast.setPredefinedKeyValue("timestamp", getTimeStamp());
		// TODO upload your device tokens, and use '\n' to split them if there
		// are multiple tokens
		// device tokens
		String fileId = PushClient.getClientInstance().uploadContents(appkey_IOS,appMasterSecret_IOS,"aa"+"\n"+"bb");
		filecast.setFileId(fileId);
		//filecast.uploadContents("lenovo" + "\n" + "htc");
		filecast.setPredefinedKeyValue("ticker", "Android filecast ticker");
		filecast.setPredefinedKeyValue("title", "中文的title");
		filecast.setPredefinedKeyValue("text", "Android filecast text");
		filecast.setPredefinedKeyValue("after_open", "go_app");
		filecast.setPredefinedKeyValue("display_type", "notification");
		//发送消息对象
		PushClient.getClientInstance().send(filecast);
	}
	
	public void sendIOSUnicast() throws Exception {
		IOSUnicast unicast = new IOSUnicast(appkey_IOS,appMasterSecret_IOS);
		unicast.setPredefinedKeyValue("timestamp", getTimeStamp());
		// TODO Set your device token
		unicast.setPredefinedKeyValue("device_tokens", "xx");
		unicast.setPredefinedKeyValue("alert", "IOS 单播测试");
		unicast.setPredefinedKeyValue("badge", 0);
		//unicast.setPredefinedKeyValue("sound", "chime");
		// TODO set 'production_mode' to 'true' if your app is under production
		// mode
		unicast.setPredefinedKeyValue("production_mode", "false");
		// Set customized fields
		unicast.setCustomizedField("test", "helloworld");
		//发送消息对象
		PushClient.getClientInstance().send(unicast);
	}

	public void sendIOSFilecast() throws Exception {
		IOSFilecast filecast = new IOSFilecast(appkey_IOS,appMasterSecret_IOS);
		filecast.setPredefinedKeyValue("timestamp", getTimeStamp());
		// TODO upload your device tokens, and use '\n' to split them if there
		// are multiple tokens
		String fileId = PushClient.getClientInstance().uploadContents(appkey_IOS,appMasterSecret_IOS,"aa"+"\n"+"bb");
		filecast.setFileId( fileId);
		filecast.setPredefinedKeyValue("alert", "IOS 文件播测试");
		filecast.setPredefinedKeyValue("badge", 0);
		//filecast.setPredefinedKeyValue("sound", "chime");
		// TODO set 'production_mode' to 'true' if your app is under production
		// mode
		filecast.setPredefinedKeyValue("production_mode", "false");
		//发送消息对象
		PushClient.getClientInstance().send(filecast);
	}

	public static void main(String[] args) {
		// TODO set your appkey and master secret here
		UMengPushTools demo = UMengPushTools.getInstance();
		try {
			// demo.sendAndroidUnicast();
			// TODO these methods are all available, just fill in some fields
			// and do the test
			 //demo.sendAndroidBroadcast();

			// demo.sendAndroidGroupcast();
			// for (i = 0; i < 150; i++) {
			//
			UMengMsg umengMsgVo = new UMengMsg();

			umengMsgVo.setAlias("ff8080815307dd760153080881150008");
			umengMsgVo.setAlias_type("userId");//给商铺发送
			umengMsgVo.setTicker("好消息来了corner");
			umengMsgVo.setTitle("领积分777111");
			umengMsgVo.setText("亲，您今天可以领3个积分!积分可以兑换现金券");
			umengMsgVo.setAlert("亲，您今天可以领3个积分!积分可以兑换现金券");//for IOS
			umengMsgVo.setAfter_open("go_url");
			umengMsgVo.setUrl("https://www.baidu.com");
			umengMsgVo.setDisplayType("notification");
			/*HashMap<String, String> params = new HashMap<String, String>();
			params.put("url", "https://www.baidu.com");
			params.put("chatMessage", "false");
			// umengMsgVo.setParams(params);
			umengMsgVo.setCustom(params);*/
			List<String> tags = new ArrayList<String>();
			tags.add("deptId");
			umengMsgVo.setTags(tags);
			demo.sendAndroidCustomizedcast(umengMsgVo);
			//demo.sendIOSCustomizedcast(umengMsgVo);
			//demo.sendAndroidGroupcast(umengMsgVo);

			// }
			// demo.sendAndroidFilecast();

			// demo.sendIOSBroadcast(); demo.sendIOSUnicast();
			// demo.sendIOSGroupcast(); demo.sendIOSCustomizedcast();
			// demo.sendIOSFilecast();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
