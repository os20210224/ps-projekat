package logika.so.kupac;

import domain.FizickoLice;
import domain.Kupac;
import domain.OpstiDomenskiObjekat;
import domain.PravnoLice;
import java.util.List;
import logika.db.dbBroker;
import logika.so.OpstaSO;
import java.sql.ResultSet;
import java.util.ArrayList;
import logika.kontroler.Kontroler;

public class vratiListuKupac  extends OpstaSO<List> {

	@Override
	protected void preduslov(OpstiDomenskiObjekat obj) throws Exception {
		if (obj == null || !(obj instanceof Kupac)) {
			throw new Exception("Prosledjeni objekat nije Kupac");
		}
	}

	@Override
	protected List transakcija(OpstiDomenskiObjekat obj) throws Exception {
		ResultSet rs = dbBroker.read((Kupac)obj);
		List<Kupac> list = new ArrayList<>();
		while (rs.next()) {
			long idKupac = rs.getLong("idKupac");
			List<PravnoLice> plica = Kontroler.vratiListuPravnoLice(new PravnoLice(idKupac));
			if (!plica.isEmpty()) {
				list.add(plica.get(0));
				continue;
			}
			List<FizickoLice> flica = Kontroler.vratiListuFizickoLice(new FizickoLice(idKupac));
			if (!flica.isEmpty()) {
				list.add(flica.get(0));
			}
		}
		return list;
	}
	
}
