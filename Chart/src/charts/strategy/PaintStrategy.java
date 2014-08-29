package charts.strategy;

import java.awt.Color;
import java.awt.Graphics;

import charts.ChartStats;
public interface PaintStrategy {
	
	Color FONT_COLOR = Color.BLACK;
	
	void paint(Graphics g, ChartStats stats, int height, int width);

}
