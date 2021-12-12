package com.java.member;


import java.awt.Component;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.java.board.Board_Read;
import com.java.dao.Board_ReadDAO;
import com.java.dao.MyPageDAO;
import com.java.dto.Board_ReadDTO;
import com.java.dto.MyPageDTO;

public class Member_TableCell1 extends AbstractCellEditor implements TableCellEditor, TableCellRenderer{
	
	
	JButton jb;
	String text = null;
	
	public Member_TableCell1(Member_Home MH, String id, String title) {
		
		jb = new JButton("보기");
		
		jb.addActionListener(e -> {
		
			
			String login_id = id;
			String login_place = null;
			
			String board_num = String.valueOf(MH.board_t.getValueAt(MH.board_t.getSelectedRow(), 0));
			String board_id = String.valueOf(MH.board_t.getValueAt(MH.board_t.getSelectedRow(), 1));
			String board_title = String.valueOf(MH.board_t.getValueAt(MH.board_t.getSelectedRow(), 2));
			String board_place = String.valueOf(MH.board_t.getValueAt(MH.board_t.getSelectedRow(), 3));
			String board_date = String.valueOf(MH.board_t.getValueAt(MH.board_t.getSelectedRow(), 4));
			
			ArrayList<Board_ReadDTO> dtos = new ArrayList<Board_ReadDTO>();
			Board_ReadDAO dao = new Board_ReadDAO();

			dtos = dao.board_read(board_title);
			
			for(int i = 0; i<dtos.size(); i++) {
				text = dtos.get(i).getBoard_text();
			}
			
			Board_Read read = new Board_Read(login_id, board_title, board_place, board_id);
			int num = Integer.parseInt(board_num);
			
			read.number_lb.setText(String.valueOf(num));
			read.id_lb2.setText(board_id);
			read.branch_lb2.setText(board_place);
			read.title_tf.setText(board_title);
			read.today_lb.setText(board_date);
			read.notice_ar.setText(text);
		
			ArrayList<MyPageDTO> mypage_dtos = new ArrayList<MyPageDTO>();
			MyPageDAO mypage_dao = new MyPageDAO();
			
			mypage_dtos = mypage_dao.myinfo_select(login_id);
			
			for(int i =0; i<mypage_dtos.size(); i++) {
				login_place = mypage_dtos.get(i).getPlace();
			}
			
			if(board_id.equals(login_id)) {
				read.deletebtn.setVisible(true);
				read.modifybtn.setVisible(true);
			} else {
				read.deletebtn.setVisible(false);
				read.modifybtn.setVisible(false);
				read.notice_ar.setEnabled(false);
				read.title_tf.setEnabled(false);
			}
			
		});
	}
	

	@Override
	public Object getCellEditorValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		// TODO Auto-generated method stub
		return jb;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		// TODO Auto-generated method stub
		return jb;
	}

}
