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
    
    //Lista tiedoista, mit� hakemisto sis�lt��
    /** Hakemiston sis�ll�n sis�lt�v� lista */
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
    
    //Muutetaan sis�lt� aakkosj�rjestykseen
    /**
     *  Laittaa hakemiston sis�ll�n aakkosj�rjestykseen
     */
    public void aakkostaSisalto() {
        sisalto.aakkosta();
    }
    
    //Haku sis�lt�listalta
    public Tieto hae(String nimi) {
        return (Tieto) sisalto.hae(nimi);
    }
    
    //Lis�ys sis�lt�listalle
    public boolean lisaa(Tieto lisattava) {
        return sisalto.lisaa(lisattava);
    }
    
    //Poisto sis�lt�listalta
    public Tieto poista(String nimi) {
        return (Tieto) sisalto.poista(nimi);
    }
    
    //toString
    public String toString() {
        return super.toString() + "/ " + sisalto.koko();
    }
}
