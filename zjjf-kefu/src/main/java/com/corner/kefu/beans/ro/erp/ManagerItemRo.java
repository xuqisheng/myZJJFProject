/**   
* @Title: ManagerItemRo.java 
* @Package com.corner.kefu.beans.ro.erp 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年9月7日 下午2:29:12 
* @version V1.0   
*/

package com.corner.kefu.beans.ro.erp;

import com.corner.core.beans.ERPManagerItem;

/** 
* @ClassName: ManagerItemRo 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年9月7日 下午2:29:12 
*  
*/

public class ManagerItemRo extends ERPManagerItem {
	    //分页
		private int pageIndex;
		private int pageSize = 10;
		
		public int getPageIndex() {
			return pageSize * ((pageIndex - 1) > 0 ? (pageIndex - 1) : 0);
		}

		public void setPageIndex(int pageIndex) {
			this.pageIndex = pageIndex;
		}

		public int getPageSize() {
			return pageSize;
		}

		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}
		
		private String managerCode;
		private String numOrName;

		public String getManagerCode() {
			return managerCode;
		}

		public void setManagerCode(String managerCode) {
			this.managerCode = managerCode == null ? null : managerCode.trim();
		}

		public String getNumOrName() {
			return numOrName;
		}

		public void setNumOrName(String numOrName) {
			this.numOrName = numOrName == null ? null : numOrName.trim();
		}
}
