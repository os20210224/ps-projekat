package logika.so.zaposleni;

import domain.OpstiDomenskiObjekat;
import domain.Zaposleni;
import java.util.List;
import logika.db.dbBroker;
import logika.so.OpstaSO;
import java.sql.ResultSet;
import java.util.ArrayList;

public class vratiListuZaposleni extends OpstaSO<List>{

	@Override
	protected void preduslov(OpstiDomenskiObjekat obj) throws Exception {
		if (obj == null || !(obj instanceof Zaposleni)) {
			throw new Exception("Prosledjeni objekat nije Zaposleni");
		}
	}

	@Override
	protected List<Zaposleni> transakcija(OpstiDomenskiObjekat obj) throws Exception {
		ResultSet rs = dbBroker.read((Zaposleni)obj);
		List<Zaposleni> list = new ArrayList<>();
		while (rs.next()) {
			list.add(new Zaposleni(
				rs.getLong(1),
				rs.getString(2),
				rs.getString(3),
				rs.getString(4),
				rs.getString(5)
			));
		}
		return list;
	}
	
}
