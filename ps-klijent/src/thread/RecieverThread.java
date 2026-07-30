package thread;

import java.io.EOFException;
import java.net.Socket;
import java.util.concurrent.CompletableFuture;
import main.Klijent;
import transfer.Reciever;
import transfer.Request;
import transfer.Response;
import transfer.enums.Operation;

public class RecieverThread extends Thread {
	Klijent klijent;
	SenderThread senderThread;
	
	Reciever rec;

	public RecieverThread(Klijent klijent, Socket soket, SenderThread senderThread) {
		this.klijent = klijent;
		this.senderThread = senderThread;
		rec = new Reciever(soket);
		start();
	}
	
	@Override
	public void run() {
		while (!isInterrupted()) {
			try {
				Object obj = rec.recieve();
				if (obj instanceof Request) {
					Request req = (Request) obj;
					if (req.getOperation() == Operation.TERMINIRAJ) {
						klijent.serverTerminacija();
						return;
					}
				} else {
					Response res = (Response) obj;
					CompletableFuture<Response> response = senderThread.response;
					response.complete(res);
				}
			} catch (Exception ex) {
				if (ex instanceof EOFException) {
					return;
				}
				System.out.println("Greska " + ex);
			}
		}
	}
	
}
