package oope2017ht;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import oope2017ht.tiedot.*;
import apulaiset.In;

public class Tulkki {
    //Attribuutit
    
    //Vakiot
    //Komennon indeksi taulukossa
    /** Komennon indeksi taulukossa */
    private final int KOMENTO = 0;
    //Komennon parametrinen m��r�n indeksi taulukossa
    /** Komennon parametrien m��r�n indeksi taulukossa */
    private final int PARAMETRIMAX = 1;
    //Virheilmoitus
    /** Virheilmoitus */
    private final String VIRHEILMOITUS = "Error!";
    //Komennot
    /** Exit komento */
    private final String EXIT = "exit";
    /** Md komento */
    private final String MD = "md";
    /** Cd komento */
    private final String CD = "cd";
    /** Mf komento */
    private final String MF = "mf";
    /** Mv komento */
    private final String MV = "mv";
    /** Ls komento */
    private final String LS = "ls";
    /** Cp komento */
    private final String CP = "cp";
    /** Rm komento */
    private final String RM = "rm";
    /** Find komento */
    private final String FIND = "find";
    
    //Luokka-attribuutit
    //Juurihakemisto
    /** Juurihakemisto */
    private Hakemisto juuri;
    //Nykyinen k�sitelt�v� hakemisto
    /** Nykyinen k�sitelt�v� hakemisto */
    private Hakemisto nykyinen;
    //Nykyisen sijainnin tulostettava polku
    /** Nykyisen sijainnin tulostettava polku */
    private String polku = "/";
    
    //K�sitelt�v�t komennot ja komentojen parametrien m��r�t
    /** K�sitelt�v�t komennot ja komentojen parametrien m��r�t */
    private String[][] komennot = {
        { EXIT, "0" },
        { MD, "1" },
        { CD, "1" },
        { MF, "2" },
        { MV, "2" },
        { LS, "1" },
        { CP, "2" },
        { RM, "1" },
        { FIND, "1" }
    };
    
    /**
     * Pyyt�� k�ytt�j�lt� sy�tteen ja suorittaa sy�tteen perusteella komennon
     * 
     * @return Oli komennon suoritus onnistunut
     */
    public boolean tarkistaKomento() {
        //Tulostetaan sijainnin polku
        System.out.print(polku + ">");
        //Pyydet��n k�ytt�j�lt� sy�te
        String komento = In.readString();
        String[] syote = komento.split(" ");
        //Lippumuuttuja, jolla tarkistetaan sy�tteen oikeellisuus
        boolean oikein = false;
        //K�yd��n kaikki komennot l�pi
        for (int i = 0; i < komennot.length; i++) {
            //Jos k�ytt�j�n sy�te on komento
            if (syote[0].equals(komennot[i][KOMENTO])) {
                //Jos k�ytt�j�n sy�tt�m�n komennon parametrit ovat oikeat
                if (syote.length <= (Integer.parseInt(komennot[i][PARAMETRIMAX]) + 1)) {
                    try {
                        //Find komennon aiheuttama poikkeustilanne
                        if (syote[0].equals(FIND)) {
                            if (syote.length > 1) {
                                System.out.println(VIRHEILMOITUS);
                                return false;
                            }
                            String aloitus = "aloitus" + polku;
                            find(nykyinen, aloitus);
                            oikein = true;
                        //Jos sy�tettiin komento, jolla on parametreja
                        } else if (syote.length > 1) {
                            //Etsit��n t�st� luokasta metodi, joka vastaa sy�tetty� komentoa
                            Method tapahtuma = 
                                    this
                                    .getClass()
                                    .getDeclaredMethod(komennot[i][KOMENTO], String[].class);
                            //Pilkotaan sy�tteest� parametrit taulukoksi
                            String[] parametrit = Arrays.copyOfRange(syote, 1, syote.length);
                            //Ajetaan sy�tteen perusteella nimetty metodi pilkotuilla parametreilla
                            tapahtuma.invoke(this, (Object) parametrit);
                            //Vaihdetaan lippumuuttuja
                            oikein = true;
                        //Jos sy�tettiin komento, jolla ei ole parametreja
                        } else {
                            //Cd komennon aiheuttama poikkeus
                            if (komennot[i][KOMENTO].equals(CD)) {
                                Method tapahtuma = this
                                        .getClass()
                                        .getDeclaredMethod(komennot[i][KOMENTO], String[].class);
                                tapahtuma.invoke(this, (Object) new String[]{ });
                            //Ls komennon aiheuttama poikkeus
                            } else if (komennot[i][KOMENTO].equals(LS)) {
                                Method tapahtuma = this
                                        .getClass()
                                        .getDeclaredMethod(komennot[i][KOMENTO], String[].class);
                                tapahtuma.invoke(this, (Object) new String[]{ });
                            //Jos sy�tettyjen parametrien m��r� ei vastaa komennon maksimiparametria
                            } else if (Integer.parseInt(komennot[i][PARAMETRIMAX]) + 1 != syote.length) {
                                    //Tulostetaan virheilmoitus
                                    System.out.println(VIRHEILMOITUS);
                                    return false;
                            //Jos sy�tteell� ei ole parametreja
                            } else {
                                //Ajetaan sy�tetty komento
                                Method tapahtuma = this
                                        .getClass()
                                        .getDeclaredMethod(komennot[i][KOMENTO]);
                                tapahtuma.invoke(this);
                            }
                            oikein = true;
                        }
                    } catch(Exception e) {
                        System.err.println(e.getCause().toString());
                    }
                }
            }
        }
        if (oikein == false)
            System.out.println(VIRHEILMOITUS);
        return false;
    }
    
    /**
     * Sulkee ohjelman suorituksen
     */
    private void exit() {
        //Suljetaan sovelluksen suoritus
        System.out.println("Shell terminated.");
        System.exit(0);
    }
    
    
    /**
     * Luo uuden hakemiston
     * @param parametri Taulukko, jonka ensimm�isen� soluna on lis�tt�v�n kansion nimi
     */
    private void md(String[] parametri) {
        try {
            //Lis�t��n nykyiseen sijaintiin uusi hakemisto
            nykyinen.lisaa(new Hakemisto(new StringBuilder(parametri[0]), nykyinen));
        } catch (Exception e) {
            System.out.println(VIRHEILMOITUS);
        }
    }
    
    /**
     * Vaihtaa nykyisen hakemiston
     * @param parametri Taulukko, jossa ensimm�isen� soluna on parametri
     */
    private void cd(String[] parametri) {
        //Jos ei annettu parametria
        if (parametri.length == 0) {
            //Vaihdetaan nykyinen sijainti juureksi
            nykyinen = juuri;
            polku = "/";
        //Jos sy�te on ..
        } else if (parametri[0].equals("..")) {
            //Annetaan virhe jos ylihakemistoa ei ole
            if (nykyinen.ylihakemisto() == null) {
                System.out.println(VIRHEILMOITUS);
                return;
            //Muutoin palautetaan ylihakemisto
            } else {
                nykyinen = nykyinen.ylihakemisto();
                String[] paikat = polku.split("/");
                paikat[paikat.length - 1] = "";
                polku = String.join("/", paikat);
            }
        //Jos sy�te on nimi, etsit��n nimi ja vaihdetaan sijanti
        } else if (nykyinen.hae(parametri[0]) instanceof Hakemisto) {
            nykyinen = (Hakemisto) nykyinen.hae(parametri[0]);
            polku += nykyinen.toString().split(" ")[0];
        //Tai tulostetaan virheilmoitus
        } else {
            System.out.println(VIRHEILMOITUS);
        }
    }
    
    /**
     * Luo tiedoston
     * @param parametri Taulukko, jossa parametrit
     */
    private void mf(String[] parametri) {
        try {
            //Lis�t��n nykyiseen sijaintiin uusi tiedosto parametrien perusteella
            nykyinen.lisaa(new Tiedosto(new StringBuilder(parametri[0]), Integer.parseInt(parametri[1])));
        } catch (Exception e) {
            System.out.println(VIRHEILMOITUS);
        }
    }
    
    /**
     * Tiedoston uudelleennime�minen
     * @param parametri Taulukko, jossa parametrit
     */
    private void mv(String[] parametri) {
        if (nykyinen.hae(parametri[0]) == null
            || nykyinen.hae(parametri[1]) != null) {
            System.out.println(VIRHEILMOITUS);
            return;
        }
        //Etsit��n parametrin perusteella tiedosto, ja vaihdetaan sen nimi
        nykyinen.hae(parametri[0]).nimi(new StringBuilder(parametri[1]));
        //Vaihdetaan aakkosj�rjestykseen
        nykyinen.aakkostaSisalto();
    }
    
    
    /**
     * Tiedoston tai hakemiston tiedostojen listaus
     * @param parametri Taulukko, jossa parametrit
     */
    private void ls(String[] parametri) {
        if (parametri.length > 0)
            //Tulostetaan parametrin perusteella tiedoston tiedot
            if (nykyinen.hae(parametri[0]) == null)
                System.out.println(VIRHEILMOITUS);
            else
                System.out.println(nykyinen.hae(parametri[0]));
        //Jos ei ole parametreja, 
        else {
            //K�yd��n kaikki tiedostot nykysijananista l�pi ja tulostetaan
            for (int i = 0; nykyinen.sisalto().alkio(i) != null; i++) {
                System.out.println(nykyinen.sisalto().alkio(i));
            }
        }
    }
    
    /**
     * Kopioi tiedoston
     * @param parametri Taulukko, jossa parametrit
     */
    private void cp(String[] parametri) {
        if (nykyinen.hae(parametri[0]) instanceof Hakemisto
            || nykyinen.hae(parametri[0]) == null) {
            System.out.println(VIRHEILMOITUS);
            return;
        }
        //Haetaan parametrin perusteella kopioitava tiedosto
        Tiedosto kopioitava = ((Tiedosto) nykyinen.hae(parametri[0]));
        //Luodaan kopiorakentajalla kopio tiedostosta
        Tiedosto kopio = new Tiedosto(kopioitava);
        //Vaihdetaan kopiolle uusi nimi
        kopio.nimi(new StringBuilder(parametri[1]));
        if (nykyinen.hae(parametri[1]) != null)
            System.out.println(VIRHEILMOITUS);
        else
            nykyinen.lisaa(kopio);
    }
    
    /**
     * Poistaa tiedoston
     * @param parametri Taulukko, jossa parametrit
     */
    private void rm(String[] parametri) {
        //Poistetaan tieto 
        Tieto poistettu = nykyinen.poista(parametri[0]);
        if (poistettu == null)
            System.out.println(VIRHEILMOITUS);
    }
    
    /**
     * Etsii kaikki tiedostot alkaen k�yt�v�st�
     * @param kaytava Hakemisto
     * @param polku Sijainti
     */
    private void find(Hakemisto kaytava, String polku) {
        if (!polku.split("/")[0].equals("aloitus")) {
            System.out.println(polku + " " + kaytava.toString().split(" ")[1]);
        } else {
            polku = polku.replace("aloitus", "");
        }
        //K�yd��n l�pi tiedostot rekursiivisesti
        for (int i = 0; kaytava.sisalto().alkio(i) != null; i++) {
            if (kaytava.sisalto().alkio(i) instanceof Hakemisto) {
                find((Hakemisto) kaytava
                        .sisalto()
                        .alkio(i), polku + kaytava
                        .sisalto()
                        .alkio(i)
                        .toString()
                        .split(" ")[0]);
            } else {
                System.out.println(polku + kaytava.sisalto().alkio(i));
            }   
        }
    }
    
    //Rakentaja
    public Tulkki() {
       juuri = new Hakemisto(new StringBuilder("JUURIHAKEMISTO"), null);
       nykyinen = juuri;
    }

}
