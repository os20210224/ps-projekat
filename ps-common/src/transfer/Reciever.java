package transfer;

import java.io.BufferedInputStream;
import java.io.ObjectInputStream;
import java.net.Socket;


public class Reciever {
	
	Socket soket;

	public Reciever(Socket soket) {
		this.soket = soket;
	}
	
	public Object recieve() throws Exception {
		ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(soket.getInputStream()));
		return in.readObject();
	}
	
}
