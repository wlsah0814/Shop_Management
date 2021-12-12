package com.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.java.dto.MyPageDTO;

public class MyPageDAO {

	
	static String driver = "org.mariadb.jdbc.Driver";
	static String url = "jdbc:mariadb://localhost:3306/shop_management";
	static String uid = "jinmo";
	static String pwd = "1234";

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public void MyPageDAO() {
		try {
			Class.forName(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<MyPageDTO> myinfo_select(String id) {
		String query = "select * from member where id=?";
		ArrayList<MyPageDTO> dtos = new ArrayList<MyPageDTO>();
		
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String m_id = rs.getString("id");
				String m_pw = rs.getString("pw");
				String m_name = rs.getString("name");
				String m_email = rs.getString("email");
				String m_phone = rs.getString("phone");
				int m_grade = rs.getInt("grade");
				String m_branch = rs.getString("place");
				
				MyPageDTO dto = new MyPageDTO(m_id, m_pw, m_name, m_email, m_phone, m_grade, m_branch);
				dtos.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return dtos;
	}
	

}
