package logika.so.knjiga;

import domain.Knjiga;
import domain.OpstiDomenskiObjekat;
import domain.Zaposleni;
import domain.enums.Format;
import domain.enums.Povez;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.OrderWith;
import org.junit.runner.manipulation.Alphanumeric;

@OrderWith(Alphanumeric.class)
public class ObrisiKnjigaTest {
	
	/**
	 * Test of preduslov method, of class ObrisiKnjiga.
	 */
	@Test
	public void testAPreduslovNull() throws Exception {
		System.out.println("ObrisiKnjiga - preduslov - null");
		ObrisiKnjiga so = new ObrisiKnjiga();
		try {
			so.preduslov(null);
			fail("Ocekivani izuzetak nije bacen");
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
	@Test
	public void testBPreduslovNeKnjiga() throws Exception {
		System.out.println("ObrisiKnjiga - preduslov - ne knjiga");
		ObrisiKnjiga so = new ObrisiKnjiga();
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
	public void testCTransakcija() throws Exception {
		System.out.println("ObrisiKnjiga - transakcija");
		(new KreirajKnjiga()).izvrsiTransakciju(new Knjiga(Format.A4, 42, Povez.MEK, 5, 100, "TEST KNJIGA", "AUTOMATSKI TEST"));
		OpstiDomenskiObjekat obj = (OpstiDomenskiObjekat) (new vratiListuKnjiga()).izvrsiTransakciju(
			new Knjiga(Format.A4, 42, Povez.MEK, 5, 100, "TEST KNJIGA", "AUTOMATSKI TEST")
		).get(0);
		ObrisiKnjiga so = new ObrisiKnjiga();
		try {
			so.izvrsiTransakciju(obj);
		} catch (Exception e) {
			fail("Neocekivani izuzetak bacen");
		}
	}
	
}
