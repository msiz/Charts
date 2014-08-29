package charts;

import java.awt.Color;

public class Stat {
	
	private static final Color DEFAULT_COLOR = Color.LIGHT_GRAY;
	
	private final String title;
	private final Number value;
	private final Color color;
	
	public Stat(String title, Number value, Color color) {
		this.title = title;
		this.value = value;
		this.color = color;
	}
	
	public Stat(String title, Number value) {
		this(title, value, DEFAULT_COLOR);
	}

	public String getTitle() {
		return title;
	}

	public Number getValue() {
		return value;
	}

	public Color getColor() {
		return color;
	}

}
