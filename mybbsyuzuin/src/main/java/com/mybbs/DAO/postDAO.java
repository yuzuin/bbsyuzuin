package com.mybbs.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mybbs.DTO.postDTO;


public class postDAO {
	private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private final String DB_URL = "jdbc:mysql://localhost/yz?&serverTimezone=UTC";
	private final String USER_NAME = "root";
	private final String PASSWORD = "1111";

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	

	public postDAO() {
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
	
	public void insertPost(postDTO p) {
		int rs=-1;
		if(getConn()!=null) {
			try {
				String sql = "insert into bbsyuzuin(name,password,title,content) values(?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, p.getName());
				pstmt.setString(2, p.getPassword());
				pstmt.setString(3, p.getTitle());
				pstmt.setString(4, p.getContent());
				rs = pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(conn!=null) conn = null;
				if(pstmt!=null) pstmt = null;
			}
		}
		System.out.println("포스트 "+rs+"건 입력 완료");
	}
	public ArrayList<postDTO> allPost(){
		ArrayList<postDTO> allPost = null;
		if(getConn()!=null) {
			try {
				allPost = new ArrayList<>();
				String sql = "select * from bbsyuzuin";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				
				while(rs.next()) {
					postDTO p = new postDTO();
					p.setNum(rs.getInt("num"));
					p.setName(rs.getString("name"));
					p.setPassword(rs.getString("password"));
					p.setTitle(rs.getString("title"));
					p.setWriteDate(rs.getString("writeDate"));
					p.setHits(rs.getInt("hits"));
					
					allPost.add(p);
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				if(conn!=null) conn=null;
				if(pstmt!=null) pstmt=null;
			}
		}
		System.out.println("select allPost 완료");
		return allPost;
	}
}
