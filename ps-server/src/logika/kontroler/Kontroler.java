package logika.kontroler;

import domain.OpstiDomenskiObjekat;
import logika.db.so.OpstaSO;
import logika.db.so.knjiga.KreirajKnjiga;

public class Kontroler {

	public static void kreirajKnjiga(OpstiDomenskiObjekat obj) throws Exception {
		OpstaSO so = new KreirajKnjiga();
		so.izvrsiTransakciju(obj);
	}
	
}
