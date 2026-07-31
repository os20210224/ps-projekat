package gui.zaposleni;

import domain.Zaposleni;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ZaposleniTableModel extends AbstractTableModel {

	String[] columns = {
		"Ime",
		"Prezime",
		"Username"
	};
	
	List<Zaposleni> zaposleni;

	public ZaposleniTableModel(List<Zaposleni> zaposleni) {
		this.zaposleni = zaposleni;
	}
	
	@Override
	public int getRowCount() {
		return zaposleni.size();
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Zaposleni z = zaposleni.get(rowIndex);
		return switch (columnIndex) {
			case 0 -> z.getIme();
			case 1 -> z.getPrezime();
			case 2 -> z.getUsername();
			default -> "";
		};
	}

	@Override
	public String getColumnName(int column) {
		return columns[column];
	}
	
	public Zaposleni getZaposleni(int row) {
		return zaposleni.get(row);
	}
	
}
