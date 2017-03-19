package com.corner.scms.beans.ro;

import com.corner.core.beans.ro.condition.EasyUIQueryModel;


public class ScmsStoreCondition extends EasyUIQueryModel{
	private String gID;
	
	private Integer from;
	
	private String spId;
	
	public String getSpId() {
		return spId;
	}

	public void setSpId(String spId) {
		this.spId = spId;
	}

	//模糊查询   店面名  或者 手机号 
	private String nameOrTelphone; 
	
	public String getNameOrTelphone() {
		return nameOrTelphone;
	}

	public void setNameOrTelphone(String nameOrTelphone) {
		this.nameOrTelphone = nameOrTelphone;
	}

	@Override
	public String toHqlString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toHqlObjects() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getgID() {
		return gID;
	}

	public void setgID(String gID) {
		this.gID = gID;
	}

	public Integer getFrom() {
		return from;
	}

	public void setFrom(Integer from) {
		this.from = from;
	}

	
	
}
