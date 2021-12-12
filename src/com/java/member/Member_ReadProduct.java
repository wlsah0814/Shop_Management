package com.java.member;


import java.awt.Color;

import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.java.dao.ProductDAO;

//import sun.applet.Main;

public class Member_ReadProduct extends JFrame{
	public JLabel pname_lb;
	public JTextField P_SizeField;
	public JTextField P_ColorField;
	public JLabel P_Img;
	public JLabel pid_lb;
	public JLabel pprice_lb;
	public JLabel P_NameLabel;
	public JLabel P_NameExLabel;
	public JLabel P_SizeLabel;
	public JLabel P_SizeExLabel;
	public JLabel P_ColorLabel;
	public JLabel P_PriceLabel;
	public JLabel P_ClassLabel;
	public JLabel P_PhotoLabel;
	public JLabel P_PhotoExLabel;
	public JButton Backbtn;
	public JButton updatebtn;
	public JButton Joinbtn;
	public JButton CheckIdbtn;
	public JLabel color_lb;
	public JLabel size_lb;
	public JLabel class_lb;
	public boolean LoginCheck;
	public Font f1;
	public Font f2;
	public Font f3;
	public Font f4;
	public Font f5;
	//ImageIcon icon = new ImageIcon("C:\\Users\\양진모\\eclipse-workspace\\Project\\src\\img\\Ex1.jpg");
	public Member_ReadProduct() {
		//Container 안에 panel 적용
		Container AddPct = getContentPane();
		//Jpanel 사용
		JPanel panel = new JPanel();
		AddPct.add(panel);
		
		//Jpanel 세팅
		setTitle("제품정보"); //스윙 제목
		setSize(580, 420); //사이즈
		setResizable(true); //사이즈 조절여부
		setLocationRelativeTo(null); //실행 시 센터에서 실행되는 여부
		
		AddP_Panel(panel);
		setVisible(true); //패널이 출력됨
	}
	
	
	
	public void AddP_Panel(JPanel panel) {

		f1 = new Font("맑은 고딕", Font.BOLD, 15); //텍스트 폰트
		f2 = new Font("맑은 고딕", Font.BOLD, 15); //라디오버튼 폰트
		f3 = new Font("맑은 고딕", Font.BOLD, 13); //중복확인버튼 폰트
		f4 = new Font("맑은 고딕", Font.BOLD, 16); //이전, 초기화, 회원가입 버튼 폰트
		f5 = new Font("맑은 고딕", Font.BOLD, 10); //텍스트 폰트
		
		String[] PColor = {"White", "Navy", "Black", "Blue", "Begie", "Gray"}; 
		String[] PSize = {"S", "M", "L", "XL", "XXL"};
		String[] PClass = {"상의", "하의", "아우터", "모자", "신발", "가방"};
		
		
		P_Img = new JLabel();
		P_Img.setBounds(25, 50, 200, 200);
		panel.add(P_Img);
		
		
		//---------------------------------------라벨---------------------------------------
		
		
		//P_Name 텍스트
		panel.setLayout(null);
		P_NameLabel = new JLabel("제품명 : ");
		P_NameLabel .setFont(f1);
		P_NameLabel .setBounds(245, 40, 80, 25);
		panel.add(P_NameLabel );

		
		//P_Size 텍스트
		panel.setLayout(null);
		P_SizeLabel = new JLabel("사이즈 : ");
		P_SizeLabel.setBounds(245, 90, 80, 25);
		P_SizeLabel.setFont(f1);
		panel.add(P_SizeLabel);
		
		
		//P_Color 텍스트
		panel.setLayout(null);
		P_ColorLabel = new JLabel("색상 : ");
		P_ColorLabel.setBounds(245, 140, 80, 25);
		P_ColorLabel.setFont(f1);
		panel.add(P_ColorLabel);
		
		//P_Price 텍스트
		panel.setLayout(null);
		P_PriceLabel = new JLabel("가격 : ");
		P_PriceLabel.setBounds(245, 190, 80, 25);
		P_PriceLabel.setFont(f1);
		panel.add(P_PriceLabel);
		
		
		//P_Class 텍스트
		panel.setLayout(null);
		P_ClassLabel = new JLabel("분류 : ");
		P_ClassLabel.setBounds(245, 240, 80, 25);
		P_ClassLabel.setFont(f1);
		panel.add(P_ClassLabel);
		
		//P_Photo 텍스트
		panel.setLayout(null);
		P_PhotoLabel = new JLabel("제품사진");
		P_PhotoLabel.setBounds(25, 10, 80, 25);
		P_PhotoLabel.setFont(f1);
		panel.add(P_PhotoLabel);

		//P_PhotoEx 텍스트
		panel.setLayout(null);
		P_PhotoExLabel = new JLabel("EX) 사진 사이즈는 200x200으로 올리세요");
		P_PhotoExLabel.setForeground(Color.RED);
		P_PhotoExLabel.setBounds(25, 260, 350, 25);
		P_PhotoExLabel.setFont(f5);
		panel.add(P_PhotoExLabel);
			
		pid_lb = new JLabel();
		
		pname_lb = new JLabel();
		pname_lb.setFont(f4);
		pname_lb.setBounds(300, 37, 160, 30);
		panel.add(pname_lb);
		
	
		pprice_lb = new JLabel();
		pprice_lb.setFont(f4);
		pprice_lb.setBounds(285, 188, 160, 30);
		panel.add(pprice_lb);
		
		
	//---------------------------------------콤보박스------------------------------------
		
	
		size_lb = new JLabel();
		size_lb.setFont(f4);
		size_lb.setBounds(300, 85, 90, 30);
		panel.add(size_lb);
		
		color_lb = new JLabel();
		color_lb.setFont(f4);
		color_lb.setBounds(285, 135, 90, 30);
		panel.add(color_lb);
		
		class_lb = new JLabel();
		class_lb.setFont(f4);
		class_lb .setBounds(285, 235, 90, 30);
		panel.add(class_lb);
		
		
		
	//---------------------------------------버튼---------------------------------------
		
		//이전 버튼
		Backbtn = new JButton("이전");
		Backbtn.setBounds(240, 290, 110, 40);
		Backbtn.setFont(f4);
		panel.add(Backbtn);
		
		
		Backbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		
		
	}
	public static void main(String[] args) {
		new Member_ReadProduct();
	}

}
