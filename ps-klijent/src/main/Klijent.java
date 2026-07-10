package main;

import domain.Knjiga;
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
	
	public static Response KreirajKnjiga(Knjiga k) {
		return (Response) kt.send(k, Operation.KREIRAJ_KNJIGA);
	}
	
	public static Response vratiListuKnjiga(Knjiga k) {
		return (Response)kt.send(k, Operation.VRATI_LISTU_KNJIGA);
	}
	
	public static Response obrisiKnjiga(Knjiga k) {
		return (Response) kt.send(k, Operation.OBRISI_KNJIGA);
	}
	
	public static Response PromeniKnjiga(Knjiga k) {
		return (Response) kt.send(k, Operation.PROMENI_KNJIGA);
	}
	
	public static Response PrijaviZaposleni(Zaposleni z) {
		return (Response) kt.send(z, Operation.PRIJAVI_ZAPOSLENI);
	}
	
	public static void main(String[] args) {
		new Klijent();
	}
	
}
