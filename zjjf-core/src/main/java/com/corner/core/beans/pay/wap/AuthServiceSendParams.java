package com.corner.core.beans.pay.wap;

import java.io.Serializable;

public class AuthServiceSendParams extends PublicParams implements Serializable{

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/ 
	private static final long serialVersionUID = 1L;
	
	/**
	 * 包括以下子参数
	 * subject
	 * out_trade_no
	 * total_fee
	 * seller_account_name
	 * call_back_url
	 * notify_url
	 * out_user
	 * merchant_url
	 * pay_expire
	 * agent_id
	 */
	private  String req_data;//请求业务	参数

	public String getReq_data() {
		return req_data;
	}

	public void setReq_data(String req_data) {
		this.req_data = req_data;
	}
}
