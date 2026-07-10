package logika.kontroler;

import domain.Knjiga;
import domain.OpstiDomenskiObjekat;
import domain.Zaposleni;
import java.util.List;
import logika.so.OpstaSO;
import logika.so.SOException;
import logika.so.knjiga.KreirajKnjiga;
import logika.so.knjiga.ObrisiKnjiga;
import logika.so.knjiga.vratiListuKnjiga;
import logika.so.zaposleni.vratiListuZaposleni;

public class Kontroler {

	public static Void kreirajKnjiga(OpstiDomenskiObjekat obj) throws Exception {
		OpstaSO so = new KreirajKnjiga();
		return (Void) so.izvrsiTransakciju(obj);
	}

	public static List<Knjiga> vratiListuKnjiga(OpstiDomenskiObjekat obj) throws Exception {
		OpstaSO so = new vratiListuKnjiga();
		return (List<Knjiga>) so.izvrsiTransakciju(obj);
	}
	
	public static List<Zaposleni> vratiListuZaposleni(OpstiDomenskiObjekat obj) throws Exception {
		OpstaSO so = new vratiListuZaposleni();
		return (List<Zaposleni>) so.izvrsiTransakciju(obj);
	}

	public static Void ObrisiKnjiga(OpstiDomenskiObjekat obj) throws SOException {
		OpstaSO so = new ObrisiKnjiga();
		return (Void) so.izvrsiTransakciju(obj);
	}
	
}
