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

import com.java.dao.Board_WriteDAO;
import com.java.dao.MyPageDAO;
import com.java.dto.MyPageDTO;

public class Board_Write extends JFrame{
	
	public Board_Write(String id) {
		Container BWct = getContentPane();
		JPanel panel = new JPanel();
		BWct.add(panel);
		panel.setLayout(null);
		//JFrame 세팅
		setTitle("게시글 작성"); //스윙 제목
		setSize(450, 600); //사이즈
		setResizable(false); //사이즈 조절여부
		setLocationRelativeTo(null); //실행 시 센터에서 실행되는 여부

		board_write(panel, id);
		setVisible(true); //패널이 출력됨

	}
	
	public void board_write(JPanel panel, String id) {
		ArrayList<MyPageDTO> dtos = new ArrayList<MyPageDTO>();
		MyPageDAO dao = new MyPageDAO();
		String login_id = id;
		String m_place = null;
		
		dtos = dao.myinfo_select(login_id);
		
		for(int i = 0; i<dtos.size(); i++) {
			m_place = dtos.get(i).getPlace();
		}
		
		
		Font f1 = new Font("맑은 고딕", Font.BOLD, 25);
		Font f2 = new Font("맑은 고딕", Font.BOLD, 18);
		Font f3 = new Font("맑은 고딕", Font.PLAIN, 20);
		
		JLabel notice_write_lb = new JLabel("게시글 작성");
		notice_write_lb.setFont(f1);
		notice_write_lb.setBounds(10, 0, 200, 50);
		panel.add(notice_write_lb);
		
		JLabel id_lb = new JLabel("아이디 : ");
		id_lb.setFont(f2);
		id_lb.setBounds(15, 60, 75, 50);
		panel.add(id_lb);
		
		JLabel id_lb2 = new JLabel(login_id);
		id_lb2.setFont(f2);
		id_lb2.setBounds(90, 35, 100, 100);
		panel.add(id_lb2);
		
		
		JLabel branch_lb = new JLabel("지점명 :");
		branch_lb.setFont(f2);
		branch_lb.setBounds(15, 110, 65, 50);
		panel.add(branch_lb);
		
		JLabel branch_lb2 = new JLabel(m_place);
		branch_lb2.setFont(f2);
		branch_lb2.setBounds(90, 85, 100, 100);
		panel.add(branch_lb2);
		
		
		JLabel day_lb = new JLabel("날짜 : ");
		day_lb.setFont(f2);
		day_lb.setBounds(15, 160, 65, 50);
		panel.add(day_lb);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String today = format.format(date);
		JLabel today_lb= new JLabel(today);
		today_lb.setFont(f2);
		today_lb.setBounds(73, 135, 100, 100);
		panel.add(today_lb);
		
		JLabel title_lb = new JLabel("제목 : ");
		title_lb.setFont(f2);
		title_lb.setBounds(15, 220, 70, 50);
		panel.add(title_lb);
		
		JTextField title_tf = new JTextField();
		title_tf.setFont(f3);
		title_tf.setBounds(70, 230, 300, 30);
		panel.add(title_tf);
	
		JLabel notice_lb = new JLabel("게시글");
		notice_lb.setFont(f2);
		notice_lb.setBounds(15, 270, 65, 50);
		panel.add(notice_lb);
		
		JTextArea notice_ar = new JTextArea();
		notice_ar.setFont(f3);
		notice_ar.setBounds(15, 320, 400, 170);
		panel.add(notice_ar);
		
		JButton resetbtn = new JButton("다시쓰기");
		resetbtn.setBounds(313, 285, 100, 30);
		panel.add(resetbtn);
		
		JButton backbtn = new JButton("이전");
		backbtn.setBounds(15, 500, 100, 40);
		panel.add(backbtn);
		
		JButton writebtn = new JButton("게시글작성");
		writebtn.setBounds(315, 500, 100, 40);
		panel.add(writebtn);
		
		backbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		resetbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				notice_ar.setText("");
			}
		});
		
		writebtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Board_WriteDAO dao = new Board_WriteDAO();
				String title = title_tf.getText();
				String notice = notice_ar.getText();
				String place = branch_lb2.getText();
				
				int result = JOptionPane.showConfirmDialog(null, "작성하시겠습니까?", "작성확인", JOptionPane.YES_NO_OPTION);
				
				if(result == JOptionPane.YES_OPTION) {
					dao.board_write(login_id, place, title, notice);
					JOptionPane.showMessageDialog(null, "게시글이 작성되었습니다.");
					dispose();
				} else if(result == JOptionPane.CLOSED_OPTION) {
					JOptionPane.showMessageDialog(null, "게시글작성이 취소되었습니다.");
				}
			
			}
		});
	}

	public static void main(String[] args) {
		
	}

}
