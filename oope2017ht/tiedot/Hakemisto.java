package oope2017ht.tiedot;

import fi.uta.csjola.oope.lista.LinkitettyLista;
import oope2017ht.omalista.OmaLista;
import apulaiset.Komennettava;


/**
 * Hakemisto luokka, joka periytyy Tieto luokasta
 * 
 * @author Riikka Malinen
 *
 */
public class Hakemisto extends Tieto implements Komennettava<Tieto> {
    //Attribuutit
    
    //Lista tiedoista, mitä hakemisto sisältää
    /** Hakemiston sisällön sisältävä lista */
    private OmaLista sisalto; 
    //Hakemiston ylihakemisto
    /** Hakemiston ylihakemisto */
    private Hakemisto ylihakemisto;
    
    //Rakentaja
    //Suoritetaan yliluokan rakentaja ja alustetaan luokka-attribuutit
    /**
     * Parametrillinen rakentaja
     * 
     * @param nimi Hakemiston nimi
     * @param ylihakemisto Ylihakemisto
     * @throws IllegalArgumentException
     */
    public Hakemisto(StringBuilder nimi, Hakemisto ylihakemisto) throws IllegalArgumentException {
        super(nimi);
        ylihakemisto(ylihakemisto);
        sisalto = new OmaLista();
    }
    
    //Aksessorit
    public LinkitettyLista sisalto() {
        return sisalto;
    }
    
    public void ylihakemisto(Hakemisto ylihakemisto) {
        this.ylihakemisto = ylihakemisto;
    } 
    
    public Hakemisto ylihakemisto() {
        return ylihakemisto;
    }
    
    //Muutetaan sisältö aakkosjärjestykseen
    /**
     *  Laittaa hakemiston sisällön aakkosjärjestykseen
     */
    public void aakkostaSisalto() {
        sisalto.aakkosta();
    }
    
    //Haku sisältölistalta
    public Tieto hae(String nimi) {
        return (Tieto) sisalto.hae(nimi);
    }
    
    //Lisäys sisältölistalle
    public boolean lisaa(Tieto lisattava) {
        return sisalto.lisaa(lisattava);
    }
    
    //Poisto sisältölistalta
    public Tieto poista(String nimi) {
        return (Tieto) sisalto.poista(nimi);
    }
    
    //toString
    public String toString() {
        return super.toString() + "/ " + sisalto.koko();
    }
}
