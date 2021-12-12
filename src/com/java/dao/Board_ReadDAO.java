package com.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.java.dto.Board_MainDTO;
import com.java.dto.Board_ReadDTO;

public class Board_ReadDAO {
	
	static String driver = "org.mariadb.jdbc.Driver";
	static String url = "jdbc:mariadb://localhost:3306/shop_management";
	static String uid = "jinmo";
	static String pwd = "1234";

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public void Board_ReadDAO() {
		try {
			Class.forName(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Board_ReadDTO> board_read(String board_title) {
		
		ArrayList<Board_ReadDTO> dtos = new ArrayList<Board_ReadDTO>();
		String query ="select * from board where board_title=?";
		String text = null;
		
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, board_title);
			rs = pstmt.executeQuery();
			
		while(rs.next()) {
			text = rs.getString("board_text");
			Board_ReadDTO dto = new Board_ReadDTO(text);
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
