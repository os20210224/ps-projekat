package domain;

public class Kupac extends OpstiDomenskiObjekat {
    
    private long idKupac;
    private String telefon;
    private String email;

    public Kupac(long idKupac, String telefon, String email) {
        this.idKupac = idKupac;
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
    
}
