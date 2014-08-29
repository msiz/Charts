package sample;

import javax.swing.UIManager;

import sample.ui.MainFrame;

public class Application {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e) {}
		new MainFrame().setVisible(true);
	}
}
