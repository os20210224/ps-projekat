package logika.so.knjiga;

import domain.Knjiga;
import domain.OpstiDomenskiObjekat;
import logika.db.dbBroker;
import logika.so.OpstaSO;

public class KreirajKnjiga extends OpstaSO<Long> {

	@Override
	protected void preduslov(OpstiDomenskiObjekat obj) throws Exception {
		if (obj == null || !(obj instanceof Knjiga)) {
			throw new Exception("Prosledjeni objekat nije Knjiga");
		}
	}

	@Override
	protected Long transakcija(OpstiDomenskiObjekat obj) throws Exception {
		return dbBroker.create((Knjiga)obj);
	}
	
}
