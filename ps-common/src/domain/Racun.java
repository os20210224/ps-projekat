package domain;

import domain.enums.MetodPlacanja;
import java.time.LocalDate;

public class Racun extends OpstiDomenskiObjekat {
    
    private long idRacun;
    private LocalDate datum;
    private MetodPlacanja metodPlacanja;
    private double ukupanIznos;
    private Zaposleni zaposleni;
    private Kupac kupac;

    public Racun(long idRacun, LocalDate datum, MetodPlacanja metodPlacanja, double ukupanIznos, Zaposleni zaposleni, Kupac kupac) {
        this.idRacun = idRacun;
        this.datum = datum;
        this.metodPlacanja = metodPlacanja;
        this.ukupanIznos = ukupanIznos;
        this.zaposleni = zaposleni;
        this.kupac = kupac;
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    public long getIdRacun() {
        return idRacun;
    }

    public void setIdRacun(long idRacun) {
        this.idRacun = idRacun;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public MetodPlacanja getMetodPlacanja() {
        return metodPlacanja;
    }

    public void setMetodPlacanja(MetodPlacanja metodPlacanja) {
        this.metodPlacanja = metodPlacanja;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }
    
}
