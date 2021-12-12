package com.java.login;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.java.dao.CheckDAO;
import com.java.dao.Find_IdDAO;

import jdk.internal.org.objectweb.asm.util.CheckAnnotationAdapter;


public class Find_Id extends JFrame{

	private Font f1;
	private Font f2;
	private JLabel NameLabel;
	private JLabel EmailLabel;
	private JTextField NameTextField;
	private JTextField EmailTextField; 
	private JButton Findbtn;
	private JButton Backbtn;
	
	public Find_Id() {
		//Container 안에 panel 적용
		Container Fidct = getContentPane();
		//Jpanel 사용
		JPanel panel = new JPanel();
		Fidct.add(panel);
		
		//Jpanel 세팅
		setTitle("아이디 찾기"); //스윙 제목
		setSize(390, 200); //사이즈
		setResizable(true); //사이즈 조절여부
		setLocationRelativeTo(null); //실행 시 센터에서 실행되는 여부
		
		id_Panel(panel);
		setVisible(true); //패널이 출력됨
	}
	
	public void id_Panel(JPanel panel) {
		
		f1 = new Font("맑은 고딕", Font.BOLD, 15); //텍스트 폰트
		f2 = new Font("맑은 고딕", Font.BOLD, 16); //버튼 폰트
		
		
		//---------------------------------------라벨--------------------------------------	
		
		
		//name 텍스트
		panel.setLayout(null);
		NameLabel = new JLabel("이름");
		NameLabel.setFont(f1);
		NameLabel.setBounds(20, 30, 80, 25);
		panel.add(NameLabel);
		
		//email 텍스트
		panel.setLayout(null);
		EmailLabel = new JLabel("이메일");
		EmailLabel.setFont(f1);
		EmailLabel.setBounds(20, 80, 80, 25);
		panel.add(EmailLabel);
		
		
		//---------------------------------------텍스트필드 시작---------------------------------------
		
		
		//name TextField
		NameTextField = new JTextField(20);
		NameTextField.setBounds(100, 30, 160, 30);
		panel.add(NameTextField);
		
		//email TextField
		EmailTextField = new JTextField(20);
		EmailTextField .setBounds(100, 80, 160, 30);
		panel.add(EmailTextField);
		
		
		//---------------------------------------버튼---------------------------------------

		
		//찾기 버튼
		Findbtn = new JButton("찾기");
		Findbtn.setBounds(270, 20, 80, 40);
		Findbtn.setFont(f2);
		panel.add(Findbtn);
		
		//이전 버튼
		Backbtn = new JButton("이전");
		Backbtn.setBounds(270, 75, 80, 40);
		Backbtn.setFont(f2);
		panel.add(Backbtn);
		
		
		//---------------------------------------------------------
		//리스너 부분
		
		Findbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CheckDAO check = new CheckDAO();
				Find_IdDAO dao = new Find_IdDAO();
				
				String email = EmailTextField.getText();
				String name = NameTextField.getText();
				String find_id = dao.findid(email, name);
								
				
				
				if(check.regularName(name) == false ) {
					 JOptionPane.showMessageDialog(null, "2~4자리 이름으로 입력하세요");
					 return;
				} else if(check.regularEmail(email) == false) {
					JOptionPane.showMessageDialog(null, "이메일을 다시 확인해주세요");
					 return;
				}
				
				if(find_id == null){
					JOptionPane.showMessageDialog(null, "해당 정보에 맞는 아이디가 없습니다.");
				} else {
					JOptionPane.showMessageDialog(null, "찾으신 아이디는 " + find_id + "입니다.");
					new Login();
					dispose();					
				}
				
			}
		});
		
		
		Backbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Login();
				dispose();
				
			}
		});
		
	}

	public static void main(String[] args) {

	}

}
