package com.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.java.dto.Board_MainDTO;
import com.java.dto.MyPageDTO;
import com.java.manager.Manager_Home;

public class Board_MainDAO {


	static String driver = "org.mariadb.jdbc.Driver";
	static String url = "jdbc:mariadb://localhost:3306/shop_management";
	static String uid = "jinmo";
	static String pwd = "1234";

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public void Board_MainDAO() {
		try {
			Class.forName(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Board_MainDTO> board_select() {
		
		String query = "select * from board";
		ArrayList<Board_MainDTO> dtos = new ArrayList<Board_MainDTO>();
		
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int num = rs.getInt("board_num");
				String id = rs.getString("id");
				String place = rs.getString("place");
				String title = rs.getString("board_title");
				String date = rs.getString("write_date");
				
				Board_MainDTO dto = new Board_MainDTO(num, id, place, title, date);
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
