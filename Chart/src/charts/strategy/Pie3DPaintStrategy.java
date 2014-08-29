package charts.strategy;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;

import charts.ChartStats;
import charts.Stat;

public class Pie3DPaintStrategy extends PiePaintStrategy {
	
	protected void drawMainContent(Graphics g, ChartStats stats, Point start, Point legend, FontMetrics fm, double sum, int legendSize, int realHeight, int realWidth) {
		
		int x = start.x + min(realHeight, realWidth) / 2;
		int y = start.y + (int)(min(realHeight, realWidth) * 0.75 / 2);
		Point center = new Point(x, y);
		
		int rx = x - start.x;
		int ry = y - start.y;
		
		int pieHeight = rx / 3;
		
		drawBottomCircle(g, stats, start, sum, pieHeight, realWidth, realHeight);
		
		drawLeftBorder(g, stats, center, sum, rx, ry, pieHeight);
		drawRightBorder(g, stats, center, sum, rx, ry, pieHeight);

		drawTopCircleAndLegend(g, stats, start, legend, fm, sum, realWidth, realHeight, legendSize);
		
	}
	
	private void drawTopCircleAndLegend(Graphics g, ChartStats stats, Point start, Point legend, FontMetrics fm, double sum, int realWidth, int realHeight, int legendSize) {
		
		int startAngle = 90;

		for(Stat stat: stats) {
			float degrees = (float) (360 * stat.getValue().doubleValue() / sum);
			int angle = Math.round(degrees);
			g.setColor(stat.getColor());
			g.fillArc(start.x, start.y, min(realHeight, realWidth), (int)(min(realHeight, realWidth) * 0.75), startAngle, -angle);
			
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
			g.fillArc(start.x, start.y, min(realHeight, realWidth), (int)(min(realHeight, realWidth) * 0.75), startAngle, -startAngle - 270);
		}
	}
	
	private void drawBottomCircle(Graphics g, ChartStats stats, Point start, double sum, int pieHeight, int realWidth, int realHeight) {
		
		int startAngle = 90;
		for(Stat stat: stats) {
			float degrees = (float) (360 * stat.getValue().doubleValue() / sum);
			int angle = Math.round(degrees);
			
			g.setColor(stat.getColor().darker());
			g.fillArc(start.x, start.y + pieHeight, min(realHeight, realWidth), (int)(min(realHeight, realWidth) * 0.75), startAngle, -angle);
			startAngle -= angle;
		}
	}
	
	private void drawLeftBorder(Graphics g, ChartStats stats, Point center, double sum, int rx, int ry, int pieHeight) {
		
		int angle = 360;
		double rad = 2 * Math.PI;

		for(int i = stats.getSize() - 1; i >= 0; i--) {
			Stat stat = stats.getStat(i);
			float degrees = (float) (360 * stat.getValue().doubleValue() / sum);
			rad -= 2 * Math.PI * stat.getValue().doubleValue() / sum;
			angle -= Math.round(degrees);
			if (angle < 180) break;
			
			g.setColor(stats.getStat(i-1).getColor().darker());
			drawBorder(g, center, angle, rad, rx, ry, pieHeight);
		}
	}
	
	private void drawRightBorder(Graphics g, ChartStats stats, Point center, double sum, int rx, int ry, int pieHeight) {
		
		int angle = 0;
		double rad = 0;

		for(int i = 0; i < stats.getSize(); i++) {
			Stat stat = stats.getStat(i);
			float degrees = (float) (360 * stat.getValue().doubleValue() / sum);
			rad += 2 * Math.PI * stat.getValue().doubleValue() / sum;
			angle += Math.round(degrees);
			if (angle > 180) break;
			
			g.setColor(stats.getStat(i+1).getColor().darker());
			drawBorder(g, center, angle, rad, rx, ry, pieHeight);
		}
	}
	
	private void drawBorder(Graphics g, Point center, int angle, double rad, int rx, int ry, int pieHeight) {
		if (angle >= 90 && angle <= 270) {
			
			int linkX = center.x + (int)(rx * Math.sin(rad));
			int linkY = center.y - (int)(ry * Math.cos(rad));

			Point intersection = getIntersection(center, new Point(linkX, linkY), rx, ry, g, pieHeight);
			g.fillPolygon(new int[] {linkX, linkX, intersection.x}, new int[] {linkY, linkY + pieHeight + 2, intersection.y + 2}, 3);
		}
	}
	
	private Point getIntersection(Point center, Point link, int rx, int ry, Graphics g, int pieHeight) {
		// x^2 / rx^2 + y^2 / ry^2 = 1
		// y = kx + c
		
		double k = (link.y - center.y) / (double)(link.x - center.x);

		double a = rx * rx * k * k + ry * ry;
		double b = 2 * rx * rx * k * -pieHeight;
		double c = rx * rx * (pieHeight * pieHeight - ry * ry);
		
		double discriminant = b * b - 4 * a * c;
		
		int x1 = (int)((-b + Math.sqrt(discriminant)) / (2 * a));
		int x2 = (int)((-b - Math.sqrt(discriminant)) / (2 * a));
		int y1 = (int)(k * x1 - pieHeight);
		
		int coord = (link.x > center.x) ? (y1 > link.y ? x1 : x2) : (y1 < link.y ? x1 : x2);
		
		return new Point(center.x - coord, center.y - (int)(k * coord - pieHeight));
	}

}
