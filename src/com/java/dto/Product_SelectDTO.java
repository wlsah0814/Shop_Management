package com.java.dto;

public class Product_SelectDTO {
	private String p_id;
	private String p_name;
	private String p_size;
	private String p_color;
	private int p_price;
	private String p_type;
	private String p_photo;

	public Product_SelectDTO(String photo) {
		this.p_photo = photo;
		
	}
	
	public Product_SelectDTO(String p_id, String p_name, String p_size, String p_color, int p_price, String p_type) {
		
		this.p_id = p_id;
		this.p_name = p_name;
		this.p_size = p_size;
		this.p_color = p_color;
		this.p_price = p_price;
		this.p_type = p_type;
	}


	public String getP_id() {
		return p_id;
	}
	public void setP_id(String p_id) {
		this.p_id = p_id;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public String getP_size() {
		return p_size;
	}
	public void setP_size(String p_size) {
		this.p_size = p_size;
	}
	public String getP_color() {
		return p_color;
	}
	public void setP_color(String p_color) {
		this.p_color = p_color;
	}
	public int getP_price() {
		return p_price;
	}
	public void setP_price(int p_price) {
		this.p_price = p_price;
	}
	public String getP_type() {
		return p_type;
	}
	public void setP_type(String p_type) {
		this.p_type = p_type;
	}
	public String getP_photo() {
		return p_photo;
	}
	public void setP_photo(String p_photo) {
		this.p_photo = p_photo;
	}
	
	
	
	
	
}
