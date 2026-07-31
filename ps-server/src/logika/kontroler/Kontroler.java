package logika.kontroler;

import domain.FizickoLice;
import domain.Knjiga;
import domain.Kupac;
import domain.OpstiDomenskiObjekat;
import domain.PravnoLice;
import domain.Racun;
import domain.Smena;
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
import logika.so.racun.KreirajRacun;
import logika.so.racun.PromeniRacun;
import logika.so.racun.vratiListuRacun;
import logika.so.smena.UbaciSmena;
import logika.so.smena.ObrisiSmena;
import logika.so.smena.PromeniSmena;
import logika.so.smena.vratiListuSmena;
import logika.so.zaposleni.KreirajZaposleni;
import logika.so.zaposleni.ObrisiZaposleni;
import logika.so.zaposleni.PromeniZaposleni;
import logika.so.zaposleni.vratiListuZaposleni;

public class Kontroler {

	// Knjiga
	public static Long KreirajKnjiga(OpstiDomenskiObjekat obj) throws Exception {
		OpstaSO so = new KreirajKnjiga();
		return (Long) so.izvrsiTransakciju(obj);
	}
	public static Void ObrisiKnjiga(OpstiDomenskiObjekat obj) throws SOException {
		OpstaSO so = new ObrisiKnjiga();
		return (Void) so.izvrsiTransakciju(obj);
	}
	public static Void PromeniKnjiga(OpstiDomenskiObjekat obj) throws SOException {
		OpstaSO so = new PromeniKnjiga();
		return (Void) so.izvrsiTransakciju(obj);
	}
	public static List<Knjiga> vratiListuKnjiga(OpstiDomenskiObjekat obj) throws Exception {
		OpstaSO so = new vratiListuKnjiga();
		return (List<Knjiga>) so.izvrsiTransakciju(obj);
	}
	
	
	// Kupac
	public static Long KreirajKupac(OpstiDomenskiObjekat obj) throws SOException {
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
	
	
	// FizickoLice
	public static Long KreirajFizickoLice(OpstiDomenskiObjekat obj) throws SOException {
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
	
	
	// PravnoLice
	public static Long KreirajPravnoLice(OpstiDomenskiObjekat obj) throws SOException {
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
	
	
	// Racun
	public static Long KreirajRacun(OpstiDomenskiObjekat obj) throws SOException {
		OpstaSO so = new KreirajRacun();
		return (Long) so.izvrsiTransakciju(obj);
	}
	public static Void PromeniRacun(OpstiDomenskiObjekat obj) throws SOException {
		OpstaSO so = new PromeniRacun();
		return (Void) so.izvrsiTransakciju(obj);
	}
	public static List<Racun> vratiListuRacun(OpstiDomenskiObjekat obj) throws SOException {
		OpstaSO so = new vratiListuRacun();
		return (List<Racun>) so.izvrsiTransakciju(obj);
	}
	
	
	// Smena
	public static Long UbaciSmena(OpstiDomenskiObjekat obj) throws SOException {
		OpstaSO so = new UbaciSmena();
		return (Long) so.izvrsiTransakciju(obj);
	}
	public static Void ObrisiSmena(OpstiDomenskiObjekat obj) throws SOException {
		OpstaSO so = new ObrisiSmena();
		return (Void) so.izvrsiTransakciju(obj);
	}
	public static Void PromeniSmena(OpstiDomenskiObjekat obj) throws SOException {
		OpstaSO so = new PromeniSmena();
		return (Void) so.izvrsiTransakciju(obj);
	}
	public static List<Smena> vratiListuSmena(OpstiDomenskiObjekat obj) throws SOException {
		OpstaSO so = new vratiListuSmena();
		return (List<Smena>) so.izvrsiTransakciju(obj);
	}
	
	
	// Zaposleni
	public static Long KreirajZaposleni(OpstiDomenskiObjekat obj) throws SOException {
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
	public static List<Zaposleni> vratiListuZaposleni(OpstiDomenskiObjekat obj) throws Exception {
		OpstaSO so = new vratiListuZaposleni();
		return (List<Zaposleni>) so.izvrsiTransakciju(obj);
	}

}
