package thread;

import domain.OpstiDomenskiObjekat;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import main.Klijent;
import transfer.Reciever;
import transfer.Request;
import transfer.Response;
import transfer.Sender;
import transfer.enums.Operation;
import transfer.enums.Status;

public class KlijentThread extends Thread {
	Klijent klijent;
    Socket soket = null;
	
	Sender sender;
	Reciever rec;

    public KlijentThread(Klijent klijent) {
		this.klijent = klijent;
		start();
    }

    @Override
    public void run() {
    }
	
	public String connect(String address, int port) {
		if (soket != null) {
			return "";
		}
		try {
			soket = new Socket(address, port);
		} catch (IOException ex) {
			System.out.println(ex);
			if (ex instanceof UnknownHostException) {
				return "Nepoznata adresa";
			} else if (ex instanceof ConnectException) {
				return "Konekcija odbijena";
			} else if (ex instanceof SocketException) {
				return "Greska sa mrezom";
			} else {
				return "Greska";
			}
		}
		
		sender = new Sender(soket);
		rec = new Reciever(soket);
		
		return "";
	}
	
	public Object send(OpstiDomenskiObjekat obj, Operation operation) {
		Response res;
		try {
			sender.send(new Request(obj, operation));
			System.out.println("zahtev poslat " +  operation);
			res = (Response) rec.recieve();
			System.out.println("odgovor primljen" + res.getStatus());
			return res.getObject();
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return "";
	}
}
