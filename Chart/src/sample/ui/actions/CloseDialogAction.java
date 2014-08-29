package sample.ui.actions;

import java.awt.Dialog;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

@SuppressWarnings("serial")
public class CloseDialogAction extends AbstractAction {

	private final Dialog dialog;
	
	public CloseDialogAction(Dialog dialog) {
		super("Cancel");
		this.dialog = dialog;
	}
	
	public void actionPerformed(ActionEvent e) {
		dialog.setVisible(false);
	}

}
