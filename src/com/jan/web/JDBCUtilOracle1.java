package com.jan.web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
* @author jan
* @data 2019年10月10日 上午10:54:11
*/
public class JDBCUtilOracle1 {
	
	public Connection getConn() {
		String driver = Consts.driver_orc;
		String url = Consts.url_orc;
		String username = Consts.username_orc;
		String password = Consts.password_orc;
		Connection conn = null;
		try {
			Class.forName(driver); // classLoader,加载对应驱动
			conn = (Connection) DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
		
	}
	
	public int query1() {
	    Connection conn = getConn();
	    String sql = "SELECT * from tj2_hkzyy_1218.\"OP_MEDICAL_DATA_V\"";
	    PreparedStatement pstmt;
	    int num = 0;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        int col = rs.getMetaData().getColumnCount();
	        System.out.println("============================");
	        while (rs.next()) {
	        	num++;
	        }
	        System.out.println("total = " + num);
	        System.out.println("============================");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return num;
	}

	public static void main(String[] args) {
		JDBCUtilOracle1 juo = new JDBCUtilOracle1();
		juo.query1();
	}

}
