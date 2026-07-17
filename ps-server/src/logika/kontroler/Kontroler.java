package logika.kontroler;

import domain.FizickoLice;
import domain.Knjiga;
import domain.Kupac;
import domain.OpstiDomenskiObjekat;
import domain.PravnoLice;
import domain.Racun;
import domain.StavkaRacuna;
import domain.Zaposleni;
import java.util.List;
import logika.so.OpstaSO;
import logika.so.SOException;
import logika.so.knjiga.KreirajKnjiga;
import logika.so.knjiga.ObrisiKnjiga;
import logika.so.knjiga.PromeniKnjiga;
import logika.so.knjiga.vratiListuKnjiga;
import logika.so.kupac.KreirajKupac;
import logika.so.kupac.ObrisiKupac;
import logika.so.kupac.PromeniKupac;
import logika.so.kupac.fizicko_lice.KreirajFizickoLice;
import logika.so.kupac.fizicko_lice.ObrisiFizickoLice;
import logika.so.kupac.fizicko_lice.PromeniFizickoLice;
import logika.so.kupac.fizicko_lice.vratiListuFizickoLice;
import logika.so.kupac.pravno_lice.KreirajPravnoLice;
import logika.so.kupac.pravno_lice.ObrisiPravnoLice;
import logika.so.kupac.pravno_lice.PromeniPravnoLice;
import logika.so.kupac.pravno_lice.vratiListuPravnoLice;
import logika.so.kupac.vratiListuKupac;
import logika.so.racun.vratiListuRacun;
import logika.so.stavka_racuna.vratiListuStavkaRacuna;
import logika.so.zaposleni.KreirajZaposleni;
import logika.so.zaposleni.ObrisiZaposleni;
import logika.so.zaposleni.PromeniZaposleni;
import logika.so.zaposleni.vratiListuZaposleni;

public class Kontroler {

	public static Long kreirajKnjiga(OpstiDomenskiObjekat obj) throws Exception {
		OpstaSO so = new KreirajKnjiga();
		return (Long) so.izvrsiTransakciju(obj);
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
	
	public static Void PromeniKnjiga(OpstiDomenskiObjekat obj) throws SOException {
		OpstaSO so = new PromeniKnjiga();
		return (Void) so.izvrsiTransakciju(obj);
	}

	public static Long kreirajZaposleni(OpstiDomenskiObjekat obj) throws SOException {
		OpstaSO so = new KreirajZaposleni();
		return (Long) so.izvrsiTransakciju(obj);
	}

	public static Void ObrisiZaposleni(OpstiDomenskiObjekat obj) throws SOException {
		OpstaSO so = new ObrisiZaposleni();
		return (Void) so.izvrsiTransakciju(obj);
	}

	public static Void PromeniZaposleni(OpstiDomenskiObjekat obj) throws SOException {
		OpstaSO so = new PromeniZaposleni();
		return (Void) so.izvrsiTransakciju(obj);
	}
	
	public static Long kreirajKupac(OpstiDomenskiObjekat obj) throws SOException {
		OpstaSO so = new KreirajKupac();
		return (Long) so.izvrsiTransakciju(obj);
	}

	public static Void ObrisiKupac(OpstiDomenskiObjekat obj) throws SOException {
		OpstaSO so = new ObrisiKupac();
		return (Void) so.izvrsiTransakciju(obj);
	}

	public static Void PromeniKupac(OpstiDomenskiObjekat obj) throws SOException {
		OpstaSO so = new PromeniKupac();
		return (Void) so.izvrsiTransakciju(obj);
	}
	
	public static List<Kupac> vratiListuKupac(OpstiDomenskiObjekat obj) throws SOException {
		OpstaSO so = new vratiListuKupac();
		return (List<Kupac>) so.izvrsiTransakciju(obj);
	}
	
	public static Long kreirajFizickoLice(OpstiDomenskiObjekat obj) throws SOException {
		OpstaSO so = new KreirajFizickoLice();
		return (Long) so.izvrsiTransakciju(obj);
	}

	public static Void ObrisiFizickoLice(OpstiDomenskiObjekat obj) throws SOException {
		OpstaSO so = new ObrisiFizickoLice();
		return (Void) so.izvrsiTransakciju(obj);
	}

	public static Void PromeniFizickoLice(OpstiDomenskiObjekat obj) throws SOException {
		OpstaSO so = new PromeniFizickoLice();
		return (Void) so.izvrsiTransakciju(obj);
	}
	
	public static List<FizickoLice> vratiListuFizickoLice(OpstiDomenskiObjekat obj) throws SOException {
		OpstaSO so = new vratiListuFizickoLice();
		return (List<FizickoLice>) so.izvrsiTransakciju(obj);
	}

	public static Long kreirajPravnoLice(OpstiDomenskiObjekat obj) throws SOException {
		OpstaSO so = new KreirajPravnoLice();
		return (Long) so.izvrsiTransakciju(obj);
	}

	public static Void ObrisiPravnoLice(OpstiDomenskiObjekat obj) throws SOException {
		OpstaSO so = new ObrisiPravnoLice();
		return (Void) so.izvrsiTransakciju(obj);
	}

	public static Void PromeniPravnoLice(OpstiDomenskiObjekat obj) throws SOException {
		OpstaSO so = new PromeniPravnoLice();
		return (Void) so.izvrsiTransakciju(obj);
	}
	
	public static List<PravnoLice> vratiListuPravnoLice(OpstiDomenskiObjekat obj) throws SOException {
		OpstaSO so = new vratiListuPravnoLice();
		return (List<PravnoLice>) so.izvrsiTransakciju(obj);
	}
	
	public static List<Racun> vratiListuRacun(OpstiDomenskiObjekat obj) throws SOException {
		OpstaSO so = new vratiListuRacun();
		return (List<Racun>) so.izvrsiTransakciju(obj);
	}
	
	public static List<StavkaRacuna> vratiListuStavkaRacuna(OpstiDomenskiObjekat obj) throws SOException {
		OpstaSO so = new vratiListuStavkaRacuna();
		return (List<StavkaRacuna>) so.izvrsiTransakciju(obj);
	}

}
