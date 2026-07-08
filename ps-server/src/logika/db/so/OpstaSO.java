package logika.db.so;

import domain.OpstiDomenskiObjekat;
import logika.db.dbBroker;

public abstract class OpstaSO {
	
	protected abstract void preduslov(OpstiDomenskiObjekat obj) throws Exception;
	protected abstract void transakcija(OpstiDomenskiObjekat obj) throws Exception;
	
	public void izvrsiTransakciju(OpstiDomenskiObjekat obj) {
		try {
			preduslov(obj);
			transakcija(obj);
			dbBroker.commit();
		} catch (Exception e) {
			dbBroker.rollback();
		}
	}
	
}
