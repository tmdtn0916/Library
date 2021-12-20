package program;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class BookListActionListener implements ActionListener {
	private BookList bookList = null;
	private JTable table = null;
	public BookListActionListener(BookList bookList) {
		this.bookList = bookList;
		createBookTable();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("Book List"))
		{	
			System.out.println("BookList");
			DefaultTableModel model= (DefaultTableModel)table.getModel();
			model.setNumRows(0);
			Database.getInstance().insertBookTable(model);
		}
	}
	
	private void createBookTable() {
		String []header = {"id", "isbn", "number", "authors", "title", "publisher",
				"book_date", "status", "regist_date"};
		DefaultTableModel model = new DefaultTableModel(header,0);
		table = new JTable(model);
		JScrollPane scroll = new JScrollPane(table);
		bookList.add(scroll);
		
		}
	
	public void deleteBook(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("DeleteBook"))
		{	
			String isbn = (String) table.getModel().getValueAt(table.getSelectedRow(), 1);
			Database.getInstance().deleteBook(isbn);
			
			System.out.println(isbn);
		}
	}
	
}
