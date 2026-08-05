package logika.so.kupac.fizicko_lice;

import domain.FizickoLice;
import domain.Kupac;
import domain.OpstiDomenskiObjekat;
import java.util.List;
import logika.so.OpstaSO;
import java.sql.ResultSet;
import java.util.ArrayList;
import logika.db.dbBroker;

public class vratiListuFizickoLice extends OpstaSO<List> {

	@Override
	protected void preduslov(OpstiDomenskiObjekat obj) throws Exception {
		if (obj == null || !(obj instanceof FizickoLice)) {
			throw new Exception("Prosledjeni objekat nije FizickoLice");
		}
	}

	@Override
	protected List transakcija(OpstiDomenskiObjekat obj) throws Exception {
		ResultSet rs = dbBroker.read((FizickoLice) obj);
		List<Kupac> list = new ArrayList<>();
		while (rs.next()) {
			long id = rs.getLong("idKupac");
			ResultSet krs = dbBroker.read(new Kupac(id, null, null));
			
			String telefon = null;
			String email = null;
			
			if(krs.next()) {
				telefon = krs.getString("telefon");
				email = krs.getString("email");
			}
			
			list.add(new FizickoLice(
				rs.getString("ime"),
				rs.getString("prezime"),
				id,
				telefon,
				email
			));
		}
		return list;
	}
	
}
