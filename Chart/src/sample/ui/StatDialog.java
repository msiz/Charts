package sample.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sample.ui.actions.AddStatAction;
import sample.ui.actions.CloseDialogAction;
import sample.ui.actions.ShowColorChooserAction;
import sample.ui.model.FilledColorBox;
import charts.ChartStats;

@SuppressWarnings("serial")
public class StatDialog extends JDialog {
	
	private final ChartStats stats;
	
	private final JTextField title 			= new JTextField(15);
	private final JFormattedTextField value = new JFormattedTextField(new DecimalFormat());
	private final FilledColorBox box		 = new FilledColorBox(Color.LIGHT_GRAY);
	
	public StatDialog(Frame frame, ChartStats stats) {
		super(frame, true);
		this.stats = stats;
		setTitle("New data");
		setResizable(false);
		createContent();
		pack();
	}
	
	private void createContent() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(createFieldsPanel(), BorderLayout.CENTER);
		panel.add(createButtonsPanel(), BorderLayout.SOUTH);
		setContentPane(panel);
	}
	
	private JPanel createFieldsPanel() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
		
		value.setPreferredSize(new Dimension(100, 20));
		
		box.setPreferredSize(new Dimension(100, 20));
		ColorDialog chooser = new ColorDialog(this, box);
		
		JButton showChooser = new JButton(new ShowColorChooserAction(chooser));
		
		panel.add(new JLabel("Title: "));
		panel.add(title);
		panel.add(new JLabel("Value: "));
		panel.add(value);
		panel.add(new JLabel("Color: "));
		panel.add(box);
		panel.add(showChooser);
		
		return panel;
	}
	
	private JPanel createButtonsPanel() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 8));
		JButton ok = new JButton(new AddStatAction(this, stats, title, value, box));
		JButton close = new JButton(new CloseDialogAction(this));
		panel.add(ok);
		panel.add(close);
		return panel;
	}

}
