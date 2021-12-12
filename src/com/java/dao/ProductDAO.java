package com.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.java.dto.Board_ReadDTO;
import com.java.dto.Product_SelectDTO;

public class ProductDAO {
	
	static String driver = "org.mariadb.jdbc.Driver";
	static String url = "jdbc:mariadb://localhost:3306/shop_management";
	static String uid = "jinmo";
	static String pwd = "1234";

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public void ProductDAO() {
		try {
			Class.forName(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Product_SelectDTO> product_select() {
		String query = "select * from shop_product";
		ArrayList<Product_SelectDTO> dtos = new ArrayList<Product_SelectDTO>();
		
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String p_id = rs.getString("p_id");
				String p_name = rs.getString("p_name");
				String p_size = rs.getString("p_size");
				String p_color = rs.getString("p_color");
				int p_price = rs.getInt("p_price");
				String p_type = rs.getString("p_type");
				String p_photo = rs.getString("p_photo");
						
				Product_SelectDTO dto = new Product_SelectDTO(p_id, p_name, p_size, p_color, p_price, p_type);
				dtos.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtos;
	}
	
	
	public ArrayList<Product_SelectDTO> product_read(String p_id) {
		ArrayList<Product_SelectDTO> dtos = new ArrayList<Product_SelectDTO>();
		String query = "select * from shop_product where p_id=?";
		String photo = null;
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, p_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				photo = rs.getString("p_photo");
				Product_SelectDTO dto = new Product_SelectDTO(photo);
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
		return dtos;
		
		}
	}
	
	
	
	public ArrayList<Product_SelectDTO> product_search(String name) {
		
		ArrayList<Product_SelectDTO> dtos = new ArrayList<Product_SelectDTO>();
		String query = "select * from shop_product where p_name like ?";
		
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			pstmt = con.prepareStatement(query);		
			pstmt.setString(1, "%" + name + "%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String p_id = rs.getString("p_id");
				String p_name = rs.getString("p_name");
				String p_size = rs.getString("p_size");
				String p_color = rs.getString("p_color");
				int p_price = rs.getInt("p_price");
				String p_type = rs.getString("p_type");
						
				Product_SelectDTO dto = new Product_SelectDTO(p_id, p_name, p_size, p_color, p_price, p_type);
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

	public void product_insert(String id, String name, String size, String color, int price, String type, String photo) {
		
		String query = "insert into shop_product(p_id, p_name, p_size, p_color, p_price, p_type, p_photo) values(?,?,?,?,?,?,?)";
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, size);
			pstmt.setString(4, color);
			pstmt.setInt(5, price);
			pstmt.setString(6, type);
			pstmt.setString(7, photo);
			int result = pstmt.executeUpdate();
	
			if(1 == result) {
				System.out.println("제품추가 성공");
			} else {
				System.out.println("제품추가 실패");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
	}
	
	public void product_delete(String id, String size, String color) {
		String query = "delete from shop_product where p_id=? and p_size=? and p_color=?";
		
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, size);
			pstmt.setString(3, color);
			
			int result = pstmt.executeUpdate();

			if (1 == result) {
				System.out.println("제품 삭제 성공");
			} else {
				System.out.println("제품 삭제 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void product_update(String code, String name, String size, String color, int price, String type, String photo, String p_size, String p_color) {
		
		String query = "update shop_product set p_id=?, p_name=?, p_size=?, p_color=?, p_price=?, p_type=?, p_photo=? where p_id=? and p_size=? and p_color=?";
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, code);
			pstmt.setString(2, name);
			pstmt.setString(3, size);
			pstmt.setString(4, color);
			pstmt.setInt(5, price);
			pstmt.setString(6, type);
			pstmt.setString(7, photo);
			
			pstmt.setString(8, code);
			pstmt.setString(9, p_size);
			pstmt.setString(10, p_color);
			int result = pstmt.executeUpdate();
			
			if(1 == result) {
				System.out.println("제품정보 수정 성공");
			} else {
				System.out.println("제품정보 수정 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
}
}
	
