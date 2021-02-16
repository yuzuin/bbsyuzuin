package com.mybbs.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class postDAO {
	private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private final String DB_URL = "jdbc:mysql://localhost/yz?&serverTimezone=UTC";
	private final String USER_NAME = "root";
	private final String PASSWORD = "1111";

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	

	postDAO() {
		try {
			Class.forName(JDBC_DRIVER);
			System.out.println("연결시도");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	Connection getConn() {
		try {
			conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			System.out.println("getConn완");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	
}
