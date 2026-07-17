package domain;

import domain.enums.MetodPlacanja;
import java.time.LocalDate;
import java.util.List;

public class Racun extends OpstiDomenskiObjekat {
    
    private long idRacun = 0;
    private LocalDate datum = null;
    private MetodPlacanja metodPlacanja = null;
    private double ukupanIznos = 0;
    private Zaposleni zaposleni = null;
    private Kupac kupac = null;
	private List<StavkaRacuna> stavkeRacuna = null;

	public Racun() {
	}
	
	public Racun(LocalDate datum, MetodPlacanja metodPlacanja, double ukupanIznos, Zaposleni zaposleni, Kupac kupac, List<StavkaRacuna> stavkeRacuna) {
		this.datum = datum;
		this.metodPlacanja = metodPlacanja;
		this.ukupanIznos = ukupanIznos;
		this.zaposleni = zaposleni;
		this.kupac = kupac;
		this.stavkeRacuna = stavkeRacuna;
	}
	
	public Racun(long idRacun, LocalDate datum, MetodPlacanja metodPlacanja, double ukupanIznos, Zaposleni zaposleni, Kupac kupac, List<StavkaRacuna> stavkeRacuna) {
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

	public LocalDate getDatum() {
		return datum;
	}

	public void setDatum(LocalDate datum) {
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
	
}
