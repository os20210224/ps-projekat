package logika.db.so.kupac;

import domain.Knjiga;
import domain.OpstiDomenskiObjekat;
import logika.db.dbBroker;
import logika.db.so.OpstaSO;

public class kreirajKnjigu extends OpstaSO {

	@Override
	protected void preduslov(OpstiDomenskiObjekat obj) throws Exception {
		if (obj == null || !(obj instanceof Knjiga)) {
			throw new Exception("Prosledjeni objekat nije Kupac");
		}
	}

	@Override
	protected void transakcija(OpstiDomenskiObjekat obj) throws Exception {
		dbBroker.kreiraj((Knjiga)obj);
	}
	
}
