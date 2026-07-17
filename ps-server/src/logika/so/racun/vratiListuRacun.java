package logika.so.racun;

import domain.Kupac;
import domain.OpstiDomenskiObjekat;
import domain.Racun;
import domain.StavkaRacuna;
import domain.Zaposleni;
import domain.enums.MetodPlacanja;
import java.util.List;
import logika.db.dbBroker;
import logika.so.OpstaSO;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import logika.kontroler.Kontroler;

public class vratiListuRacun extends OpstaSO<List> {

	@Override
	protected void preduslov(OpstiDomenskiObjekat obj) throws Exception {
		if (obj == null || !(obj instanceof Racun)) {
			throw new Exception("Prosledjeni objekat nije Racun");
		}
	}

	@Override
	protected List transakcija(OpstiDomenskiObjekat obj) throws Exception {
		ResultSet rs = dbBroker.select((Racun)obj);
		List<Racun> list = new ArrayList<>();
		while (rs.next()) {
			long idKupac = rs.getLong("idKupac");
			Kupac kupac = Kontroler.vratiListuKupac(new Kupac(idKupac, null, null)).get(0);
			
			long idZaposleni = rs.getLong("idZaposleni");
			Zaposleni zaposleni = Kontroler.vratiListuZaposleni(new Zaposleni(idZaposleni, null, null, null, null)).get(0);
			
			long idRacun = rs.getLong("idRacun");
			List<StavkaRacuna> stavke = Kontroler.vratiListuStavkaRacuna(new StavkaRacuna(idRacun));
			
			list.add(new Racun(
				idRacun,
				rs.getDate("datum").toLocalDate(),
				MetodPlacanja.valueOf(rs.getString("metodPlacanja").toUpperCase()),
				rs.getDouble("ukupanIznos"),
				zaposleni,
				kupac,
				stavke
			));
		}
		return list;
	}
	
}
