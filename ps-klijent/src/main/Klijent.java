package main;

import domain.FizickoLice;
import domain.Knjiga;
import domain.Kupac;
import domain.PravnoLice;
import domain.Racun;
import domain.Smena;
import domain.Zaposleni;
import thread.SenderThread;
import gui.FrmKlijent;
import gui.FrmLogin;
import transfer.Response;
import transfer.enums.Operation;


public class Klijent {
	static Klijent klijent;
	static FrmKlijent f;
	static FrmLogin fl;
	static SenderThread sender;
	public static Zaposleni ulogovaniZaposleni;
	
	public Klijent() {
		klijent = this;
		
		sender = new SenderThread(klijent);
		
		f = new FrmKlijent(klijent);
		fl = new FrmLogin(klijent);
		
		fl.setVisible(true);
	}
	
	public void login(Zaposleni zaposleni) {
		ulogovaniZaposleni = zaposleni;
		fl.dispose();
		f.setVisible(true);
	}
	
	public String connect(String address, int port) {
		return sender.connect(address, port);
	}
	
	public void serverTerminacija() {
		f.dispose();
	}
	
	public void terminiraj() {
		sender.send(null, Operation.TERMINIRAJ);
		sender.close();
		f.dispose();
	}
	
	// Prijava
	public static Response PrijaviZaposleni(Zaposleni z) {
		return (Response) sender.send(z, Operation.PRIJAVI_ZAPOSLENI);
	}
	
	// Knjiga
	public static Response KreirajKnjiga(Knjiga k) {
		return (Response) sender.send(k, Operation.KREIRAJ_KNJIGA);
	}
	public static Response ObrisiKnjiga(Knjiga k) {
		return (Response) sender.send(k, Operation.OBRISI_KNJIGA);
	}
	public static Response PromeniKnjiga(Knjiga k) {
		return (Response) sender.send(k, Operation.PROMENI_KNJIGA);
	}
	public static Response vratiListuKnjiga(Knjiga k) {
		return (Response)sender.send(k, Operation.VRATI_LISTU_KNJIGA);
	}
	
	// Kupac
	public static Response vratiListuKupac(Kupac k) {
		return (Response) sender.send(k, Operation.VRATI_LISTU_KUPAC);
	}
	
	// FizickoLice
	public static Response KreirajFizickoLice(FizickoLice l) {
		return (Response) sender.send(l, Operation.KREIRAJ_FIZICKO_LICE);
	}
	public static Response ObrisiFizickoLice(FizickoLice l) {
		return (Response) sender.send(l, Operation.OBRISI_FIZICKO_LICE);
	}
	public static Response PromeniFizickoLice(FizickoLice l) {
		return (Response) sender.send(l, Operation.PROMENI_FIZICKO_LICE);
	}
	public static Response vratiListuFizickoLice(FizickoLice l) {
		return (Response) sender.send(l, Operation.VRATI_LISTU_FIZICKO_LICE);
	}
	
	// PravnoLice
	public static Response KreirajPravnoLice(PravnoLice l) {
		return (Response) sender.send(l, Operation.KREIRAJ_PRAVNO_LICE);
	}
	public static Response ObrisiPravnoLice(PravnoLice l) {
		return (Response) sender.send(l, Operation.OBRISI_PRAVNO_LICE);
	}
	public static Response PromeniPravnoLice(PravnoLice l) {
		return (Response) sender.send(l, Operation.PROMENI_PRAVNO_LICE);
	}
	public static Response vratiListuPravnoLice(PravnoLice l) {
		return (Response) sender.send(l, Operation.VRATI_LISTU_PRAVNO_LICE);
	}
	
	// Racun
	public static Response KreirajRacun(Racun r) {
		return (Response) sender.send(r, Operation.KREIRAJ_RACUN);
	}
	public static Response PromeniRacun(Racun r) {
		return (Response) sender.send(r, Operation.PROMENI_RACUN);
	}
	public static Response vratiListuRacun(Racun r) {
		return (Response)sender.send(r, Operation.VRATI_LISTU_RACUN);
	}
	
	// Smena
	public static Response UbaciSmena(Smena s) {
		return (Response) sender.send(s, Operation.UBACI_SMENA);
	}
	public static Response ObrisiSmena(Smena s) {
		return (Response) sender.send(s, Operation.OBRISI_SMENA);
	}
	public static Response PromeniSmena(Smena s) {
		return (Response) sender.send(s, Operation.PROMENI_SMENA);
	}
	public static Response vratiListuSmena(Smena s) {
		return (Response)sender.send(s, Operation.VRATI_LISTU_SMENA);
	}
	
	// Zaposleni
	public static Response KreirajZaposleni(Zaposleni z) {
		return (Response) sender.send(z, Operation.KREIRAJ_ZAPOSLENI);
	}
	public static Response ObrisiZaposleni(Zaposleni z) {
		return (Response) sender.send(z, Operation.OBRISI_ZAPOSLENI);
	}
	public static Response PromeniZaposleni(Zaposleni z) {
		return (Response) sender.send(z, Operation.PROMENI_ZAPOSLENI);
	}
	public static Response vratiListuZaposleni(Zaposleni z) {
		return (Response) sender.send(z, Operation.VRATI_LISTU_ZAPOSLENI);
	}
	
	public static void main(String[] args) {
		new Klijent();
	}
	
}
