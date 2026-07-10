package logika.so.zaposleni;

import domain.OpstiDomenskiObjekat;
import domain.Zaposleni;
import logika.db.dbBroker;
import logika.so.OpstaSO;

public class PromeniZaposleni extends OpstaSO<Void> {

	@Override
	protected void preduslov(OpstiDomenskiObjekat obj) throws Exception {
		if (obj == null || !(obj instanceof Zaposleni)) {
			throw new Exception("Prosledjeni objekat nije Zaposleni");
		}
	}

	@Override
	protected Void transakcija(OpstiDomenskiObjekat obj) throws Exception {
		return dbBroker.update((Zaposleni) obj);
	}
	
}
