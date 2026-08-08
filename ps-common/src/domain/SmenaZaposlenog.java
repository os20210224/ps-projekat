package domain;

import java.time.LocalDate;

public class SmenaZaposlenog extends OpstiDomenskiObjekat {
    
    private Zaposleni zaposleni;
    private Smena smena;
    private LocalDate datum;

    public SmenaZaposlenog(Zaposleni zaposleni, Smena smena, LocalDate datum) {
        this.zaposleni = zaposleni;
        this.smena = smena;
        this.datum = datum;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public Smena getSmena() {
        return smena;
    }

    public void setSmena(Smena smena) {
        this.smena = smena;
    }

	@Override
	public String getTableName() {
		return "SmenaZaposlenog";
	}

	@Override
	public String getColumns() {
		return "(idSmena,idZaposleni,datum)";
	}

	@Override
	public String getValues() {
		return
			"VALUES("			+  smena.getIdSmena()			+
			","					+  zaposleni.getIdZaposleni()	+
			","			+ "'"	+ datum							+ "')";
	}

	@Override
	public String getCondition() {
		String condition = "WHERE 1=1 ";
		if (smena != null) {
			condition += "AND idSmena=" + smena.getIdSmena() + " ";
		}
		if (zaposleni != null) {
			condition += "AND idZaposleni=" + zaposleni.getIdZaposleni() + " ";
		}
		if (datum != null) {
			condition += "AND datum='" + datum + "' ";
		}
		return condition;
	}

	@Override
	public String getIDCondition() {
		return "idSmena=" + smena.getIdSmena() + " AND idZaposleni=" + zaposleni.getIdZaposleni();
	}

	@Override
	public String getUpdate() {
		return
			"datum=" + "'" + datum + "'" ;
	}
    
}
