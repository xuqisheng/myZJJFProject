package com.corner.kefu.beans.vo.sp;

import com.corner.core.beans.SpAcGroup;

public class SpAcGroupVo extends SpAcGroup {
	private Integer totalStore;// 改用户组下有多少店铺

	private Integer huodong;

	private Integer zhuce;
	private Integer mansong;
	private Integer manjian;

	public Integer getTotalStore() {
		return totalStore;
	}

	public void setTotalStore(Integer totalStore) {
		this.totalStore = totalStore;
	}

	public Integer getHuodong() {
		return huodong;
	}

	public void setHuodong(Integer huodong) {
		this.huodong = huodong;
	}

	public Integer getZhuce() {
		return zhuce;
	}

	public void setZhuce(Integer zhuce) {
		this.zhuce = zhuce;
	}

	public Integer getMansong() {
		return mansong;
	}

	public void setMansong(Integer mansong) {
		this.mansong = mansong;
	}

	public Integer getManjian() {
		return manjian;
	}

	public void setManjian(Integer manjian) {
		this.manjian = manjian;
	}

}
