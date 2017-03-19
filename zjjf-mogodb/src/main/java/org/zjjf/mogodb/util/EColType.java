package org.zjjf.mogodb.util;

/**
 * 数据类型列表，主要用于把其他数据转换为Mongodb使用。
 * @author xiaowu
 *
 */
public enum EColType {

	//mysql的数据类型
	mysql_varchar, mysql_int, mysql_double, mysql_bigint, mysql_datetime,
	
	//java的数据类型
	j_byte,j_int,j_double,j_long,j_char,j_string,

	//mongodb的数据类型
	m_String,m_Double,m_Int64,m_DateTime,m_Int32
	
	
}
