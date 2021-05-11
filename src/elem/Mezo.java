package elem;

/**
 * @author Kocsis Mártom <br>
 *     Ez az osztály a mező típust definiálja
 */
public class Mezo {
    int kocka;
    String tulaj;
    int id;

    public Mezo(String tulaj, int id) {
        this.kocka = 1;
        this.tulaj = tulaj;
        this.id = id;
    }

    /**
     * ez a metódus egy kockával növeli a jelenleg a mezőn lévő kockák számát
     */
    public void kockaAdd(){
        kocka++;
    }

    public int getKocka() {
        return kocka;
    }

    public String getTulaj() {
        return tulaj;
    }

    public int getId() {
        return id;
    }

    /**
     *
     * @param elvesztettKocka elvesztett kockák számát várja paraméterben
     *  ez a metódus a harc vesztése vagy nyerése kor fog nekünk kelleni
     */
    public void kockanyer(int elvesztettKocka) {
        if (getKocka()+elvesztettKocka >=8) {
            kocka=8;
        }else{
            kocka+=elvesztettKocka;
        }
    }

    public void setTulaj(String tulaj) {
        this.tulaj=tulaj;
    }
    /**
     *
     * @param elvesztettKocka elvesztett kockák számát várja paraméterben
     *  ez a metódus a harc vesztése vagy nyerése kor fog nekünk kelleni
     */
    public void kockaelveszt(int elvesztettKocka) {
        kocka-=elvesztettKocka;
    }
}
