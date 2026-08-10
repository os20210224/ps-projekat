package logika.so.knjiga;

import domain.Knjiga;
import domain.OpstiDomenskiObjekat;
import domain.Zaposleni;
import org.junit.Test;
import static org.junit.Assert.*;

public class ObrisiKnjigaTest {
	
	/**
	 * Test of preduslov method, of class ObrisiKnjiga.
	 */
	@Test
	public void testPreduslovNull() throws Exception {
		System.out.println("ObrisiKnjiga - preduslov - null");
		KreirajKnjiga so = new KreirajKnjiga();
		try {
			so.preduslov(null);
			fail("Ocekivani izuzetak nije bacen");
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
	@Test
	public void testPreduslovNeKnjiga() throws Exception {
		System.out.println("ObrisiKnjiga - preduslov - ne knjiga");
		KreirajKnjiga so = new KreirajKnjiga();
		try {
			so.preduslov(new Zaposleni());
			fail("Ocekivani izuzetak nije bacen");
		} catch (Exception e) {
			assertNotNull(e);
		}
	}

	/**
	 * Test of transakcija method, of class ObrisiKnjiga.
	 */
	@Test
	public void testTransakcijaNepostojecaKnjiga() throws Exception {
		System.out.println("ObrisiKnjiga - transakcija - nepostojeca knjiga");
		OpstiDomenskiObjekat obj = new Knjiga(-1);
		ObrisiKnjiga so = new ObrisiKnjiga();
		try {
			so.transakcija(obj);
			fail("Ocekivani izuzetak nije bacen");
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
	@Test
	public void testTransakcija() throws Exception {
		System.out.println("ObrisiKnjiga - transakcija");
		OpstiDomenskiObjekat obj = (OpstiDomenskiObjekat) (new vratiListuKnjiga()).izvrsiTransakciju(
			new Knjiga(null, 0, null, 0, 0, "TEST KNJIGA", "AUTOMATSKI TEST")
		).get(0);
		ObrisiKnjiga so = new ObrisiKnjiga();
		try {
			so.izvrsiTransakciju(obj);
		} catch (Exception e) {
			fail("Neocekivani izuzetak bacen");
		}
	}
	
}
