package com.corner.rpc.salesman.utils;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.corner.salesman.commons.utils.Constants;
import com.corner.salesman.commons.utils.DateUtils;
import com.corner.salesman.commons.utils.JedisUtils;

public class GeneratorSeqUtils {

	/**
	 * 生成当天添加的店铺编码（格式：yyyyMMdd+流水号）
	 * @return
	 * @throws Exception 
	 */
	public static String getShopNoSeq() throws Exception{
		//计算过期时间
		Date currDate = new Date();
		String startTime = DateUtils.dateToString(currDate, DateUtils.DATETIME_FORMAT);
		String endTime = DateUtils.dateToString(DateUtils.addDays(currDate, 1), "yyyy-MM-dd")+" 00:00:00";
		int cacheSeconds =new Long(DateUtils.dateDiff(startTime, endTime, DateUtils.DATETIME_FORMAT)).intValue();
		
		String shopNoSeq = JedisUtils.get(Constants.SHOP_SEQUENCE_KEY);
		if(StringUtils.isBlank(shopNoSeq)){
			shopNoSeq = DateUtils.dateToString(new Date(), DateUtils.ABBR_DATE_FORMAT)+"00001";
			JedisUtils.set(Constants.SHOP_SEQUENCE_KEY, shopNoSeq, cacheSeconds);
		}else{
			shopNoSeq = JedisUtils.incrBy(Constants.SHOP_SEQUENCE_KEY, 1L)+"";
		}
		
		return shopNoSeq;
	}
}
