package com.corner.core.beans.pay.wap;

import java.io.Serializable;

public class AuthServiceResParams extends PublicParams implements Serializable{

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/ 
	private static final long serialVersionUID = 1L;
	
	/**
	 * 包括以下子参数
	 * direct_trade_create_res
	 * request_token
	 */
	private  String res_data;//返回数据

	
	/**
	 * 包括以下子参数
	 * err
	 * code
	 * sub_code
	 * msg
	 * detail
	 */
	private  String res_erro	;//返回数据


	public String getRes_data() {
		return res_data;
	}


	public void setRes_data(String res_data) {
		this.res_data = res_data;
	}


	public String getRes_erro() {
		return res_erro;
	}


	public void setRes_erro(String res_erro) {
		this.res_erro = res_erro;
	}
	
	
	

}
