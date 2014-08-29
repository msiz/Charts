package sample.ui.actions;

import java.awt.Dialog;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

@SuppressWarnings("serial")
public class NewStatAction extends AbstractAction {

	private final Dialog dialog;
	
	public NewStatAction(Dialog dialog) {
		super("Add");
		this.dialog = dialog;
	}
	
	public void actionPerformed(ActionEvent e) {
		dialog.setVisible(true);
	}

}
