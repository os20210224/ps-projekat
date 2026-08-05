package logika.so.kupac.pravno_lice;

import domain.Kupac;
import domain.OpstiDomenskiObjekat;
import domain.PravnoLice;
import logika.db.dbBroker;
import logika.so.OpstaSO;
import logika.so.kupac.KreirajKupac;

public class KreirajPravnoLice extends OpstaSO<Long> {

	@Override
	protected void preduslov(OpstiDomenskiObjekat obj) throws Exception {
		if (obj == null || !(obj instanceof PravnoLice)) {
			throw new Exception("Prosledjeni objekat nije PravnoLice");
		}
	}

	@Override
	protected Long transakcija(OpstiDomenskiObjekat obj) throws Exception {
		PravnoLice l = (PravnoLice) obj;
		OpstaSO so = new KreirajKupac();
		long id = (long) so.izvrsiTransakciju(new Kupac(l.getIdKupac(), l.getTelefon(), l.getEmail()));
		if (id == 0) {
			throw new Exception("Greska pri cuvanju kupca");
		}
		l.setIdKupac(id);
		return dbBroker.create(l);
	}
	
}
