package logika.so.smena;

import domain.OpstiDomenskiObjekat;
import domain.Smena;
import java.util.List;
import logika.db.dbBroker;
import logika.so.OpstaSO;
import java.sql.ResultSet;
import java.util.ArrayList;

public class vratiListuSmena extends OpstaSO<List> {

	@Override
	protected void preduslov(OpstiDomenskiObjekat obj) throws Exception {
		if (obj == null || !(obj instanceof Smena)) {
			throw new Exception("Prosledjeni objekat nije Smena");
		}
	}

	@Override
	protected List transakcija(OpstiDomenskiObjekat obj) throws Exception {
		ResultSet rs = dbBroker.select((Smena)obj);
		List<Smena> list = new ArrayList<>();
		while (rs.next()) {
			list.add(new Smena(
				rs.getLong("idSmena"),
				rs.getTime("vremePocetka").toLocalTime(),
				rs.getTime("vremeKraja").toLocalTime(),
				rs.getString("ime")
			));
		}
		return list;
	}
	
}
