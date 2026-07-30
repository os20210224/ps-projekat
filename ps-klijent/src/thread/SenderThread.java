package thread;

import domain.OpstiDomenskiObjekat;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.CompletableFuture;
import main.Klijent;
import transfer.Request;
import transfer.Response;
import transfer.Sender;
import transfer.enums.Operation;

public class SenderThread extends Thread {
	Klijent klijent;
    Socket soket = null;
	
	Sender sender;
	
	CompletableFuture<Response> response;
	
    public SenderThread(Klijent klijent) {
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
		new RecieverThread(klijent, soket, this);
		
		return "";
	}
	
	public Object send(OpstiDomenskiObjekat obj, Operation operation) {
		response = new CompletableFuture<>();
		try {
			sender.send(new Request(obj, operation));
			System.out.println("zahtev poslat " +  operation);
			Response res = response.get();
			System.out.println("Odgovor primljen " + res.getStatus());
			return res;
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return "";
	}
	
	public void close() {
		try {
			soket.close();
		} catch (IOException ex) {
			System.out.println("Socket closure error " + ex);
		}
	}

}
