package sample.ui.model;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ColorColumnRenderer implements TableCellRenderer {

	public Component getTableCellRendererComponent(JTable table, final Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		if (!(value instanceof Color)) throw new RuntimeException("Wrong column type");
		Color color = (Color)value;
		return new FilledColorBox(color);
	}

}
