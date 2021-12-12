package com.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SignUpDAO {

	
	static String driver = "org.mariadb.jdbc.Driver";
	static String url = "jdbc:mariadb://localhost:3306/shop_management";
	static String uid = "jinmo";
	static String pwd = "1234";

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	public void SignUpDAO() {
		try {
			Class.forName(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void signup_insert(String id, String branch, String pw, String name, String email, String phone, int grade) {
		
		String query = "insert into member(id,place,pw,name,email,phone,grade) values(?,?,?,?,?,?,?)";
		
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			pstmt = con.prepareStatement(query);

			pstmt.setString(1, id);
			pstmt.setString(2, branch);
			pstmt.setString(3, pw);
			pstmt.setString(4, name);
			pstmt.setString(5, email);
			pstmt.setString(6, phone);
			pstmt.setInt(7, grade);
			
			int result = pstmt.executeUpdate();
			
			if(1 == result) {
				System.out.println("회원가입 성공");
			} else {
				System.out.println("회원가입 실패");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
	
}
