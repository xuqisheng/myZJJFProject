package org.zjjf.mogodb.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zjjf.mogodb.common.IMongoDB;

import com.alibaba.fastjson.JSON;
import com.mysql.jdbc.ResultSetMetaData;
/**
 * 
 * @author Administrator
 *
 */
public class Mysql2MongoDBUtil implements IMongoDB{

	private static final Logger _logger = LoggerFactory.getLogger(Mysql2MongoDBUtil.class);
	
	private String url = "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&amp;characterEncoding=utf-8";
	private String driverName = "com.mysql.jdbc.Driver";
	private String user = "salesman";
	private String password = "salesman2802";
	
	private Connection conn = null;
	
	private String querySql = null;
	/**
	 * 查询SQL语句对应的列的类型(把Mysql数据库数据转入MongoDB)
	 */
	private EColType[] colType = null;
	

	/**
	 * mysqlIP  port dbname三者有一个为空，就默认连接  127.0.0.1:3316/salesman
	 * @param mysqlIP 数据库IP
	 * @param port 数据库端口
	 * @param dbname 数据库名称
	 * @param user 数据库名称
	 * @param password 密码
	 * @param querySql 查询语句
	 */
	public Mysql2MongoDBUtil(String mysqlIP,String port,String dbname , String user , String password ,  String querySql,EColType[] colType) {
		if(StringUtils.isNotEmpty(mysqlIP) && StringUtils.isNotEmpty(port) && StringUtils.isNotEmpty(dbname)){
			this.url = "jdbc:mysql://"+mysqlIP+":"+port+"/"+dbname+"?useUnicode=true&amp;characterEncoding=utf-8";
		}else{
			this.url = "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&amp;characterEncoding=utf-8";
		}
		if(StringUtils.isNotEmpty(user))
			this.user = user;
		if(StringUtils.isNotEmpty(password))
			this.password = password;
		if(StringUtils.isNotEmpty(querySql))
			this.querySql = querySql;
		if(null != colType){
			this.colType = colType;
		}
	}
	
	public Mysql2MongoDBUtil(String mysqlIP,String port,String dbname , String user , String password ) {
		if(StringUtils.isNotEmpty(mysqlIP) && StringUtils.isNotEmpty(port) && StringUtils.isNotEmpty(dbname)){
			this.url = "jdbc:mysql://"+mysqlIP+":"+port+"/"+dbname+"?useUnicode=true&amp;characterEncoding=utf-8";
		}else{
			this.url = "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&amp;characterEncoding=utf-8";
		}
		if(StringUtils.isNotEmpty(user))
			this.user = user;
		if(StringUtils.isNotEmpty(password))
			this.password = password;
	}
	
	/**
	 * 获取数据库连接
	 * @return
	 */
	public Connection getConnection(){
		try {
			Class.forName(driverName);
			return DriverManager.getConnection(this.url, this.user, this.password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	public void close() {
		try {
			this.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Document> queryMysqlDocument(String querySql,EColType[] colType) {
		if(StringUtils.isNotEmpty(querySql)){
			this.querySql = querySql;
		}else{
			return null;
		}
		if(null != colType){
			this.colType = colType;
		}else{
			return null;
		}
		return queryMysqlDocument();
	}
	

	/**
	 * 使用SQL语句把mysql里面的所有数据。
	 * @return MongoDB的文档对象
	 */
	public List<Document> queryMysqlDocument() {
		return queryDocument(this.querySql);
	}
	
	private List<Document> queryDocument(String querySql){
		List<Document> listDoc = new ArrayList<Document>();
		Connection con = null;
		ResultSet rs  = null;
		try {
			con = this.getConnection();
			con.setAutoCommit(false);
			rs = con.prepareStatement(querySql).executeQuery();
			ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
			int count=rsmd.getColumnCount();
			List<String> dbColName = new ArrayList<String>(count);
			for(int i=1;i<=count;i++){
				dbColName.add(rsmd.getColumnName(i).toLowerCase());
			}
			
			while (rs.next()) {
				Document d = new Document();
				for(int i=0;i<count;i++){
					d.append(dbColName.get(i), getDBColVal(i+1,rs));
				}
				_logger.debug(JSON.toJSONString(d));
				listDoc.add(d);
			}
			con.commit();
			rs.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(null != rs){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(null != con){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return listDoc;
	
	}
	
	
	/**
	 * 使用SQL语句把mysql里面的所有数据。
	 * @return MongoDB的文档对象
	 */
	public List<Document> queryMysqlDocumentAutoType() {
		List<Document> listDoc = new ArrayList<Document>();
		Connection con = null;
		ResultSet rs  = null;
		try {
			con = this.getConnection();
			con.setAutoCommit(false);
			rs = con.prepareStatement(this.querySql).executeQuery();
			
			ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
			int count=rsmd.getColumnCount();
			List<String> dbColName = new ArrayList<String>(count);
			for(int i=1;i<=count;i++){
				dbColName.add(rsmd.getColumnName(i).toLowerCase());
			}
			
			while (rs.next()) {
				Document d = new Document();
				for(int i=0;i<count;i++){
					d.append(dbColName.get(i), getDBColVal(rs.getType(),i+1,rs));
				}
				_logger.debug(JSON.toJSONString(d));
				listDoc.add(d);
			}
			con.commit();
			rs.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(null != rs){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(null != con){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return listDoc;
	}
	
	/**
	 * 字段类型转换
	 * @param type
	 * @return
	 */
	private String columnType2String(int type){
		String stype = null;
		switch (type) {  
        case Types.BIGINT:  
        	stype = "BIGINT";
            break;  
        case Types.BOOLEAN:  
        	stype = "BIGINT";
            break;  
        case Types.DATE:  
        	stype = "DATE";
            break;  
        case Types.DOUBLE:    
        	stype = "DOUBLE";
            break;  
        case Types.FLOAT:    
        	stype = "FLOAT";
            break;  
        case Types.INTEGER:  
        	stype = "INTEGER";  
            break;  
        case Types.SMALLINT:  
        	stype = "SMALLINT";  
            break;  
        case Types.TIME:    
        	stype = "TIME";
            break;  
        case Types.TIMESTAMP:    
        	stype = "TIMESTAMP";
            break;  
        case Types.TINYINT:    
        	stype = "TINYINT";
            break;  
        case Types.VARCHAR:   
        	stype = "VARCHAR"; 
            break;  
        case Types.NCHAR:    
        	stype = "NCHAR";
            break;  
        case Types.NVARCHAR:   
        	stype = "NVARCHAR"; 
            break;  
        case Types.BIT:    
        	stype = "BIT";
            break;  
        } 
    
		return stype;
	}
	
	
	
	
	/**
	 * 查询表的字段
	 * @param tableName
	 * @return
	 */
	public List<String[]> queryTableCol(String tableName){
		ResultSet rs = null;
		Connection con = null;
		try {
			con = this.getConnection();
			con.setAutoCommit(false);
			rs = con.prepareStatement("select * from " + tableName + ";").executeQuery();
			ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
			int count=rsmd.getColumnCount();
			List<String[]> dbColName = new ArrayList<String[]>(count);
			for(int i=1;i<=count;i++){
				String[] col = new String[3];
				col[0] = rsmd.getColumnName(i).toLowerCase();
				col[1] = String.valueOf(rsmd.getColumnType(i));
				col[2] = columnType2String(rsmd.getColumnType(i));
				dbColName.add(col);
			}
			con.commit();
			rs.close();
			con.close();
			return dbColName;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(null != rs){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(null != con)
			{
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	
	}
	
	
	
	public List<Document> callProcedureSQL(String callSQL,EColType[] paramType,Object[] paramVal){
		List<Document> listDoc = new ArrayList<Document>();
		ResultSet rs = null;
		CallableStatement stm =null;
		Connection con = null;
		try {
			con = this.getConnection();
			con.setAutoCommit(false);
			stm = con.prepareCall(callSQL);
			if(null != paramType && paramType[0] != null 
					&& null != paramVal && paramVal[0] != null 
					&& paramType.length == paramVal.length)
			{
				for(int i=0;i<paramType.length;i++){
					if(paramType[i].equals(EColType.mysql_varchar)){
						stm.setString(i+1,String.valueOf(paramVal[i]));
					}else if(paramType[i].equals(EColType.mysql_bigint)){
						stm.setLong(i+1, Long.valueOf(paramVal[i].toString()));
					}else if(paramType[i].equals(EColType.mysql_int)){
						stm.setInt(i+1, Integer.valueOf(paramVal[i].toString()));
					}else if(paramType[i].equals(EColType.mysql_double)){
						stm.setDouble(i+1, Double.valueOf(paramVal[i].toString()));
					}
				}
			}
			
			rs = stm.executeQuery();
			
			ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
			int count=rsmd.getColumnCount();
			List<String> dbColName = new ArrayList<String>(count);
			for(int i=1;i<=count;i++){
				dbColName.add(rsmd.getColumnName(i).toLowerCase());
			}
			
			while (rs.next()) {
				Document d = new Document();
				for(int i=0;i<count;i++){
					d.append(dbColName.get(i), rs.getLong(i+1));
				}
				_logger.debug(JSON.toJSONString(d));
				listDoc.add(d);
			}
			con.commit();
			stm.close();
			con.close();
			return listDoc;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(null != stm){
				try {
					stm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(null != con){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	
	public boolean createTriggerSQL(String sql){

		PreparedStatement stm =null;
		Connection con = null;
		try {
			con = this.getConnection();
			con.setAutoCommit(false);
			stm = con.prepareStatement(sql);
			stm.execute(sql);
			con.commit();
			stm.close();
			con.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(null != stm){
				try {
					stm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(null != con){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	public boolean executeSQL(String sql){

		Statement stm =null;
		Connection con = null;
		try {
			con = this.getConnection();
			con.setAutoCommit(false);
			stm = con.createStatement();
			stm.execute(sql);
			con.commit();
			stm.close();
			con.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(null != stm){
				try {
					stm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(null != con){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	public boolean insertMysql(String insertSQL){
		return executeSQL(insertSQL);
	}
	
	public boolean updateMysql(String updateSQL){
		return executeSQL(updateSQL);
	}
	
	public boolean deleteMysql(String deleteSQL){
		return executeSQL(deleteSQL);
	}
	
	
	
	private Object getDBColVal(int idx,ResultSet ret) throws SQLException{
		if(colType[idx-1].equals(EColType.mysql_bigint)){
			return ret.getLong(idx);
		}else if(colType[idx-1].equals(EColType.mysql_datetime)){
			return ret.getDate(idx);
		}else if(colType[idx-1].equals(EColType.mysql_double)){
			return ret.getDouble(idx);
		}else if(colType[idx-1].equals(EColType.mysql_int)){
			return ret.getInt(idx);
		}else if(colType[idx-1].equals(EColType.mysql_varchar)){
			return ret.getString(idx);
		}else{
			return ret.getString(idx);
		}
	}
	
	private Object getDBColVal(int type,int idx,ResultSet ret) throws SQLException{
		switch (type) {  
        case Types.BIGINT:  
            return ret.getLong(idx);  
        case Types.BOOLEAN:  
        	return ret.getBoolean(idx);  
        case Types.DATE:  
        	return ret.getDate(idx);  
        case Types.DOUBLE:  
        	return ret.getDouble(idx);  
        case Types.FLOAT:  
        	return ret.getFloat(idx);  
        case Types.INTEGER:  
        	return ret.getInt(idx);  
        case Types.SMALLINT:  
        	return ret.getInt(idx);  
        case Types.TIME:  
        	return ret.getTime(idx);  
        case Types.TIMESTAMP:  
        	return ret.getTimestamp(idx);  
        case Types.TINYINT:  
        	return ret.getShort(idx);  
        case Types.VARCHAR:  
        	return ret.getString(idx);  
        case Types.NCHAR:  
        	return ret.getString(idx);  
        case Types.NVARCHAR:  
        	return ret.getString(idx);  
        case Types.BIT:  
        	return ret.getByte(idx);  
        }  
		return null;
	}
	
	
}
