package com.corner.account.beans.ro;

import com.corner.core.beans.ro.condition.EasyUIQueryModel;

public class AccounterCondition extends EasyUIQueryModel {
	
	private String id;
	
	private String realName;
	
	private Boolean isDelete;
	
	private Byte status;
	

	@Override
	public String toHqlString() {
		return null;
	}

	@Override
	public Object[] toHqlObjects() {
		return null;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
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
