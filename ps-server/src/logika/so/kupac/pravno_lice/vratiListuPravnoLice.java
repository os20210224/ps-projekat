package logika.so.kupac.pravno_lice;

import domain.Kupac;
import domain.OpstiDomenskiObjekat;
import domain.PravnoLice;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import logika.db.dbBroker;
import logika.so.OpstaSO;

public class vratiListuPravnoLice extends OpstaSO<List> {

	@Override
	protected void preduslov(OpstiDomenskiObjekat obj) throws Exception {
		if (obj == null || !(obj instanceof PravnoLice)) {
			throw new Exception("Prosledjeni objekat nije PravnoLice");
		}
	}

	@Override
	protected List transakcija(OpstiDomenskiObjekat obj) throws Exception {
		ResultSet rs = dbBroker.select((PravnoLice) obj);
		List<Kupac> list = new ArrayList<>();
		while (rs.next()) {
			long id = rs.getLong("idKupac");
			ResultSet krs = dbBroker.select(new Kupac(id, null, null));
			
			String telefon = null;
			String email = null;
			
			if(krs.next()) {
				telefon = krs.getString("telefon");
				email = krs.getString("email");
			}
			
			list.add(new PravnoLice(
				rs.getString("naziv"),
				rs.getString("adresa"),
				id,
				telefon,
				email
			));
		}
		return list;
	}
	
}
