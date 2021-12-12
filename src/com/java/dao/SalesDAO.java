package com.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.java.dto.Product_SelectDTO;
import com.java.dto.SalesDTO;

public class SalesDAO {
	
	static String driver = "org.mariadb.jdbc.Driver";
	static String url = "jdbc:mariadb://localhost:3306/shop_management";
	static String uid = "jinmo";
	static String pwd = "1234";

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public void SalesDAO() {
		try {
			Class.forName(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<SalesDTO> sales_select() {
		ArrayList<SalesDTO> dtos = new ArrayList<SalesDTO>();
		String query = "select * from sales";
		
		try {
			con = DriverManager.getConnection(url,uid,pwd);
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String place = rs.getString("place");
				String sale_day = rs.getString("saleday");
				int sale_total = rs.getInt("saletotal");
				
				SalesDTO dto = new SalesDTO(place, sale_day, sale_total);
				dtos.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dtos;
		
	}
	
	public ArrayList<SalesDTO> sales_search(String place) {

		ArrayList<SalesDTO> dtos = new ArrayList<SalesDTO>();
		String query = "select * from sales where place like ?";
		
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1,"%" + place + "%");
			rs = pstmt.executeQuery();

			while(rs.next()) {
				String s_place = rs.getString("place");
				String sale_day = rs.getString("saleday");
				int sale_total = rs.getInt("saletotal");
				System.out.println(s_place);
				SalesDTO dto = new SalesDTO(s_place, sale_day, sale_total);
				dtos.add(dto);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)rs.close();
				if (pstmt != null)pstmt.close();
				if (con != null)con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			return dtos;
		}
	}
	
	public void sales_update(int sales, String place) {
		String query = "update sales set m_target=? where place=?";

		try {
			con = DriverManager.getConnection(url, uid, pwd);
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, sales);
			pstmt.setString(2, place);
			rs = pstmt.executeQuery();
			int result = pstmt.executeUpdate();
			
			if(1 == result) {
				JOptionPane.showMessageDialog(null, "목표매출 수정 성공");
				System.out.println("목표매출 수정 성공");
			} else {
				JOptionPane.showMessageDialog(null, "목표매출 수정 실패");
				System.out.println("목표매출 수정 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)rs.close();
				if (pstmt != null)pstmt.close();
				if (con != null)con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void sell_total(String id, String date, int price) {
		
	}
	
}
