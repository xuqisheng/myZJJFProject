/**   
* @Title: SpVoucherTempVo.java 
* @Package com.corner.kefu.beans.vo.sp 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年4月8日 上午11:17:02 
* @version V1.0   
*/

package com.corner.scms.beans.vo;

import com.corner.core.beans.SpVoucherTemp;

/** 
* @ClassName: SpVoucherTempVo 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年4月8日 上午11:17:02 
*  
*/

public class SpVoucherTempVo extends SpVoucherTemp {

	private String spVoucherTempPayStr;

	private String mgItemsStr;
	
	private int grantCount;// 发放数
	
	private int usedCount;// 使用数

	public String getMgItemsStr() {
		return mgItemsStr;
	}

	public void setMgItemsStr(String mgItemsStr) {
		this.mgItemsStr = mgItemsStr;
	}

	public String getSpVoucherTempPayStr() {
		return spVoucherTempPayStr;
	}

	public void setSpVoucherTempPayStr(String spVoucherTempPayStr) {
		this.spVoucherTempPayStr = spVoucherTempPayStr;
	}

	public int getGrantCount() {
		return grantCount;
	}

	public void setGrantCount(int grantCount) {
		this.grantCount = grantCount;
	}

	public int getUsedCount() {
		return usedCount;
	}

	public void setUsedCount(int usedCount) {
		this.usedCount = usedCount;
	}
}
