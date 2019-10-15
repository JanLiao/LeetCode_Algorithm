package com.jan.web;
/**
* @author jan
* @data 2019年10月9日 下午8:29:43
*/
public class Consts {
	
	public static String url = "jdbc:postgresql://localhost:6666/pgsqltest";
	
	public static String driver = "org.postgresql.Driver";
	
	public static String driver_orc = "oracle.jdbc.OracleDriver";
	
	public static String url_orc = "jdbc:oracle:thin:@dm02-scan.gz.cvte.cn:1521/pdb_tjxt";
	
	public static String username_orc = "guest_yjy";
	
	public static String password_orc = "123654";

	public static String username = "root";
	
	public static String password = "123456";
	
	// 2小时(毫秒)
	public static int SESSION_TIME = 7200000;
}
