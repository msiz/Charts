package charts;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import charts.strategy.PaintStrategy;

@SuppressWarnings("serial")
public class Chart extends Component implements ChartStatListener {
	
	public static final Dimension DEFAULT_SIZE = new Dimension(500, 500);

	private final ChartStats stats;
	private PaintStrategy paintStrategy;
	
	public Chart(ChartStats stats, PaintStrategy paintStrategy) {
		this(stats, paintStrategy, DEFAULT_SIZE);
	}
	
	public Chart(ChartStats stats, PaintStrategy paintStrategy, Dimension preferredSize) {
		this.stats = stats;
		this.paintStrategy = paintStrategy;
		stats.addCharStatListener(this);
		setPreferredSize(preferredSize);
	}
	
	public void paint(Graphics g) {
		g.drawImage(getImage(), 0, 0, this);
	}
	
	private BufferedImage createClearImage() {
		BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics imageGraphics = image.createGraphics();
		imageGraphics.setColor(Color.WHITE);
		imageGraphics.fillRect(0, 0, image.getWidth(), image.getHeight());
		return image;
	}
	
	public BufferedImage getImage() {
		BufferedImage image = createClearImage();
		if (paintStrategy == null) {
			drawError(image.createGraphics());
		} else {
			if (!stats.isEmpty())
				paintStrategy.paint(image.createGraphics(), stats, image.getHeight(), image.getWidth());
		}
		return image;
	}
	
	public void saveChart(String format, String path) throws IOException {
		File out = new File(path);
		ImageIO.write(getImage(), format, out);
	}
	
	public void setPaintStrategy(PaintStrategy paintStrategy) {
		this.paintStrategy = paintStrategy;
		repaint();
	}
	
	public void chartStatChanged() {
		repaint();
	}
	
	private void drawError(Graphics g) {
		String error = "PaintStrategy is not set!";
		g.setColor(Color.RED);
		int width = g.getFontMetrics().stringWidth(error);
		g.drawString(error, (getWidth() - width) / 2, getHeight() / 2);
	}

}
