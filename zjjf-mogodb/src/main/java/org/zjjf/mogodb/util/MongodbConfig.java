package org.zjjf.mogodb.util;

import java.util.List;
/**
 * mongodb.properties的配置信息Bean
 * @author Administrator
 *
 */
public class MongodbConfig {

	// #url="mongodb://localhost:27017,localhost:37017,localhost:47017"
	// #########################################################################
	// #mongodb模式 mSingleModel，mReplicaSetModel
	private String model = "mSingleDBModel";

	// #########################################################################
	// ##### mSingleModel start
	private String mSingleIp = "127.0.0.1";
	private String mSinglePort = "27017";
	// ##### mSingleModel end
	// #########################################################################

	// #########################################################################
	// ##### mReplicaSetModel start
	private String mReplicaSetMainIP = "127.0.0.1";
	private String mReplicaSetMainPort = "27017";
	// #ReplicaSetNodeIpS 子节点使用逗号分隔;
	private String mReplicaSetNodeIpS = "127.0.0.1,127.0.0.1,127.0.0.1";
	// #ReplicaSetNodePortS 子节点端口使用逗号分隔;
	private String mReplicaSetNodePortS = "37017,47017,57017";
	
	private String mMongoDBName = "admin";
	
	// ##### mReplicaSetModel end
	// #########################################################################
	private String[] mRSNodeIpS;
	private String[] mRSNodePortS;
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getmSingleIp() {
		return mSingleIp;
	}
	public void setmSingleIp(String mSingleIp) {
		this.mSingleIp = mSingleIp;
	}
	public String getmSinglePort() {
		return mSinglePort;
	}
	public void setmSinglePort(String mSinglePort) {
		this.mSinglePort = mSinglePort;
	}
	public String getmReplicaSetMainIP() {
		return mReplicaSetMainIP;
	}
	public void setmReplicaSetMainIP(String mReplicaSetMainIP) {
		this.mReplicaSetMainIP = mReplicaSetMainIP;
	}
	public String getmReplicaSetMainPort() {
		return mReplicaSetMainPort;
	}
	public void setmReplicaSetMainPort(String mReplicaSetMainPort) {
		this.mReplicaSetMainPort = mReplicaSetMainPort;
	}
	public String getmReplicaSetNodeIpS() {
		return mReplicaSetNodeIpS;
	}
	public void setmReplicaSetNodeIpS(String mReplicaSetNodeIpS) {
		this.mReplicaSetNodeIpS = mReplicaSetNodeIpS;
	}
	public String getmReplicaSetNodePortS() {
		return mReplicaSetNodePortS;
	}
	public void setmReplicaSetNodePortS(String mReplicaSetNodePortS) {
		this.mReplicaSetNodePortS = mReplicaSetNodePortS;
	}
	public String[] getmRSNodeIpS() {
		return mRSNodeIpS;
	}
	public void setmRSNodeIpS(String[] mRSNodeIpS) {
		this.mRSNodeIpS = mRSNodeIpS;
	}
	public String[] getmRSNodePortS() {
		return mRSNodePortS;
	}
	public void setmRSNodePortS(String[] mRSNodePortS) {
		this.mRSNodePortS = mRSNodePortS;
	}
	public String getmMongoDBName() {
		return mMongoDBName;
	}
	public void setmMongoDBName(String mMongoDBName) {
		this.mMongoDBName = mMongoDBName;
	}
	
	
}
