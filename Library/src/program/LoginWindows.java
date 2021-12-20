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
	
	/* name 텍스트 필드 생성*/
	private JTextField nameField = null;
	/* password 텍스트 필드 생성*/
	private JPasswordField passField = null;
	/* name과 password getter 생성*/
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
		/* 수정 */
		nameField = new JTextField(20); 
		add(nameField);
		add(new JLabel("password"));
		/* 수정 */
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
