package logika.so.stavka_racuna;

import domain.OpstiDomenskiObjekat;
import domain.StavkaRacuna;
import logika.db.dbBroker;
import logika.so.OpstaSO;

public class KreirajStavkaRacuna extends OpstaSO<Long> {

	@Override
	protected void preduslov(OpstiDomenskiObjekat obj) throws Exception {
		if (obj == null || !(obj instanceof StavkaRacuna)) {
			throw new Exception("Prosledjeni objekat nije StavkaRacuna");
		}
	}

	@Override
	protected Long transakcija(OpstiDomenskiObjekat obj) throws Exception {
		return dbBroker.kreiraj((StavkaRacuna)obj);
	}
	
}
