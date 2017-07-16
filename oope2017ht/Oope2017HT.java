package oope2017ht;

public class Oope2017HT {
    //Attribuutti
    //Käyttöliittymä
    /**
     * Käyttöliittymäolio
     */
    private static Kayttoliittyma kl;
    
    //Pääohjelma
    //Käynnistetään käyttöliittymä
    public static void main(String[] args) {
        kl = new Kayttoliittyma();
        kl.aloita();
    }

}
