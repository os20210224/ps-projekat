package logika.so.knjiga;

import domain.Knjiga;
import domain.OpstiDomenskiObjekat;
import domain.Zaposleni;
import domain.enums.Format;
import domain.enums.Povez;
import java.util.List;
import main.Server;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class vratiListuKnjigaTest {
	
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

	/**
	 * Test of preduslov method, of class KreirajKnjiga.
	 */
	@Test
	public void testPreduslovNull() throws Exception {
		System.out.println("vratiListuKnjiga - preduslov - null");
		KreirajKnjiga so = new KreirajKnjiga();
		try {
			so.preduslov(null);
			fail("Ocekivani izuzetak nije bacen");
		} catch (Exception e) {
			if (!e.getMessage().equals("Prosledjeni objekat nije Knjiga")) {
				fail("Pogresan izuzetak je bacen");
			}
		}
	}
	@Test
	public void testPreduslovNeKnjiga() throws Exception {
		System.out.println("vratiListuKnjiga - preduslov - ne knjiga");
		KreirajKnjiga so = new KreirajKnjiga();
		try {
			so.preduslov(new Zaposleni());
			fail("Ocekivani izuzetak nije bacen");
		} catch (Exception e) {
			assertNotNull(e);
		}
	}

	/**
	 * Test of transakcija method, of class vratiListuKnjiga.
	 */
	@Test
	public void testTransakcija() throws Exception {
		System.out.println("Promeni - transakcija");
		(new KreirajKnjiga()).izvrsiTransakciju(new Knjiga(Format.A4, 42, Povez.MEK, 5, 100, "TEST KNJIGA VRATI KNJIGU", "AUTOMATSKI TEST"));
		OpstiDomenskiObjekat obj = (OpstiDomenskiObjekat) (new vratiListuKnjiga()).izvrsiTransakciju(
			new Knjiga(Format.A4, 42, Povez.MEK, 5, 100, "TEST KNJIGA VRATI KNJIGU", "AUTOMATSKI TEST")
		).get(0);
		vratiListuKnjiga so = new vratiListuKnjiga();
		try {
			List<Knjiga> knjige = so.izvrsiTransakciju(obj);
			assertEquals(1, knjige.size());
			(new ObrisiKnjiga()).izvrsiTransakciju(
				(OpstiDomenskiObjekat)(new vratiListuKnjiga()).izvrsiTransakciju(
					new Knjiga(Format.A4, 42, Povez.MEK, 5, 100, "TEST KNJIGA VRATI KNJIGU", "AUTOMATSKI TEST")
				).get(0)
			);
		} catch (Exception e) {
			fail("Neocekivani izuzetak bacen");
		}
	}
	
}
