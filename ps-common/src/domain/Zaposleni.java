package domain;

public class Zaposleni extends OpstiDomenskiObjekat {
    
    private long idZaposleni = 0;
    private String ime = null;
    private String prezime = null;
    private String username = null;
    private String password = null;

    public Zaposleni(long idZaposleni, String ime, String prezime, String username, String password) {
        this.idZaposleni = idZaposleni;
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
    }
	
	public Zaposleni(String ime, String prezime, String username, String password) {
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
    }
	
	public Zaposleni(String username, String password) {
        this.username = username;
        this.password = password;
	}
	
	public Zaposleni() {}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getIdZaposleni() {
        return idZaposleni;
    }

    public void setIdZaposleni(long idZaposleni) {
        this.idZaposleni = idZaposleni;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

	@Override
	public String getTableName() {
		return "Zaposleni";
	}

	@Override
	public String getColumns() {
		return "(ime,prezime,username,password)";
	}

	@Override
	public String getValues() {
		return
			"VALUES("	+ "'" + ime		+ "'" +
			","			+ "'" + prezime	+ "'" +
			","			+ "'" + username+ "'" +
			","			+ "'" + password+ "'" + ")";
	}

	@Override
	public String getCondition() {
		String condition = "1=1 ";
		if (idZaposleni != 0) {
			condition += "AND idZaposleni=" + idZaposleni + " ";
		}
		if (ime != null) {
			condition += "AND ime='" + ime + "' ";
		}
		if (prezime != null) {
			condition += "AND prezime='" + prezime + "' ";
		}
		if (username != null) {
			condition += "AND username='" + username + "' ";
		}
		if (password != null) {
			condition += "AND password='" + password + "' ";
		}
		return condition;
	}

	@Override
	public String getIDCondition() {
		return "idZaposleni=" + idZaposleni;
	}

	@Override
	public String getUpdate() {
		return
			"ime="		+ "'" + ime		 + "'," +
			"prezime="	+ "'" + prezime	 + "'," +
			"username="	+ "'" + username + "'," +
			"password="	+ "'" + password + "'"  ;
	}
    
}
