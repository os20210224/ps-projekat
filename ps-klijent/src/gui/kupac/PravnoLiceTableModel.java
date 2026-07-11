package gui.kupac;

import domain.PravnoLice;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class PravnoLiceTableModel extends AbstractTableModel {
	
	String[] columns = {
		"ID",
		"Telefon",
		"Email",
		"Naziv",
		"Adresa"
	};
	
	List<PravnoLice> lica;

	public PravnoLiceTableModel(List<PravnoLice> lica) {
		this.lica = lica;
	}
	
	@Override
	public int getRowCount() {
		return lica.size();
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		PravnoLice l = lica.get(rowIndex);
		return switch (columnIndex) {
			case 0 -> l.getIdKupac();
			case 1 -> l.getTelefon();
			case 2 -> l.getEmail();
			case 3 -> l.getNaziv();
			case 4 -> l.getAdresa();
			default -> "";
		};
	}

	@Override
	public String getColumnName(int column) {
		return columns[column];
	}
	
	public PravnoLice getKupac(int row) {
		return lica.get(row);
	}
	
}
