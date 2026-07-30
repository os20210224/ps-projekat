package thread;

import domain.FizickoLice;
import domain.Knjiga;
import domain.Kupac;
import domain.OpstiDomenskiObjekat;
import domain.PravnoLice;
import domain.Racun;
import domain.Smena;
import domain.Zaposleni;
import java.net.Socket;
import java.util.List;
import logika.so.SOException;
import logika.kontroler.Kontroler;
import main.Server;
import transfer.Reciever;
import transfer.Request;
import transfer.Response;
import transfer.Sender;
import transfer.enums.Operation;
import transfer.enums.Status;

public class KlijentHandler extends Thread {

    Server srv;
    Socket s;
	Sender sender;
	Reciever rec;

    public KlijentHandler(Server srv, Socket s) {
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
		while (!s.isClosed()) {
			try {
				Request req = (Request) rec.recieve();
				Operation op = req.getOperation();
				srv.log("> zahtev primljen " + op);
				try {
					switch (op) {
						case PRIJAVI_ZAPOSLENI -> {
							srv.log("> Obrada zahteva " + op);
							srv.logDB("\n> Obrada zahteva " + op);
							List<Zaposleni> zaposleni = Kontroler.vratiListuZaposleni((OpstiDomenskiObjekat) req.getObject());
							if (zaposleni.size() == 1) { 
								sender.send(new Response(zaposleni.get(0), Status.SUCCESS));
							} else {
								sender.send(new Response("Pogresni podaci", Status.FAILURE));
							}
						}
						case KREIRAJ_KNJIGA -> {
							srv.log("> Obrada zahteva " + op);
							srv.logDB("\n> Obrada zahteva " + op);
							Kontroler.kreirajKnjiga((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(null, Status.SUCCESS));
						}
						case OBRISI_KNJIGA -> {
							srv.log("> Obrada zahteva " + op);
							srv.logDB("\n> Obrada zahteva " + op);
							Kontroler.ObrisiKnjiga((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(null, Status.SUCCESS));
						}
						case PROMENI_KNJIGA -> {
							srv.log("> Obrada zahteva " + op);
							srv.logDB("\n> Obrada zahteva " + op);
							Kontroler.PromeniKnjiga((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(null, Status.SUCCESS));
						}
						case VRATI_LISTU_KNJIGA -> {
							srv.log("> Obrada zahteva " + op);
							srv.logDB("\n> Obrada zahteva " + op);
							List<Knjiga> knjige = Kontroler.vratiListuKnjiga((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(knjige, Status.SUCCESS));
						}
						case KREIRAJ_ZAPOSLENI -> {
							srv.log("> Obrada zahteva " + op);
							srv.logDB("\n> Obrada zahteva " + op);
							Kontroler.kreirajZaposleni((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(null, Status.SUCCESS));
						}
						case OBRISI_ZAPOSLENI -> {
							srv.log("> Obrada zahteva " + op);
							srv.logDB("\n> Obrada zahteva " + op);
							Kontroler.ObrisiZaposleni((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(null, Status.SUCCESS));
						}
						case PROMENI_ZAPOSLENI -> {
							srv.log("> Obrada zahteva " + op);
							srv.logDB("\n> Obrada zahteva " + op);
							Kontroler.PromeniZaposleni((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(null, Status.SUCCESS));
						}
						case VRATI_LISTU_ZAPOSLENI -> {
							srv.log("> Obrada zahteva " + op);
							srv.logDB("\n> Obrada zahteva " + op);
							List<Zaposleni> zaposleni = Kontroler.vratiListuZaposleni((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(zaposleni, Status.SUCCESS));
						}
						case KREIRAJ_FIZICKO_LICE -> {
							srv.log("> Obrada zahteva " + op);
							srv.logDB("\n> Obrada zahteva " + op);
							Kontroler.kreirajFizickoLice((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(null, Status.SUCCESS));
						}
						case OBRISI_FIZICKO_LICE -> {
							srv.log("> Obrada zahteva " + op);
							srv.logDB("\n> Obrada zahteva " + op);
							Kontroler.ObrisiFizickoLice((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(null, Status.SUCCESS));
						}
						case PROMENI_FIZICKO_LICE -> {
							srv.log("> Obrada zahteva " + op);
							srv.logDB("\n> Obrada zahteva " + op);
							Kontroler.PromeniFizickoLice((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(null, Status.SUCCESS));
						}
						case VRATI_LISTU_FIZICKO_LICE -> {
							srv.log("> Obrada zahteva " + op);
							srv.logDB("\n> Obrada zahteva " + op);
							List<FizickoLice> lica = Kontroler.vratiListuFizickoLice((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(lica, Status.SUCCESS));
						}
						case KREIRAJ_PRAVNO_LICE -> {
							srv.log("> Obrada zahteva " + op);
							srv.logDB("\n> Obrada zahteva " + op);
							Kontroler.kreirajPravnoLice((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(null, Status.SUCCESS));
						}
						case OBRISI_PRAVNO_LICE -> {
							srv.log("> Obrada zahteva " + op);
							srv.logDB("\n> Obrada zahteva " + op);
							Kontroler.ObrisiPravnoLice((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(null, Status.SUCCESS));
						}
						case PROMENI_PRAVNO_LICE -> {
							srv.log("> Obrada zahteva " + op);
							srv.logDB("\n> Obrada zahteva " + op);
							Kontroler.PromeniPravnoLice((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(null, Status.SUCCESS));
						}
						case VRATI_LISTU_PRAVNO_LICE -> {
							srv.log("> Obrada zahteva " + op);
							srv.logDB("\n> Obrada zahteva " + op);
							List<PravnoLice> lica = Kontroler.vratiListuPravnoLice((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(lica, Status.SUCCESS));
						}
						case KREIRAJ_RACUN -> {
							srv.log("> Obrada zahteva " + op);
							srv.logDB("\n> Obrada zahteva " + op);
							Kontroler.kreirajRacun((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(null, Status.SUCCESS));
						}
						case OBRISI_RACUN -> {
							srv.log("> Obrada zahteva " + op);
							srv.logDB("\n> Obrada zahteva " + op);
							Kontroler.ObrisiRacun((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(null, Status.SUCCESS));
						}
						case PROMENI_RACUN -> {
							srv.log("> Obrada zahteva " + op);
							srv.logDB("\n> Obrada zahteva " + op);
							Kontroler.PromeniRacun((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(null, Status.SUCCESS));
						}
						case VRATI_LISTU_RACUN -> {
							srv.log("> Obrada zahteva " + op);
							srv.logDB("\n> Obrada zahteva " + op);
							List<Racun> racuni = Kontroler.vratiListuRacun((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(racuni, Status.SUCCESS));
						}
						case VRATI_LISTU_KUPAC -> {
							srv.log("> Obrada zahteva " + op);
							srv.logDB("\n> Obrada zahteva " + op);
							List<Kupac> kupci = Kontroler.vratiListuKupac((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(kupci, Status.SUCCESS));
						}
						case KREIRAJ_SMENA -> {
							srv.log("> Obrada zahteva " + op);
							srv.logDB("\n> Obrada zahteva " + op);
							Kontroler.kreirajSmena((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(null, Status.SUCCESS));
						}
						case OBRISI_SMENA -> {
							srv.log("> Obrada zahteva " + op);
							srv.logDB("\n> Obrada zahteva " + op);
							Kontroler.ObrisiSmena((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(null, Status.SUCCESS));
						}
						case PROMENI_SMENA -> {
							srv.log("> Obrada zahteva " + op);
							srv.logDB("\n> Obrada zahteva " + op);
							Kontroler.PromeniSmena((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(null, Status.SUCCESS));
						}
						case VRATI_LISTU_SMENA -> {
							srv.log("> Obrada zahteva " + op);
							srv.logDB("\n> Obrada zahteva " + op);
							List<Smena> smene = Kontroler.vratiListuSmena((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(smene, Status.SUCCESS));
						}
						case TERMINIRAJ -> {
							srv.log("> Obrada zahteva " + op);
							sender.send(new Response(null, Status.SUCCESS));
							s.close();
							srv.log("> Thread ugasen");
							return;
						}
					} 
					srv.log("> Odgovor poslat\n");
				} catch (SOException e) {
					srv.log("> SOException: " + e);
					sender.send(new Response(e.getMessage(), Status.FAILURE));
				}
			} catch (Exception ex) {
				if (ex.getMessage() != null || ex.getMessage().equals("Socket closed")) {
					return;
				}
				srv.log("> Reciever error: " + ex);
			}
		}
    }
	
	public void likvidiraj() {
		try {
			sender.send(new Request(null, Operation.TERMINIRAJ));
            s.close();
			srv.log("> Thread ugasen");
        } catch (Exception e) {
            if (e.getMessage().equals("Socket closed")){
                return;
            }
            srv.log("> Klijent likvidacija error:\n" + e.getMessage());
        }
	}
    
}
