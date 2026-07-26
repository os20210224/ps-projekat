package logika.kontroler;

import domain.FizickoLice;
import domain.Knjiga;
import domain.Kupac;
import domain.OpstiDomenskiObjekat;
import domain.PravnoLice;
import domain.Racun;
import domain.Smena;
import domain.SmenaZaposlenog;
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
import logika.so.racun.KreirajRacun;
import logika.so.racun.ObrisiRacun;
import logika.so.racun.PromeniRacun;
import logika.so.racun.vratiListuRacun;
import logika.so.smena.KreirajSmena;
import logika.so.smena.ObrisiSmena;
import logika.so.smena.PromeniSmena;
import logika.so.smena.vratiListuSmena;
import logika.so.smena_zaposlenog.KreirajSmenaZaposlenog;
import logika.so.smena_zaposlenog.ObrisiSmenaZaposlenog;
import logika.so.smena_zaposlenog.PromeniSmenaZaposlenog;
import logika.so.smena_zaposlenog.vratiListuSmenaZaposlenog;
import logika.so.stavka_racuna.KreirajStavkaRacuna;
import logika.so.stavka_racuna.ObrisiStavkaRacuna;
import logika.so.stavka_racuna.PromeniStavkaRacuna;
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
	
	public static Long kreirajRacun(OpstiDomenskiObjekat obj) throws SOException {
		OpstaSO so = new KreirajRacun();
		return (Long) so.izvrsiTransakciju(obj);
	}

	public static Void ObrisiRacun(OpstiDomenskiObjekat obj) throws SOException {
		OpstaSO so = new ObrisiRacun();
		return (Void) so.izvrsiTransakciju(obj);
	}

	public static Void PromeniRacun(OpstiDomenskiObjekat obj) throws SOException {
		OpstaSO so = new PromeniRacun();
		return (Void) so.izvrsiTransakciju(obj);
	}

	public static List<Racun> vratiListuRacun(OpstiDomenskiObjekat obj) throws SOException {
		OpstaSO so = new vratiListuRacun();
		return (List<Racun>) so.izvrsiTransakciju(obj);
	}
	
	public static Long kreirajStavkaRacuna(OpstiDomenskiObjekat obj) throws SOException {
		OpstaSO so = new KreirajStavkaRacuna();
		return (Long) so.izvrsiTransakciju(obj);
	}

	public static Void ObrisiStavkaRacuna(OpstiDomenskiObjekat obj) throws SOException {
		OpstaSO so = new ObrisiStavkaRacuna();
		return (Void) so.izvrsiTransakciju(obj);
	}

	public static Void PromeniStavkaRacuna(OpstiDomenskiObjekat obj) throws SOException {
		OpstaSO so = new PromeniStavkaRacuna();
		return (Void) so.izvrsiTransakciju(obj);
	}

	public static List<StavkaRacuna> vratiListuStavkaRacuna(OpstiDomenskiObjekat obj) throws SOException {
		OpstaSO so = new vratiListuStavkaRacuna();
		return (List<StavkaRacuna>) so.izvrsiTransakciju(obj);
	}
	
	public static Long kreirajSmena(OpstiDomenskiObjekat obj) throws SOException {
		OpstaSO so = new KreirajSmena();
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
	
	public static Long kreirajSmenaZaposlenog(OpstiDomenskiObjekat obj) throws SOException {
		OpstaSO so = new KreirajSmenaZaposlenog();
		return (Long) so.izvrsiTransakciju(obj);
	}

	public static Void ObrisiSmenaZaposlenog(OpstiDomenskiObjekat obj) throws SOException {
		OpstaSO so = new ObrisiSmenaZaposlenog();
		return (Void) so.izvrsiTransakciju(obj);
	}

	public static Void PromeniSmenaZaposlenog(OpstiDomenskiObjekat obj) throws SOException {
		OpstaSO so = new PromeniSmenaZaposlenog();
		return (Void) so.izvrsiTransakciju(obj);
	}

	public static List<SmenaZaposlenog> vratiListuSmenaZaposlenog(OpstiDomenskiObjekat obj) throws SOException {
		OpstaSO so = new vratiListuSmenaZaposlenog();
		return (List<SmenaZaposlenog>) so.izvrsiTransakciju(obj);
	}

}
