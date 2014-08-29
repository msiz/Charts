package sample.ui.actions;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JColorChooser;

import sample.ui.model.FilledColorBox;

@SuppressWarnings("serial")
public class ChooseColorAction extends AbstractAction {
	
	private final Dialog dialog;
	private final JColorChooser chooser;
	private final FilledColorBox box;

	
	public ChooseColorAction(Dialog dialog, JColorChooser chooser, FilledColorBox box) {
		super("OK");
		this.dialog = dialog;
		this.chooser = chooser;
		this.box = box;
	}

	public void actionPerformed(ActionEvent e) {
		Color color = chooser.getColor();
		box.setColor(color);
		dialog.setVisible(false);
	}

}
