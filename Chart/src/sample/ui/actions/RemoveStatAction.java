package sample.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import charts.ChartStatListener;
import charts.ChartStats;

@SuppressWarnings("serial")
public class RemoveStatAction extends AbstractAction implements ChartStatListener {
	
	private final ChartStats stats;
	private final JTable table;
	
	public RemoveStatAction(ChartStats stats, JTable table) {
		super("Remove");
		this.stats = stats;
		this.table = table;
		table.addMouseListener(new Listener());
		stats.addCharStatListener(this);
		setEnabled(false);
	}

	public void actionPerformed(ActionEvent e) {
		int result = JOptionPane.showConfirmDialog(table, "Do you really want to remove selected records?", "Confirmation", JOptionPane.YES_NO_CANCEL_OPTION);
		if (result == JOptionPane.YES_OPTION) {
			int[] selected = table.getSelectedRows();
			for(int i = selected.length - 1; i >= 0; i--)
				stats.removeStat(selected[i]);
		}
		chartStatChanged();
	}

	public void chartStatChanged() {
		setEnabled(table.getSelectedRowCount() > 0);
	}
	
	private class Listener extends MouseAdapter {
		 
		 public void mousePressed(MouseEvent e) {
			 chartStatChanged();
		 }
	}
}
