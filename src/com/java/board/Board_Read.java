package com.java.board;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
//import javax.xml.bind.ParseConversionEvent;

import com.java.dao.Board_DeleteDAO;
import com.java.dao.Board_ModifyDAO;
import com.java.dao.Board_ReadDAO;
import com.java.dto.Board_ReadDTO;

public class Board_Read extends JFrame{

	public JLabel id_lb2; 
	public JLabel today_lb;
	public JTextField title_tf;
	public JTextArea notice_ar;
	public JLabel branch_lb2;
	public JButton modifybtn;
	public JButton deletebtn;
	public JLabel number_lb;
	public Board_Read(String id, String title, String place, String board_id) {
			
			Container BRct = getContentPane();
			JPanel panel = new JPanel();
			BRct.add(panel);
			panel.setLayout(null);
			//JFrame 세팅
			setTitle("게시글"); //스윙 제목s
			setSize(450, 600); //사이즈
			setResizable(false); //사이즈 조절여부
			setLocationRelativeTo(null); //실행 시 센터에서 실행되는 여부

			board_read(panel, id, title, place, board_id);
			setVisible(true); //패널이 출력됨	
		}
	
	
	private void board_read(JPanel panel, String id, String title, String place, String board_id) {
		String login_id = id;
		String login_place = place;
		String b_id = board_id;
		
		Font f1 = new Font("맑은 고딕", Font.BOLD, 25);
		Font f2 = new Font("맑은 고딕", Font.BOLD, 18);
		Font f3 = new Font("맑은 고딕", Font.PLAIN, 20);
		
		number_lb = new JLabel();
		
		JLabel notice_write_lb = new JLabel("게시글");
		notice_write_lb.setFont(f1);
		notice_write_lb.setBounds(10, 0, 200, 50);
		panel.add(notice_write_lb);
		
		
		JLabel id_lb = new JLabel("아이디 : ");
		id_lb.setFont(f2);
		id_lb.setBounds(15, 60, 75, 50);
		panel.add(id_lb);
		
		id_lb2 = new JLabel();
		id_lb2.setFont(f2);
		id_lb2.setBounds(90, 35, 100, 100);
		panel.add(id_lb2);
		
		
		JLabel branch_lb = new JLabel("지점명 :");
		branch_lb.setFont(f2);
		branch_lb.setBounds(15, 110, 65, 50);
		panel.add(branch_lb);
		
		branch_lb2 = new JLabel();
		branch_lb2.setFont(f2);
		branch_lb2.setBounds(90, 85, 100, 100);
		panel.add(branch_lb2);
		
		
		JLabel day_lb = new JLabel("날짜 : ");
		day_lb.setFont(f2);
		day_lb.setBounds(15, 160, 65, 50);
		panel.add(day_lb);
		
		today_lb= new JLabel();
		today_lb.setFont(f2);
		today_lb.setBounds(73, 135, 100, 100);
		panel.add(today_lb);
		
		JLabel title_lb = new JLabel("제목 : ");
		title_lb.setFont(f2);
		title_lb.setBounds(15, 220, 70, 50);
		panel.add(title_lb);
		
		title_tf = new JTextField();
		title_tf.setFont(f3);
		title_tf.setBounds(70, 230, 300, 30);
		panel.add(title_tf);
	
		JLabel notice_lb = new JLabel("게시글");
		notice_lb.setFont(f2);
		notice_lb.setBounds(15, 270, 65, 50);
		panel.add(notice_lb);
		
		notice_ar = new JTextArea();
		notice_ar.setFont(f3);
		notice_ar.setBounds(15, 320, 400, 170);
		panel.add(notice_ar);
		
		modifybtn = new JButton("게시글수정");
		modifybtn.setBounds(315, 500, 100, 40);
		panel.add(modifybtn);
		
		deletebtn= new JButton("게시글삭제");
		deletebtn.setBounds(200, 500, 100, 40);
		panel.add(deletebtn);
		
		
		JButton backbtn = new JButton("닫기");
		backbtn.setBounds(15, 500, 100, 40);
		panel.add(backbtn);
	
		
		
		

		
	//====================================리스너=============================
		modifybtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			String check_num = number_lb.getText();
			String update_date = today_lb.getText();
			String update_title = title_tf.getText();
			String update_text = notice_ar.getText();
			Board_ModifyDAO dao = new Board_ModifyDAO();
			
			int number = Integer.parseInt(check_num);
			
			dao.board_modify(update_date, update_title, update_text, number);
			dispose();
			
			}
		});
		
		deletebtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Board_DeleteDAO dao = new Board_DeleteDAO();
				String check_num = number_lb.getText();
				int number = Integer.parseInt(check_num);
				
				int result = JOptionPane.showConfirmDialog(null, "삭제하시겠습니까?", "삭제확인", JOptionPane.YES_NO_OPTION);
				
				if(result == JOptionPane.YES_OPTION) {
					dao.board_delect(number);
					JOptionPane.showMessageDialog(null, "게시글이 삭제되었습니다.");
					dispose();
				} else if(result == JOptionPane.CLOSED_OPTION) {
					JOptionPane.showMessageDialog(null, "게시글 삭제가 취소되었습니다.");
				}
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
		
	}

}
