package logika.so.knjiga;

import domain.Knjiga;
import domain.OpstiDomenskiObjekat;
import domain.Zaposleni;
import domain.enums.Format;
import domain.enums.Povez;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.OrderWith;
import org.junit.runner.manipulation.Alphanumeric;

@OrderWith(Alphanumeric.class)
public class KreirajKnjigaTest {

	/**
	 * Test of preduslov method, of class KreirajKnjiga.
	 */
	@Test
	public void testAPreduslovNull() throws Exception {
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
	public void testBPreduslovNeKnjiga() throws Exception {
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
	public void testCTransakcijaNedostajuciFormat() throws Exception {
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
	public void testDTransakcijaBrStrana0() throws Exception {
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
	public void testETransakcijaNedostajuciPovez() throws Exception {
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
	public void testFTransakcijaCenaStrana0() throws Exception {
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
	public void testGTransakcijaCenaPoveza0() throws Exception {
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
	public void testHTransakcijaNedostajuciNaziv() throws Exception {
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
	public void testITransakcijaNedostajuciAutor() throws Exception {
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
	public void testJTransakcijaKreirajKnjiga() throws Exception {
		System.out.println("KreirajKnjiga - transakcija");
		OpstiDomenskiObjekat obj = new Knjiga(Format.A4, 42, Povez.MEK, 5, 100, "TEST KNJIGA", "AUTOMATSKI TEST");
		KreirajKnjiga so = new KreirajKnjiga();
		try {
			so.izvrsiTransakciju(obj);
			(new ObrisiKnjiga()).izvrsiTransakciju(
				(OpstiDomenskiObjekat)(new vratiListuKnjiga()).izvrsiTransakciju(
					new Knjiga(Format.A4, 42, Povez.MEK, 5, 100, "TEST KNJIGA", "AUTOMATSKI TEST")
				).get(0)
			);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Neocekivani izuzetak bacen");
		}
	}

}
