package oope2017ht;

import apulaiset.In;

public class Kayttoliittyma {
    //Attribuutti
    //Suoritettava tulkki
    /** Suoritettava tulkki */
    private Tulkki tulkki;
    
    //Rakentaja
    //Tulostetaan otsikkoteksti
    //K�ynnistet���n tulkin suoritus
    public Kayttoliittyma() {
        tulkki = new Tulkki();
        
    }
    
    /**
     * Aloittaa k�ytt�liittym�n
     */
    public void aloita() {
        System.out.println("Welcome to SOS.");
        while (1 == 1) {
            tulkki.tarkistaKomento();
        }
    }

}
