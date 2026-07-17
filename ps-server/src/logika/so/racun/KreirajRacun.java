package logika.so.racun;

import domain.OpstiDomenskiObjekat;
import domain.Racun;
import logika.db.dbBroker;
import logika.so.OpstaSO;

public class KreirajRacun extends OpstaSO<Long> {

	@Override
	protected void preduslov(OpstiDomenskiObjekat obj) throws Exception {
		if (obj == null || !(obj instanceof Racun)) {
			throw new Exception("Prosledjeni objekat nije Racun");
		}
	}

	@Override
	protected Long transakcija(OpstiDomenskiObjekat obj) throws Exception {
		return dbBroker.kreiraj((Racun)obj);
	}
	
}
