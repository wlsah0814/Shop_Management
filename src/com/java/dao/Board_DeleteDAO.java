package com.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Board_DeleteDAO {
	
	static String driver = "org.mariadb.jdbc.Driver";
	static String url = "jdbc:mariadb://localhost:3306/shop_management";
	static String uid = "jinmo";
	static String pwd = "1234";

	Connection con = null;
	PreparedStatement pstmt = null;

	public void Board_DelectDAO() {
		try {
			Class.forName(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void board_delect(int num) {
		
		String query = "delete from board where board_num=?";
		
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, num);
			
			int result = pstmt.executeUpdate();
			
			if(1 == result) {
				System.out.println("게시글 삭제 성공");
			} else {
				System.out.println("게시글 삭제 실패");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
