package com.java.login;

import java.awt.Container;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.java.dao.LoginDAO;
import com.java.dto.LoginDTO;
import com.java.manager.Manager_Home;
import com.java.member.Member_Home;



public class Login extends JFrame{
	public JTextField IdText = new JTextField();
	public JPasswordField PwText = new JPasswordField();
	public boolean LoginCheck;
	public Font f1;
	public JButton Loginbtn;
	public JButton Signupbtn;
	public JButton FindIdbtn;
	public JButton FindPwbtn;	
	
	public Login() {
		//Container 사용
		Container Lct = getContentPane();
		//Jpanel 사용
		JPanel panel = new JPanel();
		Lct.add(panel);
		panel.setLayout(null);
		//JFrame 세팅
		setTitle("로그인"); //스윙 제목 
		setSize(370, 170); //사이즈
		setResizable(false); //사이즈 조절여부
		setLocationRelativeTo(null); //실행 시 센터에서 실행되는 여부
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //스윙 완전 종료

		LoginPanel(panel);
		setVisible(true); //패널이 출력됨

	}
	
	public void LoginPanel(JPanel panel) {

		f1 = new Font("맑은 고딕", Font.BOLD, 15);
		
		//id 텍스트
		JLabel IdLabel = new JLabel("아이디");
		IdLabel.setFont(f1);
		IdLabel.setBounds(20, 10, 80, 25);
		panel.add(IdLabel);
		
		//pw 텍스트

		JLabel PwLabel = new JLabel("비밀번호");
		PwLabel.setBounds(20, 40, 80, 25);
		PwLabel.setFont(f1);
		panel.add(PwLabel);
		
		//id TextField
		IdText = new JTextField(20);
		IdText.setBounds(90, 10, 160, 25);
		panel.add(IdText);

		//pw TextField
		PwText = new JPasswordField();
		PwText.setBounds(90, 40, 160, 25);
		panel.add(PwText);
		
		//로그인 버튼
		Loginbtn = new JButton("로그인");
		Loginbtn.setBounds(260, 18, 80, 40);
		panel.add(Loginbtn);
		
		//회원가입 버튼
		Signupbtn = new JButton("회원가입");
		Signupbtn.setBounds(20, 80, 90, 25);
		panel.add(Signupbtn);
		
		//아이디찾기 버튼
		FindIdbtn = new JButton("아이디찾기");
		FindIdbtn.setBounds(115, 80, 110, 25);
		panel.add(FindIdbtn);
		
		//비밀번호찾기 버튼
		FindPwbtn = new JButton("비밀번호찾기");
		FindPwbtn.setBounds(230, 80, 112, 25);
		panel.add(FindPwbtn);
		
		//---------------------------------------------------------
		//리스너 부분
		
		Loginbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				String id_lb = IdText.getText();
				String pw_lb = PwText.getText();
				String place = null;
				
				LoginDAO dao = new LoginDAO();
				LoginDTO dto = new LoginDTO();

				
				int result = dao.login(id_lb, pw_lb);
			
				if (result == 1) {
					JOptionPane.showMessageDialog(null, id_lb + "(관리자)님 환영합니다!");
					Manager_Home Mg_home = new Manager_Home(id_lb);
					dispose();
				} else if (result == 2) {
					JOptionPane.showMessageDialog(null, id_lb + "(회원)님 환영합니다!");
					Member_Home Mb_home = new Member_Home(id_lb);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "아이디, 비밀번호를 다시 확인해주세요.");
				}

			}
		});
		
		Signupbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new SignUp();
				dispose();
			}
		});
		
		FindIdbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Find_Id();
				dispose();
				
			}
		});
		
		FindPwbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Find_Pw();
				dispose();
			}
		});	
	}
		
	public static void main(String[] args) {
		
		new Login();
	}

}
