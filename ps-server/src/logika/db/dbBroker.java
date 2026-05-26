package logika.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import main.Server;

public class dbBroker {
	
    Server srv;
    private static Connection conn = null;
    private static String url = "";
    private static String user = "";
    private static String pass = "";
	
	public dbBroker(Server srv) {
		this.srv = srv;
	}
    
    public void setCredentials(String address, String port, String name, String username, String password) {
        url = "jdbc:mysql://"+ address + ":" + port + "/" + name;
        user = username;
        pass = password;
        srv.logDB("> credentials set:\n      url: " + url + "\n      username: " + user);
    }
    
    private void connect() {
        try {
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("> konekcija uspesna");
        } catch (SQLException e) {
            System.out.println("> connection error" + e);
        }
    }
    
    private void disconnect() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("> diskonekcija uspesna");
            }
        } catch (SQLException e) {
            System.out.println("> disconnection error" + e);
        }
    }
    
}
