package domain;

public class StavkaRacuna extends OpstiDomenskiObjekat {
    
	private long idRacun;
    private long rb;
    private int kolicina;
    private double cena;
    private double iznos;
    private Knjiga knjiga;

    public StavkaRacuna(long idRacun, long rb, int kolicina, double cena, double iznos, Knjiga knjiga) {
		this.idRacun = idRacun;
        this.rb = rb;
        this.kolicina = kolicina;
        this.cena = cena;
        this.iznos = iznos;
        this.knjiga = knjiga;
    }
	
	public StavkaRacuna(long idRacun, long rb, double cena, int kolicina, Knjiga knjiga) {
		this.idRacun = idRacun;
        this.rb = rb;
        this.kolicina = kolicina;
        this.cena = cena;
        this.iznos = cena * kolicina;
        this.knjiga = knjiga;
    }
	
	public StavkaRacuna(long rb, int kolicina, double cena, double iznos, Knjiga knjiga) {
        this.rb = rb;
        this.kolicina = kolicina;
        this.cena = cena;
        this.iznos = iznos;
        this.knjiga = knjiga;
    }
	
	public StavkaRacuna(long idRacun) {
		this.idRacun = idRacun;
    }

    public Knjiga getKnjiga() {
        return knjiga;
    }

    public void setKnjiga(Knjiga knjiga) {
        this.knjiga = knjiga;
    }

    public long getRb() {
        return rb;
    }

    public void setRb(long rb) {
        this.rb = rb;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }
	
	public long getIdRacun() {
		return idRacun;
	}

	public void setIdRacun(long idRacun) {
		this.idRacun = idRacun;
	}

	@Override
	public String getTableName() {
		return "StavkaRacuna";
	}

	@Override
	public String getColumns() {
		return "(idRacun,rb,kolicina,cena,idKnjiga)";
	}

	@Override
	public String getValues() {
		return 
			"VALUES("	+ idRacun				+ 
			","			+ rb					+
			","			+ kolicina				+
			","			+ cena					+ 
			","			+ knjiga.getIdKnjiga()	+ ")" ;
	}

	@Override
	public String getCondition() {
		String condition = "WHERE 1=1 ";
		if (idRacun != 0) {
			condition += "AND idRacun=" + idRacun + " ";
		}
		if (rb != 0) {
			condition += "AND rb=" + rb + " ";
		}
		if (kolicina != 0) {
			condition += "AND kolicina=" + kolicina + " ";
		}
		if (cena != 0) {
			condition += "AND cena=" + cena + " ";
		}
		if (iznos != 0) {
			condition += "AND iznos=" + iznos + " ";
		}
		if (knjiga != null) {
			condition += "AND idKnjiga=" + knjiga.getIdKnjiga() + " ";
		}
		return condition;
	}

	@Override
	public String getIDCondition() {
		return "idRacun=" + idRacun + " AND rb=" + rb;
	}

	@Override
	public String getUpdate() {
		return 
			"rb="	 		+ rb					+ "," 	+
			"kolicina=" 	+ kolicina				+ ","	+
			"cena=" 		+ cena					+ ","	+
			"idKnjiga=" 	+ knjiga.getIdKnjiga() 	+ ","	;
	}
	
}
