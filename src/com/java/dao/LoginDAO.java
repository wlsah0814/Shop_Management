package com.java.dao;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

import com.java.dto.LoginDTO;
import com.java.login.Login;
import com.java.manager.Manager_Home;

public class LoginDAO {

	static String driver = "org.mariadb.jdbc.Driver";
	static String url = "jdbc:mariadb://localhost:3306/shop_management";
	static String uid = "jinmo";
	static String pwd = "1234";
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public void LoginDAO() {
		try {
			Class.forName(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int login(String id, String pw) {
		LoginDTO dto = new LoginDTO();
		String query = "select * from member where id=? and pw=?";
		String place = null;
		int result = 0;
		
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			pstmt = con.prepareStatement(query);

			pstmt.setString(1, id);
			pstmt.setString(2, pw);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				
				if (rs.getString("id").equals(id) && rs.getString("pw").equals(pw)) {
					if (rs.getInt("grade") == 1) {
						result = 1;	
						System.out.println("관리자 로그인 성공");
					} else {
						result= 2;
						System.out.println("회원 로그인 성공");
					} 
				}
			} else {
				result = -1;
				System.out.println("로그인 실패");
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
}
