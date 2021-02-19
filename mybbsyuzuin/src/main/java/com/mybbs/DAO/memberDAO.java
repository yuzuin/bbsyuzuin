package com.mybbs.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mybbs.DTO.memberDTO;
import com.mybbs.DTO.postDTO;


public class memberDAO {
	private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private final String DB_URL = "jdbc:mysql://localhost/yz?&serverTimezone=UTC";
	private final String USER_NAME = "root";
	private final String PASSWORD = "1111";

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	

	public memberDAO() {
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
	
	/* 로그인 */
	public int login(memberDTO m) {
		if(getConn()!=null) {
			try {
				System.out.println(m.getId()+m.getPassword());
				String sql = "select * from member where id=? and password=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, m.getId());
				pstmt.setString(2, m.getPassword());
				rs = pstmt.executeQuery();
				if(rs.next()) {
					System.out.println("로그인 성공");
					return 1;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(conn!=null) conn = null;
				if(pstmt!=null) pstmt = null;
			}
		}
		System.out.println("로그인 실패");
		return 0;
	}
}
