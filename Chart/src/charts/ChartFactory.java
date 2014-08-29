package charts;

import java.awt.Dimension;

import charts.strategy.Bar3DPaintStrategy;
import charts.strategy.BarPaintStrategy;
import charts.strategy.Pie3DPaintStrategy;
import charts.strategy.PiePaintStrategy;
public class ChartFactory {
	
	public static final int BAR_CHART = 1;
	public static final int BAR_CHART_3D = 2;
	public static final int PIE_CHART = 3;
	public static final int PIE_CHART_3D = 4;
	
	public static Chart createChart(int type, ChartStats stats, Dimension preferredSize) {
		switch(type) {
			case BAR_CHART: 	return new Chart(stats, new BarPaintStrategy(), 	preferredSize);
			case BAR_CHART_3D: 	return new Chart(stats, new Bar3DPaintStrategy(), 	preferredSize);
			case PIE_CHART: 	return new Chart(stats, new PiePaintStrategy(), 	preferredSize);
			case PIE_CHART_3D: 	return new Chart(stats, new Pie3DPaintStrategy(), 	preferredSize);
			default: throw new RuntimeException("Unknown chart type");
		}
	}
	
	public static Chart createChart(int type, ChartStats stats) {
		return createChart(type, stats, Chart.DEFAULT_SIZE);
	}

}
