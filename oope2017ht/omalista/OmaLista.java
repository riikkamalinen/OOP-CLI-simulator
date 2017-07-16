package oope2017ht.omalista;

import fi.uta.csjola.oope.lista.*;
import apulaiset.Ooperoiva;

public class OmaLista extends LinkitettyLista implements Ooperoiva {
    
    //Rakentaja käyttäen oletusrakentajaa
    public OmaLista() {
        super();
    }
    
    //Käydään läpi solmuja kunnes löydetään haettava ja palautetaan solmu
    //Jos ei löydy palautetaan null
    public Object hae(Object haettava) {
        for (int i = 0; i < koko(); i++) {
            if (alkio(i).equals(haettava)) {
                return alkio(i);
            }
        }
        return null;
    }
    
    //Käydään läpi solmuja kunnes löytyy aakkosjärjesteksessä oikea paikka
    public boolean lisaa(Object uusi) {
        Solmu lapikaytava = paa();
        int vertailu;
        int paikka = 0;
        //Jos null
        if (uusi == null) 
            return false;
        if (lapikaytava == null) {
            lisaaAlkuun(uusi);
            return true;
        }
        while (lapikaytava != null) {
            vertailu = ((Comparable) lapikaytava.alkio()).compareTo(uusi);
            if (vertailu > 0) {
                lisaa(paikka, uusi);
                return true;
            }
            if (vertailu == 0)
                return false;
            lapikaytava = lapikaytava.seuraava();
            paikka++;
        } 
        lisaaLoppuun(uusi);
        return true;
    }
    
    /**
     * Laittaa listan aakkosjärjestykseen
     * 
     * @return Onnistuiko
     */
    public boolean aakkosta() {
        int paikka = 0;
        while (paikka < koko()) {
            if (paikka == 0 || ((Comparable) alkio(paikka)).compareTo(alkio(paikka - 1)) > 0)
                paikka++;
            else {
                Object vaihtaja = alkio(paikka);
                korvaa(paikka, alkio(paikka - 1));
                korvaa(paikka - 1, vaihtaja);
                paikka--;
            }
        }
        return true;
    }
    
    
    //Käydään läpi kunnes löydetään poistettava ja poistetaan
    public Object poista(Object poistettava) {
        Solmu lapikaytava = paa();
        Solmu edellinen;
        int paikka = 0;
        if (poistettava == null || lapikaytava == null)
            return null;
        while (lapikaytava != null) {
            if (lapikaytava.alkio().equals(poistettava)) {
                return poista(paikka);
            }
            edellinen = lapikaytava;
            lapikaytava = lapikaytava.seuraava();
            paikka++;
        }
        return null;
    }
    
}
