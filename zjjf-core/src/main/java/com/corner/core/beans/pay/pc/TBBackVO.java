package com.corner.core.beans.pay.pc;


/**
* @ClassName: TBBackvo
* @Description: TODO 同步回传的参数bean
* @author luke
* @email   luke@mibodoctor.com  
* @date 2014年12月19日 上午10:50:06
*
*/ 
public class TBBackVO extends ErrorBackVO {
	
	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/ 
	private  static final long serialVersionUID = 1L;
	
	protected String is_success;//成功标识 T/N 1  
	protected String exterface ;// 接口名称create_direct_pay_by_user
	protected String extra_common_param;//公用回传参数 送什么回来什么 可空
	protected String agent_user_id ;//信用支付购票员的代理人ID
	
	public String getIs_success() {
		return is_success;
	}
	public void setIs_success(String is_success) {
		this.is_success = is_success;
	}
	public String getExterface() {
		return exterface;
	}
	public void setExterface(String exterface) {
		this.exterface = exterface;
	}
	public String getExtra_common_param() {
		return extra_common_param;
	}
	public void setExtra_common_param(String extra_common_param) {
		this.extra_common_param = extra_common_param;
	}
	public String getAgent_user_id() {
		return agent_user_id;
	}
	public void setAgent_user_id(String agent_user_id) {
		this.agent_user_id = agent_user_id;
	}

}
