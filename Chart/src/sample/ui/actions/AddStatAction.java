package sample.ui.actions;

import java.awt.Dialog;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import sample.ui.model.FilledColorBox;

import charts.ChartStats;
import charts.Stat;

@SuppressWarnings("serial")
public class AddStatAction extends AbstractAction {

	private final Dialog dialog;
	private final ChartStats stats;
	private final JTextField title;
	private final JTextField value;
	private final FilledColorBox color;
	
	public AddStatAction(Dialog dialog, ChartStats stats, JTextField title, JTextField value, FilledColorBox color) {
		super("OK");
		this.dialog = dialog;
		this.stats = stats;
		this.title = title;
		this.value = value;
		this.color = color;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (title.getText().isEmpty()) JOptionPane.showMessageDialog(dialog, "Specify title", "Error", JOptionPane.WARNING_MESSAGE);
		else {
			String toParse = value.getText().replace(""+(char)160, "").replace(',', '.');
			Number num = toParse.isEmpty() ? 0 : Double.parseDouble(toParse);
			if (num.doubleValue() == num.intValue()) num = num.intValue();
			Stat stat = new Stat(title.getText(), num, color.getColor());
			stats.addStat(stat);
			dialog.setVisible(false);
		}
	}
}
