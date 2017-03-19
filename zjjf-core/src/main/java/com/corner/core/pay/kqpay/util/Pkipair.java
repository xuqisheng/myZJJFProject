package com.corner.core.pay.kqpay.util;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.corner.core.pay.alipay.config.AlipayConfig;
import com.corner.core.pay.config.PayConfig;

public class Pkipair {

	private static Logger logger = LoggerFactory.getLogger(AlipayConfig.LOGGERNAME);

	public String signMsg(String signMsg) {

		String base64 = "";
		try {
			// 密钥仓库
			KeyStore ks = KeyStore.getInstance("PKCS12");
			// 类路径访问文件
			Resource res2 = new ClassPathResource("config/pay/99bill-rsa.pfx");
			//System.out.println("res2:" + res2.getFilename());
			// 读取密钥仓库
			// FileInputStream ksfis = new FileInputStream("e:/tester-rsa.pfx");
			// 读取密钥仓库（相对路径）
			//String file = Pkipair.class.getResource("tester-rsa.pfx").getPath().replaceAll("%20", " ");
			//logger.info("获取密钥仓库文件路径：" + file);
			//FileInputStream ksfis = new FileInputStream(file);
			BufferedInputStream ksbufin = new BufferedInputStream(res2.getInputStream());
			char[] keyPwd = "zjjf.cn".toCharArray();
			// char[] keyPwd = "YaoJiaNiLOVE999Year".toCharArray();
			ks.load(ksbufin, keyPwd);
			// 从密钥仓库得到私钥
			PrivateKey priK = (PrivateKey) ks.getKey("test-alias", keyPwd);
			Signature signature = Signature.getInstance("SHA1withRSA");
			signature.initSign(priK);
			signature.update(signMsg.getBytes("utf-8"));
			sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
			base64 = encoder.encode(signature.sign());

		} catch (FileNotFoundException e) {
			logger.error( "{}找不到密钥仓库文件{}",PayConfig.WapKQpayBusinessLogCode,e);
		} catch (Exception ex) {
			logger.error( "{}加密过程出现异常：{}",PayConfig.WapKQpayBusinessLogCode,ex);
		}
		logger.info("返回加密字符串(base64) = {}", base64);
		return base64;
	}

	public boolean enCodeByCer(String val, String msg) {
		boolean flag = false;
		try {
			// 获得文件(绝对路径)
			// InputStream inStream = new
			// FileInputStream("e:/99bill[1].cert.rsa.20140803.cer");

			// 获得文件(相对路径)
			Resource res2 = new ClassPathResource("config/pay/99bill.cert.rsa.cer");
			//String file = Pkipair.class.getResource("99bill[1].cert.rsa.20140803.cer").toURI().getPath();
			//System.out.println(file);
			//FileInputStream inStream = new FileInputStream(file);

			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			X509Certificate cert = (X509Certificate) cf.generateCertificate(res2.getInputStream());
			// 获得公钥
			PublicKey pk = cert.getPublicKey();
			// 签名
			Signature signature = Signature.getInstance("SHA1withRSA");
			signature.initVerify(pk);
			signature.update(val.getBytes());
			// 解码
			sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
			//System.out.println(new String(decoder.decodeBuffer(msg)));
			flag = signature.verify(decoder.decodeBuffer(msg));
			//System.out.println(flag);
		} catch (Exception e) {
			logger.error(PayConfig.WapKQpayBusinessLogCode + "解码报文过程出现异常");
		}
		return flag;
	}
}
