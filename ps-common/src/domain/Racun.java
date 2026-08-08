package domain;

import domain.enums.MetodPlacanja;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Racun extends OpstiDomenskiObjekat {
    
    private long idRacun;
    private LocalDateTime datum;
    private MetodPlacanja metodPlacanja;
    private double ukupanIznos;
    private Zaposleni zaposleni;
    private Kupac kupac;
	private List<StavkaRacuna> stavkeRacuna = new ArrayList<>();

	public Racun() {
	}
	
	public Racun(MetodPlacanja metodPlacanja, Zaposleni zaposleni, Kupac kupac) {
		this.metodPlacanja = metodPlacanja;
		this.zaposleni = zaposleni;
		this.kupac = kupac;
		datum = LocalDateTime.now();
	}
	
	public Racun(LocalDateTime datum, MetodPlacanja metodPlacanja, double ukupanIznos, Zaposleni zaposleni, Kupac kupac, List<StavkaRacuna> stavkeRacuna) {
		this.datum = datum;
		this.metodPlacanja = metodPlacanja;
		this.ukupanIznos = ukupanIznos;
		this.zaposleni = zaposleni;
		this.kupac = kupac;
		this.stavkeRacuna = stavkeRacuna;
	}
	
	public Racun(long idRacun, LocalDateTime datum, MetodPlacanja metodPlacanja, double ukupanIznos, Zaposleni zaposleni, Kupac kupac, List<StavkaRacuna> stavkeRacuna) {
		this.idRacun = idRacun;
		this.datum = datum;
		this.metodPlacanja = metodPlacanja;
		this.ukupanIznos = ukupanIznos;
		this.zaposleni = zaposleni;
		this.kupac = kupac;
		this.stavkeRacuna = stavkeRacuna;
	}

	public long getIdRacun() {
		return idRacun;
	}

	public void setIdRacun(long idRacun) {
		this.idRacun = idRacun;
	}

	public LocalDateTime getDatum() {
		return datum;
	}

	public void setDatum(LocalDateTime datum) {
		this.datum = datum;
	}

	public MetodPlacanja getMetodPlacanja() {
		return metodPlacanja;
	}

	public void setMetodPlacanja(MetodPlacanja metodPlacanja) {
		this.metodPlacanja = metodPlacanja;
	}

	public double getUkupanIznos() {
		return ukupanIznos;
	}

	public void setUkupanIznos(double ukupanIznos) {
		this.ukupanIznos = ukupanIznos;
	}

	public Zaposleni getZaposleni() {
		return zaposleni;
	}

	public void setZaposleni(Zaposleni zaposleni) {
		this.zaposleni = zaposleni;
	}

	public Kupac getKupac() {
		return kupac;
	}

	public void setKupac(Kupac kupac) {
		this.kupac = kupac;
	}

	public List<StavkaRacuna> getStavkeRacuna() {
		return stavkeRacuna;
	}

	public void setStavkeRacuna(List<StavkaRacuna> stavkeRacuna) {
		this.stavkeRacuna = stavkeRacuna;
	}

	@Override
	public String getTableName() {
		return "Racun";
	}

	@Override
	public String getColumns() {
		return "(datum,metodPlacanja,idZaposleni,idKupac)";
	}

	@Override
	public String getValues() {
		return 
			"VALUES("	+ "'" + datum.toString()			+ "'" + 
			","			+ "'" +	metodPlacanja.toString()	+ "'" +
			","			+		zaposleni.getIdZaposleni()		  +
			","			+		kupac.getIdKupac()				  + ")" ;
	}

	@Override
	public String getCondition() {
		String condition = "WHERE 1=1 ";
		if (idRacun != 0) {
			condition += "AND idRacun=" + idRacun + " ";
		}
		if (datum != null) {
			condition += "AND datum='" + datum + "' ";
		}
		if (metodPlacanja != null) {
			condition += "AND metodPlacanja='" + metodPlacanja.toString() + "' ";
		}
		if (ukupanIznos != 0) {
			condition += "AND ukupanIznos=" + ukupanIznos + " ";
		}
		if (zaposleni != null) {
			condition += "AND idZaposleni=" + zaposleni.getIdZaposleni() + " ";
		}
		if (kupac != null) {
			condition += "AND idKupac=" + kupac.getIdKupac()+ " ";
		}
		return condition;
	}

	@Override
	public String getIDCondition() {
		return "idRacun=" + idRacun;
	}

	@Override
	public String getUpdate() {
		return 
			"datum='"			+ datum						+ "',"	+ 
			"metodPlacanja='"	+ metodPlacanja.toString() 	+ "',"	+
			"idZaposleni="		+ zaposleni.getIdZaposleni()+ ","	+
			"idKupac="			+ kupac.getIdKupac()				;
	}
	
	public void addStavka(StavkaRacuna stavka) {
		if (stavka == null) {
			return;
		}
		ukupanIznos += stavka.getIznos();
		stavka.setRb((long) stavkeRacuna.size() + 1);
		for (StavkaRacuna s : stavkeRacuna) {
			Knjiga k = s.getKnjiga();
			if (k.toString().equals(stavka.getKnjiga().toString())) {
				s.setKolicina(s.getKolicina() + stavka.getKolicina());
				s.setIznos(s.getIznos() + stavka.getIznos());
				return;
			}
		}
		stavkeRacuna.add(stavka);
	}
	
	public void removeStavka(StavkaRacuna stavka) {
		if (stavka == null) {
			return;
		}
		int delIndex = -1;
		for (int i = 0; i < stavkeRacuna.size(); i++) {
			if (stavkeRacuna.get(i).getRb() == stavka.getRb()) {
				delIndex = i;
				break;
			}
		}
		if (delIndex == -1) {
			return;
		}
		stavkeRacuna.remove(delIndex);
		for (int i = delIndex; i < stavkeRacuna.size(); i++) {
			stavkeRacuna.get(i).setRb(stavkeRacuna.get(i).getRb() - 1);
		}
	}
	
	public void updateStavka(StavkaRacuna stavka) {
		if (stavka == null) {
			return;
		}
		for (int i = 0; i < stavkeRacuna.size(); i++) {
			if (stavkeRacuna.get(i).getRb() == stavka.getRb()) {
				ukupanIznos -= stavkeRacuna.get(i).getIznos();
				ukupanIznos += stavka.getIznos();
				stavkeRacuna.set(i, stavka);
			}
		}
	}
	
}
