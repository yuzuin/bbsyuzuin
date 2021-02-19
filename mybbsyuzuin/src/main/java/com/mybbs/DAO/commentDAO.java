package com.mybbs.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

import com.mybbs.DTO.commentDTO;
import com.mybbs.DTO.postDTO;

public class commentDAO {
	private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private final String DB_URL = "jdbc:mysql://localhost/yz?&serverTimezone=UTC";
	private final String USER_NAME = "root";
	private final String PASSWORD = "1111";

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	

	public commentDAO() {
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
	
	/* 댓글 쓰기 */
	public void insertComment(commentDTO c) {
		int rs=-1;
		if(getConn()!=null) {
			try {
				String sql = "insert into bbsComment(name,password,content,postNum) values(?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, c.getName());
				pstmt.setString(2, c.getPassword());
				pstmt.setString(3, c.getContent());
				pstmt.setInt(4, c.getPostNum());
				rs = pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(conn!=null) conn = null;
				if(pstmt!=null) pstmt = null;
			}
		}
		System.out.println("댓글 "+rs+"건 입력 완료");
	}
	
	/* 댓글 전제 보기 */
	public ArrayList<commentDTO> allComment(int vnum){
		ArrayList<commentDTO> allComment = new ArrayList<commentDTO>();
		if(getConn()!=null) {
			try {
				String sql = "select * from bbsComment where postNum=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, vnum);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					commentDTO c = new commentDTO();
					c.setNum(rs.getInt("num"));
					c.setName(rs.getString("name"));
					c.setPassword(rs.getString("password"));
					c.setWriteDate(rs.getString("writeDate"));
					c.setContent(rs.getString("content"));
					c.setPostNum(rs.getInt("postNum"));
					
					allComment.add(c);
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				if(conn!=null) conn=null;
				if(pstmt!=null) pstmt=null;
			}
		}
		System.out.println("select 코멘트 완료");
//		Collections.reverse(allPost);	//	최신순으로 보여주기 위해 역순 정렬
		return allComment;
	}
	
	
}

