package sample.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import sample.ui.actions.ChangeStrategyAction;
import sample.ui.actions.NewStatAction;
import sample.ui.actions.RemoveStatAction;
import sample.ui.actions.SaveAction;
import sample.ui.model.ColorColumnRenderer;
import sample.ui.model.StatsTableModel;
import charts.Chart;
import charts.ChartStats;
import charts.Stat;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	private final ChartStats stats = new ChartStats();
	private final Chart chart = new Chart(stats, null);
	
	public MainFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Diagrams");
		createContent();
		pack();
	}
	
	private void createContent() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(createStatsPanel(), BorderLayout.NORTH);
		panel.add(createContentPanel(), BorderLayout.CENTER);
		setContentPane(panel);
	}
	
	private JPanel createContentPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		
		initializeDefaultStats(stats);
		
		panel.add(createSettingsPanel(), BorderLayout.NORTH);
		panel.add(chart, BorderLayout.CENTER);
		
		return panel;
	}
	
	private JPanel createSettingsPanel() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
		
		JComboBox<String> box = new JComboBox<String>(new String[] {"None", "Column", "Pie", "Column 3D", "Pie 3D"});
		box.addActionListener(new ChangeStrategyAction(chart, box));
		
		JButton save = new JButton(new SaveAction(chart));
		
		panel.add(new JLabel("Chart type: "));
		panel.add(box);
		panel.add(save);
		
		return panel;
	}
	
	private void initializeDefaultStats(ChartStats stats) {
		stats.addStat(new Stat("Pro", 67, Color.GREEN));
		stats.addStat(new Stat("Con", 28, Color.RED));
		stats.addStat(new Stat("Abstained", 5, Color.BLUE));
	}
	
	private JPanel createStatsPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		
		StatsTableModel model = new StatsTableModel(stats);
		stats.addCharStatListener(model);
		
		JTable table = new JTable(model);
		table.getColumnModel().getColumn(0).setMaxWidth(40);
		table.setDefaultRenderer(Color.class, new ColorColumnRenderer());
		
		JScrollPane scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(600, 100));
		
		panel.add(createButtonsPanel(table), BorderLayout.NORTH);
		panel.add(scroll, BorderLayout.WEST);
		
		return panel;
	}
	
	private JPanel createButtonsPanel(JTable table) {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
		
		StatDialog dialog = new StatDialog(this, stats);
		
		JButton add = new JButton(new NewStatAction(dialog));
		JButton remove = new JButton(new RemoveStatAction(stats, table));
		
		panel.add(add);
		panel.add(remove);
		
		return panel;
	}

}
