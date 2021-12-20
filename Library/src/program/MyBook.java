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

import static program.LoginButtonListener.ID;
public class MyBook extends JFrame {
	private JTextField searchtextField;
	private JTable table;

	private JTextField textField = null;

	public JTextField textField() {
		return textField;
	}

	public MyBook() {
		setTitle("MyBookList");
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
		scroll.setBounds(0, 0, 784, 470);
		getContentPane().add(scroll);
		
		JButton refreshbutton = new JButton("\uC0C8\uB85C\uACE0\uCE68");
		refreshbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setNumRows(0);
				Database.getInstance().searchMyBook(model, ID);
			}
		});
		refreshbutton.setBounds(216, 502, 91, 23);
		getContentPane().add(refreshbutton);
		
		
		
		
		JButton returnbutton = new JButton("\uBC18\uB0A9");
		returnbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String isbn = (String) table.getModel().getValueAt(table.getSelectedRow(), 0);
				Database.getInstance().returnBook(isbn);
				model.setNumRows(0);
				Database.getInstance().searchMyBook(model, ID);
				JOptionPane.showMessageDialog(null, "반납하였습니다.");
			}
		});
		returnbutton.setBounds(504, 502, 91, 23);
		getContentPane().add(returnbutton);

		
		

	}
}