package domain;

import java.time.LocalTime;

public class Smena extends OpstiDomenskiObjekat {
    
    private long idSmena = 0;
    private LocalTime vremePocetka = null;
    private LocalTime vremeKraja = null;
	private String ime = null;

	public Smena(long idSmena) {
        this.idSmena = idSmena;
    }
	
    public Smena(long idSmena, LocalTime vremePocetka, LocalTime vremeKraja, String ime) {
        this.idSmena = idSmena;
        this.vremePocetka = vremePocetka;
        this.vremeKraja = vremeKraja;
		this.ime = ime;
    }

	public Smena() {
	}
	
    public LocalTime getVremeKraja() {
        return vremeKraja;
    }

    public void setVremeKraja(LocalTime vremeKraja) {
        this.vremeKraja = vremeKraja;
    }

    public long getIdSmena() {
        return idSmena;
    }

    public void setIdSmena(long idSmena) {
        this.idSmena = idSmena;
    }

    public LocalTime getVremePocetka() {
        return vremePocetka;
    }

    public void setVremePocetka(LocalTime vremePocetka) {
        this.vremePocetka = vremePocetka;
    }
	
	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	@Override
	public String getTableName() {
		return "Smena";
	}

	@Override
	public String getColumns() {
		return "(vremePocetka,vremeKraja,ime)";
	}

	@Override
	public String getValues() {
		return
			"VALUES("	+ "'" + vremePocetka	+ "'" +
			","			+ "'" + vremeKraja		+ "'" +
			","			+ "'" + ime				+ "')";
	}

	@Override
	public String getCondition() {
		String condition = "WHERE 1=1 ";
		if (idSmena != 0) {
			condition += "AND idSmena=" + idSmena + " ";
		}
		if (vremePocetka != null) {
			condition += "AND vremePocetka='" + vremePocetka + "' ";
		}
		if (vremeKraja != null) {
			condition += "AND vremeKraja='" + vremeKraja + "' ";
		}
		if (ime != null) {
			condition += "AND ime='" + ime + "' ";
		}
		return condition;
	}

	@Override
	public String getIDCondition() {
		return "idSmena=" + idSmena;
	}

	@Override
	public String getUpdate() {
		return
			"ime="			+ "'" + ime			 + "'," +
			"vremePocetka="	+ "'" + vremePocetka + "'," +
			"vremeKraja="	+ "'" + vremeKraja	 + "'" ;
	}

	@Override
	public String toString() {
		return ime;
	}
    
}
