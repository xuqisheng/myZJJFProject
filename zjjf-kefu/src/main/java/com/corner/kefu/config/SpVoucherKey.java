package com.corner.kefu.config;

import com.corner.core.utils.PropertiesCacheUtil;

/**
 * 
* @ClassName: SpVoucherKey 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author 铁中棠  tiezhongtang@izjjf.cn 
* @date 2015年12月27日 下午5:03:10 
*
 */
public class SpVoucherKey {
	
	
	/**
	 * 现金券加密密码
	 */
	public static final String SPVOUCHERKEY = "corner2802";
	
	/**
	 * 满减活动转换为现金券的固定id
	 */
	public static final String MJ_SPVOUCHER_ID = "MJ_SPVOUCHER_ID";
	
	/**
	 * 空字符串
	 */
	public static final String EMPTY_ID = "EMPTY_ID";
	
	/**
	 * 自动发送到规则类型活动类型(0-默认活动,1-注册送优惠券,2-订单满送优惠券,3-订单满减,4-订单满折,5-送实物,6-送积分,7-送兑奖码)
	 */
	public static Integer registerSendType = 1,orderMangSendType= 2,orderMangReduce=3,orderMangBreak=4,
			sengGift=5,	sendIntegration=6,sendVfcode=7;
	
	/**
	 * 满减活动生成现金券信息
	 */
	public static String MJ_Name_1 = PropertiesCacheUtil.getValue("MJ_Name_1", PropertieNameConts.SpVoucher);
	public static String MJ_Name_2 = PropertiesCacheUtil.getValue("MJ_Name_2", PropertieNameConts.SpVoucher); 
	public static String MJ_Name_3 = PropertiesCacheUtil.getValue("MJ_Name_3", PropertieNameConts.SpVoucher); 
	public static String MJ_Remark = PropertiesCacheUtil.getValue("MJ_Remark", PropertieNameConts.SpVoucher);
	public static Integer MJ_Time_Interval=Integer.parseInt(PropertiesCacheUtil.getValue("MJ_Time_Interval", PropertieNameConts.SpVoucher));
	
	/*
	 * 是否开启短信或者友盟短信推送
	 */
	public static Integer enableUMmsg=Integer.parseInt(PropertiesCacheUtil.getValue("enableUMmsg", PropertieNameConts.SpVoucher));
	public static Integer enablePhonemsg=Integer.parseInt(PropertiesCacheUtil.getValue("enablePhonemsg", PropertieNameConts.SpVoucher));
	public static Integer phonemsgStart=Integer.parseInt(PropertiesCacheUtil.getValue("phonemsgStart", PropertieNameConts.SpVoucher));
}

