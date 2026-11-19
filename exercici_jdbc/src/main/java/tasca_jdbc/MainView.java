package tasca_jdbc;

import java.util.List;
import java.util.Scanner;

public class MainView {

    /**
     * @return
     */
    public int mainMenu() {
        // TODO Auto-generated method stub
        System.out.println("1. Llistat usuaris\n2 Afegir Usuari\n....\n...\n0 Sortir --> conn.close()");
        System.out.println("(1) Llistar Atletas");
        System.out.println("(2) Afegir Atleta");
        System.out.println("(3) Llistar Esports");
        System.out.println("(4) Afegir Esport");
        System.out.println("(5) Filtrar Atleta per Esport");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        return option;
        //throw new UnsupportedOperationException("Unimplemented method 'mainMenu'");
    }

    public void llistaAtletes(List<Atleta> list) {
        // Llistat atletas
        System.out.println("Llistat d'atletas: ");
        for (Atleta atleta : list) {
            System.out.println(atleta.name + "\t" + atleta.esport);
        }
    }

    public int codEsport() {
        Scanner llegir = new Scanner(System.in);
        System.out.println("Introdueix l'ID de l'esport per filtrar: ");
        int codi = llegir.nextInt();
        return codi;
    }

    public void llistaAtletesChosen(List<Atleta> list) {
        // Llistat atletas
        System.out.println("Llistat d'atletas per Esport: ");
        for (Atleta atleta : list) {
            System.out.println(atleta.name + "\t" + atleta.esport);
        }
    }

    public void llistaEsport(List<Esport> list) {
        // Llistat esports
        System.out.println("Llistat d'esports: ");
        for (Esport esport : list) {
            System.out.println(esport.esport + "\t" + esport.id);
        }
    }

    public Atleta atletaForm(){
        // Formulari atletres,
        System.out.println("Afegir nou Atleta: ");
        Scanner llegir = new Scanner(System.in);
        System.out.println("Nom Atleta: ");
        String atleta = llegir.nextLine();
        System.out.println("Esport que practica(ID): ");
        String esport = llegir.nextLine();

        return new Atleta(atleta,esport);
    }

    public Esport esportForm(){
        // Formulari atletres,
        System.out.println("Afegir nou Esport: ");
        Scanner llegir = new Scanner(System.in);
        System.out.println("Nom Esport: ");
        String esport = llegir.nextLine();

        return new Esport(esport);
    }


}
