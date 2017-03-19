package org.zjjf.mogodb.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zjjf.mogodb.common.IM4Collection4SalesmanTrack;
import org.zjjf.mogodb.common.IMongoDB;

import com.alibaba.fastjson.JSON;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.QueryOperators;
import com.mongodb.client.FindIterable;
import com.mongodb.client.ListIndexesIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndDeleteOptions;

/**
 * MongoDB的操作类 该Util使用单例模式，获取单一对象。 默认连接的是localhost：27017的数据库。 数据库默认是admin。
 * 
 * @author Administrator
 *
 */
public class MongodbUtil implements IMongoDB {

	private static final Logger _logger = LoggerFactory.getLogger(MongodbUtil.class);

	private MongoClientURI connectionString = null;
	private MongodbConfig cfg = null;
	private static MongodbUtil util = new MongodbUtil();

	private MongodbUtil() {
		init();
//		connectionString = new MongoClientURI("mongodb://localhost:27017");
		if(m_c_model_mReplicaSetModel.equalsIgnoreCase(cfg.getModel())){
			StringBuffer url = new StringBuffer("mongodb://");
			for(int i=0;i<cfg.getmRSNodeIpS().length;i++){
				if(i != (cfg.getmRSNodeIpS().length -1)){
					url.append(cfg.getmRSNodeIpS()[i].trim()).append(":").append(cfg.getmRSNodePortS()[i].trim()).append(",");
				}else{
					url.append(cfg.getmRSNodeIpS()[i].trim()).append(":").append(cfg.getmRSNodePortS()[i].trim());
				}
			}
			connectionString = new MongoClientURI(url.toString());
		}else if(m_c_model_mSingleModel.equalsIgnoreCase(cfg.getModel())){
			connectionString = new MongoClientURI("mongodb://"+cfg.getmSingleIp()+":"+cfg.getmSinglePort());
		}else{
			connectionString = new MongoClientURI("mongodb://localhost:27017");
		}
	}

	/**
	 * 单例
	 * @return
	 */
	public static synchronized MongodbUtil getInstance() {
		return util;
	}

	/**
	 * 根据名称获取Mongodb的数据库对象
	 * 
	 * @param dataname
	 * @return
	 */
	public MongoDatabase getMongoDatabase(String dataname) {
		MongoClient mongoClient = new MongoClient(connectionString);
		MongoDatabase database = mongoClient.getDatabase(dataname);
		return database;
	}

	/**
	 * 获取数据库的集合的所有文档
	 * 
	 * @param dataname
	 *            数据库名称
	 * @param collectionName
	 *            集合名称
	 * @return 文档集合
	 */
	public MongoCollection<Document> getCollection(String dataname, String collectionName) {
		MongoCollection<Document> collection = getMongoDatabase(dataname).getCollection(collectionName);
		if (null == collection) {
			getMongoDatabase(dataname).createCollection(collectionName);
			collection = getMongoDatabase(dataname).getCollection(collectionName);
		}
		return collection;
	}

	/**
	 * 向MongoDB数据库里面添加一个文档列表。
	 * 
	 * @param dataname
	 *            数据名称
	 * @param collectionName
	 *            表名称
	 * @param listDoc
	 *            文档列表
	 * @return
	 */
	public boolean insertDocument(String dataname, String collectionName, List<Document> listDoc) {
		getCollection(dataname, collectionName).insertMany(listDoc);
		return true;
	}

	/**
	 * 向MongoDB数据库里面添加一个文档。
	 * 
	 * @param dataname
	 *            数据库名称
	 * @param collectionName
	 *            表名称
	 * @param doc
	 *            文档
	 * @return
	 */
	public boolean insertDocument(String dataname, String collectionName, Document doc) {
		getCollection(dataname, collectionName).insertOne(doc);
		return true;
	}


	private void init() {
		_logger.info("#############################加载配置信息###########################");
		cfg = new MongodbConfig();
		URL u = Thread.currentThread().getContextClassLoader().getResource(m_c_filename);
		Properties p = new Properties();
		String filepath = u.getPath();
		_logger.info("mongodb.property file path: \n" + filepath);

		FileInputStream fis = null;
		try {
			fis = new FileInputStream(filepath);
			p.load(fis);
			cfg.setModel(p.getProperty(m_c_model));
			cfg.setmReplicaSetMainIP(p.getProperty(m_c_mReplicaSetMainIP));
			cfg.setmReplicaSetMainPort(p.getProperty(m_c_mReplicaSetMainPort));
			cfg.setmReplicaSetNodeIpS(p.getProperty(m_c_mReplicaSetNodeIpS));
			cfg.setmReplicaSetNodePortS(p.getProperty(m_c_mReplicaSetNodePortS));
			if (cfg.getModel().equals(m_c_model_mReplicaSetModel)) {
				cfg.setmRSNodeIpS(cfg.getmReplicaSetMainIP().split(m_c_mRSN_split));
				cfg.setmRSNodePortS(cfg.getmReplicaSetMainPort().split(m_c_mRSN_split));
			}
			cfg.setmSingleIp(p.getProperty(m_c_mSingleIp));
			cfg.setmSinglePort(p.getProperty(m_c_mSinglePort));
			cfg.setmMongoDBName(p.getProperty(m_c_mMongoDBName));
			
			_logger.info("#############################\n" + JSON.toJSONString(cfg));
			_logger.info("#############################加载配置信息完成###########################");
		} catch (IOException e) {
			System.out.println("加载"+m_c_filename+"文件失败，文件不存在后者路径不正确！ ");
			e.printStackTrace();
		}
	}
	
	public MongodbConfig getMongodbConfig(){
		return cfg;
	}
	
	public String getTrackDBName(){
		return cfg.getmMongoDBName();
	}
	
	public MongoCollection<Document> getTrackCollection() {
		MongoCollection<Document> collection = getMongoDatabase(cfg.getmMongoDBName()).getCollection(m_c_tbl_track_record_history_t);
		if (null == collection) {
			getMongoDatabase(cfg.getmMongoDBName()).createCollection(m_c_tbl_track_record_history_t);
			collection = getMongoDatabase(cfg.getmMongoDBName()).getCollection(m_c_tbl_track_record_history_t);
		}
		return collection;
	}

}
