package com.java.mypage;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.java.dao.ChangePwDAO;
import com.java.dao.MyPage_ModifyCheckDAO;
import com.java.dao.MyPage_ModifyDAO;

public class MyPage_Modify extends JFrame{


	public MyPage_Modify(String id, String name) {
		String login_id = id;
		String myname = name;
		
		Container Modifyct = getContentPane();
		JPanel panel = new JPanel();
		Modifyct.add(panel);
		panel.setLayout(null);

		// Jpanel 세팅
		setTitle("내정보 수정"); // 스윙 제목
		setSize(330, 570); // 사이즈
		setResizable(true); // 사이즈 조절여부
		setLocationRelativeTo(null); // 실행 시 센터에서 실행되는 여부
		modify(panel, login_id, myname);
		setVisible(true); // 패널이 출력됨
		

	}
	
	public void modify(JPanel panel, String id, String name) {

		String login_id = id;
		String myname = name;

		
		Font f1 = new Font("맑은 고딕", Font.BOLD, 15); //텍스트 폰트
		Font f2 = new Font("맑은 고딕", Font.BOLD, 15); //라디오버튼 폰트
		Font f3 = new Font("맑은 고딕", Font.BOLD, 13); //중복확인버튼 폰트
		Font f4 = new Font("맑은 고딕", Font.BOLD, 13); //이전, 초기화, 회원가입 버튼 폰트
		
		
		//---------------------------------------라벨---------------------------------------
		
		
		//id 텍스트
		JLabel id_lb = new JLabel("아이디 : ");
		id_lb.setFont(f1);
		id_lb.setBounds(20, 15, 90, 25);
		panel.add(id_lb);
		
		//pw 텍스트

		JLabel now_lb = new JLabel("현재 비밀번호 : ");
		now_lb.setBounds(20, 55, 115, 25);
		now_lb.setFont(f1);
		panel.add(now_lb);
		
		JLabel change_lb = new JLabel("변경 비밀번호 : ");
		change_lb.setBounds(20, 100, 115, 25);
		change_lb.setFont(f1);
		panel.add(change_lb);
		
		JLabel change_lb2 = new JLabel("변경 비밀번호 확인 : ");
		change_lb2.setBounds(20, 150, 160, 25);
		change_lb2.setFont(f1);
		panel.add(change_lb2);
		
		JLabel Pwhint = new JLabel("* 8~15 숫자 / 대소문자 / 특수기호를 포함");
		Pwhint.setBounds(20, 190, 250, 15);
		Pwhint.setForeground(Color.RED);
		panel.add(Pwhint);
		
		//name 텍스트

		JLabel name_lb = new JLabel("이름 : ");
		name_lb.setBounds(20, 210, 80, 25);
		name_lb.setFont(f1);
		panel.add(name_lb);
		
		//email 텍스트

		JLabel email_lb = new JLabel("이메일 : ");
		email_lb.setBounds(20, 255, 90, 25);
		email_lb.setFont(f1);
		panel.add(email_lb);
		
		//phone 텍스트

		JLabel phone_lb= new JLabel("핸드폰번호 : ");
		phone_lb.setBounds(20, 305, 90, 25);
		phone_lb.setFont(f1);
		panel.add(phone_lb);
		
		//구분
		JLabel sort_lb= new JLabel("구분 : ");
		sort_lb.setBounds(20, 396, 80, 25);
		sort_lb.setFont(f1);
		panel.add(sort_lb);
		
		
		//Branch 텍스트
		
		JLabel branch_lb = new JLabel("지점 : ");
		branch_lb.setBounds(20, 350, 80, 25);
		branch_lb.setFont(f1);
		panel.add(branch_lb);
		
		
		//---------------------------------------값 표시 라벨---------------------------------------
		
		JLabel id_lb2 = new JLabel(login_id);
		id_lb2.setFont(f1);
		id_lb2.setBounds(83, 2, 100, 50);
		panel.add(id_lb2);
		
		JPasswordField now_lb2 = new JPasswordField();
		now_lb2.setFont(f4);
		now_lb2.setBounds(130, 50, 160, 35);
		panel.add(now_lb2);
		
		JPasswordField change_lb3 = new JPasswordField();
		change_lb3.setFont(f4);
		change_lb3.setBounds(130, 100, 160, 35);
		panel.add(change_lb3);
		
		JPasswordField change_lb4 = new JPasswordField();
		change_lb4.setFont(f4);
		change_lb4.setBounds(165, 145, 130, 35);
		panel.add(change_lb4);
		
		JLabel name_lb2 = new JLabel(myname);
		name_lb2.setFont(f1);
		name_lb2.setBounds(70, 194, 100, 50);
		panel.add(name_lb2);
		
		JTextField email_lb2 = new JTextField();
		email_lb2.setFont(f4);
		email_lb2.setBounds(85, 250, 100, 35);
		panel.add(email_lb2);
		
		JTextField phone_lb2 = new JTextField();
		phone_lb2.setFont(f4);
		phone_lb2.setBounds(113, 300, 100, 35);
		panel.add(phone_lb2);
		
		
		
		JRadioButton ManagerRb = new JRadioButton("본사회원");
		ManagerRb.setFont(f1);
		ManagerRb.setBounds(70, 400, 100, 20);
		panel.add(ManagerRb);
		
		JRadioButton MemberRb = new JRadioButton("매장회원");
		MemberRb.setFont(f1);
		MemberRb.setBounds(170, 400, 100, 20);
		panel.add(MemberRb);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(ManagerRb);
		bg.add(MemberRb);
		
		JComboBox branch_cb = new JComboBox();
		branch_cb.addItem("본사");
		branch_cb.addItem("강남점");
		branch_cb.addItem("명동점");
		branch_cb.addItem("영등포점");
		branch_cb.addItem("건대점");
		branch_cb.addItem("잠실점");
		branch_cb.setBounds(70, 350, 90, 30);
		panel.add(branch_cb);
		
		
		
	//---------------------------------------버튼---------------------------------------
		
		
		//수정하기 버튼
		JButton Modifybtn = new JButton("수정하기");
		Modifybtn.setBounds(152, 450, 110, 40);
		Modifybtn.setFont(f4);
		panel.add(Modifybtn);
		
		//이전 버튼
		JButton Backbtn = new JButton("이전");
		Backbtn.setBounds(18, 450, 110, 40);
		Backbtn.setFont(f4);
		panel.add(Backbtn);
		
		
		//---------------------------------------------------------
		//리스너 부분
				
		Backbtn.addActionListener(new ActionListener() {
					
				@Override
				public void actionPerformed(ActionEvent e) {
					new MyPage(login_id);
					dispose();
				}
			});
		
		Modifybtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				MyPage_ModifyDAO dao = new MyPage_ModifyDAO();
				MyPage_ModifyCheckDAO check = new MyPage_ModifyCheckDAO();
				ChangePwDAO pwdao = new ChangePwDAO();
				
				String now_pw = now_lb2.getText();
				String change_pw = change_lb3.getText();
				String change_pw2 = change_lb4.getText();
				String email = email_lb2.getText();
				String phone = phone_lb2.getText();
				int sort = 0;
				String place = branch_cb.getSelectedItem().toString();
				String id = id_lb2.getText();
				
				
				if(check.regularPW(change_pw) == false) {
					JOptionPane.showMessageDialog(null, "8~15자리, 숫자/대문자/소문자/특수문자를 모두 포함해야 합니다.");
					return;
				} else if(check.regularEmail(email) == false) {
					JOptionPane.showMessageDialog(null, "이메일을 다시 확인해주세요");
					return;
				} else if(check.regularPhone(phone) == false ) {
					JOptionPane.showMessageDialog(null, "핸드폰번호를 다시 확인해주세요");
					return;
				}
				
				int result = JOptionPane.showConfirmDialog(null, "수정하시겠습니까?", "수정확인", JOptionPane.YES_NO_OPTION);
				
				if (result == JOptionPane.YES_OPTION) {
					if (now_lb == null) {
						JOptionPane.showMessageDialog(null, "현재 비밀번호를 입력해주세요");
					} else if (change_lb3 == null) {
						JOptionPane.showMessageDialog(null, "변경 비밀번호를 입력해주세요");
					} else if (change_lb4 == null) {
						JOptionPane.showMessageDialog(null, "변경 비밀번호 확인을 입력해주세요");
					} else if (email == null) {
						JOptionPane.showMessageDialog(null, "변경할 이메일을 입력해주세요");
					} else if (phone == null) {
						JOptionPane.showMessageDialog(null, "변경할 핸드폰번호를 입력해주세요");
					}

					
					
					
					if(ManagerRb.isSelected()) {
						sort = 1;
					} else if(MemberRb.isSelected()){
						sort = 2;
					}  
					
					
					int pwresult = pwdao.changepw(now_pw,login_id);
					if(pwresult == 1) {
						if(change_pw.equals(change_pw2)) {
							dao.mypage_update(change_pw, email, phone, sort, place, id);
							JOptionPane.showMessageDialog(null, "정보가 수정되었습니다.");
							new MyPage(login_id);
							dispose();
						}
							
						} else {
							JOptionPane.showMessageDialog(null, "현재 비밀번호와 다릅니다.");
					}
					
					

				} else if (result == JOptionPane.CLOSED_OPTION) {
					System.out.println("다이얼로그 취소");
				} else {
					JOptionPane.showMessageDialog(null, "취소하셨습니다.");
				}
			}
		});
		
		branch_cb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (branch_cb.getSelectedIndex() == 0) {
					ManagerRb.setSelected(true);
				} else if (branch_cb.getSelectedIndex() != 0) {
					MemberRb.setSelected(true);
				}

			}
		});
		
	}

	
	public static void main(String[] args) {
		new MyPage_Modify(null, null);
	}

}
