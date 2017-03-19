package com.corner.kefu.beans.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.corner.core.beans.AppItemTag;
import com.corner.core.beans.AppModule;

public class AppModuleVo extends AppModule implements Serializable {
	private String codeName;
	private String typeName;
	private String seeName;
	private String boardName;
	private String staName;
	
	private List<AppItemTag> moduleDetails = new ArrayList<AppItemTag>();

	public List<AppItemTag> getModuleDetails() {
		return moduleDetails;
	}

	public void setModuleDetails(List<AppItemTag> moduleDetails) {
		this.moduleDetails = moduleDetails;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getSeeName() {
		return seeName;
	}

	public void setSeeName(String seeName) {
		this.seeName = seeName;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public String getStaName() {
		return staName;
	}

	public void setStaName(String staName) {
		this.staName = staName;
	}
	
	
}
