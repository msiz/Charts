package sample.ui.actions;

import java.awt.Dialog;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

@SuppressWarnings("serial")
public class ShowColorChooserAction extends AbstractAction {

	private final Dialog chooser;
	
	public ShowColorChooserAction(Dialog chooser) {
		super("...");
		this.chooser = chooser;
	}
	
	public void actionPerformed(ActionEvent e) {
		chooser.setVisible(true);
	}

}
