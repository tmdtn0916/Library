package program;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookButtonListener implements ActionListener{
	
	
	private AddBookWindows window = null;
	public BookButtonListener(AddBookWindows window) {
		this.window = window;
	}
	private void closeWindow() {
		window.dispose();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals(Const.OK)){
			Database.getInstance().insertBookData(
					Integer.parseInt(window.getIdField().getText()),
					Integer.parseInt(window.getisbnField().getText()),
					Integer.parseInt(window.getnumberField().getText()),
					window.getauthorField().getText(),
					window.gettitleField().getText(),
					window.getpublisherField().getText(),
					window.getbook_dateField().getText(),
					window.getstattusField().getText(),
					Integer.parseInt(window.getregist_dateField().getText()));
			
	
			System.out.println("ok");
			new MainFrame();
		}
		
		else if(e.getActionCommand().equals(Const.CANCEL)) {
			closeWindow();
			new MainFrame();
			System.out.println("cancel");
		}
	}

}
