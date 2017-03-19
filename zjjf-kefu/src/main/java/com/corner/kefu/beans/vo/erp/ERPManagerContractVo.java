/**   
* @Title: ERPManagerContractVo.java 
* @Package com.corner.kefu.beans.vo.erp 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年9月3日 下午4:24:00 
* @version V1.0   
*/

package com.corner.kefu.beans.vo.erp;

import com.corner.core.beans.ERPManagerContract;
import com.corner.core.utils.DateUtil;

/** 
* @ClassName: ERPManagerContractVo 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年9月3日 下午4:24:00 
*  
*/

public class ERPManagerContractVo extends ERPManagerContract {

	private String addTimeStr;
	
	private String managerCode;
	
	private String managerName;
	

	public String getAddTimeStr() {
		return DateUtil.date2StandardString(this.getAddTime());
	}

	public void setAddTimeStr(String addTimeStr) {
		this.addTimeStr = addTimeStr;
	}

	public String getManagerCode() {
		return managerCode;
	}

	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
}
