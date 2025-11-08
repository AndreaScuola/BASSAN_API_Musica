// MAIN gestirà l'interfaccia utente attraverso un menu interattivo.
// Gli utenti potranno scegliere tra diverse opzioni come visualizzare canzoni,
// cercare artisti specifici, aggiungerne di nuovi o modificare quelli esistenti.


//cd C:\Users\Standard\.jdks\ms-21.0.8\bin
//java -jar C:\Users\Standard\Downloads\SpotifyAPI.jar

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        API api = new API();
        int scelta;

        if(!api.checkHealthApi())
            return;

        do{
            System.out.print("Cosa vuoi fare? \n1) Stampa catalogo completo \t2) Cerca canzone da ID " +
                    "\n 3) Stampa artisti \t 4) Visualizzare dettagli di artisti specifici con possibilità di salvarli nel database" +
                    "\n 5) Aggiungi nuovo artista \t 6) Modificare informazioni di artisti esistenti" +
                    "\n 7) Elimina artista da ID \t 8) Consultare la collezione locale \t 9) Esci" +
                    "\n Scelta: ");
            scelta = sc.nextInt();

            switch (scelta){
                case 1:
                    ArrayList<EntitaCantante> cantanti = api.selectAllCantanti();
                    for(EntitaCantante entitaCantante : cantanti)
                        System.out.println(stampaCantante(entitaCantante));
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:

                    break;
                case 8:

                    break;
            }

        }while(scelta < 1 || scelta > 8);
    }

    public static String stampaCantante(EntitaCantante cantante) {
        String result = "ID: " + cantante.id +
                "\tNome: " + cantante.nome +
                "\tPaese: " + cantante.paese +
                "\tGenere: " + cantante.genere;

        if (cantante.canzoni != null && !cantante.canzoni.isEmpty()) {
            result += "\n   Canzoni:";
            for (EntitaCanzone c : cantante.canzoni) {
                result += "\n    - " + c.titolo +
                        " (" + c.annoPubblicazione + ", " + c.durata + "s)";
            }
        } else
            result += "\n  Nessuna canzone disponibile.";

        return result;
    }

}