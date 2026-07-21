package domain;

public class PravnoLice extends Kupac {
    
    private String naziv = null;
    private String adresa = null;

	public PravnoLice() {
		super(0, null, null);
	}
	
	public PravnoLice(long idKupac) {
		super(idKupac, null, null);
	}

    public PravnoLice(String naziv, String adresa, long idKupac, String telefon, String email) {
        super(idKupac, telefon, email);
        this.naziv = naziv;
        this.adresa = adresa;
    }
	
	public PravnoLice(String naziv, String adresa, String telefon, String email) {
        super(telefon, email);
        this.naziv = naziv;
        this.adresa = adresa;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
	
	@Override
	public String getTableName() {
		return "PravnoLice";
	}

	@Override
	public String getColumns() {
		return "(idKupac,naziv,adresa)";
	}

	@Override
	public String getValues() {
		return "VALUES("+idKupac+","+"'"+naziv+"',"+"'"+adresa+"')";
	}

	@Override
	public String getCondition() {
		String condition = "JOIN Kupac ON Kupac.idKupac=PravnoLice.idKupac WHERE 1=1 ";
		if (idKupac != 0) {
			condition += "AND Kupac.idKupac=" + idKupac + " ";
		}
		if (telefon != null) {
			condition += "AND telefon LIKE '%" + telefon + "%' ";
		}
		if (email != null) {
			condition += "AND email LIKE '%" + email + "%' ";
		}
		if (naziv != null) {
			condition += "AND naziv LIKE '%" + naziv + "%' ";
		}
		if (adresa != null) {
			condition += "AND adresa LIKE '%" + adresa + "%' ";
		}
		return condition;
	}

	@Override
	public String getIDCondition() {
		return "idKupac=" + idKupac;
	}

	@Override
	public String getUpdate() {
		return 
			"naziv='"	 		+ naziv 	+ "'," 	+
			"adresa='" 			+ adresa 	+ "'"	;
	}
    
}
