package domain;

import java.time.LocalTime;

public class Smena extends OpstiDomenskiObjekat {
    
    private long idSmena;
    private LocalTime vremePocetka;
    private LocalTime vremeKraja;

    public Smena(long idSmena, LocalTime vremePocetka, LocalTime vremeKraja) {
        this.idSmena = idSmena;
        this.vremePocetka = vremePocetka;
        this.vremeKraja = vremeKraja;
    }

    public LocalTime getVremeKraja() {
        return vremeKraja;
    }

    public void setVremeKraja(LocalTime vremeKraja) {
        this.vremeKraja = vremeKraja;
    }

    public long getIdSmena() {
        return idSmena;
    }

    public void setIdSmena(long idSmena) {
        this.idSmena = idSmena;
    }

    public LocalTime getVremePocetka() {
        return vremePocetka;
    }

    public void setVremePocetka(LocalTime vremePocetka) {
        this.vremePocetka = vremePocetka;
    }
    
}
