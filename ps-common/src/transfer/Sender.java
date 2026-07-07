package transfer;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Sender {
	
	Socket soket;

	public Sender(Socket soket) {
		this.soket = soket;
	}
	
	public void send(Object obj) throws IOException {
		ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(soket.getOutputStream()));
		out.writeObject(obj);
		out.flush();
	}
	
}
