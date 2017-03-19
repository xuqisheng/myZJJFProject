package org.zjjf.mogodb.test;

/**
 * 测试用的常用参数
 * @author 小武
 *
 */
public interface ITestDefault {

	/**
	 * 测试用的mysql的URL
	 */
	String t_m_defalutUrl = "jdbc:mysql://192.168.1.11:3316/salesman?useUnicode=true&amp;characterEncoding=utf-8";
	/**
	 * 默认的IP 192.168.1.11
	 */
	String t_m_defaultIP = "192.168.1.11";
	/**
	 * 默认的端口 3316
	 */
	String t_m_defaultPort = "3316";
	/**
	 * 默认的数据库名称 salesman
	 */
	String t_m_defaultDB = "salesman";

	/**
	 * mysql的驱动字符串
	 */
	String t_m_driverName = "com.mysql.jdbc.Driver";
	/**
	 * mysql的用户名
	 */
	String t_m_default_user = "salesman";
	/**
	 * mysql的密码
	 */
	String t_m_default_password = "salesman2802";
}
