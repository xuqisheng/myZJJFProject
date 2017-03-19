package org.zjjf.mogodb;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;
import org.zjjf.mogodb.common.IMongoDB;
import org.zjjf.mogodb.model.TrackRecordDo;
import org.zjjf.mogodb.test.ITestDefault;
import org.zjjf.mogodb.util.EColType;
import org.zjjf.mogodb.util.MPageResult;
import org.zjjf.mogodb.util.MongoTrackhistoryUtil;
import org.zjjf.mogodb.util.MongodbUtil;
import org.zjjf.mogodb.util.Mysql2MongoDBUtil;

import com.alibaba.fastjson.JSON;
import com.corner.core.beans.User;
import com.corner.core.pay.util.DateUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.QueryOperators;
import com.mongodb.client.MongoCollection;

public class TestMongoDBUtil implements IMongoDB,ITestDefault{


	/**
	 * 把mysql数据里面的tbl_track_record_t的数据，插入到Mongodb的 m_c_tbl_track_record_t集合里面。
	 */
	public static void testInsert_tbl_track_record_t(){
		String sql = "SELECT"
				+ " t.`id`,t.`groupid`,t.`deviceid`,t.`timepoint`,t.`longitude`,"
				+ "t.`latitude`,t.`localtimes`,t.`type`,t.`userId`,t.`createTime`,"
				+ "t.`positionName`,t.`week`,t.`deviceType` "
				+ " FROM tbl_track_record_t t;"; 
		EColType[] colType = {EColType.mysql_varchar,EColType.mysql_varchar,EColType.mysql_varchar,EColType.mysql_varchar,EColType.mysql_double,
				EColType.mysql_bigint,EColType.mysql_varchar,EColType.mysql_varchar,EColType.mysql_varchar,EColType.mysql_datetime,
				EColType.mysql_varchar,EColType.mysql_varchar,EColType.mysql_int};
		
		Mysql2MongoDBUtil util = new Mysql2MongoDBUtil("192.168.1.11","3316","salesman",t_m_default_user,t_m_default_password,sql,colType); 
		List<Document> listDoc = util.queryMysqlDocument();
		MongodbUtil.getInstance().insertDocument(m_default_database, m_c_tbl_track_record_t, listDoc);
	}
	/**
	 * 把mysql数据里面的tbl_track_record_history_t的数据，插入到Mongodb的 m_c_tbl_track_record_history_t集合里面。
	 */
	public static void testInsert_tbl_track_record_history_t(){
		String sql = "SELECT"
				+ " t.`id`,t.`groupid`,t.`deviceid`,t.`timepoint`,t.`longitude`,"
				+ "t.`latitude`,t.`localtimes`,t.`type`,t.`userId`,t.`createTime`,"
				+ "t.`positionName`,t.`week`,t.`deviceType` "
				+ " FROM tbl_track_record_history_t t;"; 
		EColType[] colType = {EColType.mysql_varchar,EColType.mysql_varchar,EColType.mysql_varchar,EColType.mysql_varchar,EColType.mysql_double,
				EColType.mysql_bigint,EColType.mysql_varchar,EColType.mysql_varchar,EColType.mysql_varchar,EColType.mysql_datetime,
				EColType.mysql_varchar,EColType.mysql_varchar,EColType.mysql_int};
		Mysql2MongoDBUtil util = new Mysql2MongoDBUtil("192.168.1.11","3316","salesman",t_m_default_user,t_m_default_password,sql,colType); 
		List<Document> listDoc = util.queryMysqlDocument();
		MongodbUtil.getInstance().insertDocument(m_default_database, m_c_tbl_track_record_history_t, listDoc);
	}
	
	
	public static void testInsert_salesman(){
		String querySql = "SELECT  t.`id`, t.`userName`, t.`nickName`,t.`gender`, t.`password`, t.`mobile` FROM salesman t ";//SQL语句
		EColType[] colType = {EColType.mysql_varchar,EColType.mysql_varchar,EColType.mysql_varchar,EColType.mysql_int,EColType.mysql_varchar,EColType.mysql_varchar};
		
		Mysql2MongoDBUtil util = new Mysql2MongoDBUtil(t_m_defaultIP,t_m_defaultPort,t_m_defaultDB,t_m_default_user,t_m_default_password,querySql,colType); 
		List<Document> listDoc = util.queryMysqlDocument();
		MongodbUtil.getInstance().insertDocument(m_default_database, m_c_salesman, listDoc);
	}
	
	
	
	public static void testQueryByTime(){
//		MongodbUtil.getInstance().findDocument4trackByColName("deviceid", "1");
//		MongodbUtil.getInstance().findDocument4trackBy_id("56d8054abedf64143c790ef6");
		Date startDate = DateUtil.StringToDateSimple("2015-02-18 20:34:45");
		Date endDate = DateUtil.StringToDateSimple("2016-06-18 20:34:45");
		MongoTrackhistoryUtil.findDocument4trackhistoryByTime("createtime", startDate, endDate);
	}
	
	public static void testDel(){
		MongoTrackhistoryUtil.findDocument4trackByColName("id","0bcfe47f131b46b59b60cefbbc0c7950");
		MongoTrackhistoryUtil.delDocument4trackByColName("id", "0bcfe47f131b46b59b60cefbbc0c7950");
		System.out.println("delete query............");
		MongoTrackhistoryUtil.findDocument4trackByColName("id","0bcfe47f131b46b59b60cefbbc0c7950");
	}
	
	public static void test_findPageDocument4trackhistoryBy_userId(){
		MPageResult one = MongoTrackhistoryUtil.findPageDocument4trackhistoryBy_userId("ff8080814de7d40f014de8dfd8182bcb", 1, 1);
		System.out.println(JSON.toJSONString(one));
		one = MongoTrackhistoryUtil.findPageDocument4trackhistoryBy_userId("ff8080814de7d40f014de8dfd8182bcb", 2, 1);
		System.out.println(JSON.toJSONString(one));
		one = MongoTrackhistoryUtil.findPageDocument4trackhistoryBy_userId("ff8080814de7d40f014de8dfd8182bcb", 3, 1);
		System.out.println(JSON.toJSONString(one));
		one = MongoTrackhistoryUtil.findPageDocument4trackhistoryBy_userId("ff8080814de7d40f014de8dfd8182bcb", 4, 1);
		System.out.println(JSON.toJSONString(one));
		one = MongoTrackhistoryUtil.findPageDocument4trackhistoryBy_userId("ff8080814de7d40f014de8dfd8182bcb", 5, 1);
		System.out.println(JSON.toJSONString(one));
	}
	
	public static void test_findUserOneMonthTrackhistoryByPage(){
		MPageResult one = MongoTrackhistoryUtil.findUserOneMonthTrackhistoryByPage("ff8080814de7d40f014de8dfd8182bcb", 1, 8);
		System.out.println(JSON.toJSONString(one));
	}
	
	public static void test_addTrackInfo(){
		TrackRecordDo one = new TrackRecordDo();
		one.setDeviceId("deviceId");
		one.setDeviceType(99);
		one.setGroupId("groupId");
		one.setId("ssss");
		one.setLatitude(88.9);
		one.setLocaltimes("localtimes");
		one.setLongitude(88.88);
		one.setPositionName("positionName");
		one.setTimePoint("timePoint");
		one.setType(99);
		one.setWeek("week");
		MongoTrackhistoryUtil.addTrackInfo(one);
		//测试本地库，未做索引，该条数据有多条。
		MongoTrackhistoryUtil.findDocument4trackByColName("id", "ssss");
	}
	
	public static void test_updateDocument4TrackById(){
		MongoTrackhistoryUtil.findDocument4trackByColName("id", "dd00fd1045334267a0fc463d5c610a01");
		MongoTrackhistoryUtil.updateDocument4TrackById("dd00fd1045334267a0fc463d5c610a01", "type", "33");
		MongoTrackhistoryUtil.findDocument4trackByColName("id", "dd00fd1045334267a0fc463d5c610a01");
	}
	
	public static void test_findUserAllTrackhistoryByPageLikeSome(){
		MongoTrackhistoryUtil.findUserAllTrackhistoryByPageLikeSome("ff8080814de7d40f014de8dfd8182bcb", 2, 1);
	}
	
	
	public static void testMycatTalbleDataSelect(){
		String sql = "select t.t_name,t.t_val from t_test100 t;"; 
		EColType[] colType = {EColType.mysql_varchar,EColType.mysql_varchar};
		
		Mysql2MongoDBUtil util = new Mysql2MongoDBUtil("192.168.1.11","8066","corner","mibo","mibo2802",sql,colType); 
		List<Document> listDoc = util.queryMysqlDocument();
		System.out.println(JSON.toJSONString(listDoc));
//		MongodbUtil.getInstance().insertDocument(m_default_database, m_c_tbl_track_record_t, listDoc);
	}
	
	public static void testMycatTalbleDataSelectMaster(){
		String sql = "/*!mycat:db_type=master*/ select t.t_name,t.t_val from t_test100 t;"; 
		EColType[] colType = {EColType.mysql_varchar,EColType.mysql_varchar};
		
		Mysql2MongoDBUtil util = new Mysql2MongoDBUtil("192.168.1.11","8066","corner","mibo","mibo2802",sql,colType); 
		List<Document> listDoc = util.queryMysqlDocument();
		System.out.println(JSON.toJSONString(listDoc));
//		MongodbUtil.getInstance().insertDocument(m_default_database, m_c_tbl_track_record_t, listDoc);
	}
	
	
	public static void testMycatTalbleDataInsert(){
		String sql = "select t.t_name,t.t_val from t_test100 t;"; 
		EColType[] colType = {EColType.mysql_varchar,EColType.mysql_varchar};
		
		Mysql2MongoDBUtil util = new Mysql2MongoDBUtil("192.168.1.11","8066","corner","mibo","mibo2802"); 
		List<Document> listDoc = util.queryMysqlDocument(sql,colType);
		System.out.println(JSON.toJSONString(listDoc));
		String insertSQL = "insert into t_test100 (t_name,t_val)  value ('java4jdbc t_name01','java4jdbc t_val01')";
		util.insertMysql(insertSQL);
		System.out.println("----------------------------------------------");
		listDoc = util.queryMysqlDocument(sql,colType);
		System.out.println(JSON.toJSONString(listDoc));
	}
	
	
	public static void testMycatTalbleDataUpdate(){
		String querySQL = "select t.t_name,t.t_val from t_test100 t where t.t_name='java4jdbcupdate';"; 
		String insertSQL = "insert into t_test100 (t_name,t_val)  value ('java4jdbcupdate','java4jdbcupdate old Value')";
		String updateSQL = "update t_test100 t set t.t_val = 'java4jdbcupdate new Value' where t.t_name='java4jdbcupdate'";
		EColType[] colType = {EColType.mysql_varchar,EColType.mysql_varchar};
		
		Mysql2MongoDBUtil util = null;
		List<Document> listDoc = null;
		boolean flag = false;
		util = new Mysql2MongoDBUtil("192.168.1.11","8066","corner","mibo","mibo2802"); 
		
		flag = util.insertMysql(insertSQL);
		System.out.println("----------------------------------------------" + flag);
		listDoc = util.queryMysqlDocument(querySQL,colType);
		System.out.println(JSON.toJSONString(listDoc));
		
		flag = util.updateMysql(updateSQL);
		System.out.println("----------------------------------------------" + flag);
		listDoc = util.queryMysqlDocument(querySQL,colType);
		System.out.println(JSON.toJSONString(listDoc));
		
	}
	
	
	public static void testMycatTalbleDataDelete(){
		String querySQL = "select t.t_name,t.t_val from t_test100 t where t.t_name='java4jdbcupdate';"; 
		String deleteSQL = "delete from t_test100 where t_name='java4jdbcupdate';";
		EColType[] colType = {EColType.mysql_varchar,EColType.mysql_varchar};
		
		Mysql2MongoDBUtil util = null;
		List<Document> listDoc = null;
		boolean flag = false;
		util = new Mysql2MongoDBUtil("192.168.1.11","8066","corner","mibo","mibo2802"); 
		
		listDoc = util.queryMysqlDocument(querySQL,colType);
		System.out.println(JSON.toJSONString(listDoc));
		
		flag = util.deleteMysql(deleteSQL);
		System.out.println("----------------------------------------------" + flag);
		listDoc = util.queryMysqlDocument(querySQL,colType);
		System.out.println(JSON.toJSONString(listDoc));
		
	}
	
	
	
	
	public static void testMycatProcedureCreate(){
		String createProduct = 
		"create procedure pro_add_jdbc" + 
		"(" + 
		"   a int," + 
		"   b int" + 
		")" + 
		"begin" + 
		"   declare c int;" + 
		"   if a is null then" + 
		"      set a = 0;" + 
		"   end if;" + 
		"   if b is null then" + 
		"      set b = 0;" + 
		"   end if;" + 
		"   set c = a + b;" + 
		"   select c as sum;" + 
		"end;";
		
		
		
		Mysql2MongoDBUtil util = null;
		boolean flag = false;
		util = new Mysql2MongoDBUtil("192.168.1.11","8066","corner","mibo","mibo2802"); 
		flag = util.executeSQL(createProduct);
		System.out.println(flag);
	}
	
	public static void testMycatProcedureCall(){
		String callPro = "call pro_add_jdbc(?, ?);";
		EColType[] colType = {EColType.mysql_varchar,EColType.mysql_varchar};
		Object[] objs = new Object[]{10,20};
		
		Mysql2MongoDBUtil util = null;
		util = new Mysql2MongoDBUtil("192.168.1.11","8066","corner","mibo","mibo2802"); 
		List<Document> listDoc = null;
		listDoc = util.callProcedureSQL(callPro,colType,objs);
		System.out.println(JSON.toJSONString(listDoc));
		
	}
	
	public static void testMycatProcedureDrop(){
		System.out.println("a = 10;b=20;");
		System.out.println("修改以前调用 a+b;");
		testMycatProcedureCall();
		// 修改前：a+b ; 修改后 a+b+a+b ;
		String dropProduce = "DROP PROCEDURE IF EXISTS pro_add_jdbc;";
		String recreateProduce = 
		"CREATE PROCEDURE pro_add_jdbc (IN a INT, IN b INT) " +
		"begin   declare c int;   if a is null then      set a = 0;   end if;   if b is null then      set b = 0;   end if;   set c = a + b + a +b;   select c as sum;end;";
		Mysql2MongoDBUtil util = null;
		boolean flag = false;
		util = new Mysql2MongoDBUtil("192.168.1.11","8066","corner","mibo","mibo2802"); 
		flag = util.executeSQL(dropProduce);
		System.out.println("删除存储过程："+flag);
		flag = util.executeSQL(recreateProduce);
		System.out.println("重建存储过程："+flag);
		System.out.println("修改后调用 a+b+a+b;");
		testMycatProcedureCall();
		
	}
	
	
	public static void testMycatTableCreate(){
		String creatTableSQL = "create table t_test100_java(t_name varchar(100),  t_val  varchar(200) );";
		Mysql2MongoDBUtil util = null;
		boolean flag = false;
		util = new Mysql2MongoDBUtil("192.168.1.11","8066","corner","mibo","mibo2802"); 
		flag = util.executeSQL(creatTableSQL);
		System.out.println("java jdbc 创建表："+flag);
	}
		

	public static void testMycatTableAlterAddCol(){
		String alterTableAddCol = "ALTER TABLE t_test100_java ADD COLUMN  t_java_remark varchar(3000) comment '备注信息';";
		Mysql2MongoDBUtil util = null;
		List<String[]> listDoc = null;
		util = new Mysql2MongoDBUtil("192.168.1.11","8066","corner","mibo","mibo2802"); 
		listDoc = util.queryTableCol("t_test100_java");
		System.out.println(JSON.toJSONString(listDoc));
		System.out.println("--------------------------------");
		util.executeSQL(alterTableAddCol);
		listDoc = util.queryTableCol("t_test100_java");
		System.out.println(JSON.toJSONString(listDoc));
	}
	
	public static void testMycatTableAlterUpdateCol(){
		String alterTableChangeCol = "ALTER TABLE t_test100_java CHANGE t_java_remark t_java_createtime datetime DEFAULT CURRENT_TIMESTAMP NULL COMMENT '创建时间';";
		Mysql2MongoDBUtil util = null;
		List<String[]> listDoc = null;
		util = new Mysql2MongoDBUtil("192.168.1.11","8066","corner","mibo","mibo2802"); 
		listDoc = util.queryTableCol("t_test100_java");
		System.out.println(JSON.toJSONString(listDoc));
		System.out.println("--------------------------------");
		util.executeSQL(alterTableChangeCol);
		listDoc = util.queryTableCol("t_test100_java");
		System.out.println(JSON.toJSONString(listDoc));
	}
	
	public static void testMycatTableAlterDropCol(){
		String alterTableDropCol = "ALTER TABLE t_test100_java DROP COLUMN t_java_createtime;";
		Mysql2MongoDBUtil util = null;
		List<String[]> listDoc = null;
		util = new Mysql2MongoDBUtil("192.168.1.11","8066","corner","mibo","mibo2802"); 
		listDoc = util.queryTableCol("t_test100_java");
		System.out.println(JSON.toJSONString(listDoc));
		System.out.println("--------------------------------");
		util.executeSQL(alterTableDropCol);
		listDoc = util.queryTableCol("t_test100_java");
		System.out.println(JSON.toJSONString(listDoc));
	}
	
	public static void testMycatTableDrop(){
		String dropTableSQL = "drop table t_test100_java;";
		Mysql2MongoDBUtil util = null;
		boolean flag = false;
		util = new Mysql2MongoDBUtil("192.168.1.11","8066","corner","mibo","mibo2802"); 
		flag = util.executeSQL(dropTableSQL);
		System.out.println("java jdbc 删除表："+flag);
	}
	
	
	
	
	public static void testMycatTriggerCreate(){
		
		String createMtable = "create table t_test_m1_java (t_m_c01 varchar(200),t_m_c02 varchar(200));";
		String craeteStable = "create table t_test_s1_java (t_s_c01 varchar(200),t_s_c02 varchar(200),t_s_createtime datetime DEFAULT CURRENT_TIMESTAMP NULL COMMENT '创建时间');";
		String craeteStable2 = "create table t_test_s2_java (t_s2_c01 varchar(200),t_s2_c02 varchar(200),t_s2_createtime datetime DEFAULT CURRENT_TIMESTAMP NULL COMMENT '创建时间');";
		
		String createTrigger =
		"create trigger trig_t_test_m1_insert_java " + 
		"after insert on t_test_m1_java " +
		"for each row " +
		"begin " +
		"	insert into t_test_s1_java (t_s_c01,t_s_c02) value (new.t_m_c01,new.t_m_c02);" +
		"end ;"; 
		
		
		Mysql2MongoDBUtil util = null;
		
		boolean flag = false;
		util = new Mysql2MongoDBUtil("192.168.1.11","8066","corner","mibo","mibo2802"); 
		flag = util.executeSQL(createMtable);
		System.out.println("java jdbc 创建主表(t_test_m1_java)："+flag);
		flag = util.executeSQL(craeteStable);
		System.out.println("java jdbc 创建子表(t_test_s1_java)："+flag);
		flag = util.executeSQL(craeteStable2);
		System.out.println("java jdbc 创建子表(t_test_s2_java)："+flag);
		flag = util.createTriggerSQL(createTrigger);
		System.out.println("java jdbc 创建触发器(trig_t_test_m1_insert_java)："+flag);
		
		
	}
	
	
	public static void testMycatTriggerRun(){
		String insertSQL  = "insert into t_test_m1_java (t_m_c01,t_m_c02) value ('java jdbc t_m_c01','java jdbc t_m_c02');";
		String insertSQL2 = "insert into t_test_m1_java (t_m_c01,t_m_c02) value ('java jdbc t_m_c01 2222222','java jdbc t_m_c02 22222222');";
		
		EColType[] colTypeM = {EColType.mysql_varchar,EColType.mysql_varchar};
		EColType[] colTypeS = {EColType.mysql_varchar,EColType.mysql_varchar,EColType.mysql_datetime};
		String queryM = "select t_m_c01,t_m_c02 from t_test_m1_java;";
		String queryS = "select t_s_c01,t_s_c02 from t_test_s1_java;";
		String queryS2 = "select t_s2_c01,t_s2_c02 from t_test_s2_java;";
		
		List<Document> listDoc = null;
		Mysql2MongoDBUtil util = null;
		util = new Mysql2MongoDBUtil("192.168.1.11","8066","corner","mibo","mibo2802"); 
		
		System.out.println("插入主表数据，触发器向字表插入数据");
		System.out.println("--------(insert 1)--------------------------------");
		util.insertMysql(insertSQL);
		System.out.println("第一次查询数据：");
		
		listDoc = util.queryMysqlDocument(queryM, colTypeM);
		System.out.println("t_test_m1_java:\n" + JSON.toJSONString(listDoc));
		listDoc = util.queryMysqlDocument(queryS, colTypeS);
		System.out.println("t_test_s1_java:\n"+JSON.toJSONString(listDoc));
		listDoc = util.queryMysqlDocument(queryS2, colTypeS);
		System.out.println("t_test_s2_java:\n"+JSON.toJSONString(listDoc));
		System.out.println("--------(insert 2)--------------------------------");
		util.insertMysql(insertSQL2);
		
		System.out.println("第二次查询数据：");
		listDoc = util.queryMysqlDocument(queryM, colTypeM);
		System.out.println("t_test_m1_java:\n" + JSON.toJSONString(listDoc));
		listDoc = util.queryMysqlDocument(queryS, colTypeS);
		System.out.println("t_test_s1_java:\n"+JSON.toJSONString(listDoc));
		listDoc = util.queryMysqlDocument(queryS2, colTypeS);
		System.out.println("t_test_s2_java:\n"+JSON.toJSONString(listDoc));
		
	}
	
	public static void testMycatTriggerUpdate(){
		System.out.println("修改触发器以前，插入 t_test_m1_java 后触发向 t_test_s1_java 表中插入数据；修改后 插入 t_test_m1_java 后触发向 t_test_s2_java 表中插入数据；");
		testMycatTriggerRun();
		
		String dropTigger = "DROP trigger IF EXISTS trig_t_test_m1_insert_java;";
		String recreateTrigger =
				"create trigger trig_t_test_m1_insert_java " + 
				"after insert on t_test_m1_java " +
				"for each row " +
				"begin " +
				"	insert into t_test_s2_java (t_s2_c01,t_s2_c02) value (new.t_m_c01,new.t_m_c02);" +
				"end ;"; 
		
		Mysql2MongoDBUtil util = null;
		boolean flag = false;
		util = new Mysql2MongoDBUtil("192.168.1.11","8066","corner","mibo","mibo2802"); 
		flag = util.executeSQL(dropTigger);
		System.out.println("删除触发器过程："+flag);
		flag = util.createTriggerSQL(recreateTrigger);
		System.out.println("java jdbc 重新创建触发器(trig_t_test_m1_insert_java)："+flag);

		testMycatTriggerRun();
	}
	
	
	
	
	
	
	public static void main(String[] args) {
//		insert_tbl_track_record_t();
//		testInsert_tbl_track_record_history_t();
//		testQueryByTime();
//		testInsert_salesman();
//		test_findPageDocument4trackhistoryBy_userId();
//		test_findUserOneMonthTrackhistoryByPage();
//		test_updateDocument4TrackById();
//		test_addTrackInfo();
//		test_findUserAllTrackhistoryByPageLikeSome();
//		testMycatSelect();
//		testMycatUpdate();
		//数据表操作，创建表，添加字段，修改字段，删除字段，删除表。
//		testMycatTableCreate();
//		testMycatTableAlterAddCol();
//		testMycatTableAlterUpdateCol();
//		testMycatTableAlterDropCol();
//		testMycatTableDrop();
////		存储过程
//		testMycatProcedureCreate();
//		testMycatProcedureCall();
//		testMycatProcedureDrop();
//		
//		//触发器
//		testMycatTriggerCreate();
//		testMycatTriggerRun();
		testMycatTriggerUpdate();
	}
	
	
	public void queryOperators() {
		// $or (查询id等于1或者id等于2的数据)
		BasicDBObject queryObject = new BasicDBObject().append(
				QueryOperators.OR,
				new BasicDBObject[] {
						new BasicDBObject("id", 1),
						new BasicDBObject("id", 2) 
						});
		find(queryObject, "(查询id等于1或者id等于2的数据)");

		// $and(查询id等于10并且name等于10的数据)
		queryObject = new BasicDBObject().append(
				QueryOperators.AND,
				new BasicDBObject[] { 
						new BasicDBObject("id", 10),
						new BasicDBObject("name", "10") 
						});
		find(queryObject, "(查询id等于10并且name等于10的数据)");

		// $gt（查询id大于10的数据）
		queryObject = new BasicDBObject().append("id",
				new BasicDBObject().append(QueryOperators.GT, 10));
		find(queryObject, "（查询id大于10的数据）");
		
		// $gte （查询id大于等于10的数据）
		queryObject = new BasicDBObject().append("id",
				new BasicDBObject().append(QueryOperators.GTE, 11));
		find(queryObject, "（查询id大于等于11的数据）");
		
		// $lt
		queryObject = new BasicDBObject().append("id",
				new BasicDBObject().append(QueryOperators.LT, 2));
		find(queryObject, "（查询id小于2的数据）");
		
		// $lte
		queryObject = new BasicDBObject().append("id",
				new BasicDBObject().append(QueryOperators.LTE, 2));
		find(queryObject, "（查询id小于等于2的数据）");

		// $in
		queryObject = new BasicDBObject().append("id", new BasicDBObject(
				QueryOperators.IN, new int[] { 1, 2 }));
		find(queryObject, "（查询id为1和2的数据）");
		
		// $nin
		queryObject = new BasicDBObject().append("id", new BasicDBObject(
				QueryOperators.NIN, new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }));
		find(queryObject, "（查询id不为1,2,3,4,5,6,7,8,9的数据）");

		// 还有很多其他的高级查询方式可以参见QueryOperators类
	}
	
	public void find(BasicDBObject condition, String str) {
		System.out.println("================" + str + "==================");
		MongoCollection<Document> collection = MongodbUtil.getInstance().getTrackCollection();
		Iterator<Document> iter = collection.find(condition).iterator();
		while (iter.hasNext()) {
			Document doc = iter.next();
			System.out.println(JSON.toJSONString(doc));
		}

	}

}
