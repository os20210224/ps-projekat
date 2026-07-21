package gui.racun;

import domain.Racun;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class RacunTableModel extends AbstractTableModel {
	
	String[] columns = {
		"Datum",
		"Metod placanja",
		"Kupac",
		"Zaposleni",
		"Ukupan iznos"
	};
	
	List<Racun> racuni;

	public RacunTableModel(List<Racun> racuni) {
		this.racuni = racuni;
	}
	
	@Override
	public int getRowCount() {
		return racuni.size();
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Racun r = racuni.get(rowIndex);
		return switch (columnIndex) {
			case 0 -> r.getDatum();
			case 1 -> r.getMetodPlacanja();
			case 2 -> r.getKupac().toString();
			case 3 -> r.getZaposleni().toString();
			case 4 -> r.getUkupanIznos();
			default -> "";
		};
	}

	@Override
	public String getColumnName(int column) {
		return columns[column];
	}
	
	public Racun getRacun(int row) {
		return racuni.get(row);
	}
	
}
