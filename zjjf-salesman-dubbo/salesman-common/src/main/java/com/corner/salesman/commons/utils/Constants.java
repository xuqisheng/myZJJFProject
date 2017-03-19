package com.corner.salesman.commons.utils;

public class Constants {
	
	public static String PICTURE_VISIT_FILE_PATH = "";//图片访问的路径
	
	public static String PICTURE_SAVE_FILE_PATH = "";//图片存放的路径

	public static final String SHOP_TYPE_CONVERT_MAP = "shop_type_convert_map";
	
	public static final String ENABLE_REGION_CACHE_MAP = "enable_region_cache_map";
	
	public static final String ENABLE_SP_GROUP_CACHE_MAP = "enable_sp_group_cache_map";
	
	public static final String SHOP_SEQUENCE_KEY = "aj_shop_sequence_key";
	
	public static final String TOKEN_PREFIX_KEY = "aj_token_";//token前缀
	
	public static final int TOKEN_CACHESECONDS = 60*60*24*2;//48小时（即两天）
	
	public static final int DEADLINE_CACHESECONDS = 60*60*16;//16小时
	
	public static final String TIME_HZ_VAL_KEY = "time_hz_val";//时间频率值key
	
	public static final String NEW_NOTICE_PREFIX_KEY55 = "new_notice_";//新公告前缀
	public static final String NEW_NOTICE_TOTAL_KEY = "new_notice_total_";//新公告前缀
	
	public static final String NEW_DAILY_PREFIX_KEY33 = "new_daily_";//新日报及新评论前缀
	public static final String NEW_DAILY_TOTAL_KEY = "new_daily_total_";//新日报及新评论前缀
	
	public static final String DAILY_COMMENT_KEY = "daily_comment_";//日报评论统计前缀
	public static final String DAILY_COMMENT_TOTAL_KEY = "daily_comment_total_";//日报评论统计前缀
	
	public static final String NOTICE_EXAMPLE_KEY = "notice_example";//日报样例（当用户一个日报的没有的时候显示日报样例）
	
	public static final String NEWS_LIST_PREFIX_KEY = "news_list_";//新公告前缀
	
	public static final String AJ_WARN_TOTAL_KEY = "aj_warn_total_";//警告消息提醒数量
	public static final String AJ_WARN_OBJ_KEY = "aj_warn_obj_";//警告消息对象
	public static final String AJ_WARN_LIST_KEY = "aj_warn_list_";//警告消息列表
	public static final String AJ_PLANS_CHANGE_TOTAL_KEY = "aj_plans_change_total_";//拜访计划变更统计总数
	public static final String AJ_PLANS_CHANGE_KEY = "aj_plans_change_";//拜访计划变更key
	
    public final static String DIVISION = "\\[:@]";// 分割符
    
    public final static String SEMICOLON = "\\[:;]";// 分号
    
    
    public final static String POINT_IN_TIME_IOS = "57,58,59,00,01,02,03,27,28,29,30,31,32,33";//指定时间点用于判断是否在指定时间内进行反地理编码
    
    public final static String POINT_IN_TIME_ANDROID = "59,00,01,29,30,31";//指定时间点用于判断是否在指定时间内进行反地理编码
	
	public static String getPICTURE_VISIT_FILE_PATH() {
		return PICTURE_VISIT_FILE_PATH;
	}

	public static void setPICTURE_VISIT_FILE_PATH(String pICTURE_VISIT_FILE_PATH) {
		PICTURE_VISIT_FILE_PATH = pICTURE_VISIT_FILE_PATH;
	}

	public static String getPICTURE_SAVE_FILE_PATH() {
		return PICTURE_SAVE_FILE_PATH;
	}

	public static void setPICTURE_SAVE_FILE_PATH(String pICTURE_SAVE_FILE_PATH) {
		PICTURE_SAVE_FILE_PATH = pICTURE_SAVE_FILE_PATH;
	}

	public static void main(String[] args) {
		Constants.setPICTURE_SAVE_FILE_PATH("D:/Tomcat 6.0/webapps/zs/topic/");
		Constants.setPICTURE_VISIT_FILE_PATH("http://192.168.1.225:8888/zs/topic/");
	}
	
}
