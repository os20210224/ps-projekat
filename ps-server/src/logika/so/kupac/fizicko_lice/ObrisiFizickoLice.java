package logika.so.kupac.fizicko_lice;

import domain.FizickoLice;
import domain.Kupac;
import domain.OpstiDomenskiObjekat;
import logika.db.dbBroker;
import logika.so.OpstaSO;
import logika.so.kupac.ObrisiKupac;

public class ObrisiFizickoLice extends OpstaSO<Void> {

	@Override
	protected void preduslov(OpstiDomenskiObjekat obj) throws Exception {
		if (obj == null || !(obj instanceof FizickoLice)) {
			throw new Exception("Prosledjeni objekat nije FizickoLice");
		}
	}

	@Override
	protected Void transakcija(OpstiDomenskiObjekat obj) throws Exception {
		FizickoLice l = (FizickoLice) obj;
		OpstaSO so = new ObrisiKupac();
		try {
			dbBroker.delete(l);
			dbBroker.commit();
		} catch (Exception e) {
			dbBroker.rollback();
			throw new Exception(e.getMessage());
		}
		return (Void) so.izvrsiTransakciju(new Kupac(l.getIdKupac(), l.getTelefon(), l.getEmail()));
	}
	
}
