package com.corner.kefu.service.sp;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.SpPushMsg;
import com.corner.core.beans.SpPushMsgType;

public interface SpSpMsgService {

	List<SpPushMsg> getSpPushMsgList(Map<String, Object> map);

	SpPushMsg getSpPushMsgById(String id);

	List<SpPushMsgType> getSpPushMSGList();

	void addSpPushMsg(Map<String, Object> map);

	void removeMsgBySpPushMsgId(String id);

}
