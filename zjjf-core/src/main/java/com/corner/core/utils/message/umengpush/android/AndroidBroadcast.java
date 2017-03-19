package com.corner.core.utils.message.umengpush.android;

import com.corner.core.utils.message.umengpush.AndroidNotification;



public class AndroidBroadcast extends AndroidNotification {
	public AndroidBroadcast() {
		try {
			this.setPredefinedKeyValue("type", "broadcast");	
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(1);
		}
	}
}
