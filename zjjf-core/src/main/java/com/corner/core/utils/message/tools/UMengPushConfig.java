package com.corner.core.utils.message.tools;

import java.util.HashMap;
import java.util.Map;

import com.corner.core.beans.UserLoginInfo;
import com.corner.core.config.PropertieNameConts;
import com.corner.core.enums.MsgPushCodeType;
import com.corner.core.utils.PropertiesCacheUtil;

public class UMengPushConfig {
	
	//kim
	public static int message_out_time = Integer.parseInt( PropertiesCacheUtil.getValue("message_out_time", PropertieNameConts.UM));
	
	//是否将友盟消息记录日志
	public static int IS_SAVE_umMessage = Integer.parseInt( PropertiesCacheUtil.getValue("IS_SAVE_umMessage", PropertieNameConts.UM));

	//少货通知
	public static String less_goods_notify_url = PropertiesCacheUtil.getValue("less_goods_notify_url", PropertieNameConts.UM);
	
	//超时通知
	public static String order_overtime_notify_url = PropertiesCacheUtil.getValue("order_overtime_notify_url", PropertieNameConts.UM);
	
	//判断typeCode是否发送消息
	public static boolean isSendMsg(Integer code ,Integer typeCode) {
		if( code % 10 == 1 ){//全部接收
			if(code>typeCode && ((code/typeCode) % 10 )==1){//取10位数，为1则接收
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}

	//根据code name 设置对应未
	public static boolean updatePushCode(UserLoginInfo userLoginInfo, int code, Integer pushCode) {
		int rightPushCode =userLoginInfo.getUli_push_code();
		int position =(rightPushCode/code)%10;
		if( position == 1){
			if(pushCode.intValue()==1){
				return true;
			}else if(pushCode.intValue()==0){
				userLoginInfo.setUli_push_code(rightPushCode-code);
				return true;
			}else{
				return false;
			}
		}else if(position == 0){
			if(pushCode.intValue()==1){
				userLoginInfo.setUli_push_code(rightPushCode+code);
				return true;
			}else if(pushCode.intValue()==0){
				return true;
			}else{
				return false;
			}
		}else{			
			return false;
		}
	}

	//code转map
	public static Map<String, Integer> changeCodeToMap(UserLoginInfo userLoginInfo) {
		if(userLoginInfo==null || userLoginInfo.getUli_push_code() ==null){
			return null;
		}
		int userCode =userLoginInfo.getUli_push_code();
		Map<String, Integer> restMap =new HashMap<String, Integer>();
		restMap.put(MsgPushCodeType.All.getName(), ((userCode/MsgPushCodeType.All.getIndex())%10==1)?1:0);
		restMap.put(MsgPushCodeType.LessGoods.getName(), ((userCode/MsgPushCodeType.LessGoods.getIndex())%10==1)?1:0);
		restMap.put(MsgPushCodeType.OrderOverTime.getName(), ((userCode/MsgPushCodeType.OrderOverTime.getIndex())%10==1)?1:0);
		restMap.put(MsgPushCodeType.Logistics.getName(), ((userCode/MsgPushCodeType.Logistics.getIndex())%10==1)?1:0);
		restMap.put(MsgPushCodeType.Voucher.getName(), ((userCode/MsgPushCodeType.Voucher.getIndex())%10==1)?1:0);
		restMap.put(MsgPushCodeType.Order.getName(), ((userCode/MsgPushCodeType.Order.getIndex())%10==1)?1:0);
		return restMap;
	}

}
