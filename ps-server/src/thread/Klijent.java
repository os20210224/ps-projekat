package thread;

import domain.Zaposleni;
import java.net.Socket;
import main.Server;
import transfer.Reciever;
import transfer.Request;
import transfer.Response;
import transfer.Sender;
import transfer.enums.Status;

public class Klijent extends Thread {

    Server srv;
    Socket s;
	Sender sender;
	Reciever rec;

    public Klijent(Server srv, Socket s) {
        this.srv = srv;
        this.s = s;
		srv.addKlijent(this);
		sender = new Sender(s);
		rec = new Reciever(s);
        start();
    }
    
    @Override
    public void run() {
		try {
			Request req = (Request) rec.recieve();
			switch (req.getOperation()) {
				case LOGIN:
					Zaposleni zap = (Zaposleni) req.getObject();
					// db provera
					sender.send(new Response(null, Status.SUCCESS));
					// sender.send(new Response("Pogresni kredencijali", Status.FAILURE));
					// srv.log()
					break;
			}
		} catch (Exception ex) {
			srv.log("> Reciever error: " + ex);
		}
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
