package com.corner.kefu.beans.vo.sc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.corner.core.beans.ScOrderDetail;
import com.corner.core.beans.ScOrderInfo;

public class ScOrderInfoMgVo extends ScOrderInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8615380476139507143L;

	//总数量
	private Integer countNumber;
	
	private List<ScOrderDetail> scOrderDetailVos = new ArrayList<ScOrderDetail>();

	public List<ScOrderDetail> getScOrderDetailVos() {
		return scOrderDetailVos;
	}

	public void setScOrderDetailVos(List<ScOrderDetail> scOrderDetailVos) {
		this.scOrderDetailVos = scOrderDetailVos;
	}

	public Integer getCountNumber() {
		return countNumber;
	}

	public void setCountNumber(Integer countNumber) {
		this.countNumber = countNumber;
	}

}
