package sample.ui.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JComboBox;

import charts.Chart;
import charts.strategy.Bar3DPaintStrategy;
import charts.strategy.BarPaintStrategy;
import charts.strategy.PaintStrategy;
import charts.strategy.Pie3DPaintStrategy;
import charts.strategy.PiePaintStrategy;

@SuppressWarnings("serial")
public class ChangeStrategyAction extends AbstractAction {

	private final Chart chart;
	private final JComboBox<String> box;
	
	private static PaintStrategy[] STRATEGIES = new PaintStrategy[] {null, new BarPaintStrategy(), new PiePaintStrategy(), new Bar3DPaintStrategy(), new Pie3DPaintStrategy()};
	
	public ChangeStrategyAction(Chart chart, JComboBox<String> box) {
		this.chart = chart;
		this.box = box;
	}
	
	public void actionPerformed(ActionEvent e) {
		int index = box.getSelectedIndex();
		chart.setPaintStrategy(STRATEGIES[index]);
	}

}
