package logika.so.stavka_racuna;

import domain.Knjiga;
import domain.OpstiDomenskiObjekat;
import domain.StavkaRacuna;
import java.util.List;
import logika.so.OpstaSO;
import java.sql.ResultSet;
import java.util.ArrayList;
import logika.db.dbBroker;
import logika.kontroler.Kontroler;

public class vratiListuStavkaRacuna extends OpstaSO<List> {

	@Override
	protected void preduslov(OpstiDomenskiObjekat obj) throws Exception {
		if (obj == null || !(obj instanceof StavkaRacuna)) {
			throw new Exception("Prosledjeni objekat nije StavkaRacuna");
		}
	}

	@Override
	protected List transakcija(OpstiDomenskiObjekat obj) throws Exception {
		ResultSet rs = dbBroker.select((StavkaRacuna)obj);
		List<StavkaRacuna> list = new ArrayList<>();
		while (rs.next()) {
			long idKnjiga = rs.getLong("idKnjiga");
			
			Knjiga knjiga = Kontroler.vratiListuKnjiga(new Knjiga(idKnjiga)).get(0);
			
			list.add(new StavkaRacuna(
				rs.getLong("rb"),
				rs.getInt("kolicina"),
				rs.getDouble("cena"),
				rs.getDouble("iznos"),
				knjiga
			));
		}
		return list;
	}
	
}
