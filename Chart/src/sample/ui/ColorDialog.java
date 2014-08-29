package sample.ui;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;

import sample.ui.actions.ChooseColorAction;
import sample.ui.actions.CloseDialogAction;
import sample.ui.model.FilledColorBox;

@SuppressWarnings("serial")
public class ColorDialog extends JDialog {
	
	private final JColorChooser chooser = new JColorChooser();
	private final FilledColorBox box;
	
	public ColorDialog(Dialog dialog, FilledColorBox box) {
		super(dialog, true);
		this.box = box;
		setResizable(false);
		setTitle("Choose color");
		createContent();
		pack();
	}
	
	private void createContent() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(chooser, BorderLayout.CENTER);
		panel.add(createButtonsPanel(), BorderLayout.SOUTH);
		setContentPane(panel);
	}
	
	private JPanel createButtonsPanel() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 8));
		JButton ok = new JButton(new ChooseColorAction(this, chooser, box));
		JButton close = new JButton(new CloseDialogAction(this));
		panel.add(ok);
		panel.add(close);
		return panel;
	}

}
