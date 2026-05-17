package main;

import gui.FrmServer;
import logika.db.dbBroker;

public class Server {
    
    private static FrmServer fServer;
    
    public static void logDB(String log) {
        fServer.logDB(log);
    }
    
    public static void log(String log) {
        fServer.log(log);
    }
    
    public static void setDbCredentials(String address, String port, String name, String username, String password) {
        dbBroker.setCredentials(address, port, name, username, password);
    }
    
    public static void main(String[] args) {
        fServer = new FrmServer();
        fServer.setVisible(true);
    }
}
