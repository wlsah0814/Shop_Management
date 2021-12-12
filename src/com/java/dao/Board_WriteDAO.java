package com.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Board_WriteDAO {
	
	static String driver = "org.mariadb.jdbc.Driver";
	static String url = "jdbc:mariadb://localhost:3306/shop_management";
	static String uid = "jinmo";
	static String pwd = "1234";

	Connection con = null;
	PreparedStatement pstmt = null;

	public void Board_WriteDAO() {
		try {
			Class.forName(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void board_write(String id, String place, String title, String text) {
		String query = "insert into board(id, place, board_title, board_text) values(?,?,?,?)";
		
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, place);
			pstmt.setString(3, title);
			pstmt.setString(4, text);
			int result = pstmt.executeUpdate();
			
			if(1 == result) {
				System.out.println("게시글 작성 성공");
			} else {
				System.out.println("게시글 작성 실패");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}	
