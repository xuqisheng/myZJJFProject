package com.corner.core.utils.message.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import com.corner.core.utils.message.umengpush.android.AndroidBroadcast;
import com.corner.core.utils.message.umengpush.android.AndroidCustomizedcast;
import com.corner.core.utils.message.umengpush.android.AndroidFilecast;
import com.corner.core.utils.message.umengpush.android.AndroidGroupcast;
import com.corner.core.utils.message.umengpush.android.AndroidUnicast;
import com.corner.core.utils.message.umengpush.ios.IOSBroadcast;
import com.corner.core.utils.message.umengpush.ios.IOSCustomizedcast;
import com.corner.core.utils.message.umengpush.ios.IOSFilecast;
import com.corner.core.utils.message.umengpush.ios.IOSGroupcast;
import com.corner.core.utils.message.umengpush.ios.IOSUnicast;

public class UMengPushTools {
	private String appkey_Android = "537e10f556240b9ba3028436";
	private String appMasterSecret_Android = "muulugwdqxgovdg9dohddtc8vrpbyaqz";
	private String timestamp = Integer.toString((int) (System.currentTimeMillis() / 1000));
	private String appkey_IOS = "53957bd256240b1614020ab6";
	private String appMasterSecret_IOS = "0kzl0diuudwl2uygj2w2l32y73jn4vhp";

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
	 * 全部人发送 for android
	 * 
	 * @author winston at 2014年12月31日上午10:43:28
	 * @Email 330262107@qq.com
	 * @throws Exception
	 */
	public void sendAndroidBroadcast(UMengMessageVo uMengMessageVo) throws Exception {
		AndroidBroadcast broadcast = new AndroidBroadcast();
		broadcast.setAppMasterSecret(appMasterSecret_Android);
		broadcast.setPredefinedKeyValue("appkey", this.appkey_Android);
		broadcast.setPredefinedKeyValue("timestamp", getTimeStamp());
		broadcast.setPredefinedKeyValue("ticker", uMengMessageVo.getTicker());
		broadcast.setPredefinedKeyValue("title", uMengMessageVo.getTitle());
		broadcast.setPredefinedKeyValue("text", uMengMessageVo.getText());
		broadcast.setPredefinedKeyValue("after_open", uMengMessageVo.getAfter_open());
		if (uMengMessageVo.getActivity() != null) {
			broadcast.setPredefinedKeyValue("activity", uMengMessageVo.getActivity());
		}
		if (uMengMessageVo.getUrl() != null) {
			broadcast.setPredefinedKeyValue("url", uMengMessageVo.getUrl());
		}
		broadcast.setPredefinedKeyValue("display_type", "notification");
		broadcast.setPredefinedKeyValue("production_mode", "true");
		HashMap<String, String> custom = uMengMessageVo.getCustom();
		if (custom != null) {
			broadcast.setPredefinedKeyValue("custom", custom);
		}
		HashMap<String, String> params = uMengMessageVo.getParams();
		if (params != null) {
			Set<String> keySet = params.keySet();
			for (String key : keySet) {
				String value = params.get(key);
				broadcast.setExtraField(key, value);
			}
		}
		broadcast.send();
	}

	/**
	 * 单发或者群发 for android
	 * 
	 * @author winston at 2014年12月31日下午1:50:01
	 * @Email 330262107@qq.com
	 * @throws Exception
	 */
	public void sendAndroidCustomizedcast(UMengMessageVo uMengMessageVo) throws Exception {
		AndroidCustomizedcast customizedcast = new AndroidCustomizedcast();
		customizedcast.setAppMasterSecret(appMasterSecret_Android);
		customizedcast.setPredefinedKeyValue("appkey", this.appkey_Android);
		customizedcast.setPredefinedKeyValue("timestamp", getTimeStamp());
		// customizedcast.setPredefinedKeyValue("alias", "lenovo,htc");
		customizedcast.setPredefinedKeyValue("alias", uMengMessageVo.getAlias());
		customizedcast.setPredefinedKeyValue("alias_type", uMengMessageVo.getAlias_type());
		customizedcast.setPredefinedKeyValue("ticker", uMengMessageVo.getTicker());
		customizedcast.setPredefinedKeyValue("title", uMengMessageVo.getTitle());
		customizedcast.setPredefinedKeyValue("text", uMengMessageVo.getText());
		customizedcast.setPredefinedKeyValue("after_open", uMengMessageVo.getAfter_open());
		if (uMengMessageVo.getActivity() != null) {
			customizedcast.setPredefinedKeyValue("activity", uMengMessageVo.getActivity());
		}
		if (uMengMessageVo.getUrl() != null) {
			customizedcast.setPredefinedKeyValue("url", uMengMessageVo.getUrl());
		}
		customizedcast.setPredefinedKeyValue("largeIcon", "jifen");
		// customizedcast.setPredefinedKeyValue("icon", "jifen");

		// customizedcast.setPredefinedKeyValue("img",
		// "http://192.168.3.108:8080/MiBo-Web/app/framework/images/mibo_push_notification_default_large_icon.png");
		customizedcast.setPredefinedKeyValue("display_type", "notification");
		customizedcast.setPredefinedKeyValue("builder_id", "0");
		customizedcast.setPredefinedKeyValue("production_mode", "true");
		HashMap<String, String> custom = uMengMessageVo.getCustom();
		if (custom != null) {
			customizedcast.setPredefinedKeyValue("custom", custom);
		}
		HashMap<String, String> params = uMengMessageVo.getParams();
		if (params != null) {
			Set<String> keySet = params.keySet();
			for (String key : keySet) {
				String value = params.get(key);
				customizedcast.setExtraField(key, value);
			}
		}
		customizedcast.send();
	}

	/**
	 * 
	 * 单发或者群发 for IOS
	 * 
	 * @author winston at 2015年2月10日下午2:13:04
	 * @Email 330262107@qq.com
	 * @param uMengMessageVo
	 * @throws Exception
	 */
	public void sendIOSCustomizedcast(UMengMessageVo uMengMessageVo) throws Exception {
		IOSCustomizedcast customizedcast = new IOSCustomizedcast();
		customizedcast.setAppMasterSecret(appMasterSecret_IOS);
		customizedcast.setPredefinedKeyValue("appkey", this.appkey_IOS);
		customizedcast.setPredefinedKeyValue("timestamp",getTimeStamp());
		customizedcast.setPredefinedKeyValue("alias", uMengMessageVo.getAlias());
		customizedcast.setPredefinedKeyValue("alias_type", uMengMessageVo.getAlias_type());
		customizedcast.setPredefinedKeyValue("alert", uMengMessageVo.getAlert());
		customizedcast.setPredefinedKeyValue("badge", 0);
		customizedcast.setPredefinedKeyValue("sound", "chime");
		customizedcast.setPredefinedKeyValue("production_mode", "true");
		HashMap<String, String> params = uMengMessageVo.getParams();
		if (params != null) {
			Set<String> keySet = params.keySet();
			for (String key : keySet) {
				String value = params.get(key);
				customizedcast.setCustomizedField(key, value);
			}
		}
		customizedcast.send();
	}

	/**
	 * 全部人发送 for IOS
	 * 
	 * @author winston at 2015年2月10日下午2:18:12
	 * @Email 330262107@qq.com
	 * @param uMengMessageVo
	 * @throws Exception
	 */
	public void sendIOSBroadcast(UMengMessageVo uMengMessageVo) throws Exception {
		IOSBroadcast broadcast = new IOSBroadcast();
		broadcast.setAppMasterSecret(appMasterSecret_IOS);
		broadcast.setPredefinedKeyValue("appkey", this.appkey_IOS);
		broadcast.setPredefinedKeyValue("timestamp", getTimeStamp());

		broadcast.setPredefinedKeyValue("alert", uMengMessageVo.getAlert());
		broadcast.setPredefinedKeyValue("badge", 0);
		broadcast.setPredefinedKeyValue("sound", "chime");
		// TODO set 'production_mode' to 'true' if your app is under production
		// mode
		broadcast.setPredefinedKeyValue("production_mode", "true");
		// Set customized fields
		HashMap<String, String> params = uMengMessageVo.getParams();
		if (params != null) {
			Set<String> keySet = params.keySet();
			for (String key : keySet) {
				String value = params.get(key);
				broadcast.setCustomizedField(key, value);
			}
		}
		broadcast.send();
	}

	public void sendAndroidUnicast() throws Exception {
		AndroidUnicast unicast = new AndroidUnicast();
		unicast.setAppMasterSecret(appMasterSecret_Android);
		unicast.setPredefinedKeyValue("appkey", this.appkey_Android);
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
		unicast.send();
	}

	/**
	 * 可以按tag发送，比如tag为用户或者商铺
	 * 
	 * @author winston at 2015年2月12日下午2:01:16
	 * @Email 330262107@qq.com
	 * @param uMengMessageVo
	 * @throws Exception
	 */
	public void sendAndroidGroupcast(UMengMessageVo uMengMessageVo) throws Exception {
		AndroidGroupcast groupcast = new AndroidGroupcast();
		groupcast.setAppMasterSecret(appMasterSecret_Android);
		groupcast.setPredefinedKeyValue("appkey", this.appkey_Android);
		groupcast.setPredefinedKeyValue("timestamp", getTimeStamp());
		groupcast.setPredefinedKeyValue("ticker", uMengMessageVo.getTicker());
		groupcast.setPredefinedKeyValue("title", uMengMessageVo.getTitle());
		groupcast.setPredefinedKeyValue("text", uMengMessageVo.getText());
		groupcast.setPredefinedKeyValue("after_open", uMengMessageVo.getAfter_open());
		JSONObject filterJson = new JSONObject();
		JSONObject whereJson = new JSONObject();
		JSONArray tagArray = new JSONArray();
		for (String tag : uMengMessageVo.getTags()) {
			JSONObject testTag = new JSONObject();
			testTag.put("tag", tag);
			tagArray.put(testTag);
		}
		whereJson.put("and", tagArray);
		filterJson.put("where", whereJson);
		System.out.println(filterJson.toString());
		groupcast.setPredefinedKeyValue("filter", filterJson);
		if (uMengMessageVo.getActivity() != null) {
			groupcast.setPredefinedKeyValue("activity", uMengMessageVo.getActivity());
		}
		if (uMengMessageVo.getUrl() != null) {
			groupcast.setPredefinedKeyValue("url", uMengMessageVo.getUrl());
		}
		groupcast.setPredefinedKeyValue("display_type", "notification");
		groupcast.setPredefinedKeyValue("production_mode", "true");
		HashMap<String, String> custom = uMengMessageVo.getCustom();
		if (custom != null) {
			groupcast.setPredefinedKeyValue("custom", custom);
		}
		HashMap<String, String> params = uMengMessageVo.getParams();
		if (params != null) {
			Set<String> keySet = params.keySet();
			for (String key : keySet) {
				String value = params.get(key);
				groupcast.setExtraField(key, value);
			}
		}
		groupcast.send();
	}

	/**
	 * 可以按tag发送，比如tag为用户或者商铺
	 * 
	 * @author winston at 2015年2月12日下午2:01:16
	 * @Email 330262107@qq.com
	 * @param uMengMessageVo
	 * @throws Exception
	 */

	public void sendIOSGroupcast(UMengMessageVo uMengMessageVo) throws Exception {
		IOSGroupcast groupcast = new IOSGroupcast();
		groupcast.setAppMasterSecret(appMasterSecret_IOS);
		groupcast.setPredefinedKeyValue("appkey", this.appkey_IOS);
		groupcast.setPredefinedKeyValue("timestamp", getTimeStamp());
		/*
		 * TODO Construct the filter condition: "where": { "and": [
		 * {"tag":"iostest"} ] }
		 */
		JSONObject filterJson = new JSONObject();
		JSONObject whereJson = new JSONObject();
		JSONArray tagArray = new JSONArray();
		for (String tag : uMengMessageVo.getTags()) {
			JSONObject testTag = new JSONObject();
			testTag.put("tag", tag);
			tagArray.put(testTag);
		}
		whereJson.put("and", tagArray);
		filterJson.put("where", whereJson);
		System.out.println(filterJson.toString());
		// Set filter condition into rootJson
		groupcast.setPredefinedKeyValue("filter", filterJson);
		groupcast.setPredefinedKeyValue("alert", uMengMessageVo.getAlert());
		groupcast.setPredefinedKeyValue("badge", 0);
		groupcast.setPredefinedKeyValue("sound", "chime");
		// TODO set 'production_mode' to 'true' if your app is under production
		// mode
		groupcast.setPredefinedKeyValue("production_mode", "true");
		groupcast.send();
	}

	public void sendAndroidFilecast() throws Exception {
		AndroidFilecast filecast = new AndroidFilecast();
		filecast.setAppMasterSecret(appMasterSecret_Android);
		filecast.setPredefinedKeyValue("appkey", this.appkey_Android);
		filecast.setPredefinedKeyValue("timestamp", getTimeStamp());
		// TODO upload your device tokens, and use '\n' to split them if there
		// are multiple tokens
		// device tokens
		filecast.uploadContents("lenovo" + "\n" + "htc");
		filecast.setPredefinedKeyValue("ticker", "Android filecast ticker");
		filecast.setPredefinedKeyValue("title", "中文的title");
		filecast.setPredefinedKeyValue("text", "Android filecast text");
		filecast.setPredefinedKeyValue("after_open", "go_app");
		filecast.setPredefinedKeyValue("display_type", "notification");
		filecast.send();
	}
	public void sendIOSUnicast() throws Exception {
		IOSUnicast unicast = new IOSUnicast();
		unicast.setAppMasterSecret(appMasterSecret_IOS);
		unicast.setPredefinedKeyValue("appkey", this.appkey_IOS);
		unicast.setPredefinedKeyValue("timestamp", getTimeStamp());
		// TODO Set your device token
		unicast.setPredefinedKeyValue("device_tokens", "xx");
		unicast.setPredefinedKeyValue("alert", "IOS 单播测试");
		unicast.setPredefinedKeyValue("badge", 0);
		unicast.setPredefinedKeyValue("sound", "chime");
		// TODO set 'production_mode' to 'true' if your app is under production
		// mode
		unicast.setPredefinedKeyValue("production_mode", "false");
		// Set customized fields
		unicast.setCustomizedField("test", "helloworld");
		unicast.send();
	}

	public void sendIOSFilecast() throws Exception {
		IOSFilecast filecast = new IOSFilecast();
		filecast.setAppMasterSecret(appMasterSecret_Android);
		filecast.setPredefinedKeyValue("appkey", this.appkey_Android);
		filecast.setPredefinedKeyValue("timestamp", getTimeStamp());
		// TODO upload your device tokens, and use '\n' to split them if there
		// are multiple tokens
		filecast.uploadContents("aa" + "\n" + "bb");
		filecast.setPredefinedKeyValue("alert", "IOS 文件播测试");
		filecast.setPredefinedKeyValue("badge", 0);
		filecast.setPredefinedKeyValue("sound", "chime");
		// TODO set 'production_mode' to 'true' if your app is under production
		// mode
		filecast.setPredefinedKeyValue("production_mode", "false");
		filecast.send();
	}

	public static void main(String[] args) {
		// TODO set your appkey and master secret here
		UMengPushTools demo = UMengPushTools.getInstance();
		try {
			// demo.sendAndroidUnicast();
			// TODO these methods are all available, just fill in some fields
			// and do the test
			// demo.sendAndroidBroadcast();

			// demo.sendAndroidGroupcast();
			int i = 0;
			// for (i = 0; i < 150; i++) {
			//
			UMengMessageVo uMengMessageVo = new UMengMessageVo();

			uMengMessageVo.setAlias("4028b8814bc3a232014bc3a232290000");
			uMengMessageVo.setAlias_type("SHOP");//给商铺发送
			//uMengMessageVo.setAlias_type("CUST");//给消费者发送
			uMengMessageVo.setTicker("好消息来了corner");
			uMengMessageVo.setTitle("领积分777111");
			uMengMessageVo.setText("亲，您今天可以领3个积分!积分可以兑换现金券");
			uMengMessageVo.setAlert("亲，您今天可以领3个积分!积分可以兑换现金券");//for IOS
			uMengMessageVo.setAfter_open("go_custom");
			HashMap<String, String> params = new HashMap<String, String>();
			params.put("URL", "file:///android_asset/webapp/html/store/index.html#storeIndex");
			params.put("chatMessage", "false");
			// uMengMessageVo.setParams(params);
			uMengMessageVo.setCustom(params);
			List<String> tags = new ArrayList<String>();
			tags.add("cornerv2");
			uMengMessageVo.setTags(tags);
			demo.sendAndroidCustomizedcast(uMengMessageVo);
			//demo.sendIOSCustomizedcast(uMengMessageVo);
			//demo.sendAndroidGroupcast(uMengMessageVo);

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
