package jatekos;
import elem.Mezo;
import java.util.Random;

/**
 * Az ellenfél egy speciális játékost reperezentál <br>
 * @author Kocsis Márton
 */
public class Elenfel extends Jatekos {

    public Elenfel(int szelesseg, int magassag, Mezo[][] mezok, String nev) {
        super(szelesseg, magassag, mezok,nev);
    }

    /**
     * ez a metódus a gép támadását végzi el
     */
    public void tamadoValaszt() {
        kockakiosz();
        System.out.println("gép1 átadta a körét");
    }

    /**
     * ez a metódus a minden kör végén lévő k kockát osztja ki véletlen szerűen (k= Birtokolt_mezők/2)
     */
    public void kockakiosz(){
        Random r = new Random();
        int osszesMezo = szelesseg*magassag;
        int jatkosSzam=0;
        if (osszesMezo == 6) {
            jatkosSzam=2;
        }else{
            jatkosSzam=3;
        }
        int temezo=kiszamolTe()/2;
        int gep1 = kiszamolGep1()/2;
        int gep2= kiszamolGep2()/2;


        if (jatkosSzam == 3) {
            int[] s={temezo,gep1,gep2};
            int rndsz = r.nextInt(szelesseg);
            int rndm = r.nextInt(magassag);

            while (true){
                if (mezok[rndm][rndsz].getTulaj().equals("Te") && mezok[rndm][rndsz].getKocka() < 8 && s[0] !=0) {
                    mezok[rndm][rndsz].kockaAdd();
                    s[0]--;
                    rndsz = r.nextInt(szelesseg);
                    rndm = r.nextInt(magassag);
                }else if (mezok[rndm][rndsz].getTulaj().equals("gép1") && mezok[rndm][rndsz].getKocka() < 8 && s[1] !=0) {
                    mezok[rndm][rndsz].kockaAdd();
                    s[1]--;
                    rndsz = r.nextInt(szelesseg);
                    rndm = r.nextInt(magassag);
                } else if (mezok[rndm][rndsz].getTulaj().equals("gép2") && mezok[rndm][rndsz].getKocka() < 8 && s[2] !=0) {
                    mezok[rndm][rndsz].kockaAdd();
                    s[2]--;
                    rndsz = r.nextInt(szelesseg);
                    rndm = r.nextInt(magassag);
                }else{
                    rndsz = r.nextInt(szelesseg);
                    rndm = r.nextInt(magassag);

                }
                if (s[0] == 0 && s[1] == 0 && s[2] == 0) {
                    break;
                }
            }
        }else{
            int[] s={temezo,gep1};
            int rndsz = r.nextInt(szelesseg);
            int rndm = r.nextInt(magassag);

            while (true){
                if (mezok[rndm][rndsz].getTulaj().equals("Te") && mezok[rndm][rndsz].getKocka() < 8 && s[0] !=0) {
                    mezok[rndm][rndsz].kockaAdd();
                    s[0]--;
                    rndsz = r.nextInt(szelesseg);
                    rndm = r.nextInt(magassag);
                }else if (mezok[rndm][rndsz].getTulaj().equals("gép1") && mezok[rndm][rndsz].getKocka() < 8 && s[1] !=0) {
                    mezok[rndm][rndsz].kockaAdd();
                    s[1]--;
                    rndsz = r.nextInt(szelesseg);
                    rndm = r.nextInt(magassag);
                }else{
                    rndsz = r.nextInt(szelesseg);
                    rndm = r.nextInt(magassag);

                }
                if (s[0] == 0 && s[1] == 0) {
                    break;
                }
            }

        }

    }

    /**
     * a játékos tulajdonábanlévő mezők számát adja vissza
     * @return
     */
    private int kiszamolTe() {
        int szam=0;
        for (int i = 0; i < magassag; i++) {
            for (int j = 0; j < szelesseg; j++) {
                if (mezok[i][j].getTulaj().equals("Te")) {
                    szam++;
                }
            }
        }
        return szam;
    }
    /**
     * a gép1 tulajdonábanlévő mezők számát adja vissza
     * @return
     */
    private int kiszamolGep1() {
        int szam=0;
        for (int i = 0; i < magassag; i++) {
            for (int j = 0; j < szelesseg; j++) {
                if (mezok[i][j].getTulaj().equals("gép1")) {
                    szam++;
                }
            }
        }
        return szam;
    }
    /**
     * a gép2 tulajdonábanlévő mezők számát adja vissza
     * @return
     */
    private int kiszamolGep2() {
        int szam=0;
        for (int i = 0; i < magassag; i++) {
            for (int j = 0; j < szelesseg; j++) {
                if (mezok[i][j].getTulaj().equals("gép2")) {
                    szam++;
                }
            }
        }
        return szam;
    }

}
