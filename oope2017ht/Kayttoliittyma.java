package oope2017ht;

import apulaiset.In;

public class Kayttoliittyma {
    //Attribuutti
    //Suoritettava tulkki
    /** Suoritettava tulkki */
    private Tulkki tulkki;
    
    //Rakentaja
    //Tulostetaan otsikkoteksti
    //Käynnistetäään tulkin suoritus
    public Kayttoliittyma() {
        tulkki = new Tulkki();
        
    }
    
    /**
     * Aloittaa käyttöliittymän
     */
    public void aloita() {
        System.out.println("Welcome to SOS.");
        while (1 == 1) {
            tulkki.tarkistaKomento();
        }
    }

}
