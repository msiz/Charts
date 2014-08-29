package charts.strategy;

import java.awt.Font;
import java.awt.Graphics;

import charts.ChartStats;
import charts.Stat;

public class BarPaintStrategy implements PaintStrategy {
	
	protected static final int INTERVAL = 10;
	
	protected static final int OFFSET_LEFT = 20;
	protected static final int OFFSET_RIGHT = 15;
	protected static final int OFFSET_TOP = 15;
	protected static final int OFFSET_BOTTOM = 20;
	
	private static final int DEFAULT_FONT_SIZE = 12;
	
	
	protected double findMax(ChartStats stats) {
		double max = 0;
		for(Stat s: stats) {
			if (max < s.getValue().doubleValue()) max = s.getValue().doubleValue();
		}
		return max;
	}

	protected Font getSuitableFont(String label, Graphics g, int maxWidth) {
		
		for(int i = DEFAULT_FONT_SIZE; i > 0; i--) {
			Font font = new Font("Dialog", 0, i);
			int width = g.getFontMetrics(font).stringWidth(label);
			if (width <= maxWidth) return font;
		}
		return new Font("Dialog", 0, 0);
	}
	
	protected int getColumnWidth(ChartStats stats, int imageWidth) {
		double realWidth = imageWidth - OFFSET_LEFT - OFFSET_RIGHT;
		return (int) (realWidth / stats.getSize() - INTERVAL);
	}
	
	protected int getColumnHeight(Stat stat, int columnWidth, double max, int height) {
		double percent = stat.getValue().doubleValue() / max;
		return (int)((height - OFFSET_TOP - OFFSET_BOTTOM) * percent);
	}
	
	protected void drawSign(Graphics g, String label, int columnWidth, int startPos, int height) {
		Font font = getSuitableFont(label, g, columnWidth);
		int signWidth = g.getFontMetrics(font).stringWidth(label);
		g.setFont(font);
		g.setColor(FONT_COLOR);
		g.drawString(label, startPos + (columnWidth - signWidth) / 2, height - (OFFSET_BOTTOM / 3));
	}

	public void paint(Graphics g, ChartStats stats, int height, int width) {

		int startPos = OFFSET_LEFT;
		
		int columnWidth = getColumnWidth(stats, width);
		
		double max = findMax(stats);
		
		for(Stat stat: stats) {
			
			int columnHeight = getColumnHeight(stat, columnWidth, max, height);
			
			drawMainContent(g, stat, startPos, height, columnHeight, columnWidth);
			
			drawSign(g, stat.getTitle(), columnWidth, startPos, height);
			
			startPos += columnWidth + INTERVAL;
		}
	}
	
	protected void drawMainContent(Graphics g, Stat stat, int startPos, int height, int columnHeight, int columnWidth) {
		g.setColor(stat.getColor());
		g.fillRect(startPos, height - OFFSET_BOTTOM - columnHeight, columnWidth, columnHeight);
	}

}
