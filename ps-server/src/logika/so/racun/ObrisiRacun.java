package logika.so.racun;

import domain.OpstiDomenskiObjekat;
import domain.Racun;
import domain.StavkaRacuna;
import logika.db.dbBroker;
import logika.so.OpstaSO;

public class ObrisiRacun extends OpstaSO<Void> {

	@Override
	protected void preduslov(OpstiDomenskiObjekat obj) throws Exception {
		if (obj == null || !(obj instanceof Racun)) {
			throw new Exception("Prosledjeni objekat nije Racun");
		}
	}

	@Override
	protected Void transakcija(OpstiDomenskiObjekat obj) throws Exception {
		Racun racun = (Racun) obj;
		for (StavkaRacuna s : racun.getStavkeRacuna()) {
			dbBroker.delete(s);
		}
		return dbBroker.delete(racun);
	}
	
}
