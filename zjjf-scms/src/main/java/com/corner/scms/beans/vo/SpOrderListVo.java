package com.corner.scms.beans.vo;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import com.corner.core.beans.SpOrderDetail;
import com.corner.core.beans.SpOrderInfo;
import com.corner.core.beans.vo.Pager;



public class SpOrderListVo extends SpOrderInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String createtime;//下单时间(字符串类型)
	private Pager<SpOrderDetail> detail;//订单详情集合
	private List<SpOrderDetail> detailTemp;//订单详情集合
	private String sendtime;//送货时间
	private Integer toPay;//待付款数量
	private Integer toConfirm;//待收货数量
	private Integer toSend;//代发货数量
	private Integer unEvaluation;//代发货数量
	
	private int timeNum;
	private String statusName;
	private String supportName;
	
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public Pager<SpOrderDetail> getDetail() {
		return detail;
	}
	public void setDetail(Pager<SpOrderDetail> detail) {
		this.detail = detail;
	}
	public String getSendtime() {
		if(super.getSendDate() != null){
			DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			sendtime = df.format(super.getSendDate());
		}
		return sendtime;
	}
	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}
	public Integer getToPay() {
		return toPay;
	}
	public void setToPay(Integer toPay) {
		this.toPay = toPay;
	}
	public Integer getToConfirm() {
		return toConfirm;
	}
	public void setToConfirm(Integer toConfirm) {
		this.toConfirm = toConfirm;
	}
	public List<SpOrderDetail> getDetailTemp() {
		return detailTemp;
	}
	public void setDetailTemp(List<SpOrderDetail> detailTemp) {
		this.detailTemp = detailTemp;
	}
	public Integer getToSend() {
		return toSend;
	}
	public void setToSend(Integer toSend) {
		this.toSend = toSend;
	}
	public Integer getUnEvaluation() {
		return unEvaluation;
	}
	public void setUnEvaluation(Integer unEvaluation) {
		this.unEvaluation = unEvaluation;
	}
	public int getTimeNum() {
		return timeNum;
	}
	public void setTimeNum(int timeNum) {
		this.timeNum = timeNum;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getSupportName() {
		return supportName;
	}
	public void setSupportName(String supportName) {
		this.supportName = supportName;
	}
	
}
