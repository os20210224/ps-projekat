package gui.racun;

import domain.StavkaRacuna;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class StavkaRacunaTableModel extends AbstractTableModel {
	
	String[] columns = {
		"rb",
		"Knjiga",
		"Kolicina",
		"Cena",
		"Iznos",
	};
	
	List<StavkaRacuna> stavke;

	public StavkaRacunaTableModel(List<StavkaRacuna> stavke) {
		this.stavke = stavke;
	}
	
	@Override
	public int getRowCount() {
		return stavke.size();
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		StavkaRacuna s = stavke.get(rowIndex);
		return switch (columnIndex) {
			case 0 -> s.getRb();
			case 1 -> s.getKnjiga().toString();
			case 2 -> s.getKolicina();
			case 3 -> s.getCena();
			case 4 -> s.getIznos();
			default -> "";
		};
	}

	@Override
	public String getColumnName(int column) {
		return columns[column];
	}
	
	public StavkaRacuna getStavka(int row) {
		return stavke.get(row);
	}
	
}
