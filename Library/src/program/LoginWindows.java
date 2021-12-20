package program;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginWindows extends JFrame {
	private static final long serialVersionUID = 1L;
	private LoginButtonListener buttonListener = null;
	
	/* name �ؽ�Ʈ �ʵ� ����*/
	private JTextField nameField = null;
	/* password �ؽ�Ʈ �ʵ� ����*/
	private JPasswordField passField = null;
	/* name�� password getter ����*/
	public JTextField getNameField() {
		return nameField;
	}
	public JPasswordField getPassField() {
		return passField;
	}
	
	public LoginWindows() {
		buttonListener = new LoginButtonListener(this);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(new FlowLayout());
		add(new JLabel("name        "));
		/* ���� */
		nameField = new JTextField(20); 
		add(nameField);
		add(new JLabel("password"));
		/* ���� */
		passField = new JPasswordField(20);
		add(passField);
		
		JButton okButton = new JButton(Const.OK);
		okButton.addActionListener(buttonListener);
		add(okButton);
		JButton cancelButton = new JButton(Const.CANCEL);
		cancelButton.addActionListener(buttonListener);
		add(cancelButton);
		JButton joinButton = new JButton(Const.JOIN);
		joinButton.addActionListener(buttonListener);
		add(joinButton);
		
		setLocationRelativeTo(null);
		setSize(350, 150);
		setVisible(true);
	}
}
