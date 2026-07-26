package main;

import domain.FizickoLice;
import domain.Knjiga;
import domain.Kupac;
import domain.PravnoLice;
import domain.Racun;
import domain.Smena;
import domain.StavkaRacuna;
import domain.Zaposleni;
import thread.KlijentThread;
import gui.FrmKlijent;
import gui.FrmLogin;
import transfer.Response;
import transfer.enums.Operation;


public class Klijent {
	static Klijent klijent;
	static FrmKlijent f;
	static FrmLogin fl;
	static KlijentThread kt;
	
	public Klijent() {
		klijent = this;
		
		kt = new KlijentThread(klijent);
		
		f = new FrmKlijent(klijent);
		fl = new FrmLogin(klijent);
		
		fl.setVisible(true);
	}
	
	public String connect(String address, int port) {
		return kt.connect(address, port);
	}
	
	public void login() {
		fl.dispose();
		f.setVisible(true);
	}
	
	public static Response PrijaviZaposleni(Zaposleni z) {
		return (Response) kt.send(z, Operation.PRIJAVI_ZAPOSLENI);
	}
	
	public static Response KreirajKnjiga(Knjiga k) {
		return (Response) kt.send(k, Operation.KREIRAJ_KNJIGA);
	}
	
	public static Response ObrisiKnjiga(Knjiga k) {
		return (Response) kt.send(k, Operation.OBRISI_KNJIGA);
	}
	
	public static Response PromeniKnjiga(Knjiga k) {
		return (Response) kt.send(k, Operation.PROMENI_KNJIGA);
	}
	
	public static Response vratiListuKnjiga(Knjiga k) {
		return (Response)kt.send(k, Operation.VRATI_LISTU_KNJIGA);
	}
	
	public static Response KreirajZaposleni(Zaposleni z) {
		return (Response) kt.send(z, Operation.KREIRAJ_ZAPOSLENI);
	}
	
	public static Response ObrisiZaposleni(Zaposleni z) {
		return (Response) kt.send(z, Operation.OBRISI_ZAPOSLENI);
	}
	
	public static Response PromeniZaposleni(Zaposleni z) {
		return (Response) kt.send(z, Operation.PROMENI_ZAPOSLENI);
	}
	
	public static Response vratiListuZaposleni(Zaposleni z) {
		return (Response) kt.send(z, Operation.VRATI_LISTU_ZAPOSLENI);
	}
	
	public static Response KreirajFizickoLice(FizickoLice l) {
		return (Response) kt.send(l, Operation.KREIRAJ_FIZICKO_LICE);
	}
	
	public static Response ObrisiFizickoLice(FizickoLice l) {
		return (Response) kt.send(l, Operation.OBRISI_FIZICKO_LICE);
	}
	
	public static Response PromeniFizickoLice(FizickoLice l) {
		return (Response) kt.send(l, Operation.PROMENI_FIZICKO_LICE);
	}
	
	public static Response vratiListuFizickoLice(FizickoLice l) {
		return (Response) kt.send(l, Operation.VRATI_LISTU_FIZICKO_LICE);
	}
	
	public static Response KreirajPravnoLice(PravnoLice l) {
		return (Response) kt.send(l, Operation.KREIRAJ_PRAVNO_LICE);
	}
	
	public static Response ObrisiPravnoLice(PravnoLice l) {
		return (Response) kt.send(l, Operation.OBRISI_PRAVNO_LICE);
	}
	
	public static Response PromeniPravnoLice(PravnoLice l) {
		return (Response) kt.send(l, Operation.PROMENI_PRAVNO_LICE);
	}
	
	public static Response vratiListuPravnoLice(PravnoLice l) {
		return (Response) kt.send(l, Operation.VRATI_LISTU_PRAVNO_LICE);
	}
	
	public static Response vratiListuKupac(Kupac k) {
		return (Response) kt.send(k, Operation.VRATI_LISTU_KUPAC);
	}
	
	public static Response KreirajRacun(Racun r) {
		return (Response) kt.send(r, Operation.KREIRAJ_RACUN);
	}
	
	public static Response ObrisiRacun(Racun r) {
		return (Response) kt.send(r, Operation.OBRISI_RACUN);
	}
	
	public static Response PromeniRacun(Racun r) {
		return (Response) kt.send(r, Operation.PROMENI_RACUN);
	}
	
	public static Response vratiListuRacun(Racun r) {
		return (Response)kt.send(r, Operation.VRATI_LISTU_RACUN);
	}
	
	public static Response KreirajStavkaRacuna(StavkaRacuna s) {
		return (Response) kt.send(s, Operation.KREIRAJ_STAVKA_RACUNA);
	}
	
	public static Response ObrisiStavkaRacuna(StavkaRacuna s) {
		return (Response) kt.send(s, Operation.OBRISI_STAVKA_RACUNA);
	}
	
	public static Response PromeniStavkaRacuna(StavkaRacuna s) {
		return (Response) kt.send(s, Operation.PROMENI_STAVKA_RACUNA);
	}
	
	public static Response vratiListuStavkaRacuna(StavkaRacuna s) {
		return (Response)kt.send(s, Operation.VRATI_LISTU_STAVKA_RACUNA);
	}
	
	public static Response KreirajSmena(Smena s) {
		return (Response) kt.send(s, Operation.KREIRAJ_SMENA);
	}
	
	public static Response ObrisiSmena(Smena s) {
		return (Response) kt.send(s, Operation.OBRISI_SMENA);
	}
	
	public static Response PromeniSmena(Smena s) {
		return (Response) kt.send(s, Operation.PROMENI_SMENA);
	}
	
	public static Response vratiListuSmena(Smena s) {
		return (Response)kt.send(s, Operation.VRATI_LISTU_SMENA);
	}
	
	public static void main(String[] args) {
		new Klijent();
	}
	
}
