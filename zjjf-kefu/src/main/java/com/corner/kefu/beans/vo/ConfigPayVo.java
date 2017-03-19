package com.corner.kefu.beans.vo;

import java.util.ArrayList;
import java.util.List;

import com.corner.core.beans.ConfigPay;
import com.corner.core.beans.SpGroup;

public class ConfigPayVo extends ConfigPay {
	
	private List<SpGroup> spGroupList = new ArrayList<SpGroup>();

	public List<SpGroup> getSpGroupList() {
		return spGroupList;
	}

	public void setSpGroupList(List<SpGroup> spGroupList) {
		this.spGroupList = spGroupList;
	}

}
