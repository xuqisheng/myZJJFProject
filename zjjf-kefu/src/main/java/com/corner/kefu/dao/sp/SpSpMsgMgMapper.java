package com.corner.kefu.dao.sp;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.SpPushMsg;
import com.corner.core.beans.SpPushMsgType;

public interface SpSpMsgMgMapper {

	List<SpPushMsg> getSpPushMsgList(Map<String, Object> map);

	List<SpPushMsgType> getSpPushMSGList();

	void saveSpPushMsgMap(Map<String, Object> map);

	void removeMsgBySpPushMsgId(String id);

}
