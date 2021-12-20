package program;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class LoginButtonListener implements ActionListener{
	static int ID;
	
	private LoginWindows window = null;
	public LoginButtonListener(LoginWindows window) {
		this.window = window;
	}
	private void closeWindow() {
		window.dispose();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals(Const.OK)){
			System.out.println("ok");
			
			if(window.getNameField().getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "The name is empty");
				window.getNameField().requestFocus();
			}
			else if(window.getPassField().getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "The password is empty");
				window.getPassField().requestFocus();
			}
			else
			{	
				if(Database.getInstance().checkAdmin(
						window.getNameField().getText(),
						window.getPassField().getText())) {
					new MainFrame();
					closeWindow();
				}
				
				else if(Database.getInstance().checkNameAndPwd(
						window.getNameField().getText(), 
						window.getPassField().getText()) ) {
					
					ID = Database.getInstance().whoseID(window.getNameField().getText());
					new UserInterFace();
					closeWindow();
				}
				else {
					JOptionPane.showMessageDialog(null, "The name or password is invalid");
					window.getNameField().requestFocus();
				}
			}
		}
		else if(e.getActionCommand().equals(Const.JOIN)) {
			MemberJoinDialog dialog = new MemberJoinDialog();
			dialog.show();
			System.out.println("join");
		}
		else if(e.getActionCommand().equals(Const.CANCEL)) {
			int dialogResult = JOptionPane.showConfirmDialog (null, 
					"Would you like to cancel?",
					"Warning",
					JOptionPane.YES_NO_OPTION);
			if(dialogResult == JOptionPane.YES_OPTION){
				closeWindow();
				System.out.println("cancel");
			}
		}
	}

}
