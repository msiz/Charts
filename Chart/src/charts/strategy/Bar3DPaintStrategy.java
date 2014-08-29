package charts.strategy;

import java.awt.Graphics;

import charts.Stat;

public class Bar3DPaintStrategy extends BarPaintStrategy {
	
	protected int getColumnHeight(Stat stat, int columnWidth, double max, int height) {
		double percent = stat.getValue().doubleValue() / max;
		return (int)((height - OFFSET_TOP - OFFSET_BOTTOM - columnWidth / 3) * percent);
	}
	
	protected void drawMainContent(Graphics g, Stat stat, int startPos, int height, int columnHeight, int columnWidth) {
		
		int bottom = height - OFFSET_BOTTOM;
		int top = height - OFFSET_BOTTOM - columnHeight;
		
		g.setColor(stat.getColor());
		g.fillRect(startPos, bottom - columnHeight, columnWidth * 2 / 3, columnHeight);
		
		g.setColor(stat.getColor().darker());
		g.fillPolygon(	new int[] {startPos, startPos + columnWidth / 3, startPos + columnWidth, startPos + columnWidth * 2 / 3}, 
						new int[] {top, top - columnWidth / 3, top - columnWidth / 3, top}, 
						4);
		g.fillPolygon(	new int[] {startPos + columnWidth * 2 / 3, startPos + columnWidth * 2 / 3, startPos + columnWidth, startPos + columnWidth}, 
						new int[] {bottom, top, top - columnWidth / 3, bottom - columnWidth / 3}, 
						4);
	}
}
