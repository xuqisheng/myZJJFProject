package com.corner.kefu.beans.vo.erp;

import java.io.Serializable;

import com.corner.core.beans.ERPManager;
import com.corner.core.utils.DateUtil;

public class ERPManagerVo extends ERPManager implements Serializable {
	private static final long serialVersionUID = 2690474881624799839L;
	
	private String updateDateStr;

	public String getUpdateDateStr() {
		return DateUtil.date2StandardString(this.getUpdateTime());
	}
	public void setUpdateDateStr(String updateDateStr) {
		this.updateDateStr = updateDateStr;
	}

}
