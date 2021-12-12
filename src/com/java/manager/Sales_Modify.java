package com.java.manager;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.java.dao.SalesDAO;

public class Sales_Modify extends JFrame{
	public Sales_Modify(Object sales, String place) {
		
		Container SMct = getContentPane();
		JPanel panel = new JPanel();
		SMct.add(panel);
		panel.setLayout(null);
		//JFrame 세팅
		setTitle("목표매출 수정"); //스윙 제목
		setSize(400, 300); //사이즈
		setResizable(false); //사이즈 조절여부
		setLocationRelativeTo(null); //실행 시 센터에서 실행되는 여부

		sales_modify(panel, sales, place);
		setVisible(true); //패널이 출력됨	
	}

	private void sales_modify(JPanel panel, Object sales, String place) {
		
		
		Font f1 = new Font("맑은 고딕", Font.BOLD, 25);
		Font f2 = new Font("맑은 고딕", Font.BOLD, 18);
		
		JLabel title = new JLabel(place);
		title.setFont(f1);
		title.setBounds(10, 0, 200, 50);
		panel.add(title);
		
		JLabel now_lb = new JLabel("현재 목표매출 : ");
		now_lb.setFont(f2);
		now_lb.setBounds(15, 70, 140, 30);
		panel.add(now_lb);
		
		JLabel now_lb2 = new JLabel(String.valueOf(sales));
		now_lb2.setFont(f2);
		now_lb2.setBounds(150, 70, 150, 30);
		panel.add(now_lb2);
		
		
		JLabel change_lb  = new JLabel("변경할 목표매출 : ");
		change_lb.setFont(f2);
		change_lb.setBounds(15, 130, 150, 30);
		panel.add(change_lb);
		
		JTextField change_lb2 = new JTextField("변경할 목표매출 입력");
		change_lb2.setFont(f2);
		change_lb2.setBounds(165, 130, 200, 30);
		panel.add(change_lb2);
		
		JButton modifybtn = new JButton("변경하기");
		modifybtn.setBounds(265, 180, 100, 40);
		panel.add(modifybtn);
		
		JButton backbtn = new JButton("이전");
		backbtn.setBounds(10, 180, 100, 40);
		panel.add(backbtn);
		
		backbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		
		modifybtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String change_sales = change_lb2.getText();
				int sales = Integer.parseInt(change_sales);
				
				SalesDAO dao = new SalesDAO();
				
				dao.sales_update(sales, place);
				dispose();
			}
		});
		
		change_lb2.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				change_lb2.setText("");
			}
		});
	}



	public static void main(String[] args) {
		new Sales_Modify(0,null);
	}
}

