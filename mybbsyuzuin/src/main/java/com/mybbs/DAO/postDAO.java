package com.mybbs.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

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
	
	/* 글 쓰기 */
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
	
	/* 글 전체 보기 */
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
		Collections.reverse(allPost);	//	최신순으로 보여주기 위해 역순 정렬
		return allPost;
	}
	
	/* 글 상세 보기 */
	public postDTO selectPost(int vnum) {
		postDTO p = new postDTO();
		if(getConn()!=null) {
			try {
				String sql = "select * from bbsyuzuin where num=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, vnum);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					p.setNum(rs.getInt("num"));
					p.setName(rs.getString("name"));
					p.setPassword(rs.getString("password"));
					p.setTitle(rs.getString("title"));
					p.setContent(rs.getString("content"));
					p.setWriteDate(rs.getString("writeDate"));
					p.setHits(rs.getInt("hits"));
				}
				/* 클릭시 조회수 +1 */
				sql = "update bbsyuzuin set hits=hits+1 where num=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, vnum);
				pstmt.executeUpdate();
				p.increaseHits();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(conn!=null) conn=null;
				if(pstmt!=null) pstmt=null;
			}
		}
		return p;
	}
	/* 글 삭제 */
	public void delPost(int dnum) {
		int rs = -1;
		if(getConn()!=null) {
			try {
				String sql = "delete from bbsyuzuin where num=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, dnum);
				rs = pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(conn!=null) conn=null;
				if(pstmt!=null) pstmt=null;
			}
		}
		System.out.println(rs+"건 삭제 완료");
	}
	
	/* 글 수정 */
	public void modPost(postDTO p) {
		int rs=-1;
		if(getConn()!=null) {
			try {
				String sql = "update bbsyuzuin set title=?,name=?,content=? where num=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, p.getTitle());
				pstmt.setString(2, p.getName());
				pstmt.setString(3, p.getContent());
				pstmt.setInt(4, p.getNum());
				rs=pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(conn!=null) conn=null;
				if(pstmt!=null) pstmt=null;
			}
		}
		if(rs==1) {
			System.out.println(p.getNum()+"번 글 수정 완료");
		}
	}
}
