package com.java.mypage;

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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.java.dao.MyPageDAO;
import com.java.dto.MyPageDTO;

public class MyPage extends JFrame{
	private JLabel IdLabel;
	private JLabel PwLabel;
	private JLabel NameLabel;
	private JLabel EmailLabel;
	private JLabel PhoneLabel;
	private JLabel SortationLabel;
	private JLabel BranchLabel;
	public JLabel id_lb2;
	public JPasswordField pw_lb2;
	public JLabel name_lb2;
	public JLabel email_lb2;
	public JLabel phone_lb2;
	public JLabel sort_lb2;
	public JLabel branch_lb2;
	private JButton Backbtn;
	private JButton Modifybtn;
	private JButton Joinbtn;
	private JButton CheckIdbtn;
	private JRadioButton ManagerRb; 
	private JRadioButton MemberRb; 
	private ButtonGroup Sortation;
	private JComboBox BranchCb;
	private boolean LoginCheck;
	private Font f1;
	private Font f2;
	private Font f3;
	private Font f4;
	
	public MyPage(String id) {
		String login_id = id;
		
		//Container 안에 panel 적용
		Container Mpct = getContentPane();
		//Jpanel 사용
		JPanel panel = new JPanel();
		Mpct.add(panel);
		
		//Jpanel 세팅
		setTitle("내정보"); //스윙 제목
		setSize(300, 500); //사이즈
		setResizable(true); //사이즈 조절여부
		setLocationRelativeTo(null); //실행 시 센터에서 실행되는 여부
		
		panel.setLayout(null);
		mypagePanel(panel, id);
		setVisible(true); //패널이 출력됨
	}
	
	public void mypagePanel(JPanel panel, String id) {

		ArrayList<MyPageDTO> dtos = new ArrayList<MyPageDTO>();
		MyPageDAO dao = new MyPageDAO();
		
		String login_id = id;
		
		String id_lb = null;
		String pw_lb = null;
		String pw_star = pw_lb;
		String name_lb = null;
		String email_lb = null;
		String phone_lb = null;
		String point_lb = null;
		String sort_lb = null;
		int grade_lb = 0;
		
		
		f1 = new Font("맑은 고딕", Font.BOLD, 15); //텍스트 폰트
		f2 = new Font("맑은 고딕", Font.BOLD, 15); //라디오버튼 폰트
		f3 = new Font("맑은 고딕", Font.BOLD, 13); //중복확인버튼 폰트
		f4 = new Font("맑은 고딕", Font.BOLD, 16); //이전, 초기화, 회원가입 버튼 폰트
		
		//---------------------------------------라벨---------------------------------------
		
		dtos = dao.myinfo_select(login_id);
		
		for(int i = 0; i<dtos.size(); i++) {
			id_lb = dtos.get(i).getId();
			pw_lb = dtos.get(i).getPw();
			name_lb = dtos.get(i).getName();
			email_lb = dtos.get(i).getEmail();
			phone_lb = dtos.get(i).getPhone();
			grade_lb = dtos.get(i).getGrade();
			point_lb = dtos.get(i).getPlace();
		}
		
		if(grade_lb == 1) {
			sort_lb = "본사회원";
		} else if(grade_lb == 2) {
			sort_lb = "매장회원";
		}
		

		
		//id 텍스트
		IdLabel = new JLabel("아이디 : ");
		IdLabel.setFont(f1);
		IdLabel.setBounds(20, 30, 90, 25);
		panel.add(IdLabel);
		
		//pw 텍스트
		PwLabel = new JLabel("비밀번호 : ");
		PwLabel.setBounds(20, 80, 90, 25);
		PwLabel.setFont(f1);
		panel.add(PwLabel);
		
		//name 텍스트

		NameLabel = new JLabel("성명 : ");
		NameLabel.setBounds(20, 130, 80, 25);
		NameLabel.setFont(f1);
		panel.add(NameLabel);
		
		//email 텍스트

		EmailLabel = new JLabel("이메일 : ");
		EmailLabel.setBounds(20, 180, 90, 25);
		EmailLabel.setFont(f1);
		panel.add(EmailLabel);
		
		//phone 텍스트

		PhoneLabel = new JLabel("핸드폰번호 : ");
		PhoneLabel.setBounds(20, 230, 90, 25);
		PhoneLabel.setFont(f1);
		panel.add(PhoneLabel);
		
		//구분
		SortationLabel = new JLabel("구분 : ");
		SortationLabel.setBounds(20, 280, 80, 25);
		SortationLabel.setFont(f1);
		panel.add(SortationLabel);
		
		
		//Branch 텍스트
		
		BranchLabel = new JLabel("지점 : ");
		BranchLabel.setBounds(20, 323, 80, 25);
		BranchLabel.setFont(f1);
		panel.add(BranchLabel);
		
		
		//---------------------------------------값 표시 라벨---------------------------------------
	
		
		id_lb2 = new JLabel(id_lb);
		id_lb2.setFont(f4);
		id_lb2.setBounds(83, 17, 100, 50);
		panel.add(id_lb2);
		
		pw_lb2 = new JPasswordField(pw_lb);
		pw_lb2.enable(false);
		pw_lb2.setFont(f4);
		pw_lb2.setBounds(100, 80, 100, 30);
		panel.add(pw_lb2);
		
		
		name_lb2 = new JLabel(name_lb);
		name_lb2.setFont(f4);
		name_lb2.setBounds(73, 117, 100, 50);
		panel.add(name_lb2);
		
		email_lb2 = new JLabel(email_lb);
		email_lb2.setFont(f4);
		email_lb2.setBounds(85, 167, 150, 50);
		panel.add(email_lb2);
		
		phone_lb2 = new JLabel(phone_lb);
		phone_lb2.setFont(f4);
		phone_lb2.setBounds(113, 217, 100, 50);
		panel.add(phone_lb2);
		
		sort_lb2 = new JLabel(sort_lb);
		sort_lb2.setFont(f1);
		sort_lb2.setBounds(70, 242, 100, 100);
		panel.add(sort_lb2);
		
		branch_lb2 = new JLabel(point_lb);
		branch_lb2.setFont(f1);
		branch_lb2.setBounds(70, 285, 100, 100);
		panel.add(branch_lb2);
		
		
		
		
	//---------------------------------------버튼---------------------------------------
		
		
		//수정하기 버튼
		Modifybtn = new JButton("수정하기");
		Modifybtn.setBounds(152, 380, 110, 40);
		Modifybtn.setFont(f4);
		panel.add(Modifybtn);
		
		//이전 버튼
		Backbtn = new JButton("이전");
		Backbtn.setBounds(18, 380, 110, 40);
		Backbtn.setFont(f4);
		panel.add(Backbtn);
		
		
		//---------------------------------------------------------
		//리스너 부분
				
		Backbtn.addActionListener(new ActionListener() {
					
				@Override
				public void actionPerformed(ActionEvent e) {
					String shop = sort_lb2.getText();
					String id_lb = id_lb2.getText();
					String branch = branch_lb2.getText();
					dispose();
				}
			});
		
		Modifybtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = name_lb2.getText();
				
				new MyPage_Modify(login_id, name);
				dispose();
				
			}
		});
		
	}
	public static void main(String[] args) {
		
	}

}
