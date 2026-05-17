package logika.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbBroker {
    
    private static Connection conn = null;
    
    private static void connect() {
        try {
            String url = "jdbc:mysql://localhost:3306/ps-projekat";
            String user = "root";
            String pass = "";
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("> konekcija uspesna");
        } catch (SQLException e) {
            System.out.println("> connection error" + e);
        }
    }
    
    private static void disconnect() {
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
