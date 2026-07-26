package logika.so.smena_zaposlenog;

import domain.OpstiDomenskiObjekat;
import domain.Smena;
import domain.SmenaZaposlenog;
import domain.Zaposleni;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import logika.db.dbBroker;
import logika.kontroler.Kontroler;
import logika.so.OpstaSO;

public class vratiListuSmenaZaposlenog extends OpstaSO<List> {

	@Override
	protected void preduslov(OpstiDomenskiObjekat obj) throws Exception {
		if (obj == null || !(obj instanceof SmenaZaposlenog)) {
			throw new Exception("Prosledjeni objekat nije SmenaZaposlenog");
		}
	}

	@Override
	protected List transakcija(OpstiDomenskiObjekat obj) throws Exception {
		ResultSet rs = dbBroker.select((SmenaZaposlenog)obj);
		List<SmenaZaposlenog> list = new ArrayList<>();
		while (rs.next()) {
			long idZaposleni = rs.getLong("idZaposleni");
			long idSmena = rs.getLong("idSmena");
			
			Zaposleni zaposleni = Kontroler.vratiListuZaposleni(new Zaposleni(idZaposleni)).get(0);
			Smena smena = Kontroler.vratiListuSmena(new Smena(idSmena)).get(0);
			
			list.add(new SmenaZaposlenog(
				zaposleni,
				smena,
				rs.getDate("datum").toLocalDate()
			));
		}
		return list;
	}
	
}
