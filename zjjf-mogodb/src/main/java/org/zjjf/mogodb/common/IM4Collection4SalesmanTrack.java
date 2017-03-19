package org.zjjf.mogodb.common;

/**
 * 用户轨迹数据的数据集的字段
 * mongodb集合说明 SalesmanTrack。
 * @author 小武
 *
 */
public interface IM4Collection4SalesmanTrack {
	/* 	例子展示:
		{ 
		 	"_id" : ObjectId("56d8fdf0bedf643734580367"), 
		 	"id" : "2", 
		 	"groupid" : "1", 
		 	"deviceid" : "1", 
		 	"timepoint" : "9:20", 
		 	"longitude" : 123, 
		 	"latitude" : NumberLong(133), 
		 	"localtimes" : "1453870966", 
		 	"type " : "0", 
		 	"userid" : "ff8080814de7d40f014de8dfd8182bcb", 
		 	"createtime" : ISODate("2015-12-31T16:00:00Z"),  
		 	"positionname" : "深圳龙岗", 
		 	"week" : "星期二", 
		 	"devicetype" : 0 
		 }
	 */
	
	
	
	/**
	 * ObjectId Mongodb自动产生
	 */
	String c__id = "_id";
	/**
	 * 原始mysql里面的自动生成的id;
	 */
	String c_id = "id";
	/**
	 * 
	 */
	String c_groupid = "groupid";
	/**
	 * 
	 */
	String c_deviceid = "deviceid";
	/**
	 * 
	 */
	String c_timepoint = "timepoint";
	/**
	 * 
	 */
	String c_longitude = "longitude";
	/**
	 * 
	 */
	String c_latitude = "latitude";
	/**
	 * 
	 */
	String c_localtimes = "localtimes";
	/**
	 * 
	 */
	String c_type = "type";
	/**
	 * 
	 */
	String c_userid = "userid";
	/**
	 * 
	 */
	String c_createtime = "createtime";
	/**
	 * 
	 */
	String c_positionname = "positionname";
	/**
	 * 
	 */
	String c_week = "week";
	/**
	 * 
	 */
	String c_devicetype = "devicetype";
	/**
	 * 
	 */
	String c_delflag = "delflag";
//	String c_ = "";
	
}
