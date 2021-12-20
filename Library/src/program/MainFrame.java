package program;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.MenuListener;
import javax.swing.JButton;
import java.awt.BorderLayout;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	MenuActionListener menuListener = new MenuActionListener(this);
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Admin Mode");
		setSize(800, 500);
		
		createMenu();
		
		setVisible(true);
		//화면 가운데 생성
		setLocationRelativeTo(null);
		
		JButton DeleteUser = new JButton("DeleteUser");
		getContentPane().add(DeleteUser, BorderLayout.SOUTH);
		DeleteUser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				menuListener.deleteAction(e);
				menuListener.resetTable(e);
			}
		});
	}
	
	private void createMenu() {
		/* Menu를 만듭니다. */
		JMenuBar mb = new JMenuBar();
		JMenu personMenu = new JMenu("Person");
		JMenuItem loginMenuItem = new JMenuItem("Log-in ...");
		loginMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				new LoginWindows();
			}
		});
		
		
		JMenuItem logoutMenuItem = new JMenuItem("Log-out ...");
		logoutMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int dialogResult = JOptionPane.showConfirmDialog (null, 
						"Would you like to Logout?",
						"Warning",
						JOptionPane.YES_NO_OPTION);
				if(dialogResult == JOptionPane.YES_OPTION) {
					dispose();
					new LoginWindows();
				}
			}
		});
		
		
		personMenu.add(loginMenuItem);
		personMenu.add(logoutMenuItem);
		
		JMenu memberMenu = new JMenu("Members");
		JMenuItem membersMenuItem = new JMenuItem("Members ...");
		memberMenu.add(membersMenuItem);
		
		mb.add(personMenu);
		mb.add(memberMenu);
		
		JMenu bookMenu = new JMenu("Book");
		JMenuItem addBook = new JMenuItem("Add Book");
		bookMenu.add(addBook);
		addBook.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				new AddBookWindows();
			}
		});
	
		JMenuItem bookList = new JMenuItem("Book List");
		bookMenu.add(bookList);
		bookList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new BookList();
			}
		});
		
		mb.add(bookMenu);
		
		
		
		
		membersMenuItem.addActionListener(menuListener);
		setJMenuBar(mb);
		
	
	}
}
