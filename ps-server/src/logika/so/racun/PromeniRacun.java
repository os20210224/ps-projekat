package logika.so.racun;

import domain.OpstiDomenskiObjekat;
import domain.Racun;
import domain.StavkaRacuna;
import logika.db.dbBroker;
import logika.so.OpstaSO;

public class PromeniRacun extends OpstaSO<Void> {

	@Override
	protected void preduslov(OpstiDomenskiObjekat obj) throws Exception {
		if (obj == null || !(obj instanceof Racun)) {
			throw new Exception("Prosledjeni objekat nije Racun");
		}
	}

	@Override
	protected Void transakcija(OpstiDomenskiObjekat obj) throws Exception {
		Racun racun = (Racun) obj;
		dbBroker.delete(racun);
		Long idRacun = dbBroker.kreiraj(racun);
		for (StavkaRacuna s : racun.getStavkeRacuna()) {
			s.setIdRacun(idRacun);
			dbBroker.kreiraj(s);
		}
		return dbBroker.update((Racun) obj);
	}
	
}
