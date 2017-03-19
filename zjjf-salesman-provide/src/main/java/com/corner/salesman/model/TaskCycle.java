package com.corner.salesman.model;

import java.io.Serializable;
/**
 * 任务周期对象
 * @author Longx
 *
 */
public class TaskCycle implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cycleId;  //任务周期ID
	private String cycleName;//任务周期名称
	private int isDelete;//是否删除（0：有效；1：删除；）
	
	public String getCycleId() {
		return cycleId;
	}
	public void setCycleId(String cycleId) {
		this.cycleId = cycleId;
	}
	public String getCycleName() {
		return cycleName;
	}
	public void setCycleName(String cycleName) {
		this.cycleName = cycleName;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
}