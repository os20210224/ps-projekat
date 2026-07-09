package logika.so;

import domain.OpstiDomenskiObjekat;
import logika.db.dbBroker;

public abstract class OpstaSO<T> {	
	
	protected abstract void preduslov(OpstiDomenskiObjekat obj) throws Exception;
	protected abstract T transakcija(OpstiDomenskiObjekat obj) throws Exception;
	
	public T izvrsiTransakciju(OpstiDomenskiObjekat obj) throws SOException {
		try {
			preduslov(obj);
			T res = transakcija(obj);
			dbBroker.commit();
			return res;
		} catch (Exception e) {
			dbBroker.rollback();
			throw new SOException(e.getMessage());
		}
	}
	
}
