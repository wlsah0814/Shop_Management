package com.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Find_PwDAO {

	
	static String driver = "org.mariadb.jdbc.Driver";
	static String url = "jdbc:mariadb://localhost:3306/shop_management";
	static String uid = "jinmo";
	static String pwd = "1234";

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public void Find_PwDAO() {
		try {
			Class.forName(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String findpw(String id,String email, String name) {
		

		String query = "select * from member where id=? and email=? and name=?";
		String find_pw = null;
		
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, email);
			pstmt.setString(3, name);

			rs = pstmt.executeQuery();
			while(rs.next()) {
				if(rs.getString("id").equals(id) && rs.getString("email").equals(email) && rs.getString("name").equals(name)) {
					find_pw = rs.getString("pw");
					System.out.println("비밀번호 찾기 성공");
				} else {
					System.out.println("아이디, 이메일, 이름을 다시 확인하세요");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return find_pw; 
	
		}

	
}
