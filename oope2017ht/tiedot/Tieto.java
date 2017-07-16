package oope2017ht.tiedot;

public abstract class Tieto implements Comparable<Tieto> {
    //Attribuutti
    //Tiedon nimi
    /** Tiedon nimi */
    private StringBuilder nimi;
    
    //Rakentaja
    //Alustetaan nimi
    public Tieto(StringBuilder nimi) throws IllegalArgumentException {
        nimi(nimi);
    }
    
    //Kopiorakentaja
    public Tieto(Tieto kopioitava) {
        this.nimi = kopioitava.nimi();
    }
    
    //Aksessorit
    /**
     * Asettaa tiedolle nimen
     * 
     * @param nimi Nimi
     * @throws IllegalArgumentException
     */
    public void nimi(StringBuilder nimi) throws IllegalArgumentException {
        int pisteMaara = 0;
        for (int i = 0; i < nimi.length(); i++) {
            if (nimi.charAt(i) == ".".charAt(0))
                pisteMaara++;
        }
        if (!(nimi.toString().matches("\\.?\\w+\\_*\\.?\\w*\\_*")) || pisteMaara > 1) {
            throw new IllegalArgumentException("Error!");
        }
        this.nimi = nimi;
    }
    
    //Aksessori
    public StringBuilder nimi() {
        return nimi;
    }
    
    //toString
    public String toString() {
        return nimi.toString();
    }
    
    //equals
    public boolean equals(Object verrattava) {
        if (verrattava == null)
            return false;
        if (verrattava instanceof Tieto)
            verrattava = ((Tieto) verrattava).nimi();
        if (verrattava.toString().equals(this.nimi().toString()))
            return true;
        return false;
    }
    
    //compareTo
    public int compareTo(Tieto verrattava) {
        return this.nimi().toString().compareTo(verrattava.nimi().toString());
    }
}
