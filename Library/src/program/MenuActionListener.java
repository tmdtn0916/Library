package program;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class MenuActionListener implements ActionListener {

	private MainFrame mainWindow = null;
	private JTable table = null;
	public MenuActionListener(MainFrame mainWindow) {
		this.mainWindow = mainWindow;
		createTable();
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("Members ..."))
		{	
			
			System.out.println("members... ok");
			DefaultTableModel model= (DefaultTableModel)table.getModel();
			model.setNumRows(0);
			
			Database.getInstance().insertJTable(model);
			
		}
	
	}
	
	public void deleteAction(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("DeleteUser"))
		{	
			String id = (String) table.getModel().getValueAt(table.getSelectedRow(), 0);
			Database.getInstance().deleteUser(id);
			
			System.out.println(id);
		}
	}
	
	public void resetTable(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("DeleteUser"))
		{	
			DefaultTableModel model= (DefaultTableModel)table.getModel();
			model.setNumRows(0);
			Database.getInstance().insertJTable(model);
			
		}
	}
	
	private void createTable() {
		String []header = {"id", "name", "password"};
		DefaultTableModel model=new DefaultTableModel(header,0);
		table = new JTable(model);
		JScrollPane scroll = new JScrollPane(table);
		
		mainWindow.add(scroll);
	}
	
	
	private void createBookTable() {
		String []header = {"id", "isbn", "number", "authors", "title", "publisher",
				"book_date", "status", "regist_date"};
		DefaultTableModel model = new DefaultTableModel(header,0);
		table = new JTable(model);
		JScrollPane scroll = new JScrollPane(table);
		mainWindow.add(scroll);
		
	}
}
