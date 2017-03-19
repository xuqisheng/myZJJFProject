package org.zjjf.mogodb;

import static com.mongodb.client.model.Filters.eq;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.zjjf.mogodb.common.IMongoDB;
import org.zjjf.mogodb.test.ITestDefault;
import org.zjjf.mogodb.test.SalesmanMgMO;
import org.zjjf.mogodb.test.SalesmanMgSwitch;
import org.zjjf.mogodb.util.EColType;
import org.zjjf.mogodb.util.Mysql2MongoDBUtil;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.InsertOneModel;

public class MongoDBTest implements IMongoDB,ITestDefault {

	
	
	
	
	public static void main(String[] args) {
		// or, to connect to a replica set, with auto-discovery of the primary,
		// supply a seed list of members
		// MongoClient mongoClient = new MongoClient(
		// Arrays.asList(
		// new ServerAddress("localhost", 27017),
		// new ServerAddress("localhost", 37018),
		// new ServerAddress("localhost", 47019)));

		// or use a connection string
//		MongoClientURI uri = new MongoClientURI("mongodb://user1:pwd1@host1/?authSource=db1");
//		MongoClientURI uri = new MongoClientURI("mongodb://user1:pwd1@host1/?authSource=db1&authMechanism=SCRAM-SHA-1");
//		MongoClientURI uri = new MongoClientURI("mongodb://user1:pwd1@host1/?authSource=db1&authMechanism=MONGODB-CR");
		MongoClientURI connectionString = new MongoClientURI(
				"mongodb://localhost:27017,localhost:37017,localhost:47017");
		MongoClient mongoClient = new MongoClient(connectionString);

		MongoDatabase database = mongoClient.getDatabase("admin");
		MongoCollection<Document> collection = database.getCollection("zzjf");
		MongoCursor<Document> cursor = collection.find().iterator();
		try {
			while (cursor.hasNext()) {
				System.out.println(cursor.next().toJson());
			}
		} finally {
			cursor.close();
		}

		System.out.println("--------");
		Document myDoc = collection.find(eq("name", "city1000")).first();
		System.out.println(myDoc.toJson());

		MongoCollection<Document> c2 = database.getCollection("salesmanmg");
		if (null == c2) {
			database.createCollection("salesmanmg");
			c2 = database.getCollection("salesmanmg");
		}

		Document nDoc = null;
		List<Document> list = new ArrayList<Document>();
		for (int i = 0; i < 10; i++) {
			String id = String.valueOf(System.currentTimeMillis());
			nDoc = new SalesmanMgSwitch().switchBean2Mongo(getVMSalesmanMg(id));
			list.add(nDoc);
		}
//		c2.insertMany(list);
		
//		Map<String, SalesmanMg> map = initData(10);
//		MongoCredential credential = MongoCredential.createCredential("user", "database", "password".toCharArray());
//		c2.bulkWrite(getVMList(5));
//		Document d = new Document(initData(1));
		
		//使用JDBC的方式从数据库提取表，插入到mongodb里面
		insertFromMysqlDB(database, "salesmanmg_mysql", null);

	}
	
	public static void insertFromMysqlDB(MongoDatabase database,String collectionName,String querySql){
		MongoCollection<Document> c2 = database.getCollection(collectionName);
		if (null == c2) {
			database.createCollection(collectionName);
			c2 = database.getCollection(collectionName);
		}
		querySql = "select id, username, nickname, gender, password, mobile, email,  province, city, area, "
				+ "credit,   regip, lastip, status, token, url, isdelete, monthcredit,"
				+ " intensity, ismanager, ismodify, col1, col2, col3, col4, col5, col6, identitycard, positiveidentitycard, "
				+ "negativeidentitycard, address, deviceuuid, devicename, usertype, posttype, createby, updateby from salesman";//SQL语句
		
		EColType[] colType = {EColType.mysql_varchar,EColType.mysql_varchar,EColType.mysql_varchar,EColType.mysql_varchar,EColType.mysql_double,
				EColType.mysql_bigint,EColType.mysql_varchar,EColType.mysql_varchar,EColType.mysql_varchar,EColType.mysql_datetime,
				EColType.mysql_varchar,EColType.mysql_varchar,EColType.mysql_int};
		
		
		Mysql2MongoDBUtil db1 = new Mysql2MongoDBUtil(t_m_defaultIP,t_m_defaultPort,t_m_defaultDB , t_m_default_user , t_m_default_password ,  querySql,colType);
		List<Document> listDoc = db1.queryMysqlDocument();
		c2.insertMany(listDoc);
	}
	
	public static List<InsertOneModel<SalesmanMgMO>> getVMList(int size){
		List<InsertOneModel<SalesmanMgMO>> list = new ArrayList<InsertOneModel<SalesmanMgMO>>(size);
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmssZ");
		for (int i = 0; i < size; i++) {
			String id = f.format(new Date());
			list.add(new InsertOneModel<SalesmanMgMO>(getVMSalesmanMg(id)));
		}
		return list;
	}
	

	public static SalesmanMgMO getVMSalesmanMg(String id) {
		SalesmanMgMO one = new SalesmanMgMO();
		one.setId(id);
		one.setAddress("address" + id);
		one.setArea(0);
		one.setBirthday(new Date());
		one.setCity(2);
		one.setCreateBy("createBy" + id);
		one.setCreateTime(new Date());
		one.setCredit(2);
		one.setDeptId("deptId" + id);
		one.setDeptName("deptName" + id);
		one.setDeviceUUID("deviceUUID" + id);
		one.setEmail("email" + id);
		one.setGender(3);
		one.setIdentitycard("identitycard" + id);
		one.setIntensity(5);
		one.setIsDelete(2);
		one.setIsManager(true);
		one.setLastIP("lastIP" + id);
		one.setLastTime(new Date());
		one.setMobile("mobile" + id);
		one.setMonthCredit(8);
		one.setNegativeidentitycard("negativeidentitycard" + id);
		one.setNickName("nickName" + id);
		one.setOldDeptId("oldDeptId" + id);
		one.setPassword("password" + id);
		one.setPositiveidentitycard("positiveidentitycard" + id);
		one.setPostName("postName" + id);
		one.setPostType(6);
		one.setProvince(8);
		one.setRegDays(8);
		one.setRegIP("regIP" + id);
		one.setRegistration(new Date());
		one.setRegTime(new Date());
		one.setToken("token" + id);
		one.setUpdateBy("updateBy" + id);
		one.setUpdateTime(new Date());
		one.setUrl("url" + id);
		one.setUserName("userName" + id);
		one.setUserType(8);
		return one;
	}

	public static Map<String, SalesmanMgMO> initData(int size) {
		Map<String, SalesmanMgMO> map = new HashMap<String, SalesmanMgMO>();
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmssZ");
		for (int i = 0; i < size; i++) {
			String id = f.format(new Date());
			map.put(id, getVMSalesmanMg(id));
		}

		return map;
	}
}
