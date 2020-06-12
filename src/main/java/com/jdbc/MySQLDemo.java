package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.commons.LogManagement;

public class MySQLDemo implements Runnable {

	// JDBC 驱动名及数据库 URL
	static final String	JDBC_DRIVER	= "com.mysql.jdbc.Driver";
	static final String	DB_URL		= "jdbc:mysql://localhost:3306/bus";

	// 数据库的用户名与密码，需要根据自己的设置
	static final String	USER		= "root";
	static final String	PASS		= "123456";

	@Override
	public void run() {
		Connection conn = null;
		Statement stmt = null;
		try {
			// 注册 JDBC 驱动
			Class.forName("com.mysql.jdbc.Driver");

			// 打开链接
			LogManagement.Info("连接数据库...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// 执行查询
			LogManagement.Info(" 实例化Statement对象...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT id, busname, price FROM limitforecast";
			ResultSet rs = stmt.executeQuery(sql);

			// 展开结果集数据库
			while (rs.next()) {
				// 通过字段检索
				int id = rs.getInt("id");
				String name = rs.getString("busname");
				String url = rs.getString("price");

//				// 输出数据
//				System.out.print("ID: " + id);
//				System.out.print(", 站点名称: " + name);
//				System.out.print(", 站点 URL: " + url);
//				System.out.print("\n");
			}
			// 完成后关闭
//			rs.close();
//			stmt.close();
//			conn.close();
		} catch (SQLException se) {
			LogManagement.Error("", se, false);
			// 处理 JDBC 错误
			se.printStackTrace();
		} catch (Exception e) {
			LogManagement.Error("", e, false);
			// 处理 Class.forName 错误
			e.printStackTrace();
		} finally {
			// 关闭资源
//			try {
//				if (stmt != null)
//					stmt.close();
//			} catch (SQLException se2) {
//			}// 什么都不做
//			try {
//				if (conn != null)
//					conn.close();
//			} catch (SQLException se) {
//				LogManagement.Error("", se, false);
//				se.printStackTrace();
//			}
		}
		LogManagement.Info("Goodbye!");
	}

}