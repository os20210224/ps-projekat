package logika.so.knjiga;

import domain.Knjiga;
import domain.OpstiDomenskiObjekat;
import domain.Zaposleni;
import domain.enums.Format;
import domain.enums.Povez;
import main.Server;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class KreirajKnjigaTest {
	
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
		System.out.println("KreirajKnjiga - preduslov - null");
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
		System.out.println("KreirajKnjiga - preduslov - ne knjiga");
		KreirajKnjiga so = new KreirajKnjiga();
		try {
			so.preduslov(new Zaposleni());
			fail("Ocekivani izuzetak nije bacen");
		} catch (Exception e) {
			assertNotNull(e);
		}
	}

	/**
	 * Test of transakcija method, of class KreirajKnjiga.
	 */
	@Test
	public void testTransakcijaNedostajuciFormat() throws Exception {
		System.out.println("KreirajKnjiga - transakcija - nedostajuci format");
		OpstiDomenskiObjekat obj = new Knjiga(null, 42, Povez.MEK, 5, 100, "TEST KNJIGA", "AUTOMATSKI TEST");
		KreirajKnjiga so = new KreirajKnjiga();
		try {
			so.transakcija(obj);
			fail("Ocekivani izuzetak nije bacen");
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
	@Test
	public void testTransakcijaBrStrana0() throws Exception {
		System.out.println("KreirajKnjiga - transakcija - broj strana 0");
		OpstiDomenskiObjekat obj = new Knjiga(Format.A4, 0, Povez.MEK, 5, 100, "TEST KNJIGA", "AUTOMATSKI TEST");
		KreirajKnjiga so = new KreirajKnjiga();
		try {
			so.transakcija(obj);
			fail("Ocekivani izuzetak nije bacen");
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
	@Test
	public void testTransakcijaNedostajuciPovez() throws Exception {
		System.out.println("KreirajKnjiga - transakcija - nedostajuci povez");
		OpstiDomenskiObjekat obj = new Knjiga(Format.A4, 42, null, 5, 100, "TEST KNJIGA", "AUTOMATSKI TEST");
		KreirajKnjiga so = new KreirajKnjiga();
		try {
			so.transakcija(obj);
			fail("Ocekivani izuzetak nije bacen");
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
	@Test
	public void testTransakcijaCenaStrana0() throws Exception {
		System.out.println("KreirajKnjiga - transakcija - cena strana 0");
		OpstiDomenskiObjekat obj = new Knjiga(Format.A4, 42, Povez.MEK, 0, 100, "TEST KNJIGA", "AUTOMATSKI TEST");
		KreirajKnjiga so = new KreirajKnjiga();
		try {
			so.transakcija(obj);
			fail("Ocekivani izuzetak nije bacen");
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
	@Test
	public void testTransakcijaCenaPoveza0() throws Exception {
		System.out.println("KreirajKnjiga - transakcija - cena poveza 0");
		OpstiDomenskiObjekat obj = new Knjiga(Format.A4, 42, Povez.MEK, 5, 0, "TEST KNJIGA", "AUTOMATSKI TEST");
		KreirajKnjiga so = new KreirajKnjiga();
		try {
			so.transakcija(obj);
			fail("Ocekivani izuzetak nije bacen");
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
	@Test
	public void testTransakcijaNedostajuciNaziv() throws Exception {
		System.out.println("KreirajKnjiga - transakcija - nedostajuci naziv");
		OpstiDomenskiObjekat obj = new Knjiga(Format.A4, 42, Povez.MEK, 5, 100, null, "AUTOMATSKI TEST");
		KreirajKnjiga so = new KreirajKnjiga();
		try {
			so.transakcija(obj);
			fail("Ocekivani izuzetak nije bacen");
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
	@Test
	public void testTransakcijaNedostajuciAutor() throws Exception {
		System.out.println("KreirajKnjiga - transakcija - nedostajuci autor");
		OpstiDomenskiObjekat obj = new Knjiga(Format.A4, 42, Povez.MEK, 5, 100, "TEST KNJIGA", null);
		KreirajKnjiga so = new KreirajKnjiga();
		try {
			so.transakcija(obj);
			fail("Ocekivani izuzetak nije bacen");
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
	@Test
	public void testTransakcijaKreirajKnjiga() throws Exception {
		System.out.println("KreirajKnjiga - transakcija");
		OpstiDomenskiObjekat obj = new Knjiga(Format.A4, 42, Povez.MEK, 5, 100, "TEST KNJIGA", "AUTOMATSKI TEST");
		KreirajKnjiga so = new KreirajKnjiga();
		try {
			so.izvrsiTransakciju(obj);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Neocekivani izuzetak bacen");
		}
	}

}
