package logika.so.kupac.fizicko_lice;

import domain.FizickoLice;
import domain.Kupac;
import domain.OpstiDomenskiObjekat;
import logika.db.dbBroker;
import logika.so.OpstaSO;
import logika.so.kupac.PromeniKupac;

public class PromeniFizickoLice extends OpstaSO<Void> {

	@Override
	protected void preduslov(OpstiDomenskiObjekat obj) throws Exception {
		if (obj == null || !(obj instanceof FizickoLice)) {
			throw new Exception("Prosledjeni objekat nije FizickoLice");
		}
	}

	@Override
	protected Void transakcija(OpstiDomenskiObjekat obj) throws Exception {
		FizickoLice l = (FizickoLice) obj;
		OpstaSO so = new PromeniKupac();
		so.izvrsiTransakciju(new Kupac(l.getIdKupac(), l.getTelefon(), l.getEmail()));
		return dbBroker.update(l);
	}
	
}
