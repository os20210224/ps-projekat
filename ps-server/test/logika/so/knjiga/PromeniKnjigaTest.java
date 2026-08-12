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
public class PromeniKnjigaTest {
	
	/**
	 * Test of preduslov method, of class PromeniKnjiga.
	 */
	@Test
	public void testAPreduslovNull() throws Exception {
		System.out.println("PromeniKnjiga - preduslov - null");
		PromeniKnjiga so = new PromeniKnjiga();
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
		System.out.println("PromeniKnjiga - preduslov - ne knjiga");
		PromeniKnjiga so = new PromeniKnjiga();
		try {
			so.preduslov(new Zaposleni());
			fail("Ocekivani izuzetak nije bacen");
		} catch (Exception e) {
			assertNotNull(e);
		}
	}

	/**
	 * Test of transakcija method, of class PromeniKnjiga.
	 */
	@Test
	public void testCTransakcija() throws Exception {
		System.out.println("PromeniKnjiga - transakcija");
		(new KreirajKnjiga()).izvrsiTransakciju(new Knjiga(Format.A4, 42, Povez.MEK, 5, 100, "TEST KNJIGA", "AUTOMATSKI TEST"));
		OpstiDomenskiObjekat obj = (OpstiDomenskiObjekat) (new vratiListuKnjiga()).izvrsiTransakciju(
			new Knjiga(Format.A4, 42, Povez.MEK, 5, 100, "TEST KNJIGA", "AUTOMATSKI TEST")
		).get(0);
		PromeniKnjiga so = new PromeniKnjiga();
		Knjiga k = (Knjiga) obj;
		k.setFormat(Format.A5);
		k.setNaziv("TEST KNJIGA DVA");
		try {
			so.izvrsiTransakciju(k);
			(new ObrisiKnjiga()).izvrsiTransakciju(
				(OpstiDomenskiObjekat)(new vratiListuKnjiga()).izvrsiTransakciju(
					new Knjiga(Format.A5, 42, Povez.MEK, 5, 100, "TEST KNJIGA DVA", "AUTOMATSKI TEST")
				).get(0)
			);
		} catch (Exception e) {
			fail("Neocekivani izuzetak bacen");
		}
	}
	
}
