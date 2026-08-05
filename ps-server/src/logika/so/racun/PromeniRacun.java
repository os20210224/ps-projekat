package logika.so.racun;

import domain.OpstiDomenskiObjekat;
import domain.Racun;
import domain.StavkaRacuna;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import logika.db.dbBroker;
import logika.so.OpstaSO;

public class PromeniRacun extends OpstaSO<Void> {

	@Override
	protected void preduslov(OpstiDomenskiObjekat obj) throws Exception {
		if (obj == null || !(obj instanceof Racun)) {
			throw new Exception("Prosledjeni objekat nije Racun");
		}
	}

	@Override
	protected Void transakcija(OpstiDomenskiObjekat obj) throws Exception {
		Racun racun = (Racun) obj;
		List<StavkaRacuna> stavke = new ArrayList<>();
		ResultSet srrs = dbBroker.read(new StavkaRacuna(racun.getIdRacun()));
		while (srrs.next()) {
			stavke.add(new StavkaRacuna(srrs.getLong("idRacun"), srrs.getLong("rb"), 0, 0, null));
		}
		for (StavkaRacuna s : stavke) {
			dbBroker.delete(s);
		}
		for (StavkaRacuna s : racun.getStavkeRacuna()) {
			dbBroker.create(s);
		}
		return dbBroker.update(racun);
	}
	
}
