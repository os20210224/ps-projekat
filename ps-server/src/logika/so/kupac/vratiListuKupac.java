package logika.so.kupac;

import domain.Kupac;
import domain.OpstiDomenskiObjekat;
import java.util.List;
import logika.db.dbBroker;
import logika.so.OpstaSO;
import java.sql.ResultSet;
import java.util.ArrayList;

public class vratiListuKupac  extends OpstaSO<List> {

	@Override
	protected void preduslov(OpstiDomenskiObjekat obj) throws Exception {
		if (obj == null || !(obj instanceof Kupac)) {
			throw new Exception("Prosledjeni objekat nije Kupac");
		}
	}

	@Override
	protected List transakcija(OpstiDomenskiObjekat obj) throws Exception {
		ResultSet rs = dbBroker.select((Kupac)obj);
		List<Kupac> list = new ArrayList<>();
		while (rs.next()) {
			// fizicko/pravno lice
			list.add(new Kupac(
				rs.getLong(1),
				rs.getString(2),
				rs.getString(3)
			));
		}
		return list;
	}
	
}
