package logika.db.so.knjiga;

import domain.Knjiga;
import domain.OpstiDomenskiObjekat;
import domain.enums.Format;
import domain.enums.Povez;
import java.util.List;
import logika.db.dbBroker;
import logika.db.so.OpstaSO;
import java.sql.ResultSet;
import java.util.ArrayList;

public class vratiListuKnjiga extends OpstaSO<List> {

	@Override
	protected void preduslov(OpstiDomenskiObjekat obj) throws Exception {
		if (obj == null || !(obj instanceof Knjiga)) {
			throw new Exception("Prosledjeni objekat nije Knjiga");
		}
	}

	@Override
	protected List<Knjiga> transakcija(OpstiDomenskiObjekat obj) throws Exception {
		ResultSet rs = dbBroker.select((Knjiga)obj);
		List<Knjiga> list = new ArrayList<Knjiga>();
		while (rs.next()) {
			list.add(new Knjiga(
				rs.getLong(1),
				Format.valueOf(rs.getString(2).toUpperCase()),
				rs.getInt(3),
				Povez.valueOf(rs.getString(4).toUpperCase()),
				rs.getDouble(5),
				rs.getDouble(6),
				rs.getString(7),
				rs.getString(8),
				rs.getDouble(9)
			));
		}
		return list;
	}
	
}
