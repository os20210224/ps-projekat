package thread;

import domain.FizickoLice;
import domain.Knjiga;
import domain.Kupac;
import domain.OpstiDomenskiObjekat;
import domain.PravnoLice;
import domain.Racun;
import domain.Smena;
import domain.SmenaZaposlenog;
import domain.StavkaRacuna;
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
				switch (op) {
					case PRIJAVI_ZAPOSLENI:
						srv.log("> Obrada zahteva " + op);
						try {
							List<Zaposleni> zaposleni = Kontroler.vratiListuZaposleni((OpstiDomenskiObjekat) req.getObject());
							if (zaposleni.size() == 1) { 
								sender.send(new Response(null, Status.SUCCESS));
								srv.log("> Odgovor poslat\n");
							} else {
								sender.send(new Response("Pogresni podaci", Status.FAILURE));
							}
						} catch (SOException e) {
							srv.log("> SOException: " + e);
							sender.send(new Response(e.getMessage(), Status.FAILURE));
						}
						break;
					case KREIRAJ_KNJIGA:
						srv.log("> Obrada zahteva " + op);
						try {
							Kontroler.kreirajKnjiga((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(null, Status.SUCCESS));
							srv.log("> Odgovor poslat\n");
						} catch (SOException e) {
							srv.log("> SOException: " + e);
							sender.send(new Response(e.getMessage(), Status.FAILURE));
						}
						break;
					case OBRISI_KNJIGA:
						srv.log("> Obrada zahteva " + op);
						try {
							Kontroler.ObrisiKnjiga((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(null, Status.SUCCESS));
							srv.log("> Odgovor poslat\n");
						} catch (SOException e) {
							srv.log("> SOException: " + e);
							sender.send(new Response(e.getMessage(), Status.FAILURE));
						}
						break;
					case PROMENI_KNJIGA:
						srv.log("> Obrada zahteva " + op);
						try {
							Kontroler.PromeniKnjiga((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(null, Status.SUCCESS));
							srv.log("> Odgovor poslat\n");
						} catch (SOException e) {
							srv.log("> SOException: " + e);
							sender.send(new Response(e.getMessage(), Status.FAILURE));
						}
						break;
					case VRATI_LISTU_KNJIGA:
						srv.log("> Obrada zahteva " + op);
						try {
							List<Knjiga> knjige = Kontroler.vratiListuKnjiga((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(knjige, Status.SUCCESS));
							srv.log("> Odgovor poslat\n");
						} catch (SOException e) {
							srv.log("> SOException: " + e);
							sender.send(new Response(e.getMessage(), Status.FAILURE));
						}
						break;
					case KREIRAJ_ZAPOSLENI:
						srv.log("> Obrada zahteva " + op);
						try {
							Kontroler.kreirajZaposleni((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(null, Status.SUCCESS));
							srv.log("> Odgovor poslat\n");
						} catch (SOException e) {
							srv.log("> SOException: " + e);
							sender.send(new Response(e.getMessage(), Status.FAILURE));
						}
						break;
					case OBRISI_ZAPOSLENI:
						srv.log("> Obrada zahteva " + op);
						try {
							Kontroler.ObrisiZaposleni((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(null, Status.SUCCESS));
							srv.log("> Odgovor poslat\n");
						} catch (SOException e) {
							srv.log("> SOException: " + e);
							sender.send(new Response(e.getMessage(), Status.FAILURE));
						}
						break;
					case PROMENI_ZAPOSLENI:
						srv.log("> Obrada zahteva " + op);
						try {
							Kontroler.PromeniZaposleni((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(null, Status.SUCCESS));
							srv.log("> Odgovor poslat\n");
						} catch (SOException e) {
							srv.log("> SOException: " + e);
							sender.send(new Response(e.getMessage(), Status.FAILURE));
						}
						break;
					case VRATI_LISTU_ZAPOSLENI:
						srv.log("> Obrada zahteva " + op);
						try {
							List<Zaposleni> zaposleni = Kontroler.vratiListuZaposleni((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(zaposleni, Status.SUCCESS));
							srv.log("> Odgovor poslat\n");
						} catch (SOException e) {
							srv.log("> SOException: " + e);
							sender.send(new Response(e.getMessage(), Status.FAILURE));
						}
						break;
					case KREIRAJ_FIZICKO_LICE:
						srv.log("> Obrada zahteva " + op);
						try {
							Kontroler.kreirajFizickoLice((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(null, Status.SUCCESS));
							srv.log("> Odgovor poslat\n");
						} catch (SOException e) {
							srv.log("> SOException: " + e);
							sender.send(new Response(e.getMessage(), Status.FAILURE));
						}
						break;
					case OBRISI_FIZICKO_LICE:
						srv.log("> Obrada zahteva " + op);
						try {
							Kontroler.ObrisiFizickoLice((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(null, Status.SUCCESS));
							srv.log("> Odgovor poslat\n");
						} catch (SOException e) {
							srv.log("> SOException: " + e);
							sender.send(new Response(e.getMessage(), Status.FAILURE));
						}
						break;
					case PROMENI_FIZICKO_LICE:
						srv.log("> Obrada zahteva " + op);
						try {
							Kontroler.PromeniFizickoLice((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(null, Status.SUCCESS));
							srv.log("> Odgovor poslat\n");
						} catch (SOException e) {
							srv.log("> SOException: " + e);
							sender.send(new Response(e.getMessage(), Status.FAILURE));
						}
						break;
					case VRATI_LISTU_FIZICKO_LICE:
						srv.log("> Obrada zahteva " + op);
						try {
							List<FizickoLice> lica = Kontroler.vratiListuFizickoLice((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(lica, Status.SUCCESS));
							srv.log("> Odgovor poslat\n");
						} catch (SOException e) {
							srv.log("> SOException: " + e);
							sender.send(new Response(e.getMessage(), Status.FAILURE));
						}
						break;
					case KREIRAJ_PRAVNO_LICE:
						srv.log("> Obrada zahteva " + op);
						try {
							Kontroler.kreirajPravnoLice((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(null, Status.SUCCESS));
							srv.log("> Odgovor poslat\n");
						} catch (SOException e) {
							srv.log("> SOException: " + e);
							sender.send(new Response(e.getMessage(), Status.FAILURE));
						}
						break;
					case OBRISI_PRAVNO_LICE:
						srv.log("> Obrada zahteva " + op);
						try {
							Kontroler.ObrisiPravnoLice((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(null, Status.SUCCESS));
							srv.log("> Odgovor poslat\n");
						} catch (SOException e) {
							srv.log("> SOException: " + e);
							sender.send(new Response(e.getMessage(), Status.FAILURE));
						}
						break;
					case PROMENI_PRAVNO_LICE:
						srv.log("> Obrada zahteva " + op);
						try {
							Kontroler.PromeniPravnoLice((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(null, Status.SUCCESS));
							srv.log("> Odgovor poslat\n");
						} catch (SOException e) {
							srv.log("> SOException: " + e);
							sender.send(new Response(e.getMessage(), Status.FAILURE));
						}
						break;
					case VRATI_LISTU_PRAVNO_LICE:
						srv.log("> Obrada zahteva " + op);
						try {
							List<PravnoLice> lica = Kontroler.vratiListuPravnoLice((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(lica, Status.SUCCESS));
							srv.log("> Odgovor poslat\n");
						} catch (SOException e) {
							srv.log("> SOException: " + e);
							sender.send(new Response(e.getMessage(), Status.FAILURE));
						}
						break;
					case KREIRAJ_RACUN:
						srv.log("> Obrada zahteva " + op);
						try {
							Kontroler.kreirajRacun((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(null, Status.SUCCESS));
							srv.log("> Odgovor poslat\n");
						} catch (SOException e) {
							srv.log("> SOException: " + e);
							sender.send(new Response(e.getMessage(), Status.FAILURE));
						}
						break;
					case OBRISI_RACUN:
						srv.log("> Obrada zahteva " + op);
						try {
							Kontroler.ObrisiRacun((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(null, Status.SUCCESS));
							srv.log("> Odgovor poslat\n");
						} catch (SOException e) {
							srv.log("> SOException: " + e);
							sender.send(new Response(e.getMessage(), Status.FAILURE));
						}
						break;
					case PROMENI_RACUN:
						srv.log("> Obrada zahteva " + op);
						try {
							Kontroler.PromeniRacun((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(null, Status.SUCCESS));
							srv.log("> Odgovor poslat\n");
						} catch (SOException e) {
							srv.log("> SOException: " + e);
							sender.send(new Response(e.getMessage(), Status.FAILURE));
						}
						break;
					case VRATI_LISTU_RACUN:
						srv.log("> Obrada zahteva " + op);
						try {
							List<Racun> racuni = Kontroler.vratiListuRacun((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(racuni, Status.SUCCESS));
							srv.log("> Odgovor poslat\n");
						} catch (SOException e) {
							srv.log("> SOException: " + e);
							sender.send(new Response(e.getMessage(), Status.FAILURE));
						}
						break;
					case KREIRAJ_STAVKA_RACUNA:
						srv.log("> Obrada zahteva " + op);
						try {
							Kontroler.kreirajStavkaRacuna((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(null, Status.SUCCESS));
							srv.log("> Odgovor poslat\n");
						} catch (SOException e) {
							srv.log("> SOException: " + e);
							sender.send(new Response(e.getMessage(), Status.FAILURE));
						}
						break;
					case OBRISI_STAVKA_RACUNA:
						srv.log("> Obrada zahteva " + op);
						try {
							Kontroler.ObrisiStavkaRacuna((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(null, Status.SUCCESS));
							srv.log("> Odgovor poslat\n");
						} catch (SOException e) {
							srv.log("> SOException: " + e);
							sender.send(new Response(e.getMessage(), Status.FAILURE));
						}
						break;
					case PROMENI_STAVKA_RACUNA:
						srv.log("> Obrada zahteva " + op);
						try {
							Kontroler.PromeniStavkaRacuna((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(null, Status.SUCCESS));
							srv.log("> Odgovor poslat\n");
						} catch (SOException e) {
							srv.log("> SOException: " + e);
							sender.send(new Response(e.getMessage(), Status.FAILURE));
						}
						break;
					case VRATI_LISTU_STAVKA_RACUNA:
						srv.log("> Obrada zahteva " + op);
						try {
							List<StavkaRacuna> stavke = Kontroler.vratiListuStavkaRacuna((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(stavke, Status.SUCCESS));
							srv.log("> Odgovor poslat\n");
						} catch (SOException e) {
							srv.log("> SOException: " + e);
							sender.send(new Response(e.getMessage(), Status.FAILURE));
						}
						break;
					case VRATI_LISTU_KUPAC:
						srv.log("> Obrada zahteva " + op);
						try {
							List<Kupac> kupci = Kontroler.vratiListuKupac((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(kupci, Status.SUCCESS));
							srv.log("> Odgovor poslat\n");
						} catch (SOException e) {
							srv.log("> SOException: " + e);
							sender.send(new Response(e.getMessage(), Status.FAILURE));
						}
						break;
					case KREIRAJ_SMENA:
						srv.log("> Obrada zahteva " + op);
						try {
							Kontroler.kreirajSmena((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(null, Status.SUCCESS));
							srv.log("> Odgovor poslat\n");
						} catch (SOException e) {
							srv.log("> SOException: " + e);
							sender.send(new Response(e.getMessage(), Status.FAILURE));
						}
						break;
					case OBRISI_SMENA:
						srv.log("> Obrada zahteva " + op);
						try {
							Kontroler.ObrisiSmena((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(null, Status.SUCCESS));
							srv.log("> Odgovor poslat\n");
						} catch (SOException e) {
							srv.log("> SOException: " + e);
							sender.send(new Response(e.getMessage(), Status.FAILURE));
						}
						break;
					case PROMENI_SMENA:
						srv.log("> Obrada zahteva " + op);
						try {
							Kontroler.PromeniSmena((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(null, Status.SUCCESS));
							srv.log("> Odgovor poslat\n");
						} catch (SOException e) {
							srv.log("> SOException: " + e);
							sender.send(new Response(e.getMessage(), Status.FAILURE));
						}
						break;
					case VRATI_LISTU_SMENA:
						srv.log("> Obrada zahteva " + op);
						try {
							List<Smena> smene = Kontroler.vratiListuSmena((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(smene, Status.SUCCESS));
							srv.log("> Odgovor poslat\n");
						} catch (SOException e) {
							srv.log("> SOException: " + e);
							sender.send(new Response(e.getMessage(), Status.FAILURE));
						}
						break;
					case KREIRAJ_SMENA_ZAPOSLENOG:
						srv.log("> Obrada zahteva " + op);
						try {
							Kontroler.kreirajSmenaZaposlenog((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(null, Status.SUCCESS));
							srv.log("> Odgovor poslat\n");
						} catch (SOException e) {
							srv.log("> SOException: " + e);
							sender.send(new Response(e.getMessage(), Status.FAILURE));
						}
						break;
					case OBRISI_SMENA_ZAPOSLENOG:
						srv.log("> Obrada zahteva " + op);
						try {
							Kontroler.ObrisiSmenaZaposlenog((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(null, Status.SUCCESS));
							srv.log("> Odgovor poslat\n");
						} catch (SOException e) {
							srv.log("> SOException: " + e);
							sender.send(new Response(e.getMessage(), Status.FAILURE));
						}
						break;
					case PROMENI_SMENA_ZAPOSLENOG:
						srv.log("> Obrada zahteva " + op);
						try {
							Kontroler.PromeniSmenaZaposlenog((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(null, Status.SUCCESS));
							srv.log("> Odgovor poslat\n");
						} catch (SOException e) {
							srv.log("> SOException: " + e);
							sender.send(new Response(e.getMessage(), Status.FAILURE));
						}
						break;
					case VRATI_LISTU_SMENA_ZAPOSLENOG:
						srv.log("> Obrada zahteva " + op);
						try {
							List<SmenaZaposlenog> smeneZaposlenih = Kontroler.vratiListuSmenaZaposlenog((OpstiDomenskiObjekat) req.getObject());
							sender.send(new Response(smeneZaposlenih, Status.SUCCESS));
							srv.log("> Odgovor poslat\n");
						} catch (SOException e) {
							srv.log("> SOException: " + e);
							sender.send(new Response(e.getMessage(), Status.FAILURE));
						}
						break;
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
