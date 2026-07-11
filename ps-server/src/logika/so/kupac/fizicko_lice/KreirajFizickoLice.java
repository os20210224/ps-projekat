package logika.so.kupac.fizicko_lice;

import domain.FizickoLice;
import domain.Kupac;
import domain.OpstiDomenskiObjekat;
import logika.db.dbBroker;
import logika.so.OpstaSO;
import logika.so.kupac.KreirajKupac;

public class KreirajFizickoLice extends OpstaSO<Long> {

	@Override
	protected void preduslov(OpstiDomenskiObjekat obj) throws Exception {
		if (obj == null || !(obj instanceof FizickoLice)) {
			throw new Exception("Prosledjeni objekat nije FizickoLice");
		}
	}

	@Override
	protected Long transakcija(OpstiDomenskiObjekat obj) throws Exception {
		FizickoLice l = (FizickoLice) obj;
		OpstaSO so = new KreirajKupac();
		long id = (long) so.izvrsiTransakciju(new Kupac(l.getIdKupac(), l.getTelefon(), l.getEmail()));
		if (id == 0) {
			throw new Exception("Greska pri cuvanju kupca");
		}
		l.setIdKupac(id);
		return dbBroker.kreiraj(l);
	}
	
}
