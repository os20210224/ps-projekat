package main;

import Thread.KlijentThread;
import gui.FrmKlijent;
import gui.FrmLogin;


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
	
	public String login(String username, String password) {
		String res = kt.login(username, password);
		if (!res.equals("")) {
			return res;
		}
		fl.dispose();
		f.setVisible(true);
		return "";
	}
	
	public static void main(String[] args) {
		new Klijent();
	}
	
}
