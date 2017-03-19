package com.corner.scms.beans.vo;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.corner.core.beans.SpOrderDetail;
import com.corner.core.beans.SpOrderInfo;
import com.corner.core.beans.Store;
import com.corner.core.beans.vo.Pager;



public class StoreVo extends Store implements Serializable{

	private String GroupName;

	public String getGroupName() {
		return GroupName;
	}

	public void setGroupName(String groupName) {
		GroupName = groupName;
	}
	
	
	
}
