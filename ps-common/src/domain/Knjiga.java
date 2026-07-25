package domain;

import domain.enums.Format;
import domain.enums.Povez;

public class Knjiga extends OpstiDomenskiObjekat {
    
    private long idKnjiga = 0;
    private Format format = null;
    private int brStranica = 0;
    private Povez povez = null;
    private double cenaStranica = 0;
    private double cenaPoveza = 0;
    private String naziv = null;
    private String autor = null;
    private double cena = 0;
	
	public Knjiga(Format format, int brStranica, Povez povez, double cenaStranica, double cenaPoveza, String naziv, String autor) {
        this.format = format;
        this.brStranica = brStranica;
        this.povez = povez;
        this.cenaStranica = cenaStranica;
        this.cenaPoveza = cenaPoveza;
        this.naziv = naziv;
        this.autor = autor;
    }

	public Knjiga(long idKnjiga, Format format, int brStranica, Povez povez, double cenaStranica, double cenaPoveza, String naziv, String autor, double cena) {
		this.idKnjiga = idKnjiga;
		this.format = format;
		this.brStranica = brStranica;
		this.povez = povez;
		this.cenaStranica = cenaStranica;
		this.cenaPoveza = cenaPoveza;
		this.naziv = naziv;
		this.autor = autor;
		this.cena = cena;
	}
	
	public Knjiga(long idKnjiga) {
		this.idKnjiga = idKnjiga;
	}

	public Knjiga() {
	}

	public Knjiga(long idKnjiga, Format format, int brStranica, Povez povez, double cenaStranica, double cenaPoveza, String naziv, String autor) {
		this.idKnjiga = idKnjiga;
		this.format = format;
		this.brStranica = brStranica;
		this.povez = povez;
		this.cenaStranica = cenaStranica;
		this.cenaPoveza = cenaPoveza;
		this.naziv = naziv;
		this.autor = autor;
	}

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public long getIdKnjiga() {
        return idKnjiga;
    }

    public void setIdKnjiga(long idKnjiga) {
        this.idKnjiga = idKnjiga;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    public int getBrStranica() {
        return brStranica;
    }

    public void setBrStranica(int brStranica) {
        this.brStranica = brStranica;
    }

    public Povez getPovez() {
        return povez;
    }

    public void setPovez(Povez povez) {
        this.povez = povez;
    }

    public double getCenaStranica() {
        return cenaStranica;
    }

    public void setCenaStranica(double cenaStranica) {
        this.cenaStranica = cenaStranica;
    }

    public double getCenaPoveza() {
        return cenaPoveza;
    }

    public void setCenaPoveza(double cenaPoveza) {
        this.cenaPoveza = cenaPoveza;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

	@Override
	public String getTableName() {
		return "Knjiga";
	}

	@Override
	public String getColumns() {
		return "(format,brStranica,povez,cenaStranica,cenaPoveza,naziv,autor)";
	}

	@Override
	public String getValues() {
		return 
			"VALUES("	+ "'" + format.toString()	+ "'"	+ 
			","			+		brStranica					+ 
			","			+ "'" +	povez.toString()	+ "'"	+ 
			","			+		cenaStranica				+ 
			","			+		cenaPoveza					+
			","			+ "'" +	naziv				+ "'"	+
			","			+ "'" +	autor				+ "'"	+ ")" ;
	}

	@Override
	public String getCondition() {
		String condition = "WHERE 1=1 ";
		if (idKnjiga != 0) {
			condition += "AND idKnjiga=" + idKnjiga + " ";
		}
		if (format != null) {
			condition += "AND format='" + format.toString() + "' ";
		}
		if (brStranica != 0) {
			condition += "AND brStranica=" + brStranica + " ";
		}
		if (povez != null) {
			condition += "AND povez='" + povez.toString() + "' ";
		}
		if (cenaStranica != 0) {
			condition += "AND cenaStranica=" + cenaStranica + " ";
		}
		if (cenaPoveza != 0) {
			condition += "AND cenaPoveza=" + cenaPoveza + " ";
		}
		if (naziv != null) {
			condition += "AND naziv LIKE '%" + naziv + "%' ";
		}
		if (autor != null) {
			condition += "AND autor LIKE '%" + autor + "%' ";
		}
		if (cena != 0) {
			condition += "AND cena=" + cena + " ";
		}
		return condition;
	}

	@Override
	public String getIDCondition() {
		return "idKnjiga=" + idKnjiga;
	}

	@Override
	public String getUpdate() {
		return 
			"format='"	 		+ format.toString() 	+ "'," 	+
			"brStranica=" 		+ brStranica 			+ ","	+
			"povez='" 			+ povez.toString() 		+ "',"	+
			"cenaStranica=" 	+ cenaStranica 			+ ","	+
			"cenaPoveza=" 		+ cenaPoveza 			+ ","	+
			"naziv='" 			+ naziv 				+ "',"	+
			"autor='" 			+ autor 				+ "'"	;
	}

	@Override
	public String toString() {
		return naziv + " " + format + " " + povez;
	}
    
}
