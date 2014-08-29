package sample.ui.model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

@SuppressWarnings("serial")
public class FilledColorBox extends Component {
	
	private Color color;
	
	public FilledColorBox(Color color) {
		this.color = color;
	}
	
	public void paint(Graphics g) {
		g.setColor(color);
		g.fillRect(0, 0, getWidth(), getHeight());
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
		repaint();
	}

}
