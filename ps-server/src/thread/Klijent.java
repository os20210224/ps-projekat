package thread;

import domain.OpstiDomenskiObjekat;
import domain.Zaposleni;
import java.net.Socket;
import logika.db.so.SOException;
import logika.kontroler.Kontroler;
import main.Server;
import transfer.Reciever;
import transfer.Request;
import transfer.Response;
import transfer.Sender;
import transfer.enums.Operation;
import static transfer.enums.Operation.KREIRAJ_KNJIGA;
import static transfer.enums.Operation.PRIJAVI_ZAPOSLENI;
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
		srv.log("> Klijent konektovan");
		sender = new Sender(s);
		rec = new Reciever(s);
        start();
    }
    
    @Override
    public void run() {
		while (s.isConnected()) {
			try {
				Request req = (Request) rec.recieve();
				Operation op = req.getOperation();
				srv.log("> zahtev primljen " + op);
				switch (op) {
					case PRIJAVI_ZAPOSLENI:
						srv.log("> Obrada zahteva " + op);
						Zaposleni zap = (Zaposleni) req.getObject();
						// db provera TODO
						sender.send(new Response(null, Status.SUCCESS));
						srv.log("> Odgovor poslat\n");
						// sender.send(new Response("Pogresni kredencijali", Status.FAILURE));
						// srv.log()
						break;
					case KREIRAJ_KNJIGA:
						srv.log("> Obrada zahteva " + op);
						try {
							Kontroler.kreirajKnjiga((OpstiDomenskiObjekat) req.getObject());
						} catch (SOException e) {
							srv.log("> SOException: " + e);
							sender.send(new Response(e.getMessage(), Status.FAILURE));
						}
						sender.send(new Response(null, Status.SUCCESS));
						srv.log("> Odgovor poslat\n");
						break;
				}
			} catch (Exception ex) {
				srv.log("> Reciever error: " + ex);
			}
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
