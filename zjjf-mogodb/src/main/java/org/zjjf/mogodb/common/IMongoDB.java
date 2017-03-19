package org.zjjf.mogodb.common;

/**
 * 常量标示用于规范变了名称和全局使用
 * 
 * @author 小武
 *
 */
public interface IMongoDB {
	

	/**
	 * mongodb 集合的前缀 "m"
	 */
	String m_prefix4Collection = "m";
	/**
	 * mongodb 默认的数据库的名称
	 */
	String m_default_database = "admin";

	/**
	 * mongodb 用户轨迹表 m_tbl_track_record_t
	 */
	String m_c_tbl_track_record_t = "m_tbl_track_record_t";
	/**
	 * mongodb里面用户轨迹历史表 m_tbl_track_record_history_t
	 */
	String m_c_tbl_track_record_history_t = "m_tbl_track_record_history_t";
	/**
	 * mongodb 里面销售人员的表  m_c_salesman
	 */
	String m_c_salesman = "m_c_salesman";
//	/**
//	 * mongodb 集合 m_tbl_track_record_history_t 的列名  userId
//	 */
//	String m_c_tbl_track_record_history_t_COLNAME_userId ="userid";
//	String m_c_tbl_track_record_history_t_COLNAME_createtime ="createtime";
	/**
	 * 查询的时候倒叙
	 */
	int m_query_sort_desc=-1;
	/**
	 * 查询的时候正序
	 */
	int m_query_sort_asc=1;
	
	/***********************************************************************************/
	/**
	 * mongodb资源配置文件
	 */
	String m_c_filename = "mongodb.properties";
	String m_c_model_mSingleModel = "mSingleModel";
	String m_c_model_mReplicaSetModel = "mReplicaSetModel";
	String m_c_model = "model";
	String m_c_mSingleIp = "mSingleIp";
	String m_c_mSinglePort = "mSinglePort";
	String m_c_mReplicaSetMainIP = "mReplicaSetMainIP";
	String m_c_mReplicaSetMainPort = "mReplicaSetMainPort";
	String m_c_mReplicaSetNodeIpS = "mReplicaSetNodeIpS";
	String m_c_mReplicaSetNodePortS = "mReplicaSetNodePortS";
	String m_c_mMongoDBName = "mMongoDBName";
	String m_c_mRSN_split=",";
	/************************************************************************************/
}
