package logika.so.kupac.pravno_lice;

import domain.Kupac;
import domain.OpstiDomenskiObjekat;
import domain.PravnoLice;
import logika.db.dbBroker;
import logika.so.OpstaSO;
import logika.so.kupac.PromeniKupac;

public class PromeniPravnoLice extends OpstaSO<Void> {

	@Override
	protected void preduslov(OpstiDomenskiObjekat obj) throws Exception {
		if (obj == null || !(obj instanceof PravnoLice)) {
			throw new Exception("Prosledjeni objekat nije PravnoLice");
		}
	}

	@Override
	protected Void transakcija(OpstiDomenskiObjekat obj) throws Exception {
		PravnoLice l = (PravnoLice) obj;
		OpstaSO so = new PromeniKupac();
		so.izvrsiTransakciju(new Kupac(l.getIdKupac(), l.getTelefon(), l.getEmail()));
		return dbBroker.update(l);
	}
	
}
