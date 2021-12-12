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

public class Stock_Plus extends JFrame{
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
	private JTextField pnum_lb2;
	private JLabel place_lb;
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
	private JLabel pnum_lb;
	private JButton Backbtn;
	private JButton updatebtn;
	private JButton Joinbtn;
	private JButton CheckIdbtn;
	private JButton uploadbtn;
	private JRadioButton ManagerRb; 
	private JRadioButton MemberRb; 
	private ButtonGroup Sortation;
	private JComboBox PlaceCb;
	private JComboBox ColorCb;
	private JComboBox SizeCb;
	private JComboBox ClassCb;
	private boolean LoginCheck;
	private Font f1;
	private Font f2;
	private Font f3;
	private Font f4;
	private Font f5;
	
	public Stock_Plus(String id, String name, int price, Icon icons, String size, String color, String type) {
		//Container 안에 panel 적용
		Container AddPct = getContentPane();
		//Jpanel 사용
		JPanel panel = new JPanel();
		AddPct.add(panel);
		
		//Jpanel 세팅
		setTitle("제품수정"); //스윙 제목
		setSize(600, 420); //사이즈
		setResizable(true); //사이즈 조절여부
		setLocationRelativeTo(null); //실행 시 센터에서 실행되는 여부
		
		AddP_Panel(panel, id, name, price, icons, size, color, type);
		setVisible(true); //패널이 출력됨
	}
	
	
	
	public void AddP_Panel(JPanel panel,String id, String name, int price, Icon icons, String size, String color, String type) {
		
		f1 = new Font("맑은 고딕", Font.BOLD, 15); //텍스트 폰트
		f2 = new Font("맑은 고딕", Font.BOLD, 15); //라디오버튼 폰트
		f3 = new Font("맑은 고딕", Font.BOLD, 13); //중복확인버튼 폰트
		f4 = new Font("맑은 고딕", Font.BOLD, 16); //이전, 초기화, 회원가입 버튼 폰트
		f5 = new Font("맑은 고딕", Font.BOLD, 10); //텍스트 폰트
		String[] PColor = {"White", "Navy", "Black", "Blue", "Begie", "Gray"}; 
		String[] PSize = {"S", "M", "L", "XL", "XXL"};
		String[] PClass = {"상의", "하의", "아우터", "모자", "신발", "가방"};
		String[] place = {"강남점", "영등포점"};
		JLabel P_Img = new JLabel();
		P_Img.setIcon(icons);
		P_Img.setBounds(25, 50, 200, 200);
		panel.add(P_Img);
		
		
		//---------------------------------------라벨---------------------------------------
		
		
		panel.setLayout(null);
		pnum_lb = new JLabel("수량 : ");
		pnum_lb.setBounds(410, 280, 80, 25);
		pnum_lb.setFont(f1);
		panel.add(pnum_lb);
		
		panel.setLayout(null);
		pnum_lb2 = new JTextField();
		pnum_lb2.setBounds(460, 283, 80, 25);
		pnum_lb2.setFont(f1);
		panel.add(pnum_lb2);
		
		
		//P_Name 텍스트
		panel.setLayout(null);
		P_NameLabel = new JLabel("제품코드");
		P_NameLabel .setFont(f1);
		P_NameLabel .setBounds(240, 10, 80, 25);
		panel.add(P_NameLabel );
		
		panel.setLayout(null);
		P_NameLabel = new JLabel("제품명");
		P_NameLabel .setFont(f1);
		P_NameLabel .setBounds(240, 60, 80, 25);
		panel.add(P_NameLabel );

		
		//P_Size 텍스트
		panel.setLayout(null);
		P_SizeLabel = new JLabel("사이즈");
		P_SizeLabel.setBounds(240, 105, 80, 25);
		P_SizeLabel.setFont(f1);
		panel.add(P_SizeLabel);
		
		
		//P_Color 텍스트
		panel.setLayout(null);
		P_ColorLabel = new JLabel("색상");
		P_ColorLabel.setBounds(240, 155, 80, 25);
		P_ColorLabel.setFont(f1);
		panel.add(P_ColorLabel);
		
		//P_Price 텍스트
		panel.setLayout(null);
		P_PriceLabel = new JLabel("가격");
		P_PriceLabel.setBounds(240, 200, 80, 25);
		P_PriceLabel.setFont(f1);
		panel.add(P_PriceLabel);
		
		
		//P_Class 텍스트
		panel.setLayout(null);
		P_ClassLabel = new JLabel("분류");
		P_ClassLabel.setBounds(240, 240, 80, 25);
		P_ClassLabel.setFont(f1);
		panel.add(P_ClassLabel);
		
		//P_Photo 텍스트
		panel.setLayout(null);
		P_PhotoLabel = new JLabel("제품사진");
		P_PhotoLabel.setBounds(25, 10, 80, 25);
		P_PhotoLabel.setFont(f1);
		panel.add(P_PhotoLabel);
		
		panel.setLayout(null);
		place_lb = new JLabel("지점");
		place_lb.setBounds(240, 285, 80, 25);
		place_lb.setFont(f1);
		panel.add(place_lb);

		//P_PhotoEx 텍스트
		panel.setLayout(null);
		P_PhotoExLabel = new JLabel("EX) 사진 사이즈는 200px x 200px로 올려주세요!");
		P_PhotoExLabel.setForeground(Color.RED);
		P_PhotoExLabel.setBounds(10, 260, 350, 25);
		P_PhotoExLabel.setFont(f5);
		panel.add(P_PhotoExLabel);
		
		P_CodeField = new JTextField(id);
		P_CodeField.setBounds(310, 10, 160, 30);
		P_CodeField.setEnabled(false);
		panel.add(P_CodeField);
		
		P_NameField = new JTextField(name);
		P_NameField.setBounds(300, 60, 160, 30);
		P_NameField.setEnabled(false);
		panel.add(P_NameField);

		int p_price = price;
		String Price = Integer.toString(price);
		
		P_PriceField = new JTextField(Price);
		P_PriceField.setBounds(300, 200, 160, 30);
		P_PriceField.setEnabled(false);
		panel.add(P_PriceField);
	
		PlaceCb = new JComboBox(place);
		PlaceCb.setBounds(300, 280, 90, 30);
		panel.add(PlaceCb);

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
		ClassCb.setBounds(300, 240, 90, 30);
		ClassCb.setEnabled(false);
		ClassCb.setSelectedItem(type);
		panel.add(ClassCb);
		
		updatebtn = new JButton("추가하기");
		updatebtn.setBounds(380, 320, 110, 40);
		updatebtn.setFont(f4);
		panel.add(updatebtn);	
		
		Backbtn = new JButton("이전");
		Backbtn.setBounds(240, 320, 110, 40);
		Backbtn.setFont(f4);
		panel.add(Backbtn);
		
		uploadbtn = new JButton("사진 업로드");
		uploadbtn.setBounds(30, 290, 150, 40);
		uploadbtn.setFont(f4);
		panel.add(uploadbtn);
		
uploadbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				image.setDialogTitle("사진 업로드");
				image.setAcceptAllFileFilterUsed(false); //모든 파일 유형 불가
				image.setMultiSelectionEnabled(false); //다중 선택 불가
				image.setFileFilter(new FileNameExtensionFilter("JPG","JPG"));
				
				int open = image.showOpenDialog(null);
				
				try {
					//파일 열기버튼 눌렀을 때
					if(open == JFileChooser.APPROVE_OPTION) {
						String imgpath = image.getSelectedFile().getPath();
						String imgname = image.getSelectedFile().getName();
						image_save = new File(imgpath);
						JOptionPane.showMessageDialog(null, imgname + " 사진을 업로드 합니다.");
						p_icon = new ImageIcon(imgpath);
						P_Img.setIcon(p_icon);
					}  else {
						JOptionPane.showMessageDialog(null, "취소하셨습니다.");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		
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
				
				String p_code = P_CodeField.getText();
				String p_name = P_NameField.getText();
				String p_size = SizeCb.getSelectedItem().toString();
				String p_color = ColorCb.getSelectedItem().toString();
				String p_type = ClassCb.getSelectedItem().toString();
				String price = P_PriceField.getText();
				String place = PlaceCb.getSelectedItem().toString();
				String num= pnum_lb2.getText();
				int p_num = Integer.parseInt(num);
				int p_price = Integer.parseInt(price);
				
				image.setDialogTitle("사진 업로드");
				image.setAcceptAllFileFilterUsed(false); //모든 파일 유형 불가
				image.setMultiSelectionEnabled(false); //다중 선택 불가
				image.setFileFilter(new FileNameExtensionFilter("JPG","JPG"));
				
				int save = image.showSaveDialog(null);	

				if(save == image.APPROVE_OPTION) {
					try {
						//경로 + 이름 설정
						BufferedImage buffered = ImageIO.read(image_save);
						String imgpath = image.getSelectedFile().getPath();
						String imgname = image.getSelectedFile().getName();
						image_save = new File(imgpath);
						ImageIO.write(buffered, "jpg", image_save);
						JOptionPane.showMessageDialog(null, imgname + "사진을 업로드합니다.");
						dao.stock_insert(place, p_code, p_name, p_size, p_color, p_price, p_type, imgname, p_num);
						dispose();
						} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				}
			}
		});
		
	}
	public static void main(String[] args) {
		new Stock_Plus(null, null, ABORT, null, null, null, null);
	}
	

}
