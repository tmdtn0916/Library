package program;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.BorderLayout;

public class BookList extends JFrame {

	private static final long serialVersionUID = 1L;
	BookListActionListener bookListActionListener = new BookListActionListener(this);
	public BookList() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("BookList");
		setSize(800, 500);
		createMenu();
		setLocationRelativeTo(null);
		
		JButton DeleteBook = new JButton("DeleteBook");
		DeleteBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookListActionListener.deleteBook(e);
				
			}
		});
		getContentPane().add(DeleteBook, BorderLayout.SOUTH);
		setVisible(true);
	}
	
	private void createMenu() {
		JMenuBar mb = new JMenuBar();
		JMenu bookListMenu = new JMenu("BookList");
		JMenuItem book_List = new JMenuItem("Book List");
		bookListMenu.add(book_List);
		
		book_List.addActionListener(bookListActionListener);
		mb.add(bookListMenu);
		setJMenuBar(mb);
	
	}
	
}
