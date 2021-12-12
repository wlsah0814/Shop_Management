package com.java.manager;

import java.awt.Container;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.java.dao.Board_MainDAO;
import com.java.dao.ProductDAO;
import com.java.dto.Board_MainDTO;
import com.java.dto.Product_SelectDTO;

public class Stock_PlusTable extends JFrame{
	public JTable product_table;
	public DefaultTableModel product_tmodel;
	public JScrollPane product_sp;
	public Stock_PlusTable() {
		

		// Container 안에 panel 적용
		Container stock_ct = getContentPane();
		
		JPanel panel = new JPanel();
		panel.setName("재고 추가");
		panel.setLayout(null);
		stock_ct.add(panel);
		
		// JFrame 세팅
		setTitle("재고추가"); // 스윙 제목
		setSize(800, 400); // 사이즈
		setResizable(false); // 사이즈 조절여부
		setLocationRelativeTo(null); // 실행 시 센터에서 실행되는 여부
		setVisible(true); // 패널이 출력됨

		Font f1 = new Font("맑은 고딕", Font.BOLD, 20); //텍스트 폰트
		
		JLabel title = new JLabel("재고추가");
		title.setFont(f1);
		title.setBounds(10, 10, 100, 20);
		panel.add(title);

		ArrayList<Product_SelectDTO> dtos = new ArrayList<Product_SelectDTO>();
		ProductDAO dao = new ProductDAO();

		String product[] = {"제품번호", "제품명", "사이즈", "색상", "가격", "분류", "보기"};
		product_tmodel = new DefaultTableModel();
		product_tmodel.setColumnIdentifiers(product);
		product_table = new JTable(product_tmodel);
		
		
		dtos = dao.product_select();
		
		for(int i = 0; i<dtos.size(); i++) {
			product_tmodel.addRow(new Object[]{dtos.get(i).getP_id(), dtos.get(i).getP_name(), dtos.get(i).getP_size(), dtos.get(i).getP_color(), dtos.get(i).getP_price(), dtos.get(i).getP_type()});
		}	
		
	    product_table.setRowHeight(30);
		product_table.getTableHeader().setReorderingAllowed(false); // 컬럼들 이동 불가
		product_table.getTableHeader().setResizingAllowed(false); // 컬럼 크기 조절 불가
	    product_table.getColumnModel().getColumn(6).setCellRenderer(new Stock_TableCell(this));
	    product_table.getColumnModel().getColumn(6).setCellEditor(new Stock_TableCell(this));

	    product_sp = new JScrollPane(product_table);
	    product_sp.setBounds(10, 50, 760, 300);
	    panel.add(product_sp);
	}
	
	public static void main(String[] args) {
		new Stock_PlusTable();
	}
	
}


