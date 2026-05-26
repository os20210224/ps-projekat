package thread;

import java.net.Socket;
import main.Server;

public class Klijent extends Thread {

    Server srv;
    Socket s;
	// out
	// in

    public Klijent(Server srv, Socket s) {
        this.srv = srv;
        this.s = s;
		srv.addKlijent(this);
        start();
		// out
		// in
    }
    
    @Override
    public void run() {
    }
	
	public void likvidiraj() {
		try {
            s.close();
            srv.removeKlijent(this);
			// log
        } catch (Exception e) {
            if (e.getMessage().equals("Socket closed")){
                return;
            }
            srv.log("> Klijent likvidacija error:\n" + e.getMessage());
        }
	}
    
}
