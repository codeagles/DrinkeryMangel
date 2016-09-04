package com.DM.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Dao {
	protected static String dbClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	protected static String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=db_drinkeryManagel";
	protected static String dbUser = "sa";
	protected static String dbPWD = "123456";
	private static Connection conn = null;

	private Dao() {
		try {
			// 加载数据库驱动
			if (conn == null) {
				Class.forName(dbClassName).newInstance();
				conn = DriverManager.getConnection(dbURL, dbUser, dbPWD);
			} else {
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 执行查询操作
	static ResultSet executeQuery(String sql) {

		try {
			if (conn == null) {
				new Dao();
			}
			return conn.createStatement().executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	// 执行修改操作
	static int executeUpdate(String sql) {
		
		try {
			if (conn == null) {
				new Dao();
			}
			return conn.createStatement().executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	// 关闭连接
	public static void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn = null;
		}
	}

}
