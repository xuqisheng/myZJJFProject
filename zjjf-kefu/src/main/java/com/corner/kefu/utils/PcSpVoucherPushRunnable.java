/**   
* @Title: PcSpVoucherPushRunnable.java 
* @Package com.corner.pc.util 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年1月12日 下午6:49:15 
* @version V1.0   
*/

package com.corner.kefu.utils;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.corner.core.utils.message.tools.UMengMessageVo;
import com.corner.kefu.beans.vo.sp.StoreVo;
import com.corner.kefu.uMeng.tools.PcSpPushUMengPushTools;

/**
 * @ClassName: PcSpVoucherPushRunnable
 * @Description:
 * @author 杨开泰 yangkaitai@izjjf.cn
 * @date 2016年1月12日 下午6:49:15
 * 
 */

public class PcSpVoucherPushRunnable implements Runnable {
	private static Logger logger = LoggerFactory.getLogger(PcSpVoucherPushRunnable.class);

	private List<StoreVo> list;
	private String content;

	public PcSpVoucherPushRunnable(List<StoreVo> list, String content) {
		this.list = list;
		this.content = content;
	}

	@Override
	public void run() {
		try {
			UMengMessageVo uMengMessageVo = new UMengMessageVo();
			uMengMessageVo.setAlert("转角街坊优惠券消息");
			uMengMessageVo.setTitle("转角街坊优惠券消息");
			uMengMessageVo.setTicker("转角街坊优惠券消息");
			uMengMessageVo.setText(content);
			PcSpPushUMengPushTools uMengPushTools = PcSpPushUMengPushTools.getInstance();
			for (int i = 0; i < list.size(); i++) {
				uMengMessageVo.setAlias(list.get(i).getMobile());
	            uMengMessageVo.setAlias_type("android");
				uMengPushTools.sendAndroidCustomizedcast(uMengMessageVo);// android
				uMengMessageVo.setAlias_type("iOS");
				uMengPushTools.sendIOSCustomizedcast(uMengMessageVo);// ios
				uMengPushTools.sendIOSCustomizedcast2(uMengMessageVo);// ios
			}
		} catch (Exception e) {
			logger.error("发送优惠劵消息推送出错:",e);
		}
	}

}
