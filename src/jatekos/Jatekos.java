package jatekos;
import elem.Mezo;
import java.util.Random;
import java.util.Scanner;

/**
 * ez az osztáj egy játékost reperezentál
 */
public class Jatekos {
    int mezoszam;
    Mezo mezok[][];
    String nev;
    int magassag;
    int szelesseg;
    int indito_i;
    int indito_j;
    int cel_i;
    int cel_j;

    public Jatekos(int szelesseg, int magassag, Mezo mezok[][], String nev) {
       this.nev=nev;
       this.mezok = new Mezo[magassag][szelesseg];
        for (int i = 0; i < magassag; i++) {
            for (int j = 0; j < szelesseg; j++) {
                this.mezok[i][j] = mezok[i][j];
                if(mezok[i][j].getTulaj().equals(this.nev)){
                    mezoszam++;
                }
            }
        }
        this.magassag=magassag;
        this.szelesseg=szelesseg;
    }

    /**
     * ez a metódus felel azért hogy kivalasszuk a mezőt amivel szeretnénk támadni
     */
    public void tamadoValaszt() {
        System.out.println("Melyik mezöddel Támadnál? Kérlek írd be az ID-jét");
        Scanner be1 = new Scanner(System.in);
        int tamadId= be1.nextInt();

        if (tamadId > magassag*szelesseg) {
            System.err.println("Túl nagy id-t Írtál be!");
            tamadoValaszt();
        }
        for (int i = 0; i < magassag; i++) {
            for (int j = 0; j < szelesseg; j++) {
                if (mezok[i][j].getId()==tamadId) {
                    if (mezok[i][j].getTulaj().equals("Te")) {
                        if (mezok[i][j].getKocka() !=1) {
                            indito_i=i;
                            indito_j=j;
                            celtValaszt();
                        }else{
                            System.err.println("a támadó mezőnek legalább 2 kockával rendelkeznie kell");
                            tamadoValaszt();
                        }


                    }else{
                        System.err.println("Ez a mező nem a tied!");
                        tamadoValaszt();
                    }
                }
            }
        }
    }

    /**
     * ez a metódus felel azért, hogy kivalasszuk melyik mezőt szeretnénk megtámadni
     */
    public void celtValaszt() {

        System.out.println("Melyik mezőt Támadnál meg? Kérlek írd be az ID-jét.");
        Scanner be2= new Scanner(System.in);
        int celID = be2.nextInt();

        if (celID > magassag*szelesseg) {
            System.err.println("Túl nagy id-t Írtál be!");
            celtValaszt();

        }
        for (int i = 0; i < magassag; i++) {
            for (int j = 0; j < szelesseg; j++) {
                if (mezok[i][j].getId()==celID) {
                    if (!mezok[i][j].getTulaj().equals("Te")) {
                        cel_i= i;
                        cel_j= j;
                        //mezok[indito_i][indito_j].getId()
                        //mezok[cel_i][cel_j].getId()
                        //mezok[cel_i][cel_j].getId()==mezok[indito_i][indito_j].getId()+1
                        //mezok[cel_i][cel_j].getId()==mezok[indito_i][indito_j].getId()-1
                        //mezok[cel_i][cel_j].getId()==mezok[indito_i][indito_j].getId()-mezoszam
                        //mezok[cel_i][cel_j].getId()==mezok[indito_i][indito_j].getId()-mezoszam
                        //mezok[cel_i][cel_j].getId()==mezoszam-mezok[indito_i][indito_j].getId()
                        if (mezok[cel_i][cel_j].getId()==mezok[indito_i][indito_j].getId()+1 || mezok[cel_i][cel_j].getId()==mezok[indito_i][indito_j].getId()-1 ||
                        mezok[cel_i][cel_j].getId()==mezok[indito_i][indito_j].getId()-mezoszam || mezok[cel_i][cel_j].getId()==mezoszam-mezok[indito_i][indito_j].getId() ||
                                mezok[cel_i][cel_j].getId()==mezoszam+mezok[indito_i][indito_j].getId()) {
                            harc(indito_i,indito_j,cel_i,cel_j);
                        }else{
                            System.err.println("Ezt nem tudod megtámadni, mert nem szoszédos azzal amivel indítanád a támadást");
                            celtValaszt();

                        }


                    }else{
                        System.err.println("Ez a mező már a tied!");
                        celtValaszt();

                    }
                }
            }
        }
    }

    /**
     * ez a metódus valósítja meg a harcot <br>
     * @param tamado_i támedó mező x koordinátálya <br>
     * @param tamado_j támadó mező y koordinátája <br>
     * @param cel_i cél mező x koordinátája <br>
     * @param cel_j cél mező y koordinátája
     */
    private void harc(int tamado_i,int tamado_j, int cel_i, int cel_j) {
        int tamado=dob(mezok[tamado_i][tamado_j].getKocka());
        int cel=dob(mezok[cel_i][cel_j].getKocka());
        System.out.println("Te dobásod eredménye: "+tamado);
        System.out.println("cél mező dobása: "+cel);
        if (tamado <= cel) {
            System.out.println("vesztettél!");
            int elvesztettKocka = mezok[tamado_i][tamado_j].getKocka()-1;
            mezok[cel_i][cel_j].kockanyer(elvesztettKocka);
            mezok[tamado_i][tamado_j].kockaelveszt(elvesztettKocka);
            mezok[tamado_i][tamado_j].setTulaj(mezok[cel_i][cel_j].getTulaj());
            folytat();
        }else{
            System.out.println("nyertél!");
            int elvesztettKocka = mezok[cel_i][cel_j].getKocka()-1;
            mezok[tamado_i][tamado_j].kockanyer(elvesztettKocka);
            mezok[cel_i][cel_j].kockaelveszt(elvesztettKocka);
            mezok[cel_i][cel_j].setTulaj(mezok[tamado_i][tamado_j].getTulaj());
            nyertele();
            folytat();

        }

    }

    /**
     * Ez a metódus vizsgálja, hogy vége van e a játéknak vagyis elnyerted e már az összes területet vagy esetleg a gépek elnyerték töled mindet
     */
    private void nyertele() {
        int s =0;
        for (int i = 0; i < magassag; i++) {
            for (int j = 0; j < szelesseg; j++) {
                if (mezok[i][j].getTulaj().equals("Te")) {
                    s++;
                }
            }
            if (s==0) {
                System.out.println("A játéknak vége: Vesztettél!");
                System.exit(1);
            }
            if (s==mezoszam) {
                System.out.println("A játéknak vége: Nyertél!");
                System.exit(1);
            }
        }
    }

    /**
     * ez a metódus szimulálja a dobását a játékosoknak <br>
     * @param kockaszam ez a praméter hogy az adott mezőn mennyi kocka van.
     * @return a kockákkal való dobást értékét adja vissza
     */
    private int dob(int kockaszam){
        int sum=0;
        Random r = new Random();
        for (int i = 0; i < kockaszam; i++) {
            sum += r.nextInt(6)+1;
        }
        return sum;
    }

    /**
     * ez a metódus a vezérlést irányítja vissza a main fükvénybe a megvolt a harc
     */
    public void folytat() {}
}
