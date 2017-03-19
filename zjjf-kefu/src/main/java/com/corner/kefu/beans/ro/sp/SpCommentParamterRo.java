package com.corner.kefu.beans.ro.sp;

import java.util.Date;

import com.corner.core.beans.ro.AmazeUIListRo;

public class SpCommentParamterRo extends AmazeUIListRo {

	private static final long serialVersionUID = 1L;


	public SpCommentParamterRo() {
		super();
	}

	//批发商名称
	private String spNm;
	//评论开始时间
	private Date beginTime;
	//评论结束时间
	private Date endTime;
	//评论内容
	private String info;
	//综合评分1星
	private Byte unionFenA; 
	//综合评分2星
	private Byte unionFenB; 
	//综合评分3星
	private Byte unionFenC; 
	//综合评分4星
	private Byte unionFenD; 
	//综合评分5星
	private Byte unionFenE; 
	//处理状态
	private Byte csDealstat;
	
	private String unionFen;
	//private Integer pageIndex = SystemKeys.commonPageIndex;
	//每页显示多少行
	//private Integer pageSize = SystemKeys.commonPageSize;


	public String getSpNm() {
		return spNm;
	}
	public void setSpNm(String spNm) {
		this.spNm = spNm;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Byte getUnionFenA() {
		return unionFenA;
	}
	public void setUnionFenA(Byte unionFenA) {
		this.unionFenA = unionFenA;
	}
	public Byte getUnionFenB() {
		return unionFenB;
	}
	public void setUnionFenB(Byte unionFenB) {
		this.unionFenB = unionFenB;
	}
	public Byte getUnionFenC() {
		return unionFenC;
	}
	public void setUnionFenC(Byte unionFenC) {
		this.unionFenC = unionFenC;
	}
	public Byte getUnionFenD() {
		return unionFenD;
	}
	public void setUnionFenD(Byte unionFenD) {
		this.unionFenD = unionFenD;
	}
	public Byte getUnionFenE() {
		return unionFenE;
	}
	public void setUnionFenE(Byte unionFenE) {
		this.unionFenE = unionFenE;
	}
	public Byte getCsDealstat() {
		return csDealstat;
	}
	public void setCsDealstat(Byte csDealstat) {
		this.csDealstat = csDealstat;
	}
	public String getUnionFen() {
		return unionFen;
	}
	public void setUnionFen(String unionFen) {
		this.unionFen = unionFen;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
