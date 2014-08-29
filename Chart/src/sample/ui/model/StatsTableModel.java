package sample.ui.model;

import java.awt.Color;

import javax.swing.table.AbstractTableModel;

import charts.ChartStatListener;
import charts.ChartStats;
import charts.Stat;

@SuppressWarnings("serial")
public class StatsTableModel extends AbstractTableModel implements ChartStatListener {

	private final ChartStats stats;
	
	public StatsTableModel(ChartStats stats) {
		this.stats = stats;
	}
	
	public int getRowCount() {
		return stats.getSize();
	}

	public int getColumnCount() {
		return 4;
	}

	public Object getValueAt(int row, int column) {
		Stat stat = stats.getStat(row);
		switch (column)
		{
			case 0:  return row + 1;
			case 1:  return stat.getTitle();
			case 2:  return stat.getValue();
			case 3:  return stat.getColor();
			default: return null;
		}
	}
	
	@Override
	public String getColumnName(int column)
	{
		switch(column) 
		{
			case 0:	 return "#";
			case 1:	 return "Title";
			case 2:	 return "Value";
			case 3:	 return "Color";
			default: return "";
		}
	}
	
	@Override
	public Class<?> getColumnClass(int column) 
	{
		switch(column) 
		{
			case 0:	 return Number.class;
			case 1:	 return String.class;
			case 2:	 return Number.class;
			case 3:	 return Color.class;
			default: return Object.class;
		}
	}

	public void chartStatChanged() {
		fireTableDataChanged();
	}

}
