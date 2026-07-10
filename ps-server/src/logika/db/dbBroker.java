package logika.db;

import domain.OpstiDomenskiObjekat;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import main.Server;

public class dbBroker {
	
    private static Server srv;
    private static Connection conn = null;
    private static String url = "";
    private static String user = "";
    private static String pass = "";
	
	public dbBroker(Server srv) {
		this.srv = srv;
	}
    
    public boolean setCredentials(String address, int port, String name, String username, String password) {
        url = "jdbc:mysql://"+ address + ":" + port + "/" + name;
        user = username;
        pass = password;
        srv.logDB("> credentials set:\n      url: " + url + "\n      username: " + user + "\n");
		if (!connect()) {
			return false;
		}
		disconnect();
		return true;
    }
    
    private static boolean connect() {
        try {
            conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false);
            srv.logDB("> konekcija uspesna");
        } catch (SQLException e) {
            srv.logDB("> connection error" + e);
			return false;
        }
		return true;
    }
    
    private static boolean disconnect() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                srv.logDB("> diskonekcija uspesna");
            }
        } catch (SQLException e) {
            srv.logDB("> disconnection error" + e);
			return false;
        }
		return true;
    }
	
	public static void commit() {
		try {
			conn.commit();
			srv.logDB("> commit uspesan");
		} catch (SQLException e) {
			srv.logDB("> commit error" + e);
		} finally {
			disconnect();
		}
	}
	
	public static void rollback() {
		try {
			conn.rollback();
			srv.logDB("> rollback uspesan");
		} catch (SQLException e) {
			srv.logDB("> rollback error" + e);
		} finally {
			disconnect();
		}
	}
	
	public static Void kreiraj(OpstiDomenskiObjekat obj) throws Exception {
		if (!connect()) {
			throw new Exception("Greska pri konekciji sa bazom podataka");
		}
		try {
			String q = 
				"INSERT INTO "		+ obj.getTableName()	+
				" "					+ obj.getColumns()		+
				" "					+ obj.getValues()		;
			srv.logDB("> Querry:\n\t" + q);
			Statement s = conn.createStatement();
			s.executeUpdate(q);
			srv.logDB("> Objekat " + obj + "uspesno sacuvan");
		} catch (SQLException e) {
			srv.logDB("> greska pri kreiranju sloga" + e);
			throw e;
		}
		return null;
	}
	
	public static ResultSet select(OpstiDomenskiObjekat obj) throws Exception {
		if (!connect()) {
			throw new Exception("Greska pri konekciji sa bazom podataka");
		}
		ResultSet rs;
		try {
			String q =
				"SELECT * FROM "	+ obj.getTableName() + 
				" WHERE "			+ obj.getCondition() ;
			srv.logDB("> Querry:\n\t" + q);
			Statement s = conn.createStatement();
			rs = s.executeQuery(q);
			srv.logDB("> slogovi selektovani");
		} catch (SQLException e) {
			srv.logDB("> greska pri selektovanju slogova" + e);
			throw e;
		}
		return rs;
	}
	
	public static Void delete(OpstiDomenskiObjekat obj) throws Exception {
		if (!connect()) {
			throw new Exception("Greska pri konekciji sa bazom podataka");
		}
		try {
			String q =
				"DELETE FROM "	+ obj.getTableName()	+ 
				" WHERE "		+ obj.getIDCondition()	;
			srv.logDB("> Querry:\n\t" + q);
			Statement s = conn.createStatement();
			s.executeUpdate(q);
			srv.logDB("> slogovi selektovani");
		} catch (SQLException e) {
			srv.logDB("> greska pri selektovanju slogova" + e);
			throw e;
		}
		return null;
	}
	
	public static Void update(OpstiDomenskiObjekat obj) throws Exception {
		if (!connect()) {
			throw new Exception("Greska pri konekciji sa bazom podataka");
		}
		try {
			String q =
				"UPDATE "		+ obj.getTableName()	+
				" SET "			+ obj.getUpdate()		+
				" WHERE "		+ obj.getIDCondition()	;
			srv.logDB("> Querry:\n\t" + q);
			Statement s = conn.createStatement();
			s.executeUpdate(q);
			srv.logDB("> slogovi selektovani");
		} catch (SQLException e) {
			srv.logDB("> greska pri selektovanju slogova" + e);
			throw e;
		}
		return null;
	}
    
}
