package logika.so.knjiga;

import main.Server;
import org.junit.AfterClass;
import static org.junit.Assert.fail;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({logika.so.knjiga.KreirajKnjigaTest.class, logika.so.knjiga.vratiListuKnjigaTest.class, logika.so.knjiga.PromeniKnjigaTest.class, logika.so.knjiga.ObrisiKnjigaTest.class})
public class KnjigaSuite {
	
	static Server server;
	
	@BeforeClass
	public static void init() {
		server = new Server();
		if (!server.setDbCredentials("localhost", 3306, "ps-projekat", "root", "")) {
			fail("KONEKCIJA NE RADI");
		}
		server.start();
	}
	
	@AfterClass
	public static void deinit() {
		server.stop();
	}
}
