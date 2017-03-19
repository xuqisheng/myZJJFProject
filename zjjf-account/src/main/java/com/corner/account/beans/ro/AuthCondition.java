package com.corner.account.beans.ro;

import com.corner.core.beans.ro.condition.EasyUIQueryModel;

public class AuthCondition extends EasyUIQueryModel{

	private String id;
	
	private String authname;
	
	private Boolean isDelete;
	
	private Byte status;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthname() {
		return authname;
	}

	public void setAuthname(String authname) {
		this.authname = authname;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}
	
}
