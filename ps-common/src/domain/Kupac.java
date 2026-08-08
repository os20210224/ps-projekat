package domain;

public class Kupac extends OpstiDomenskiObjekat {
    
    protected long idKupac;
    protected String telefon;
    protected String email;
	
	public Kupac() {
    }

    public Kupac(long idKupac, String telefon, String email) {
        this.idKupac = idKupac;
        this.telefon = telefon;
        this.email = email;
    }
	
	public Kupac(String telefon, String email) {
        this.telefon = telefon;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getIdKupac() {
        return idKupac;
    }

    public void setIdKupac(long idKupac) {
        this.idKupac = idKupac;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

	@Override
	public String getTableName() {
		return "Kupac";
	}

	@Override
	public String getColumns() {
		return "(telefon,email)";
	}

	@Override
	public String getValues() {
		return
			"VALUES("	+ "'" +	telefon	+ "'"	+
			","			+ "'" +	email	+ "'"	+ ")" ;
	}

	@Override
	public String getCondition() {
		String condition = "WHERE 1=1 ";
		if (idKupac != 0) {
			condition += "AND idKupac=" + idKupac + " ";
		}
		if (telefon != null) {
			condition += "AND telefon LIKE '%" + telefon + "%' ";
		}
		if (email != null) {
			condition += "AND email LIKE '%" + email + "%' ";
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
			"telefon='"	 		+ telefon 	+ "'," 	+
			"email='" 			+ email 	+ "'"	;
	}
    
}
