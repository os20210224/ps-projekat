package main;

import domain.Knjiga;
import domain.Zaposleni;
import thread.KlijentThread;
import gui.FrmKlijent;
import gui.FrmLogin;
import java.util.List;
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
	
	public static String KreirajKnjiga(Knjiga k) {
		return (String) kt.send(k, Operation.KREIRAJ_KNJIGA);
	}
	
	public static List<Knjiga> vratiListuKnjiga() {
		return (List<Knjiga>) kt.send(new Knjiga(), Operation.VRATI_LISTU_KNJIGA);
	}
	
	public static String PrijaviZaposleni(Zaposleni z) {
		return (String) kt.send(z, Operation.PRIJAVI_ZAPOSLENI);
	}
	
	public static void main(String[] args) {
		new Klijent();
	}
	
}
