package logika.so.smena;

import domain.OpstiDomenskiObjekat;
import domain.Smena;
import logika.db.dbBroker;
import logika.so.OpstaSO;

public class ObrisiSmena extends OpstaSO<Void> {

	@Override
	protected void preduslov(OpstiDomenskiObjekat obj) throws Exception {
		if (obj == null || !(obj instanceof Smena)) {
			throw new Exception("Prosledjeni objekat nije Smena");
		}
	}

	@Override
	protected Void transakcija(OpstiDomenskiObjekat obj) throws Exception {
		return dbBroker.delete((Smena) obj);
	}
	
}
