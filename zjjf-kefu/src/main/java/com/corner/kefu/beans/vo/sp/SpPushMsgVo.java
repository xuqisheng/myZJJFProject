package com.corner.kefu.beans.vo.sp;

import java.io.Serializable;

import com.corner.core.beans.SpPushMsg;

public class SpPushMsgVo extends SpPushMsg implements Serializable {
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
