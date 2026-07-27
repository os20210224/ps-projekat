package main;

import gui.FrmServer;
import java.util.ArrayList;
import java.util.List;
import logika.db.dbBroker;
import thread.KlijentHandler;
import thread.Lobby;

public class Server {
    
	static Server srv;
    static FrmServer f;
	static dbBroker dbBroker;
	Lobby lobby;
	
	List<KlijentHandler> klijenti = new ArrayList<>();
	
	public Server() {
		srv = this;
		
		dbBroker = new dbBroker(srv);
		f = new FrmServer(srv);
		f.setVisible(true);
	}
	
	public void addKlijent(KlijentHandler klijent) {
		klijenti.add(klijent);
	}
	
	public void removeKlijent(KlijentHandler klijent) {
		klijenti.remove(klijent);
	}
    
    public void logDB(String log) {
        f.logDB(log);
    }
    
    public void log(String log) {
        f.log(log);
    }
    
    public boolean setDbCredentials(String address, int port, String name, String username, String password) {
        return dbBroker.setCredentials(address, port, name, username, password);
    }

    public void start() {
		try {
			dbBroker.connect();
		} catch (Exception ex) {
			f.log(ex.getMessage());
		}
		lobby = new Lobby(srv);
    }
	
	public void stop() {
		dbBroker.disconnect();
		try {
            lobby.serverSocket.close();
			for (KlijentHandler k : klijenti) {
				k.likvidiraj();
			}
			klijenti = new ArrayList<>();
            log("> Server offline.");
        } catch (Exception e) {
            log("> Server stop error:\n" + e);
        }
	}
    
    public static void main(String[] args) {
        new Server();
    }
}
