package com.corner.pc.beans.ro;


public class RecruitCondition extends ABaseQueryModel {
	
	private String id;
	
	private Boolean isDelete;
	
	private Byte status;
	
	private Integer recruitTypeId;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Integer getRecruitTypeId() {
		return recruitTypeId;
	}

	public void setRecruitTypeId(Integer recruitTypeId) {
		this.recruitTypeId = recruitTypeId;
	}

}
