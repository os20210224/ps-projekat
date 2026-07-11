package logika.so.kupac;

import domain.Kupac;
import domain.OpstiDomenskiObjekat;
import logika.db.dbBroker;
import logika.so.OpstaSO;

public class KreirajKupac extends OpstaSO<Long> {

	@Override
	protected void preduslov(OpstiDomenskiObjekat obj) throws Exception {
		if (obj == null || !(obj instanceof Kupac)) {
			throw new Exception("Prosledjeni objekat nije Kupac");
		}
	}

	@Override
	protected Long transakcija(OpstiDomenskiObjekat obj) throws Exception {
		return dbBroker.kreiraj(obj);
	}
	
}
