package gui.smena;

import domain.Smena;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class SmenaTableModel extends AbstractTableModel {

	String[] columns = {
		"vremePocetka",
		"vremeKraja",
		"ime"
	};
	
	List<Smena> smene;

	public SmenaTableModel(List<Smena> smene) {
		this.smene = smene;
	}
	
	@Override
	public int getRowCount() {
		return smene.size();
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Smena s = smene.get(rowIndex);
		return switch (columnIndex) {
			case 0 -> s.getVremePocetka();
			case 1 -> s.getVremeKraja();
			case 2 -> s.getIme();
			default -> "";
		};
	}

	@Override
	public String getColumnName(int column) {
		return columns[column];
	}
	
	public Smena getSmena(int row) {
		return smene.get(row);
	}
	
}
