package charts;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

public class ChartStats implements Iterable<Stat> {
	
	private final List<Stat> stats = new ArrayList<Stat>();
	private final List<ChartStatListener> listeners = new ArrayList<ChartStatListener>();
	
	public void addCharStatListener(ChartStatListener listener) {
		listeners.add(listener);
	}
	
	public void removeCharStatListener(ChartStatListener listener) {
		listeners.remove(listener);
	}
	
	public void addStat(Stat stat) {
		stats.add(stat);
		fireStatsChanged();
	}
	
	public void removeStat(int index) {
		stats.remove(index);
		fireStatsChanged();
	}
	
	private void fireStatsChanged() {
		for(ChartStatListener listener: listeners)
			listener.chartStatChanged();
	}

	public Iterator<Stat> iterator() {
		return stats.iterator();
	}
	
	public int getSize() {
		return stats.size();
	}
	
	public Stat getStat(int index) {
		return stats.get(index);
	}
	
	public boolean isEmpty() {
		return stats.size() == 0;
	}

}
