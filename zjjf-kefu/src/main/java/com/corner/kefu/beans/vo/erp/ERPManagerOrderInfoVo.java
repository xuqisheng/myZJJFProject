/**   
* @Title: ERPManagerOrderInfoVo.java 
* @Package com.corner.kefu.beans.vo.erp 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年9月5日 下午5:52:22 
* @version V1.0   
*/

package com.corner.kefu.beans.vo.erp;

import java.io.Serializable;
import java.util.List;

import com.corner.core.beans.ERPManager;
import com.corner.core.beans.ERPManagerOrderInfo;
import com.corner.core.beans.ERPWarehouse;
import com.corner.core.utils.DateUtil;

/** 
* @ClassName: ERPManagerOrderInfoVo 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年9月5日 下午5:52:22 
*  
*/

public class ERPManagerOrderInfoVo extends ERPManagerOrderInfo implements Serializable{

	private static final long serialVersionUID = 4401403033903769533L;

	private Integer totalNum;

	private List<ERPManagerOrderDetailVo> detailVos;
	
	private ERPManager erpManager;
	
	
	@SuppressWarnings("unused")
	private String addTimeStr;
	
	@SuppressWarnings("unused")
	private String gaveTimeStr;
	
	private ERPWarehouse erpWarehouse;
	

	
	/*public Integer getTotalNum() {
		if(detailVos==null||detailVos.size()==0){
			return 0;
		}else{
			int totalProductNum = 0;
			for (ERPManagerOrderDetailVo erpManagerOrderDetailVo : detailVos) {
				totalProductNum+=erpManagerOrderDetailVo.getQuantity().intValue();
			}
			return totalProductNum;
		}
	}*/

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public List<ERPManagerOrderDetailVo> getDetailVos() {
		return detailVos;
	}

	public void setDetailVos(List<ERPManagerOrderDetailVo> detailVos) {
		this.detailVos = detailVos;
	}

	public ERPManager getErpManager() {
		return erpManager;
	}

	public void setErpManager(ERPManager erpManager) {
		this.erpManager = erpManager;
	}

	public String getAddTimeStr() {
		return DateUtil.date2StandardString(super.getAddTime());
	}

	public void setAddTimeStr(String addTimeStr) {
		this.addTimeStr = addTimeStr;
	}

	public ERPWarehouse getErpWarehouse() {
		return erpWarehouse;
	}

	public void setErpWarehouse(ERPWarehouse erpWarehouse) {
		this.erpWarehouse = erpWarehouse;
	}

	public String getGaveTimeStr() {
		return DateUtil.date2String(super.getGaveTime());
	}

	public void setGaveTimeStr(String gaveTimeStr) {
		this.gaveTimeStr = gaveTimeStr;
	}

	public Integer getTotalNum() {
		return totalNum;
	}
}
