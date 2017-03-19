package com.corner.core.beans.pay.wx;

import java.io.Serializable;
/**
 * 
* @ClassName: WXGZPTBean
* @Description: TODO(接收公众平台的消息事件)
* @author luke
* @email   luke@mibodoctor.com  
* @date 2015年3月18日 下午4:10:38
*
 */
public class WXGZPTBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private  String signature;
	private  String timestamp;
	private  String nonce;
	private  String echostr;
	//ncrypt_type的值为raw时表示为不加密，encrypt_type的值为aes时，表示aes加密（暂时只有raw和aes两种值)，无encrypt_type参数同样表示不加密
	private  String encrypt_type;
	//在url上增加参数：消息体签名
	//msg_signature=sha1(sort(Token、timestamp、nonce, msg_encrypt))
	private  String msg_signature;

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public String getEchostr() {
		return echostr;
	}

	public void setEchostr(String echostr) {
		this.echostr = echostr;
	}

	public String toString(){
		return "signature:"+this.signature+",timestamp:"+this.timestamp+",nonce:"+this.nonce+",echostr:"+this.echostr;
	}

	public String getEncrypt_type() {
		return encrypt_type;
	}

	public void setEncrypt_type(String encrypt_type) {
		this.encrypt_type = encrypt_type;
	}

	public String getMsg_signature() {
		return msg_signature;
	}

	public void setMsg_signature(String msg_signature) {
		this.msg_signature = msg_signature;
	}
}