package gui.kupac;

import domain.FizickoLice;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class FizickoLiceTableModel extends AbstractTableModel {
	
	String[] columns = {
		"Telefon",
		"Email",
		"Ime",
		"Prezime"
	};
	
	List<FizickoLice> lica;

	public FizickoLiceTableModel(List<FizickoLice> lica) {
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
		FizickoLice l = lica.get(rowIndex);
		return switch (columnIndex) {
			case 0 -> l.getTelefon();
			case 1 -> l.getEmail();
			case 2 -> l.getIme();
			case 3 -> l.getPrezime();
			default -> "";
		};
	}

	@Override
	public String getColumnName(int column) {
		return columns[column];
	}
	
	public FizickoLice getKupac(int row) {
		return lica.get(row);
	}
	
}
