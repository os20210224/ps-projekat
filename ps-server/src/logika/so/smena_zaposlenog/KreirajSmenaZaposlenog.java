package logika.so.smena_zaposlenog;

import domain.OpstiDomenskiObjekat;
import domain.SmenaZaposlenog;
import logika.db.dbBroker;
import logika.so.OpstaSO;

public class KreirajSmenaZaposlenog extends OpstaSO<Long> {

	@Override
	protected void preduslov(OpstiDomenskiObjekat obj) throws Exception {
		if (obj == null || !(obj instanceof SmenaZaposlenog)) {
			throw new Exception("Prosledjeni objekat nije SmenaZaposlenog");
		}
	}

	@Override
	protected Long transakcija(OpstiDomenskiObjekat obj) throws Exception {
		return dbBroker.kreiraj((SmenaZaposlenog)obj);
	}
	
}
