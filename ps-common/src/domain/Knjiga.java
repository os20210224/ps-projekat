package domain;

import domain.enums.Format;
import domain.enums.Povez;

public class Knjiga extends OpstiDomenskiObjekat {
    
    private long idKnjiga;
    private Format format;
    private int brStranica;
    private Povez povez;
    private double cenaStranica;
    private double cenaPoveza;
    private double cena;
    private String naziv;
    private String autor;

    public Knjiga(long idKnjiga, Format format, int brStranica, Povez povez, double cenaStranica, double cenaPoveza, double cena, String naziv, String autor) {
        this.idKnjiga = idKnjiga;
        this.format = format;
        this.brStranica = brStranica;
        this.povez = povez;
        this.cenaStranica = cenaStranica;
        this.cenaPoveza = cenaPoveza;
        this.cena = cena;
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
    
}
