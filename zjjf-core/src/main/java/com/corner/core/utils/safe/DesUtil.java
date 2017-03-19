package com.corner.core.utils.safe;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;

import com.corner.core.pay.config.SystemCoreKey;

public class DesUtil {
	
	public static AesCipherService aesCipherService = new AesCipherService();
	public static  KeyGenerator kgen = null;
	public static   SecretKey secretKey = null;
	public static   byte[] keyBytes ;
	static{
		try {
			aesCipherService.setKeySize(128); //设置key长度
			kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(SystemCoreKey.TOKENKEY.getBytes()));  
			secretKey = kgen.generateKey();
			keyBytes = secretKey.getEncoded();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public static String crypto(String datasource) {
		if(StringUtils.isEmpty(datasource) || StringUtils.isEmpty(datasource)){
			return datasource;
		}else{
			return aesCipherService.encrypt(datasource.getBytes(), keyBytes ).toHex();			
		}
	}


	public static String decrypt(String datasource ) {
		if(StringUtils.isEmpty(datasource) || StringUtils.isEmpty(datasource)){
			return datasource;
		}else{
			return new String(aesCipherService.decrypt(Hex.decode(datasource),  keyBytes ).getBytes());		
		}
	}

	
	public static String hexEncodeToString(String originalString){
		return  Hex.encodeToString(originalString.getBytes());  
	}

	public static String hexEncodeToString(byte[] originalString){
		return  new String(Hex.encode(originalString));
	}
	
	//Hex解码
	public static String hexDecodeToString(String hexCodedString){
		return  new String(Hex.decode(hexCodedString));
	}
	
	public static String base64EecodeToString(String originalString){ 
		return  Base64.encodeToString(originalString.getBytes());
	}
	
	public static String base64DecodeToString(byte[] originalString){
		return  Base64.encodeToString(originalString);
	}

	//base64解码
	public static String base64DecodeToString(String base64CodedString){
		return  Base64.decodeToString(base64CodedString);
	}

}
