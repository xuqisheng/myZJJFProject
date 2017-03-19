/**   
* @Title: FinWalletLogVo.java 
* @Package com.corner.kefu.beans.vo.sp 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年7月21日 下午12:47:43 
* @version V1.0   
*/

package com.corner.kefu.beans.vo.sp;

import com.corner.core.beans.FinWalletLog;
import com.corner.kefu.enums.wallet.BusinessType;
import com.corner.kefu.enums.wallet.Purpose;

/** 
* @ClassName: FinWalletLogVo 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年7月21日 下午12:47:43 
*  
*/

public class FinWalletLogVo extends FinWalletLog {

	private String userName;

	private String mobile;

	private String faOrderId;
	
	private String sonOrderId;
	
	private String businessTypeStr;
	
	private String purposeStr;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFaOrderId() {
		return faOrderId;
	}

	public void setFaOrderId(String faOrderId) {
		this.faOrderId = faOrderId;
	}

	public String getSonOrderId() {
		return sonOrderId;
	}

	public void setSonOrderId(String sonOrderId) {
		this.sonOrderId = sonOrderId;
	}

	public String getBusinessTypeStr() {
		if(this.getBusinessType()==null){
			return "";
		}
		return BusinessType.getName(this.getBusinessType());
	}


	public String getPurposeStr() {
		if(this.getPurpose()==null){
			return "";
		}
		return Purpose.getName(this.getPurpose());
	}
}
