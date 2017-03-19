package com.corner.core.beans.pay.wap;

import java.io.Serializable;

public class BussinesServiceSendParams extends PublicParams implements Serializable{

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/ 
	private static final long serialVersionUID = 1L;
	
	/**
	 * 包括以下子参数
	 * auth_and_execute_req(根节点)
	 * request_token 授权令牌
	 */
	private  String req_data;//请求业务	参数

	public String getReq_data() {
		return req_data;
	}

	public void setReq_data(String req_data) {
		this.req_data = req_data;
	}
}
