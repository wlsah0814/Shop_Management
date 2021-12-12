package com.java.member;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Member_SellStock extends JFrame{
	public JTextField num;
	public JLabel title;
	public JLabel stock_name;
	public JLabel stock_price;
	public JLabel sell_date;
	public JLabel sell_num;
	public JLabel name_lb;
	public JLabel price_lb;
	public JLabel date_lb;
	
	
	public Member_SellStock(String p_name, int p_price, String id) {
		//Container 안에 panel 적용
		Container AddPct = getContentPane();
		//Jpanel 사용
		JPanel panel = new JPanel();
		AddPct.add(panel);
		panel.setLayout(null);
		
		//Jpanel 세팅
		setTitle("판매확인"); //스윙 제목
		setSize(400, 400); //사이즈
		setResizable(true); //사이즈 조절여부
		setLocationRelativeTo(null); //실행 시 센터에서 실행되는 여부
		setVisible(true); //패널이 출력됨
		
		Font f1 = new Font("맑은 고딕", Font.BOLD, 20); //텍스트 폰트
		Font f2 = new Font("맑은 고딕", Font.BOLD, 23); //텍스트 폰트
		
		int price = p_price;
		String sell_price = Integer.toString(price);
		
		title = new JLabel("판매창");
		title.setFont(f1);
		title.setBounds(10, 10, 70, 30);
		panel.add(title);
		
		stock_name = new JLabel("상품명 : ");
		stock_name.setBounds(10, 70, 200, 30);
		stock_name.setFont(f2);
		panel.add(stock_name);
		
		name_lb = new JLabel(p_name);
		name_lb.setFont(f2);
		name_lb.setBounds(100, 70, 100, 30);
		panel.add(name_lb);
		
		stock_price = new JLabel("상품가격 : ");
		stock_price.setBounds(10, 130, 200, 30);
		stock_price.setFont(f2);
		panel.add(stock_price);	
		
		price_lb = new JLabel(sell_price);
		price_lb.setFont(f2);
		price_lb.setBounds(122, 130, 100, 30);
		panel.add(price_lb);
		

		sell_date = new JLabel("판매날짜 : ");
		sell_date.setBounds(10, 190, 150, 30);
		sell_date.setFont(f2);
		panel.add(sell_date);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String today = format.format(date);
		date_lb = new JLabel(today);
		date_lb.setBounds(120, 190, 150, 30);
		date_lb.setFont(f2);
		panel.add(date_lb);
		
		sell_num = new JLabel("판매수량 : ");
		sell_num.setBounds(10, 250, 150, 30);
		sell_num.setFont(f2);
		panel.add(sell_num);
		
		num = new JTextField();
		num.setBounds(130, 250, 100, 40);
		panel.add(num);
		
		JButton backbtn = new JButton("이전");
		backbtn.setBounds(10, 300, 100, 50);
		panel.add(backbtn);
		
		JButton sellbtn = new JButton("판매");
		sellbtn.setBounds(250, 300, 100, 50);
		panel.add(sellbtn);
		
		sellbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String p_name = name_lb.getText();
				String total_num = num.getText();
				String p_price = price_lb.getText();
				String sell_date = date_lb.getText();
				String login_id = id;
				int price = Integer.parseInt(p_price);
			}
		});	
		
		
		backbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				dispose();
			}
		});
	}
	
	public static void main(String[] args) {
		new Member_SellStock(null, 0, null);
	}
}


