package domain;

public class PravnoLice extends Kupac {
    
    private String naziv;
    private String adresa;

    public PravnoLice(String naziv, String adresa, long idKupac, String telefon, String email) {
        super(idKupac, telefon, email);
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
    
}
