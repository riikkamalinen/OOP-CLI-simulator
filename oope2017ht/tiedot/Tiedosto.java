package oope2017ht.tiedot;

public class Tiedosto extends Tieto {
    /** Tiedoston koko */
    private int koko;
    
    //Rakentaja
    public Tiedosto(StringBuilder nimi, int koko) throws IllegalArgumentException {
        super(nimi);
        koko(koko);
    }
    
    //Kopiorakentaja
    public Tiedosto(Tiedosto kopioitava) {
        super(kopioitava);
        this.koko = kopioitava.koko();
    }
    
    //Aksessorit
    public void koko(int koko) throws IllegalArgumentException {
        if (koko < 0)
            throw new IllegalArgumentException("Tiedoston koon oltava positiivinen");
        this.koko = koko;
    }
    
    public int koko() {
        return koko;
    }
    
    //toString
    public String toString() {
        return super.toString() + " " + koko;
    }
    
    //equals
    public boolean equals(Object verrattava) {
        return super.equals(verrattava);
    }
    
}
