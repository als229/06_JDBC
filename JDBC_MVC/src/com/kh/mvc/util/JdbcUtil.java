package com.kh.mvc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {
	
	/* 
	 * JDBC API 사용 중 중복 코드가 너무 많음!
	 * 중복된 코드를 메서드로 분리하여 필요할 때 마다 '재사용'하자
	 */
	
	/* 굳이 여기에 할 필요 없음.
		private static final String URL = "jdbc:oracle:thin:@112.221.156.34:12345:XE";
		private static final String USERNAME = "KH02_KKM";
		private static final String USERPW = "KH1234";
	 */
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
			
		final String URL = "jdbc:oracle:thin:@112.221.156.34:12345:XE";
		final String USERNAME = "KH02_KKM";
		final String USERPW = "KH1234";
		
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(URL, USERNAME, USERPW);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static void close(Statement stmt) {
		try {
			if( stmt != null && !stmt.isClosed()) stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
