package com.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import com.java.manager.Manager_Home;

public class Board_ModifyDAO {
	
	static String driver = "org.mariadb.jdbc.Driver";
	static String url = "jdbc:mariadb://localhost:3306/shop_management";
	static String uid = "jinmo";
	static String pwd = "1234";

	Connection con = null;
	PreparedStatement pstmt = null;

	public void Board_ModifyDAO() {
		try {
			Class.forName(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void board_modify(String date, String title, String text, int num) {
		String query = "update board set write_date=?, board_title=?, board_text=? where board_num=?";
		int result = 0;
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, date);
			pstmt.setString(2, title);
			pstmt.setString(3, text);
			pstmt.setInt(4, num);
			result = pstmt.executeUpdate();
			
			if(1 == result) {
				JOptionPane.showMessageDialog(null, "게시글 수정 성공");
				System.out.println("게시글 수정 성공");
			} else {
				JOptionPane.showMessageDialog(null, "게시글 수정 실패");
				System.out.println("게시글 수정 실패");
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)pstmt.close();
				if (con != null)con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}
}
