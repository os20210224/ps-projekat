package thread;

import java.io.IOException;
import java.net.ServerSocket;
import main.Server;

public class Lobby extends Thread {
    
	Server srv;
    public ServerSocket serverSocket;

    public Lobby(Server srv) {
		this.srv = srv;
		start();
    }

    @Override
    public void run() {
		try {
			serverSocket = new ServerSocket(7259);
			srv.log("> Server online.");
			while (true) {
				new KlijentHandler(srv, serverSocket.accept());
			}
		} catch (IOException ex) {
			if (ex.getMessage().equals("Socket closed")) {
				return;
			}
			srv.log("> Server thread error: \n" + ex.getMessage());
		}
    }
    
}
