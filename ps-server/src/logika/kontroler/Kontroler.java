package logika.kontroler;

import domain.Knjiga;
import domain.OpstiDomenskiObjekat;
import java.util.List;
import logika.so.OpstaSO;
import logika.so.knjiga.KreirajKnjiga;
import logika.so.knjiga.vratiListuKnjiga;

public class Kontroler {

	public static Void kreirajKnjiga(OpstiDomenskiObjekat obj) throws Exception {
		OpstaSO so = new KreirajKnjiga();
		return (Void) so.izvrsiTransakciju(obj);
	}

	public static List<Knjiga> vratiListuKnjiga(OpstiDomenskiObjekat obj) throws Exception {
		OpstaSO so = new vratiListuKnjiga();
		return (List<Knjiga>) so.izvrsiTransakciju(obj);
	}
	
}
