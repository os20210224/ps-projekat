package logika.so.knjiga;

import domain.Knjiga;
import domain.OpstiDomenskiObjekat;
import domain.Zaposleni;
import domain.enums.Format;
import domain.enums.Povez;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.OrderWith;
import org.junit.runner.manipulation.Alphanumeric;

@OrderWith(Alphanumeric.class)
public class vratiListuKnjigaTest {

	/**
	 * Test of preduslov method, of class vratiListuKnjiga.
	 */
	@Test
	public void testAPreduslovNull() throws Exception {
		System.out.println("vratiListuKnjiga - preduslov - null");
		vratiListuKnjiga so = new vratiListuKnjiga();
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
		System.out.println("vratiListuKnjiga - preduslov - ne knjiga");
		vratiListuKnjiga so = new vratiListuKnjiga();
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
	public void testCTransakcija() throws Exception {
		System.out.println("vratiListuKnjiga - transakcija");
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
