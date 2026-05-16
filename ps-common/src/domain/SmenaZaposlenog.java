package domain;

import java.time.LocalDate;

public class SmenaZaposlenog extends OpstiDomenskiObjekat {
    
    private Zaposleni zaposleni;
    private Smena smena;
    private LocalDate datum;

    public SmenaZaposlenog(Zaposleni zaposleni, Smena smena, LocalDate datum) {
        this.zaposleni = zaposleni;
        this.smena = smena;
        this.datum = datum;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public Smena getSmena() {
        return smena;
    }

    public void setSmena(Smena smena) {
        this.smena = smena;
    }
    
}
