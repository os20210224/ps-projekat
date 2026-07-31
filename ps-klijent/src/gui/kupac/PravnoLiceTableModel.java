package gui.kupac;

import domain.PravnoLice;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class PravnoLiceTableModel extends AbstractTableModel {
	
	String[] columns = {
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
			case 0 -> l.getTelefon();
			case 1 -> l.getEmail();
			case 2 -> l.getNaziv();
			case 3 -> l.getAdresa();
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
