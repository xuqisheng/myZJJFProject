/**   
* @Title: LogisticsStatus.java 
* @Package com.corner.core.enums 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年9月12日 下午3:15:37 
* @version V1.0   
*/

package com.corner.core.enums;

/** 
* @ClassName: LogisticsStatus 
* @Description:物流状态
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年9月12日 下午3:15:37 
*  
*/

public enum LogisticsStatus {
	ZAICANG("在仓", 1), ZAIJIAN("在检", 2), DAIYUN("待运", 3), ZAITU("在途", 4), SONGDA("送达", 5);

	private String name;
	private Byte index;

	private LogisticsStatus(String name, int index) {
		this.name = name;
		this.index = (byte) index;
	}

	public static String getName(Byte index) {
		for(LogisticsStatus status: LogisticsStatus.values()){
			if(status.getIndex().intValue()==index.intValue()){
				return status.getName();
			}
		}
		return "";
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Byte getIndex() {
		return index;
	}

	public void setIndex(Byte index) {
		this.index = index;
	}
}
