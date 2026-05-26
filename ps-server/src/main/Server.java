package main;

import gui.FrmServer;
import java.util.ArrayList;
import java.util.List;
import logika.db.dbBroker;
import thread.Klijent;
import thread.Lobby;

public class Server {
    
	static Server srv;
    static FrmServer f;
	static dbBroker dbBroker;
	Lobby lobby;
	
	List<Klijent> klijenti = new ArrayList<>();
	
	public Server() {
		srv = this;
		
		dbBroker = new dbBroker(srv);
		f = new FrmServer(srv);
		f.setVisible(true);
	}
	
	public void addKlijent(Klijent klijent) {
		klijenti.add(klijent);
	}
	
	public void removeKlijent(Klijent klijent) {
		klijenti.remove(klijent);
	}
    
    public void logDB(String log) {
        f.logDB(log);
    }
    
    public void log(String log) {
        f.log(log);
    }
    
    public void setDbCredentials(String address, String port, String name, String username, String password) {
        dbBroker.setCredentials(address, port, name, username, password);
    }

    public void start() {
		lobby = new Lobby(srv);
    }
	
	public void stop() {
		try {
            lobby.serverSocket.close();
            for (Klijent k : klijenti) {
                k.likvidiraj();
            }
            log("> Server offline.");
        } catch (Exception e) {
            log("> Server stop error:\n" + e.getMessage());
        }
	}
    
    public static void main(String[] args) {
        new Server();
    }
}
