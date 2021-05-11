import jatekos.Elenfel;
import jatekos.Jatekos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Main {

    public static Palya jatekTer;

    /**
     * Az első almenüt tartalmazza a metódus az "uj" játék kezdése kor
     * @throws FileNotFoundException fájl nemtalálás miatti hibát kap el
     */
    public static void jatekKezdes() throws FileNotFoundException {
        int gepek=0;
        System.out.println("Az uj játékkezdését választottad kérlek add meg mennyi  géppel szeretnél játszani [1-2]");
        Scanner be2 = new Scanner(System.in);
        System.out.println("gépek száma: ");
        gepek = be2.nextInt();

        if ((gepek) > 2 || gepek < 1 ) {
            System.err.println("A gépek szám meghalatta a 2-et vagy kisebb volt mint 1!");
            jatekKezdes();
        }else{

            if (gepek == 1) {
                jatekTer = new Palya(gepek);
                Jatekos j = new Jatekos(jatekTer.getSzelesseg(), jatekTer.getMagassag(), jatekTer.getMezok(),"Te");
                Elenfel e1 = new Elenfel(jatekTer.getSzelesseg(), jatekTer.getMagassag(), jatekTer.getMezok(),"gép1");

                System.out.println(jatekTer.kirajzol());
                while (true){
                    jatek(j, e1);
                }
            }else{
                jatekTer = new Palya(gepek);
                Jatekos j = new Jatekos(jatekTer.getSzelesseg(), jatekTer.getMagassag(), jatekTer.getMezok(),"Te");
                Elenfel e1 = new Elenfel(jatekTer.getSzelesseg(), jatekTer.getMagassag(), jatekTer.getMezok(),"gép1");
                Elenfel e2 = new Elenfel(jatekTer.getSzelesseg(), jatekTer.getMagassag(), jatekTer.getMezok(),"gép2");

                System.out.println(jatekTer.kirajzol());
                while (true){
                    jatek(j, e1, e2);
                }
            }

        }

        be2.close();
    }

    /**
     * a főmenöt tartalmazza a metódus
     * @throws FileNotFoundException fájl nemtalálás miatti hibát kap el
     */
    public static void indit() throws FileNotFoundException {
        System.out.println("Üdvözöllek a Dice Wars játékban. Kérlek válassz, hogy új játékot szeretnél kezdeni korábbit szeretnél betölteni vagy egy korábbit vissza játszani ezeke közül a következő parancs valamelyikét gépeld be majd nyomj egy entert");
        System.out.println("új jaték - uj");
        System.out.println("játék vissza játszása - visszajatszás");
        Scanner be = new Scanner(System.in);
        String tipus = be.next();
        if (tipus.equals("uj")) {
            jatekKezdes();
        }else if (tipus.equals("visszajatszás")) {
            visszajatszik();
        }else {
            System.err.println("Nem a fentjelzettek közül választottál!");
            indit();
        }
        be.close();
    }

    /**
     * A mentett játék visszanézéséért felel
     */
    private static void visszajatszik() {
        File f = new File("ment");
        try {
            Scanner be = new Scanner(f);
            for (int i = 0; be.hasNextLine(); i++) {
                System.out.println(be.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.err.println("nincs mentett játékod");
        }
    }

    /**
     * Magát a játék menetet reprezentálja a metódus 1 gép esetén
     * @param j játékos
     * @param e1 ellenség
     * @throws FileNotFoundException fájl nemtalálás miatti hibát kap el
     */
    public static void jatek(Jatekos j, Elenfel e1) throws FileNotFoundException {
        j.folytat();
        System.out.println("Kérlek gépeld be, hogy mit szeretnél csinálni");
        System.out.println("Támad - tamad");
        System.out.println("Atad - atad");
        System.out.println("Mentés - ment");
        Scanner be = new Scanner(System.in);
        String folyamat = be.next();
        if (folyamat.equals("tamad")) {
            j.tamadoValaszt();
            System.out.println(jatekTer.kirajzol());
        }else if(folyamat.equals("atad")){
          e1.tamadoValaszt();
            System.out.println(jatekTer.kirajzol());
        }else if(folyamat.equals("ment")){
            jatekTer.mentes();
        }

    }
    /**
     * Magát a játék menetet reprezentálja a metódus 2 gép esetén
     * @param j játékos
     * @param e1 ellenség
     * @throws FileNotFoundException fájl nemtalálás miatti hibát kap el
     */
    public static void jatek(Jatekos j, Elenfel e1, Elenfel e2) throws FileNotFoundException {
        j.folytat();
        System.out.println("Kérlek gépeld be, hogy mit szeretnél csinálni");
        System.out.println("Támad - tamad");
        System.out.println("Atad - atad");
        System.out.println("Mentés - ment");
        Scanner be = new Scanner(System.in);
        String folyamat = be.next();
        if (folyamat.equals("tamad")) {
            j.tamadoValaszt();
            System.out.println(jatekTer.kirajzol());
        }else if(folyamat.equals("atad")){
            e1.tamadoValaszt();
            e2.tamadoValaszt();
            System.out.println(jatekTer.kirajzol());
        }else if(folyamat.equals("ment")){
            jatekTer.mentes();
        }

    }

    public static void main(String[] args) throws FileNotFoundException {
        indit();
    }
}
