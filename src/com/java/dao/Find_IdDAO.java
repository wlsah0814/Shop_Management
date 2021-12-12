package com.java.dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Find_IdDAO {
	
	
	static String driver = "org.mariadb.jdbc.Driver";
	static String url = "jdbc:mariadb://localhost:3306/shop_management";
	static String uid = "jinmo";
	static String pwd = "1234";

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public void Find_IdDAO() {
		try {
			Class.forName(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String findid(String email, String name) {
		

		String find_id = null;
		String query = "select * from member where email=? and name=?";

		try {
			con = DriverManager.getConnection(url, uid, pwd);
			pstmt = con.prepareStatement(query);

			pstmt.setString(1, email);
			pstmt.setString(2, name);

			rs = pstmt.executeQuery();
			while(rs.next()) {
				if(rs.getString("email").equals(email) && rs.getString("name").equals(name)) {
					find_id = rs.getString("id");
					System.out.println("아이디 찾기 성공");
				} else {
					System.out.println("이메일, 이름을 다시 확인하세요");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return find_id; 
			
		}
	}

