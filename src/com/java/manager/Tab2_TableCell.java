package com.java.manager;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.java.dao.ProductDAO;
import com.java.dto.Product_SelectDTO;

public class Tab2_TableCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {
	
	JButton jb;
	
	public Tab2_TableCell(Manager_Home MH) {
		
		jb = new JButton("보기");
		String p_photo = null;
		
		jb.addActionListener(e -> {
			
			String p_id = String.valueOf(MH.product_t.getValueAt(MH.product_t.getSelectedRow(), 0));
			String p_name = String.valueOf(MH.product_t.getValueAt(MH.product_t.getSelectedRow(), 1));
			String p_size = String.valueOf(MH.product_t.getValueAt(MH.product_t.getSelectedRow(), 2));
			String p_color = String.valueOf(MH.product_t.getValueAt(MH.product_t.getSelectedRow(), 3));
			String p_price = String.valueOf(MH.product_t.getValueAt(MH.product_t.getSelectedRow(), 4));	
			String p_type = String.valueOf(MH.product_t.getValueAt(MH.product_t.getSelectedRow(), 5));	

			ArrayList<Product_SelectDTO> dtos = new ArrayList<Product_SelectDTO>();
			ProductDAO dao = new ProductDAO();
			
			String photo = null;
			Manager_ReadProduct read = new Manager_ReadProduct();
			
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
			
			File imagefile = new File("C:\\Users\\양진모\\Desktop\\자바 스윙\\자바스윙 사진\\" + photo);
			String file = imagefile.getPath();
			ImageIcon p_icon = new ImageIcon(file);
			read.P_Img.setIcon(p_icon);
			
			System.out.println(imagefile);
			

			
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
