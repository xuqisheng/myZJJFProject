package com.corner.core.beans.pay.wx;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
* @ClassName: WXTYZFBean
* @Description: TODO(微信统一支付通知返回)
* @author luke
* @email   luke@mibodoctor.com  
* @date 2015年3月13日 上午11:10:24
*
 */
@XmlRootElement(name="xml")
public class WXTYZFBACKBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private String return_code;
	private String return_msg ;
	
	public WXTYZFBACKBean(){
	}
	
	public WXTYZFBACKBean(String return_code,String return_msg){
		this.return_code=return_code;
		this.return_msg=return_msg;
	}
	
	public String getReturn_code() {
		return return_code;
	}
	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}
	public String getReturn_msg() {
		return return_msg;
	}
	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}
	
}