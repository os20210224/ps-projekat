package gui.knjiga;

import domain.Knjiga;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class KnjigaTableModel extends AbstractTableModel {

	String[] columns = {
		"ID",
		"Format",
		"Br. strana",
		"Povez",
		"Cena strane",
		"Cena poveza",
		"Naziv",
		"Autor",
		"Cena"
	};
	
	List<Knjiga> knjige;

	public KnjigaTableModel(List<Knjiga> knjige) {
		this.knjige = knjige;
	}
	
	@Override
	public int getRowCount() {
		return knjige.size();
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Knjiga k = knjige.get(rowIndex);
		return switch (columnIndex) {
			case 0 -> k.getIdKnjiga();
			case 1 -> k.getFormat();
			case 2 -> k.getBrStranica();
			case 3 -> k.getPovez();
			case 4 -> k.getCenaStranica();
			case 5 -> k.getCenaPoveza();
			case 6 -> k.getNaziv();
			case 7 -> k.getAutor();
			case 8 -> k.getCena();
			default -> "";
		};
	}

	@Override
	public String getColumnName(int column) {
		return columns[column];
	}
	
	public Knjiga getKnjiga(int row) {
		return knjige.get(row);
	}
	
}
