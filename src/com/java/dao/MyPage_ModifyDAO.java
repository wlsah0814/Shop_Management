package com.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.java.dto.MyPageDTO;

public class MyPage_ModifyDAO {
	
	
	static String driver = "org.mariadb.jdbc.Driver";
	static String url = "jdbc:mariadb://localhost:3306/shop_management";
	static String uid = "jinmo";
	static String pwd = "1234";

	Connection con = null;
	PreparedStatement pstmt = null;
	
	
	public void MyPage_ModifyDAO() {
		try {
			Class.forName(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void mypage_update(String pw, String email, String phone, int grade, String place, String id) {
		
		String query = "update member set pw=?, email=?, phone=?, grade=?, place=? where id=?";
		
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, pw);
			pstmt.setString(2, email);
			pstmt.setString(3, phone);
			pstmt.setInt(4, grade);
			pstmt.setString(5, place);
			pstmt.setString(6, id);
			int result = pstmt.executeUpdate();
			
			if(1 == result) {
				System.out.println("내정보 수정 성공");
			} else {
				System.out.println("내정보 수정 실패");
			}
			
	} catch (Exception e) {
		e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	
	}

}