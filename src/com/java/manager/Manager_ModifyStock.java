package com.java.manager;

import java.awt.Color;

import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.java.dao.ProductDAO;
import com.java.dao.StockDAO;

//import sun.applet.Main;

public class Manager_ModifyStock extends JFrame{
	public JFileChooser image = new JFileChooser(new File(System.getProperty("user.home") + "//" + "Desktop"));
	public BufferedImage img = null;
	public FileWriter writer = null;
	public ImageIcon p_icon = null;
	public File image_save = null;
	public FileOutputStream saves = null;
	
	private JTextField P_NameField;
	private JTextField P_SizeField;
	private JTextField P_ColorField;
	private JTextField P_PriceField;
	private JTextField P_CodeField;
	public JTextField pnum_lb2;
	public JLabel pnum_lb;
	private JLabel P_CodeLabel;
	private JLabel P_NameLabel;
	private JLabel P_NameExLabel;
	private JLabel P_SizeLabel;
	private JLabel P_SizeExLabel;
	private JLabel P_ColorLabel;
	private JLabel P_PriceLabel;
	private JLabel P_ClassLabel;
	private JLabel P_PhotoLabel;
	private JLabel P_PhotoExLabel;
	private JButton Backbtn;
	private JButton updatebtn;
	private JButton Joinbtn;
	private JButton CheckIdbtn;
	private JButton uploadbtn;
	private JRadioButton ManagerRb; 
	private JRadioButton MemberRb; 
	private ButtonGroup Sortation;
	private JComboBox ColorCb;
	private JComboBox SizeCb;
	private JComboBox ClassCb;
	private boolean LoginCheck;
	private Font f1;
	private Font f2;
	private Font f3;
	private Font f4;
	private Font f5;
	
	public Manager_ModifyStock(String id, String name, int price, Icon icons, int num, String size, String color, String type) {
		//Container ?????? panel ??????
		Container AddPct = getContentPane();
		//Jpanel ??????
		JPanel panel = new JPanel();
		AddPct.add(panel);
		
		//Jpanel ??????
		setTitle("???????????? ??????"); //?????? ??????
		setSize(600, 420); //?????????
		setResizable(true); //????????? ????????????
		setLocationRelativeTo(null); //?????? ??? ???????????? ???????????? ??????
		
		AddP_Panel(panel, id, name, price, icons, num, size, color, type);
		setVisible(true); //????????? ?????????
	}
	
	
	
	public void AddP_Panel(JPanel panel,String id, String name, int price, Icon icons, int num, String size, String color, String type) {
		
		f1 = new Font("?????? ??????", Font.BOLD, 15); //????????? ??????
		f2 = new Font("?????? ??????", Font.BOLD, 15); //??????????????? ??????
		f3 = new Font("?????? ??????", Font.BOLD, 13); //?????????????????? ??????
		f4 = new Font("?????? ??????", Font.BOLD, 16); //??????, ?????????, ???????????? ?????? ??????
		f5 = new Font("?????? ??????", Font.BOLD, 10); //????????? ??????
		String[] PColor = {"White", "Navy", "Black", "Blue", "Begie", "Gray"}; 
		String[] PSize = {"S", "M", "L", "XL", "XXL"};
		String[] PClass = {"??????", "??????", "?????????", "??????", "??????", "??????"};
		
		JLabel P_Img = new JLabel();
		P_Img.setIcon(icons);
		P_Img.setBounds(25, 50, 200, 200);
		panel.add(P_Img);
		
		
		//---------------------------------------??????---------------------------------------
		
		
		//P_Name ?????????
		panel.setLayout(null);
		P_NameLabel = new JLabel("????????????");
		P_NameLabel .setFont(f1);
		P_NameLabel .setBounds(240, 10, 80, 25);
		panel.add(P_NameLabel );
		
		panel.setLayout(null);
		P_NameLabel = new JLabel("?????????");
		P_NameLabel .setFont(f1);
		P_NameLabel .setBounds(240, 60, 80, 25);
		panel.add(P_NameLabel );

		
		//P_Size ?????????
		panel.setLayout(null);
		P_SizeLabel = new JLabel("?????????");
		P_SizeLabel.setBounds(240, 105, 80, 25);
		P_SizeLabel.setFont(f1);
		panel.add(P_SizeLabel);
		
		
		//P_Color ?????????
		panel.setLayout(null);
		P_ColorLabel = new JLabel("??????");
		P_ColorLabel.setBounds(240, 155, 80, 25);
		P_ColorLabel.setFont(f1);
		panel.add(P_ColorLabel);
		
		//P_Price ?????????
		panel.setLayout(null);
		P_PriceLabel = new JLabel("??????");
		P_PriceLabel.setBounds(240, 200, 80, 25);
		P_PriceLabel.setFont(f1);
		panel.add(P_PriceLabel);
		
		
		//P_Class ?????????
		panel.setLayout(null);
		P_ClassLabel = new JLabel("??????");
		P_ClassLabel.setBounds(240, 240, 80, 25);
		P_ClassLabel.setFont(f1);
		panel.add(P_ClassLabel);
		
		//P_Photo ?????????
		panel.setLayout(null);
		P_PhotoLabel = new JLabel("????????????");
		P_PhotoLabel.setBounds(25, 10, 80, 25);
		P_PhotoLabel.setFont(f1);
		panel.add(P_PhotoLabel);

		//P_PhotoEx ?????????
		panel.setLayout(null);
		P_PhotoExLabel = new JLabel("EX) ?????? ???????????? 200px x 200px??? ???????????????!");
		P_PhotoExLabel.setForeground(Color.RED);
		P_PhotoExLabel.setBounds(10, 260, 350, 25);
		P_PhotoExLabel.setFont(f5);
		panel.add(P_PhotoExLabel);
		
		P_CodeField = new JTextField(id);
		P_CodeField.setEnabled(false);
		P_CodeField.setBounds(310, 10, 160, 30);
		panel.add(P_CodeField);
		
		P_NameField = new JTextField(name);
		P_NameField.setEnabled(false);
		P_NameField.setBounds(300, 60, 160, 30);
		panel.add(P_NameField);

		int p_price = price;
		String Price = Integer.toString(price);
		
		P_PriceField = new JTextField(Price);
		P_PriceField.setFont(f1);
		P_PriceField.setEnabled(false);
		P_PriceField.setBounds(300, 200, 160, 30);
		panel.add(P_PriceField);
		
		int p_num = num;
		String Num = Integer.toString(p_num);
	
		panel.setLayout(null);
		pnum_lb = new JLabel("??????");
		pnum_lb.setBounds(245, 280, 80, 25);
		pnum_lb.setFont(f1);
		panel.add(pnum_lb);
		
		panel.setLayout(null);
		pnum_lb2 = new JTextField(Num);
		pnum_lb2.setBounds(300, 280, 80, 25);
		pnum_lb2.setFont(f1);
		panel.add(pnum_lb2);

		SizeCb = new JComboBox(PSize);
		SizeCb.setBounds(300, 100, 90, 30);
		SizeCb.setEnabled(false);
		SizeCb.setSelectedItem(size);
		panel.add(SizeCb);
		
		
		ColorCb = new JComboBox(PColor);
		ColorCb.setBounds(300, 150, 90, 30);
		ColorCb.setEnabled(false);
		ColorCb.setSelectedItem(color);
		panel.add(ColorCb);
		

		ClassCb = new JComboBox(PClass);
		ClassCb .setBounds(300, 240, 90, 30);
		ClassCb.setEnabled(false);
		ClassCb.setSelectedItem(type);
		panel.add(ClassCb );
		
		updatebtn = new JButton("????????????");
		updatebtn.setBounds(380, 310, 110, 40);
		updatebtn.setFont(f4);
		panel.add(updatebtn);
		
		
		Backbtn = new JButton("??????");
		Backbtn.setBounds(240, 310, 110, 40);
		Backbtn.setFont(f4);
		panel.add(Backbtn);

		
		Backbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		
		updatebtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				StockDAO dao = new StockDAO();
				System.out.println(id);
				System.out.println(size);
				System.out.println(color);
				String num = pnum_lb2.getText();
				int p_num = Integer.parseInt(num);
				System.out.println(p_num);
				

				int result = JOptionPane.showConfirmDialog(null, "?????????????????????????", "????????????", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					dao.stock_update(p_num, id, size, color);
					JOptionPane.showMessageDialog(null, "?????? ????????? ???????????????.");
					dispose();
				}
					
				
			}
		});
		
	}
	public static void main(String[] args) {
		new Manager_ModifyStock(null, null, 0, null, 0, null, null, null);
	}
	

}
