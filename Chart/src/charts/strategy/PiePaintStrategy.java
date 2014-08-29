package charts.strategy;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;

import charts.ChartStats;
import charts.Stat;

public class PiePaintStrategy implements PaintStrategy {
	
	protected static final int INTERVAL = 10;
	
	protected static final int OFFSET_LEFT = 10;
	protected static final int OFFSET_RIGHT = 10;
	protected static final int OFFSET_TOP = 10;
	protected static final int OFFSET_BOTTOM = 70;

	private static final int DEFAULT_LEGEND_SIZE = 12;
	
	
	protected double findSum(ChartStats stats) {
		double sum = 0;
		for(Stat stat: stats) {
			sum += stat.getValue().doubleValue();
		}
		return sum;
	}
	
	protected int getSuitableLegendSize(Graphics g, ChartStats stats, int maxWidth) {
		for(int i = DEFAULT_LEGEND_SIZE; i > 0; i--) {
			Font font = new Font("Dialog", 0, i);
			FontMetrics fm = g.getFontMetrics(font);
			int legendWidth = getLegendWidth(stats, i, fm);
			if (legendWidth < maxWidth) return i;
		}
		return 0;
	}
	
	protected int getLegendWidth(ChartStats stats, int size, FontMetrics fm) {
		int legendWidth = 0;
		for(Stat stat: stats) {
			legendWidth += 4*size + INTERVAL + fm.stringWidth(stat.getTitle());
		}
		legendWidth -= INTERVAL;
		return legendWidth;
	}
	
	protected int min(int x, int y) {
		return x < y ? x : y;
	}

	public void paint(Graphics g, ChartStats stats, int height, int width) {
		
		int realHeight = height - OFFSET_BOTTOM - OFFSET_TOP;
		int realWidth = width - OFFSET_LEFT - OFFSET_RIGHT;
		
		int startX = (realHeight < realWidth) ? OFFSET_LEFT + (realWidth - realHeight) / 2 : OFFSET_LEFT;
		int startY = (realWidth < realHeight) ? OFFSET_TOP + (realHeight - realWidth) / 2 : OFFSET_TOP;
		Point start = new Point(startX, startY);
		
		double sum = findSum(stats);
		
		int legendSize = getSuitableLegendSize(g, stats, width);
		Font font = new Font("Dialog", 0, legendSize);
		FontMetrics fm = g.getFontMetrics(font);
		g.setFont(font);
		
		int legendX = (width - getLegendWidth(stats, legendSize, fm) + 2*legendSize) / 2;
		int legendY = height - (OFFSET_BOTTOM / 2);
		Point legend = new Point(legendX, legendY);
		
		drawMainContent(g, stats, start, legend, fm, sum, legendSize, realHeight, realWidth);
	}
	
	protected void drawMainContent(Graphics g, ChartStats stats, Point start, Point legend, FontMetrics fm, double sum, int legendSize, int realHeight, int realWidth) {
		
		int startAngle = 90;
		
		for(Stat stat: stats) {
			
			float degrees = (float) (360 * stat.getValue().doubleValue() / sum);
			int angle = Math.round(degrees);
			g.setColor(stat.getColor());
			g.fillArc(start.x, start.y, min(realHeight, realWidth), min(realHeight, realWidth), startAngle, -angle);
			
			g.fillOval(legend.x - legendSize, legend.y - legendSize, 2*legendSize, 2*legendSize);
			
			g.setColor(FONT_COLOR);
			g.drawString(stat.getTitle(), legend.x + 2*legendSize, legend.y + legendSize / 2);
			legend.x += 4*legendSize + INTERVAL + fm.stringWidth(stat.getTitle());
			
			startAngle -= angle;
		}
		
		drawRest(g, startAngle, stats, start, realHeight, realWidth);
	}
	
	protected void drawRest(Graphics g, int startAngle, ChartStats stats, Point start, int realHeight, int realWidth) {
		if (startAngle > -270) {
			Color last = stats.getStat(stats.getSize()-1).getColor();
			g.setColor(last);
			g.fillArc(start.x, start.y, min(realHeight, realWidth), min(realHeight, realWidth), startAngle, -startAngle - 270);
		}
	}

}
