package program;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AddBookWindows extends JFrame {
	private static final long serialVersionUID = 1L;
	private BookButtonListener buttonListener = null;
	

	private JTextField idField = null;
	private JTextField isbnField = null;
	private JTextField numberField = null;
	private JTextField authorsField = null;
	private JTextField titleField = null;
	private JTextField publisherField = null;
	private JTextField book_dateField = null;
	private JTextField statusField = null;
	private JTextField regist_dateField = null;
	
	public JTextField getIdField() {
		return idField;
	}
	public JTextField getisbnField() {
		return isbnField;
	}
	public JTextField getnumberField() {
		return numberField;
	}
	public JTextField getauthorField() {
		return authorsField;
	}
	public JTextField gettitleField() {
		return titleField;
	}
	public JTextField getpublisherField() {
		return publisherField;
	}
	public JTextField getbook_dateField() {
		return book_dateField;
	}
	public JTextField getstattusField() {
		return statusField;
	}
	public JTextField getregist_dateField() {
		return regist_dateField;
	}
	
	public AddBookWindows() {
		buttonListener = new BookButtonListener(this);
		setTitle("AddBook");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setLayout(new FlowLayout());
		add(new JLabel("id             "));
		idField = new JTextField(20); 
		add(idField);
		
		add(new JLabel("isbn         "));
		isbnField = new JTextField(20);
		add(isbnField);
		
		add(new JLabel("number   "));
		numberField = new JTextField(20);
		add(numberField);
		
		add(new JLabel("author         "));
		authorsField = new JTextField(20); 
		add(authorsField);
		
		add(new JLabel("title        "));
		titleField = new JTextField(20); 
		add(titleField);
		
		add(new JLabel("publisher    "));
		publisherField = new JTextField(20); 
		add(publisherField);
		
		add(new JLabel("book_date    "));
		book_dateField = new JTextField(20); 
		add(book_dateField);
		
		add(new JLabel("status    "));
		statusField = new JTextField(20);
		add(statusField);
		
		add(new JLabel("regist_date    "));
		regist_dateField = new JTextField(20); 
		add(regist_dateField);
		
		JButton okButton = new JButton(Const.OK);
		okButton.addActionListener(buttonListener);
		add(okButton);
		JButton cancelButton = new JButton(Const.CANCEL);
		cancelButton.addActionListener(buttonListener);
		add(cancelButton);
		
		
		setLocationRelativeTo(null);
		setSize(350, 350);
		setVisible(true);
	}
}
