package com.java.manager;

import java.awt.Component;
import java.io.File;
import java.util.ArrayList;

import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.java.dao.ProductDAO;
import com.java.dto.Product_SelectDTO;

public class Stock_TableCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer{
	
	JButton jb;

	public Stock_TableCell(Stock_PlusTable stock_pt) {

		jb = new JButton("보기");

		jb.addActionListener(e -> {
			String p_id = String.valueOf(stock_pt.product_table.getValueAt(stock_pt.product_table.getSelectedRow(), 0));
			String p_name = String.valueOf(stock_pt.product_table.getValueAt(stock_pt.product_table.getSelectedRow(), 1));
			String p_size = String.valueOf(stock_pt.product_table.getValueAt(stock_pt.product_table.getSelectedRow(), 2));
			String p_color = String.valueOf(stock_pt.product_table.getValueAt(stock_pt.product_table.getSelectedRow(), 3));
			String p_price = String.valueOf(stock_pt.product_table.getValueAt(stock_pt.product_table.getSelectedRow(), 4));	
			String p_type = String.valueOf(stock_pt.product_table.getValueAt(stock_pt.product_table.getSelectedRow(), 5));	

			
			ArrayList<Product_SelectDTO> dtos = new ArrayList<Product_SelectDTO>();
			ProductDAO dao = new ProductDAO();
			
			String photo = null;
			
			Stock_Read read = new Stock_Read();
			
			read.pid_lb.setText(p_id);
			read.pname_lb.setText(p_name);
			read.size_lb.setText(p_size);
			read.color_lb.setText(p_color);
			read.pprice_lb.setText(p_price);
			read.class_lb.setText(p_type);
			
			dtos = dao.product_read(p_id);
			
			for(int i = 0; i<dtos.size(); i++) {
				photo = dtos.get(i).getP_photo();
			}
			System.out.println(photo);
			
			File imagefile = new File("C:\\Users\\양진모\\Desktop\\자바 스윙\\자바스윙 사진\\" + photo);
			String file = imagefile.getPath();
			ImageIcon p_icon = new ImageIcon(file);
			read.P_Img.setIcon(p_icon);
			
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
