import elem.Mezo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

/**
 * ez az olytáj a pályát reprezentálja
 */
public class Palya {
    Mezo[][] mezok;
    int korszam;
    int szelesseg;
    int magassag;
    int jatkosSzam =-1;
    ArrayList<String> players= new ArrayList<>();
    ArrayList<String> jatekmenet= new ArrayList<>();
    Random r = new Random();


    public Mezo[][] getMezok() {
        return mezok;
    }


    public int getSzelesseg() {
        return szelesseg;
    }


    public int getMagassag() {
        return magassag;
    }


    public Palya(int gepszam) {
        this.szelesseg= gepszam*3;
        this.magassag = gepszam*2;
        this.korszam=0;
        mezok= new Mezo[this.magassag+1][this.szelesseg+1];
        jatkosSzam=gepszam+1;
        mezoInit();
    }

    /**
     * ez a metódus a pálya kirajzolásáért felel
     * @return a kiralyzólt pályát adja vissza
     */
    public String kirajzol(){
        String s1= "";
        System.out.println("Kör: "+korszam);
        for (int i = 0; i < magassag; i++) {
            for (int j = 0; j < szelesseg; j++) {
                s1+="id: "+mezok[i][j].getId()+" Tulaj: "+mezok[i][j].getTulaj()+" kockak szama: "+mezok[i][j].getKocka()+"\n";
            }
        }
        s1+="------------------------------------------------------------------------";
        System.out.println(s1);
        System.out.println();
        String s= "";

        for (int i = 0; i < magassag; i++) {
            for (int j = 0; j < szelesseg; j++) {
                if (mezok[i][j].getId() <10) {
                    s+="|    "+mezok[i][j].getId()+"      |";
                }else{
                    s+="|    "+mezok[i][j].getId()+"     |";
                }

            }
            s+="\n";
        }
        String adatok  = ""+jatkosSzam+"\nkör: "+korszam+"\n"+s1+"\n"+s;
        korszam++;
        jatekmenet.add(adatok);
        return s;

    }

    /**
     * ez a metódus inicializálja a mezőket és olytja ki véletlen szerűen a játékosok között
     */
    private void mezoInit(){
        feltoltSeged();
        int sz =0;
        for (int i = 0; i < magassag; i++) {
            for (int j = 0; j < szelesseg; j++) {
                mezok[i][j]= new Mezo(randomTulaj(),sz);
                sz++;
            }
        }
        kockaKioszt();
    }

    /**
     * a mezoInit metódust teszi átlethatóvá a tényleges tulyjkiosztás egyenletességéért ez a metódus felel
     * @return tulajdonos neve
     */
    private String randomTulaj(){
        String s="";
        Random r = new Random();
        int rnd = r.nextInt(players.size());
        while (true){
            if (players.get(rnd) != null) {
               s = players.get(rnd);
                players.remove(rnd);
                return s;
            }else{
                rnd = r.nextInt(players.size())+1;
            }
        }
    }

    /**
     * kiosztást segító függvény ami egy segéd tömböt inicializál
     */
    private void feltoltSeged(){
        int osszesMezo = szelesseg*magassag;
        int eloszlas=osszesMezo/jatkosSzam;
        String seged[]={"Te","gép1","gép2"};
        for (int i = 0; i < eloszlas; i++) {
            for (int j = 0; j < jatkosSzam; j++) {
                players.add(seged[j]);
            }
        }
    }

    /**
     * ez a metódus elsökörös kockák egyenletes kiosztásáért felel
     */
    public void kockaKioszt(){

        int osszesMezo = szelesseg*magassag;
        int eloszlas=osszesMezo/jatkosSzam;

        if (jatkosSzam == 3) {
            int[] s={eloszlas*2,eloszlas*2,eloszlas*2};
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
            int[] s={eloszlas*2,eloszlas*2};
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
     * játékmenet kimentéséért felel
     * @throws FileNotFoundException fájl nemtalálás miatti hibát kap el
     */
    public void mentes() throws FileNotFoundException {
        File f = new File("Mentes.txt");
        PrintWriter ki = new PrintWriter(f);
        for (int i = 0; i < jatekmenet.size(); i++) {
            ki.println(jatekmenet.get(i));
        }
        ki.close();
        System.out.println("metés sikeres volt");
        System.exit(1);
    }

}
