package com.corner.kefu.beans.vo.sp;

import java.io.Serializable;

import com.corner.core.beans.SpVoucher;

public class SpVhVo extends SpVoucher implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public SpVhVo(){
	}

	public SpVhVo(String id,Integer money){
		this.setId(id);
		this.setMoney(money);
	}
    //private String id;
    //private String name;
    //private String remark;
    //private Byte billType;
    //private Integer money;
    //private BigDecimal startPrice;
    //private Date startTime;
    //private Date expiredTime;
    //private Date createTime;
    //private String orderId;
    //private Integer status;
    private Integer checkStatus=0;//检查状态0-不可用，1-可用
    private String checkStr;//错误原因
    
    public Integer getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}
	public String getCheckStr() {
		return checkStr;
	}
	public void setCheckStr(String checkStr) {
		this.checkStr = checkStr;
	}

}
