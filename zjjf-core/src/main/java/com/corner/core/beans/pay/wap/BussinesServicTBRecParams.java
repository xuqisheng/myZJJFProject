package com.corner.core.beans.pay.wap;

import java.io.Serializable;
import java.util.Map;

public class BussinesServicTBRecParams extends PublicParams implements Serializable{

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/ 
	private static final long serialVersionUID = 1L;

	private  String sign; //签名

	private  String out_trade_no; //支付结果
	
	private  String trade_no; //支付宝交易号
	
	private  String result; //支付结果 success
	
	private  String request_token; //授权令牌
	
	//该方法只初始化同步返回接口的参数
	public void  tbMapToBean( Map<String, String> map ) {
		this.sign =  map.get("sign");
		this.result =  map.get("result");
		this.out_trade_no =  map.get("out_trade_no");
		this.trade_no =  map.get("trade_no");
		this.request_token =  map.get("request_token");
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	public String getRequest_token() {
		return request_token;
	}

	public void setRequest_token(String request_token) {
		this.request_token = request_token;
	}
	
	
 
}
