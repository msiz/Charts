package sample.ui.actions;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import charts.Chart;

@SuppressWarnings("serial")
public class SaveAction extends AbstractAction {
	
	private final Chart chart;
	
	public SaveAction(Chart chart) {
		super("Save");
		this.chart = chart;
	}

	public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(new FileNameExtensionFilter("Png files", "png"));
		int result = chooser.showSaveDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = chooser.getSelectedFile();
		    try {
		    	String path = selectedFile.getPath();
		    	if (!path.endsWith(".png")) {
		    		path += ".png";
		    	}
				chart.saveChart("png", path);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}

}
