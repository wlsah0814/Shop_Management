package com.java.dto;

public class Board_MainDTO {
	private int board_num = 0;
	private String id = null;
	private String place = null;
	private String board_title = null;
	private String write_date = null;

	
	public Board_MainDTO(int board_num, String id, String place, String board_title, String write_date) {
		this.board_num = board_num;
		this.id = id;
		this.place = place;
		this.board_title = board_title;
		this.write_date = write_date;
	}


	public int getBoard_num() {
		return board_num;
	}
	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getWrite_date() {
		return write_date;
	}
	public void setWrite_date(String write_date) {
		this.write_date = write_date;
	}
	
	
	
	
}
