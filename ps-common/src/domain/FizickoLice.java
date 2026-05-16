package domain;

public class FizickoLice extends Kupac {
    
    private String ime;
    private String prezime;

    public FizickoLice(String ime, String prezime, long idKupac, String telefon, String email) {
        super(idKupac, telefon, email);
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
    
}
