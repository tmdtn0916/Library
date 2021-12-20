package program;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;

import java.awt.Window.Type;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserInterFace extends JFrame {
	private JTextField searchtextField;
	private JTable table;

	private JTextField textField = null;
	
	public JTextField textField() {
		return textField;
	}

	public UserInterFace() {
		setTitle("Library");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 587);
		setVisible(true);
		// 화면 가운데 생성
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		table = new JTable();
		table.setBounds(0, 0, 0, 0);
		getContentPane().add(table);
		String[] header = {"isbn","authors", "title", "publisher",  "status"};
		DefaultTableModel model = new DefaultTableModel(header, 0);
		table = new JTable(model);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(0, 22, 784, 445);
		getContentPane().add(scroll);

		JLabel titleSearchNewLabel = new JLabel("\uCC45 \uC81C\uBAA9");
		titleSearchNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleSearchNewLabel.setBounds(12, 477, 70, 41);
		getContentPane().add(titleSearchNewLabel);

		textField = new JTextField();
		textField.setBounds(94, 477, 280, 41);
		getContentPane().add(textField);
		textField.setColumns(10);

		JButton searchNewButton = new JButton("\uAC80\uC0C9");
		searchNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField().getText().length() == 0) {
					model.setNumRows(0);
					Database.getInstance().searchBook(model, textField.getText());
					textField().requestFocus();
				} else {
					if (Database.getInstance().checkTitle(textField().getText())) {
						DefaultTableModel model = (DefaultTableModel) table.getModel();
						model.setNumRows(0);
						Database.getInstance().searchBook(model, textField.getText());
					} else {
						JOptionPane.showMessageDialog(null, "검색할 제목과 일치하는 책이 없습니다.");
						textField().requestFocus();
					}
				}
			}
		});
		searchNewButton.setBounds(386, 477, 70, 41);
		getContentPane().add(searchNewButton);

		JButton brrowNewButton_1 = new JButton("\uB300\uCD9C");
		
		brrowNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String isbn = (String) table.getModel().getValueAt(table.getSelectedRow(), 0);
				if (Database.getInstance().checkStatus(isbn)) {
					Database.getInstance().BorrowBook("대출중", isbn);
					model.setNumRows(0);
					Database.getInstance().searchBook(model, textField.getText());
					JOptionPane.showMessageDialog(null, "대출되었습니다.");
					
				} else {
					JOptionPane.showMessageDialog(null, "대여중입니다");
				}
											
			}
		});
		brrowNewButton_1.setBounds(468, 477, 70, 41);
		getContentPane().add(brrowNewButton_1);

		

		JButton btnNewButton_3 = new JButton("\uB098\uC758 \uC11C\uC7AC");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MyBook();
				//Database.getInstance().insertJTableSearch(model);
			}
		});
		btnNewButton_3.setBounds(632, 477, 105, 41);
		getContentPane().add(btnNewButton_3);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 105, 23);
		getContentPane().add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Login");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Log-out");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new LoginWindows();
			}
		});
		mnNewMenu.add(mntmNewMenuItem);

	}
}