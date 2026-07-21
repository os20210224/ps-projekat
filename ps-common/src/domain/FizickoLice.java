package domain;

public class FizickoLice extends Kupac {
    
    private String ime = null;
    private String prezime = null;

	public FizickoLice() {
		super(0, null, null);
	}
	
	public FizickoLice(long idKupac) {
		super(idKupac, null, null);
	}

    public FizickoLice(String ime, String prezime, long idKupac, String telefon, String email) {
        super(idKupac, telefon, email);
        this.ime = ime;
        this.prezime = prezime;
    }
	
	public FizickoLice(String ime, String prezime, String telefon, String email) {
        super(telefon, email);
        this.ime = ime;
        this.prezime = prezime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }
	
	@Override
	public String getTableName() {
		return "FizickoLice";
	}

	@Override
	public String getColumns() {
		return "(idKupac,ime,prezime)";
	}

	@Override
	public String getValues() {
		return "VALUES("+idKupac+","+"'"+ime+"',"+"'"+prezime+"')";
	}

	@Override
	public String getCondition() {
		String condition = "JOIN Kupac ON Kupac.idKupac=FizickoLice.idKupac WHERE 1=1 ";
		if (idKupac != 0) {
			condition += "AND Kupac.idKupac=" + idKupac + " ";
		}
		if (telefon != null) {
			condition += "AND telefon LIKE '%" + telefon + "%' ";
		}
		if (email != null) {
			condition += "AND email LIKE '%" + email + "%' ";
		}
		if (ime != null) {
			condition += "AND ime LIKE '%" + ime + "%' ";
		}
		if (prezime != null) {
			condition += "AND prezime LIKE '%" + prezime + "%' ";
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
			"ime='"	 		+ ime		+ "'," 	+
			"prezime='" 	+ prezime 	+ "'"	;
	}
    
}
