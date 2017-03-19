package com.corner.scms.beans.vo;

import java.io.Serializable;

import com.corner.core.beans.SpComment;

public class SpCommentVo extends SpComment implements Serializable {
	
	private Integer num = 0;//每颗心评论的总人数
	private Integer countNum = 0;//评论总人数
	private Double avgScore = 0.0;//总平均分
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Integer getCountNum() {
		return countNum;
	}
	public void setCountNum(Integer countNum) {
		this.countNum = countNum;
	}
	public Double getAvgScore() {
		return avgScore;
	}
	public void setAvgScore(Double avgScore) {
		this.avgScore = avgScore;
	}

}
