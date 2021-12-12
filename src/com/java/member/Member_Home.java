package com.java.member;


import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.java.board.Board_Write;
import com.java.dao.Board_MainDAO;
import com.java.dao.MyPageDAO;
import com.java.dao.ProductDAO;
import com.java.dao.SalesDAO;
import com.java.dao.StockDAO;
import com.java.dto.Board_MainDTO;
import com.java.dto.LoginDTO;
import com.java.dto.MyPageDTO;
import com.java.dto.Product_SelectDTO;
import com.java.dto.SalesDTO;
import com.java.dto.Stock_SelectDTO;
import com.java.login.Login;
import com.java.mypage.MyPage;

public class Member_Home extends JFrame{

	public JLabel id_lb;
	public JButton selectbtn_p;
	public DefaultTableModel stock_tm;
	public DefaultTableModel sell_tm;
	public DefaultTableModel board_tm;
	public DefaultTableModel product_tm;
	public JScrollPane T2_product;
	public JScrollPane T1_board;
	public JScrollPane T3_sell;
	public JScrollPane T4_stock;
	public JTable stock_t;
	public JTable sell_t;
	public JTable product_t;
	public JTable board_t;
	private Font f1;
	

	public Member_Home(String id) {

		// Container 안에 panel 적용
		Container MaHct = getContentPane();
		// JTabbedPane 사용
		JTabbedPane pane = createTabbedPane();
		MaHct.add(pane);

		JPanel Tab1 = new JPanel();
		JPanel Tab2 = new JPanel();
		JPanel Tab3 = new JPanel();
		JPanel Tab4 = new JPanel();

		Tab1.setName("요청 게시판");
		Tab2.setName("제품관리");
		Tab3.setName("판매관리");
		Tab4.setName("재고관리");

		Tab1.setLayout(null);
		Tab2.setLayout(null);
		Tab3.setLayout(null);
		Tab4.setLayout(null);

		// JFrame 세팅
		setTitle("매장 프로그램"); // 스윙 제목
		setSize(800, 500); // 사이즈
		setResizable(false); // 사이즈 조절여부
		setLocationRelativeTo(null); // 실행 시 센터에서 실행되는 여부
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 스윙 완전 종료
		add(pane, null);

		pane.add(Tab1);
		pane.add(Tab2);
		pane.add(Tab3);
		pane.add(Tab4);

		setVisible(true); // 패널이 출력됨

		Tab1_p(Tab1, id);
		Tab2_p(Tab2);
		Tab3_p(Tab3, id);
		Tab4_p(Tab4, id);

	}

	
	// 메인 게시판 화면
	public void Tab1_p(JPanel T1_panel, String id) {

		Font font1 = new Font("맑은고딕", Font.BOLD, 25); // 제목 폰트
		Font font2 = new Font("맑은고딕", Font.BOLD, 18); // 내정보 라벨 폰트
		Font font3 = new Font("맑은고딕", Font.BOLD, 15); // 정보 아이디 폰트

		String login_id = id;

		JLabel info_lb = new JLabel("내정보");
		info_lb.setBounds(550, -30, 100, 100);
		info_lb.setFont(font2);
		T1_panel.add(info_lb);

		id_lb = new JLabel(id + "님 환영합니다!");
		id_lb.setBounds(500, 45, 200, 15);
		id_lb.setFont(font3);
		T1_panel.add(id_lb);

		JButton mypagebtn = new JButton("내정보");
		mypagebtn.setBounds(500, 70, 75, 30);
		T1_panel.add(mypagebtn);

		JButton logoutbtn = new JButton("로그아웃");
		logoutbtn.setBounds(580, 70, 90, 30);
		T1_panel.add(logoutbtn);

		// 제목부분
		JLabel notice_lb = new JLabel("요청 게시판");
		notice_lb.setBounds(10, -90, 220, 220);
		notice_lb.setFont(font1);
		T1_panel.add(notice_lb);

		// 버튼
		JButton write_btn = new JButton("게시글 작성");
		write_btn.setBounds(580, 403, 105, 40);
		T1_panel.add(write_btn);
		
		JButton refresh_btn = new JButton("게시판 새로고침");
		refresh_btn.setBounds(0, 403, 130, 40);
		T1_panel.add(refresh_btn);

		// JTable 부분
		ArrayList<Board_MainDTO> dtos = new ArrayList<Board_MainDTO>();
		Board_MainDAO dao = new Board_MainDAO();

		String title = null;
		
		String board[] = {"No","아이디", "제목", "지점명", "날짜", "보기"};
		board_tm = new DefaultTableModel(); 
		board_tm.setColumnIdentifiers(board);
		board_t = new JTable(board_tm);
		
		
		dtos = dao.board_select();
		
		for(int i = 0; i<dtos.size(); i++) {
			board_tm.addRow(new Object[]{dtos.get(i).getBoard_num(), dtos.get(i).getId(), dtos.get(i).getBoard_title(), dtos.get(i).getPlace(), dtos.get(i).getWrite_date()});
			title = dtos.get(i).getBoard_title();
		}	

		board_t.getTableHeader().setReorderingAllowed(false); // 컬럼들 이동 불가
	    board_t.getTableHeader().setResizingAllowed(false); // 컬럼 크기 조절 불가
	    board_t.getColumnModel().getColumn(5).setCellRenderer(new Member_TableCell1(this, login_id, title));
	    board_t.getColumnModel().getColumn(5).setCellEditor(new Member_TableCell1(this, login_id, title));

	    board_t.setRowHeight(30);
	    board_t.getColumnModel().getColumn(0).setPreferredWidth(1);
		board_t.getColumnModel().getColumn(1).setPreferredWidth(150);
		board_t.getColumnModel().getColumn(2).setPreferredWidth(150);
		board_t.getColumnModel().getColumn(3).setPreferredWidth(150);
		board_t.getColumnModel().getColumn(4).setPreferredWidth(150);
		board_t.getColumnModel().getColumn(5).setPreferredWidth(150);
		
	    T1_board = new JScrollPane(board_t);
	    T1_board.setBounds(0, 120, 687, 280);
		T1_panel.add(T1_board);
		

		mypagebtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Tab1_p(T1_panel, login_id);
				new MyPage(login_id);
			}
		});

		logoutbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Login();
				dispose();
			}
		});

		write_btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Board_Write(login_id);
			}
		});
		
		refresh_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				board_tm.setNumRows(0);
				
				ArrayList<Board_MainDTO> dtos = new ArrayList<Board_MainDTO>();
				Board_MainDAO dao = new Board_MainDAO();
				String title = null;
				
				
				dtos = dao.board_select();
				
				for(int i = 0; i<dtos.size(); i++) {
					board_tm.addRow(new Object[]{dtos.get(i).getBoard_num(), dtos.get(i).getId(), dtos.get(i).getBoard_title(), dtos.get(i).getPlace(), dtos.get(i).getWrite_date()});
					title = dtos.get(i).getBoard_title();
				}
				
				board_t.setRowSelectionInterval(0, 0);
			}
		});
	}
			



	// 제품관리
	private void Tab2_p(JPanel T2_panel) {

		Font font = new Font("맑은고딕", Font.BOLD, 25);

		JLabel title_lb = new JLabel("제품관리");
		title_lb.setBounds(10, 5, 100, 30);
		title_lb.setFont(font);
		T2_panel.add(title_lb);

		JTextField search = new JTextField();
		search.setBounds(150, 5, 300, 40);
		search.setText("제품명");
		T2_panel.add(search);

		JButton searchbtn = new JButton("검색");
		searchbtn.setBounds(500, 5, 100, 40);
		T2_panel.add(searchbtn);

		JButton select_p = new JButton("전체조회");
		select_p.setBounds(500, 70, 100, 40);
		T2_panel.add(select_p);

		
	
		ArrayList<Product_SelectDTO> dtos = new ArrayList<Product_SelectDTO>();
		ProductDAO dao = new ProductDAO();

		String product[] = {"제품번호", "제품명", "사이즈", "색상", "가격", "분류", "보기"};
		product_tm = new DefaultTableModel();
		product_tm.setColumnIdentifiers(product);
		product_t = new JTable(product_tm);
		
		
		dtos = dao.product_select();
		
		for(int i = 0; i<dtos.size(); i++) {
			product_tm.addRow(new Object[]{dtos.get(i).getP_id(), dtos.get(i).getP_name(), dtos.get(i).getP_size(), dtos.get(i).getP_color(), dtos.get(i).getP_price(), dtos.get(i).getP_type()});
		}	
		
	    product_t.setRowHeight(30);
		product_t.getTableHeader().setReorderingAllowed(false); // 컬럼들 이동 불가
		product_t.getTableHeader().setResizingAllowed(false); // 컬럼 크기 조절 불가
	    product_t.getColumnModel().getColumn(6).setCellRenderer(new Member_TableCell2(this));
	    product_t.getColumnModel().getColumn(6).setCellEditor(new Member_TableCell2(this));

	    T2_product= new JScrollPane(product_t);
	    T2_product.setBounds(0, 120, 687, 280);
		T2_panel.add(T2_product);
		
		select_p.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				product_tm.setNumRows(0);
				
				ArrayList<Product_SelectDTO> dtos = new ArrayList<Product_SelectDTO>();
				ProductDAO dao = new ProductDAO();
				
				dtos = dao.product_select();
				
				for(int i = 0; i<dtos.size(); i++) {
					product_tm.addRow(new Object[]{dtos.get(i).getP_id(), dtos.get(i).getP_name(), dtos.get(i).getP_size(), dtos.get(i).getP_color(), dtos.get(i).getP_price(), dtos.get(i).getP_type()});
				}
				product_t.setRowSelectionInterval(0, 0);
			}
		});	
		

	

		searchbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String p_search = search.getText();
				
				product_tm.setNumRows(0);
				
				ArrayList<Product_SelectDTO> dtos = new ArrayList<Product_SelectDTO>();
				ProductDAO dao = new ProductDAO();
				
				dtos = dao.product_search(p_search);
				
				for(int i = 0; i<dtos.size(); i++) {
					product_tm.addRow(new Object[]{dtos.get(i).getP_id(), dtos.get(i).getP_name(), dtos.get(i).getP_size(), dtos.get(i).getP_color(), dtos.get(i).getP_price(), dtos.get(i).getP_type()});
					System.out.println(dtos.get(i).getP_id());
				}
				product_t.setRowSelectionInterval(0, 0);
			}
		});
		
		search.addMouseListener(new MouseListener() {
			
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
				search.setText("");	
			}
		});
	}
	


	// 매출관리
	private void Tab3_p(JPanel T3_panel, String id) {
		
		String login_id = id;
		String place = null;
		
		Font font = new Font("맑은고딕", Font.BOLD, 25);
		Font font1 = new Font("맑은고딕", Font.BOLD, 13);

		JLabel title_lb = new JLabel("판매관리");
		title_lb.setBounds(10, 5, 100, 30);
		title_lb.setFont(font);
		T3_panel.add(title_lb);
		
		JTextField search = new JTextField();
		search.setBounds(150, 5, 300, 40);
		search.setText("매장명");
		T3_panel.add(search);

		JButton searchbtn = new JButton("검색");
		searchbtn.setBounds(500, 5, 100, 40);
		T3_panel.add(searchbtn);

		JButton selectbtn = new JButton("전체조회");
		selectbtn.setBounds(500, 50, 100, 40);
		T3_panel.add(selectbtn);

		ArrayList<MyPageDTO> mypagedtos = new ArrayList<MyPageDTO>();
		MyPageDAO mypagedao = new MyPageDAO();
		
		mypagedtos = mypagedao.myinfo_select(login_id);
		
		for(int i = 0; i<mypagedtos.size(); i++) {
			 place = mypagedtos.get(i).getPlace();
		}
		
		ArrayList<Stock_SelectDTO> dtos = new ArrayList<Stock_SelectDTO>();
		StockDAO dao = new StockDAO();
		
		String stock[] = { "지점명", "제품코드", "제품명", "사이즈", "색상", "가격", "분류", "수량", "판매"};
		sell_tm = new DefaultTableModel();
		sell_tm.setColumnIdentifiers(stock);
		sell_t = new JTable(sell_tm);
		
		dtos = dao.place_select(place);
		
		for(int i = 0; i<dtos.size(); i++) {
			sell_tm.addRow(new Object[] {dtos.get(i).getPlace(), dtos.get(i).getP_id(), dtos.get(i).getP_name(), dtos.get(i).getP_size(), dtos.get(i).getP_color(), dtos.get(i).getP_price(), dtos.get(i).getP_type(), dtos.get(i).getP_num()});
		}
		sell_t.setRowHeight(30);
		sell_t.getTableHeader().setReorderingAllowed(false); // 컬럼들 이동 불가
		sell_t.getTableHeader().setResizingAllowed(false); // 컬럼 크기 조절 불가
		sell_t.getColumnModel().getColumn(8).setCellRenderer(new Member_TableCell3(this, id));
		sell_t.getColumnModel().getColumn(8).setCellEditor(new Member_TableCell3(this, id));
		
		T3_sell = new JScrollPane(sell_t);
		T3_sell.setBounds(0, 120, 687, 280);
		T3_panel.add(T3_sell);
		
		selectbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sell_tm.setNumRows(0);
				
				ArrayList<SalesDTO> dtos = new ArrayList<SalesDTO>();
				SalesDAO dao = new SalesDAO();
				
				dtos = dao.sales_select();
				
				for(int i  = 0; i<dtos.size(); i++) {
					sell_tm.addRow(new Object[]{dtos.get(i).getPlace(), dtos.get(i).getSale_date(),dtos.get(i).getSale_total()});
				}
				sell_t.setRowSelectionInterval(0, 0);
			}
		});

		searchbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String p_search = search.getText();
				
				sell_tm.setNumRows(0);
				
				ArrayList<SalesDTO> dtos = new ArrayList<SalesDTO>();
				SalesDAO dao = new SalesDAO();
				
				dtos = dao.sales_search(p_search);
				
				for(int i  = 0; i<dtos.size(); i++) {
					sell_tm.addRow(new Object[]{dtos.get(i).getPlace(), dtos.get(i).getSale_date(),dtos.get(i).getSale_total()});
				}
				
				sell_t.setRowSelectionInterval(0, 0);
			}
		});
		
		search.addMouseListener(new MouseListener() {
			
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
				search.setText("");	
			}
		});
	}
		

	// 매장재고관리
	private void Tab4_p(JPanel T4_panel, String id) {
		Font font = new Font("맑은고딕", Font.BOLD, 25);

		String login_id = id;
		String place = null;
		
		JLabel title_lb = new JLabel("재고관리");
		title_lb.setBounds(10, 5, 150, 30);
		title_lb.setFont(font);
		T4_panel.add(title_lb);
	
		ArrayList<MyPageDTO> mypagedtos = new ArrayList<MyPageDTO>();
		MyPageDAO mypagedao = new MyPageDAO();
		
		mypagedtos = mypagedao.myinfo_select(login_id);
		
		for(int i = 0; i<mypagedtos.size(); i++) {
			 place = mypagedtos.get(i).getPlace();
		}
		
		ArrayList<Stock_SelectDTO> dtos = new ArrayList<Stock_SelectDTO>();
		StockDAO dao = new StockDAO();

		String stock[] = { "지점명", "제품코드", "제품명", "사이즈", "색상", "가격", "분류", "수량"};
		stock_tm = new DefaultTableModel();
		stock_tm.setColumnIdentifiers(stock);
		stock_t = new JTable(stock_tm);
		
		dtos = dao.place_select(place);
		
		for(int i = 0; i<dtos.size(); i++) {
			stock_tm.addRow(new Object[] {dtos.get(i).getPlace(), dtos.get(i).getP_id(), dtos.get(i).getP_name(), dtos.get(i).getP_size(), dtos.get(i).getP_color(), dtos.get(i).getP_price(), dtos.get(i).getP_type(), dtos.get(i).getP_num()});
		}
		
		stock_t.setRowHeight(30);
		stock_t.getTableHeader().setReorderingAllowed(false);
		stock_t.getTableHeader().setResizingAllowed(false);


	    T4_stock= new JScrollPane(stock_t);
	    T4_stock.setBounds(0, 120, 687, 280);
		T4_panel.add(T4_stock);
	}
		

	private JTabbedPane createTabbedPane() {
		JTabbedPane pane = new JTabbedPane(JTabbedPane.LEFT);
		return pane;

	}

	public static void main(String[] args) {
		new Member_Home(null);
	}
}
