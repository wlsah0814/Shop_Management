package com.java.login;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.java.dao.SignUpDAO;
import com.java.dao.SignUp_IdCheckDAO;
import com.java.dao.CheckDAO;
import com.java.dto.SignUp_IdCheckDTO;

public class SignUp extends JFrame{
	public JTextField IdText;
	public JTextField PwText;
	public JTextField NameText;
	public JTextField EmailText;
	public JTextField PhoneText;
	public JLabel IdLabel;
	public JLabel PwLabel;
	public JLabel NameLabel;
	public JLabel EmailLabel;
	public JLabel PhoneLabel;
	public JLabel SortationLabel;
	public JLabel BranchLabel;
	public JButton Backbtn;
	public JButton Resetbtn;
	public JButton Signbtn;
	public JButton CheckIdbtn;
	public JRadioButton ManagerRb; 
	public JRadioButton MemberRb; 
	public ButtonGroup Sortation;
	public JComboBox BranchCb;
	public boolean LoginCheck;
	public Font f1;
	public Font f2;
	public Font f3;
	public Font f4;
	
	public SignUp() {
		//Container 안에 panel 적용
		Container Singct = getContentPane();
		//Jpanel 사용
		JPanel panel = new JPanel();
		Singct.add(panel);
		panel.setLayout(null);
		
		//Jpanel 세팅
		setTitle("회원가입"); //스윙 제목
		setSize(390, 500); //사이즈
		setResizable(true); //사이즈 조절여부
		setLocationRelativeTo(null); //실행 시 센터에서 실행되는 여부
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //스윙 완전 종료
		
		joinPanel(panel);
		setVisible(true); //패널이 출력됨
	}
	
	public void joinPanel(JPanel panel) {

		f1 = new Font("맑은 고딕", Font.BOLD, 15); //텍스트 폰트
		f2 = new Font("맑은 고딕", Font.BOLD, 15); //라디오버튼 폰트
		f3 = new Font("맑은 고딕", Font.BOLD, 13); //중복확인버튼 폰트
		f4 = new Font("맑은 고딕", Font.BOLD, 16); //이전, 초기화, 회원가입 버튼 폰트

		//---------------------------------------라벨---------------------------------------
		
		
		//id 텍스트

		IdLabel = new JLabel("아이디");
		IdLabel.setFont(f1);
		IdLabel.setBounds(20, 30, 80, 25);
		panel.add(IdLabel);
		
		JLabel Idhint = new JLabel("* 6~15자리 영문/숫자 입력");
		Idhint.setBounds(100, 60, 150, 15);
		Idhint.setForeground(Color.RED);
		panel.add(Idhint);
		
		//pw 텍스트

		PwLabel = new JLabel("비밀번호");
		PwLabel.setBounds(20, 80, 80, 25);
		PwLabel.setFont(f1);
		panel.add(PwLabel);
		
		JLabel Pwhint = new JLabel("* 8~15 숫자 / 대소문자 / 특수기호를 포함");
		Pwhint.setBounds(100, 110, 250, 15);
		Pwhint.setForeground(Color.RED);
		panel.add(Pwhint);
		
		//name 텍스트

		NameLabel = new JLabel("성명");
		NameLabel.setBounds(20, 130, 80, 25);
		NameLabel.setFont(f1);
		panel.add(NameLabel);
		
		//email 텍스트
	
		EmailLabel = new JLabel("이메일");
		EmailLabel.setBounds(20, 180, 80, 25);
		EmailLabel.setFont(f1);
		panel.add(EmailLabel);
		
		//phone 텍스트
	
		PhoneLabel = new JLabel("핸드폰 번호");
		PhoneLabel.setBounds(20, 230, 80, 25);
		PhoneLabel.setFont(f1);
		panel.add(PhoneLabel);
		
		JLabel Phhint = new JLabel("* - 제외하고 입력");
		Phhint.setBounds(110, 260, 250, 15);
		Phhint.setForeground(Color.RED);
		panel.add(Phhint);
		
		//Sortation(구분) 텍스트

		SortationLabel = new JLabel("구분");
		SortationLabel.setBounds(20, 320, 80, 25);
		SortationLabel.setFont(f1);
		panel.add(SortationLabel);
		

		BranchLabel = new JLabel("지점");
		BranchLabel.setBounds(20, 280, 80, 25);
		BranchLabel.setFont(f1);
		panel.add(BranchLabel);
		
		
		
		
		//---------------------------------------텍스트필드---------------------------------------
		
		
		//id TextField
		IdText = new JTextField();
		IdText.setBounds(100, 30, 160, 30);
		panel.add(IdText);

		//pw TextField
		PwText = new JPasswordField();
		PwText.setBounds(100, 80, 160, 30);
		panel.add(PwText);
		
		//name TextField
		NameText = new JTextField();
		NameText.setBounds(100, 130, 160, 30);
		panel.add(NameText);
		
		//email TextField
		EmailText = new JTextField();
		EmailText.setBounds(100, 180, 160, 30);
		panel.add(EmailText);
		
		//phone TextField
		PhoneText = new JTextField();
		PhoneText.setBounds(110, 230, 150, 30);
		panel.add(PhoneText);
		
		
	//---------------------------------------콤보박스------------------------------------
		
		//지점 콤보박스
		BranchCb = new JComboBox();
		BranchCb.addItem("본사");
		BranchCb.addItem("강남점");
		BranchCb.addItem("명동점");
		BranchCb.addItem("영등포점");
		BranchCb.addItem("건대점");
		BranchCb.addItem("잠실점");
		BranchCb.setBounds(100, 278, 90, 30);
		panel.add(BranchCb);
		
		
	//---------------------------------------버튼---------------------------------------

		
		//중복확인 버튼
		CheckIdbtn = new JButton("중복확인");
		CheckIdbtn.setBounds(270, 30, 87, 30);
		CheckIdbtn.setFont(f3);
		panel.add(CheckIdbtn);
		
		//회원가입 버튼
		Signbtn = new JButton("회원가입");
		Signbtn.setBounds(248, 380, 110, 40);
		Signbtn.setFont(f4);
		panel.add(Signbtn);
		
		//리셋 버튼
		Resetbtn = new JButton("다시쓰기");
		Resetbtn.setBounds(133, 380, 110, 40);
		Resetbtn.setFont(f4);
		panel.add(Resetbtn);
		
		//이전 버튼
		Backbtn = new JButton("이전");
		Backbtn.setBounds(18, 380, 110, 40);
		Backbtn.setFont(f4);
		panel.add(Backbtn);
		
		//Sortation(구분) 라디오버튼(1) true로 기본값 설정
		ManagerRb = new JRadioButton("본사회원", true);
		ManagerRb.setBounds(95, 318, 90, 30);
		ManagerRb.setFont(f2);
		panel.add(ManagerRb);
		
		//Sortation(구분) 라디오버튼(2)
		MemberRb = new JRadioButton("매장회원");
		MemberRb.setBounds(180, 318, 90, 30);
		MemberRb.setFont(f2);
		panel.add(MemberRb);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(ManagerRb);
		bg.add(MemberRb);
		
		
		//---------------------------------------------------------
		//리스너 부분
		
		BranchCb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(BranchCb.getSelectedIndex() == 0) {
					ManagerRb.setSelected(true);
				} else if(BranchCb.getSelectedIndex() != 0){
					MemberRb.setSelected(true);
				}
				
			}
		});
		
		
		//이전 버튼
		Backbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Login();
				dispose();
				
			}
		});
		
		//다시쓰기 버튼
		Resetbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				IdText.setText(null);
				PwText.setText(null);
				NameText.setText(null);
				EmailText.setText(null);
				PhoneText.setText(null);
				BranchCb.setSelectedIndex(0);
				ManagerRb.setSelected(true);
				
			}
		});
		
		CheckIdbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String check_id = IdText.getText();
				
				SignUp_IdCheckDAO dao = new SignUp_IdCheckDAO();
				int result = dao.idCheck_select(check_id);
				
				
				
				if(result == 1) {
					JOptionPane.showMessageDialog(null, "중복된 아이디입니다.");
					IdText.setText(null);
				} else if(result == 2) {
					JOptionPane.showMessageDialog(null, "사용가능한 아이디입니다.");
				}
			}
		});
		
		
		//회원가입 버튼
		Signbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				SignUpDAO dao = new SignUpDAO();
				
				String id = IdText.getText();
				String pw = PwText.getText();
				String name = NameText.getText();
				String email = EmailText.getText();
				String phone = PhoneText.getText();
				String branch = BranchCb.getSelectedItem().toString();
				int sort = 0;
			
				CheckDAO check = new CheckDAO();
				
				if(check.regularID(id) == false) {
					 JOptionPane.showMessageDialog(null, "6~15자리 영문/숫자만 사용가능합니다.");	
					 return;
				} else if(check.regularPW(pw) == false) {
					 JOptionPane.showMessageDialog(null, "8~15자리, 숫자/대문자/소문자/특수문자를 모두 포함해야 합니다.");
					 return;
				} else if(check.regularName(name) == false ) {
					 JOptionPane.showMessageDialog(null, "2~4자리 이름으로 입력하세요");
					 return;
				} else if(check.regularEmail(email) == false) {
					 JOptionPane.showMessageDialog(null, "이메일을 다시 확인해주세요");
					 return;
				} else if(check.regularPhone(phone) == false) {
					JOptionPane.showMessageDialog(null, "핸드폰번호를 다시 확인해주세요");
					return;
				}
				
				if(ManagerRb.isSelected()) {
					sort = 1;
				} else if(MemberRb.isSelected()){
					sort = 2;
				}  
				
				//DAO에 값 넣어서 insert문 실행
				dao.signup_insert(id, branch, pw, name, email, phone, sort);
				JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다!");
				new Login();
				dispose();
			}
		});
		
	}
	public static void main(String[] args) {
		new SignUp();
	}

}
