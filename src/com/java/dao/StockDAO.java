package com.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.java.dto.Product_SelectDTO;
import com.java.dto.Stock_SelectDTO;

public class StockDAO {

	static String driver = "org.mariadb.jdbc.Driver";
	static String url = "jdbc:mariadb://localhost:3306/shop_management";
	static String uid = "jinmo";
	static String pwd = "1234";

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public void StockDAO() {
		try {
			Class.forName(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Stock_SelectDTO> stock_select() {

		String query = "select * from shop_stock";
		ArrayList<Stock_SelectDTO> dtos = new ArrayList<Stock_SelectDTO>();

		try {
			con = DriverManager.getConnection(url, uid, pwd);
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String p_place = rs.getString("place");
				String p_id = rs.getString("p_id");
				String p_name = rs.getString("p_name");
				String p_size = rs.getString("p_size");
				String p_color = rs.getString("p_color");
				int p_price = rs.getInt("p_price");
				String p_type = rs.getString("p_type");
				int p_num = rs.getInt("p_num");

				Stock_SelectDTO dto = new Stock_SelectDTO(p_place, p_id, p_name, p_size, p_color, p_price, p_type,
						p_num);
				dtos.add(dto);
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

			return dtos;

		}
	}

	public ArrayList<Stock_SelectDTO> stock_search(String name) {

		ArrayList<Stock_SelectDTO> dtos = new ArrayList<Stock_SelectDTO>();
		String query = "select * from shop_stock where place like ?";

		try {
			con = DriverManager.getConnection(url, uid, pwd);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "%" + name + "%");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String p_place = rs.getString("place");
				String p_id = rs.getString("p_id");
				String p_name = rs.getString("p_name");
				String p_size = rs.getString("p_size");
				String p_color = rs.getString("p_color");
				int p_price = rs.getInt("p_price");
				String p_type = rs.getString("p_type");
				int p_num = rs.getInt("p_num");

				Stock_SelectDTO dto = new Stock_SelectDTO(p_place, p_id, p_name, p_size, p_color, p_price, p_type,
						p_num);
				dtos.add(dto);
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
			return dtos;
		}
	}

	public void stock_update(int num, String id, String size, String color) {

		String query = "update shop_stock set p_num=? where p_id=? and p_size=? and p_color=?";
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, num);
			pstmt.setString(2, id);
			pstmt.setString(3, size);
			pstmt.setString(4, color);

			int result = pstmt.executeUpdate();

			if (1 == result) {
				System.out.println("재고수량 수정 성공");
			} else {
				System.out.println("재고수량 수정 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public void stock_insert(String place, String id, String name, String size, String color, int price, String type,
			String photo, int num) {

		String query = "insert into shop_stock(place, p_id, p_name, p_size, p_color, p_price, p_type, p_photo, p_num) values(?,?,?,?,?,?,?,?,?)";
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, place);
			pstmt.setString(2, id);
			pstmt.setString(3, name);
			pstmt.setString(4, size);
			pstmt.setString(5, color);
			pstmt.setInt(6, price);
			pstmt.setString(7, type);
			pstmt.setString(8, photo);
			pstmt.setInt(9, num);
			int result = pstmt.executeUpdate();

			if (1 == result) {
				System.out.println("재고추가 성공");
			} else {
				System.out.println("재고추가 실패");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
	}

	public void stock_delete(String id, String size, String color) {
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
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public ArrayList<Stock_SelectDTO> place_select(String place) {

		String query = "select * from shop_stock where place=?";
		ArrayList<Stock_SelectDTO> dtos = new ArrayList<Stock_SelectDTO>();

		try {
			con = DriverManager.getConnection(url, uid, pwd);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, place);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String p_place = rs.getString("place");
				String p_id = rs.getString("p_id");
				String p_name = rs.getString("p_name");
				String p_size = rs.getString("p_size");
				String p_color = rs.getString("p_color");
				int p_price = rs.getInt("p_price");
				String p_type = rs.getString("p_type");
				int p_num = rs.getInt("p_num");

				Stock_SelectDTO dto = new Stock_SelectDTO(p_place, p_id, p_name, p_size, p_color, p_price, p_type,
						p_num);
				dtos.add(dto);
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

			return dtos;

		}
	}
	
	
}
