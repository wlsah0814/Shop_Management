package com.java.dto;

public class SalesDTO {
	private String place;
	private String id;
	private String sale_date;
	int sale_total;
	
	public SalesDTO() {
		
	}

	public SalesDTO(String place, String sale_date, int sale_total) {
		super();
		this.place = place;
		this.sale_date = sale_date;
		this.sale_total = sale_total;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSale_date() {
		return sale_date;
	}

	public void setSale_date(String sale_date) {
		this.sale_date = sale_date;
	}

	public int getSale_total() {
		return sale_total;
	}

	public void setSale_total(int sale_total) {
		this.sale_total = sale_total;
	}
	
	
	
}
