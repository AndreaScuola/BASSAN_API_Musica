// MAIN gestirà l'interfaccia utente attraverso un menu interattivo.
// Gli utenti potranno scegliere tra diverse opzioni come visualizzare canzoni,
// cercare artisti specifici, aggiungerne di nuovi o modificare quelli esistenti.


//cd C:\Users\Standard\.jdks\ms-21.0.8\bin
//java -jar C:\Users\Standard\Downloads\SpotifyAPI.jar

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static API api = new API();
    static Database db = Database.getInstance();

    public static void main(String[] args) {
        int scelta;
        System.out.print("--------------------------\nBenvenuto alla ........\n--------------------------");

        do{
            System.out.print("\n\nScegli il ... da utilizzare \n 1) Dati della API \n 2) Dati dal DB locale \n 3) Esci \nScelta: ");
            scelta = sc.nextInt();
            sc.nextLine();

            switch (scelta){
                case 1:
                    menuAPI();
                    break;

                case 2:
                    menuDBLocale();
                    break;
            }

        } while(scelta >= 1 && scelta <= 2);
    }


    public static void menuAPI(){
        if(!api.checkHealthApi()){
            System.err.println("Errore connessione api");
            return;
        }

        int scelta;
        System.out.println("\n--------------\nBenvenuto ai dati della API\n--------------\n");

        do{
            System.out.print("Cosa vuoi fare? " +
                    "\n 1) Stampa catalogo completo \t2) Cerca artista con un ID" +
                    "\n 3) Stampa le canzoni di un artista con un ID \t 4) Stampa tutte le canzoni" +
                    "\n 5) Stampa la canzone con un ID \t 6) Crea nuovo artista" +
                    "\n 7) Aggiorna artista con un ID \t 8) Cancella artista con un ID" +
                    "\n 9) Esci" +
                    "\n Scelta: ");

            scelta = sc.nextInt();
            sc.nextLine();
            System.out.println();

            switch (scelta){
                case 1:
                    ArrayList<EntitaArtista> artisti = api.selectAllArtisti();
                    for(EntitaArtista artista : artisti)
                        System.out.println(artista.toString());
                    break;
                case 2:
                    System.out.print("Inserisci ID dell'artista: ");
                    int ID = sc.nextInt();
                    sc.nextLine();
                    EntitaArtista artista = api.selectArtistaByID(ID);
                    System.out.println(artista.toString());
                    break;
                case 3:
                    System.out.print("Inserisci ID dell'artista: ");
                    ID = sc.nextInt();
                    sc.nextLine();
                    ArrayList<EntitaCanzone> canzoni = api.selectCanzoniByArtistaID(ID);
                    for(EntitaCanzone c : canzoni)
                        System.out.println(c.toString());
                    break;
                case 4:
                    canzoni = api.selectAllCanzoni();
                    for(EntitaCanzone c : canzoni)
                        System.out.println(c.toString());
                    break;
                case 5:
                    System.out.print("Inserisci ID della canzone: ");
                    ID = sc.nextInt();
                    sc.nextLine();
                    EntitaCanzone canzone = api.selectCanzoneByID(ID);
                    System.out.println(canzone.toString());
                    break;
                case 6:
                    System.out.print("Inserisci nome artista: ");
                    String nome = sc.nextLine();
                    System.out.print("Inserisci paese: ");
                    String paese = sc.nextLine();
                    System.out.print("Inserisci genere: ");
                    String genere = sc.nextLine();

                    String nuovoArtistaJson = "{\"nome\":\"" + nome + "\",\"paese\":\"" + paese + "\",\"genere\":\"" + genere + "\"}";
                    EntitaArtista rispostaPost = api.postNewArtista(nuovoArtistaJson);
                    System.out.println("Artista creato: " + rispostaPost);
                    break;

                case 7:
                    System.out.print("Inserisci ID artista da aggiornare: ");
                    ID = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nuovo nome artista: ");
                    nome = sc.nextLine();
                    System.out.print("Nuovo paese: ");
                    paese = sc.nextLine();
                    System.out.print("Nuovo genere: ");
                    genere = sc.nextLine();

                    nuovoArtistaJson = "{\"nome\":\"" + nome + "\",\"paese\":\"" + paese + "\",\"genere\":\"" + genere + "\"}";
                    EntitaArtista rispostaPut = api.putNewArtista(ID, nuovoArtistaJson);
                    System.out.println("Artista aggiornato: " + rispostaPut.toString());
                    break;

                case 8:
                    System.out.print("Inserisci ID artista da eliminare: ");
                    ID = sc.nextInt();
                    sc.nextLine();

                    EntitaArtista rispostaDelete = api.deleteArtistaByID(ID);
                    System.out.println("Risposta delete: " + rispostaDelete);
                    break;
            }

        }while(scelta >= 1 && scelta <= 8);
    }


    public static void menuDBLocale(){
        int scelta;
        System.out.println("\n--------------\nBenvenuto al DB locale\n--------------\n");

        do{
            System.out.print("Cosa vuoi fare? " +
                    "\n 1) Stampa catalogo completo \t2) Cerca artista con un ID" +
                    "\n 3) Inserisci un nuovo artista \t 4) Cancella artista con un ID" +
                    "\n 5) Stampa tutte le canzoni \t 6) Inserisci una nuova canzone" +
                    "\n 7) Elimina una canzona con un ID \t 8) Esci" +
                    "\n Scelta: ");

                    /*
                    "\n 5) Aggiungi nuovo artista \t 6) Modificare informazioni di artisti esistenti" +
                    "\n 7) Elimina artista da ID \t 8) Consultare la collezione locale \t 9) Esci" +
                    "\n Scelta: ");
                    */

            scelta = sc.nextInt();
            sc.nextLine();
            System.out.println();

            switch (scelta){
                case 1:
                    System.out.println(db.selectAllArtisti());
                    break;

                case 2:
                    System.out.print("Inserisci ID dell'artista: ");
                    int ID = sc.nextInt();
                    sc.nextLine();
                    System.out.println(db.selectArtistaByID(ID));
                    break;

                case 3:
                    System.out.print("Inserisci nome artista: ");
                    String nome = sc.nextLine();
                    System.out.print("Inserisci paese: ");
                    String paese = sc.nextLine();
                    System.out.print("Inserisci genere: ");
                    String genere = sc.nextLine();

                    if(db.createNewArtista(nome, paese, genere))
                        System.out.println("Artista creato con successo");
                    else
                        System.out.println("Errore nella creazione dell'artista");
                    break;

                case 4:
                    System.out.print("Inserisci ID artista da eliminare: ");
                    ID = sc.nextInt();
                    sc.nextLine();

                    if(db.deleteArtistaByID(ID))
                        System.out.println("Artista eliminato con successo");
                    else
                        System.out.println("Errore nell'eliminazione dell'artista");
                    break;

                case 5:
                    System.out.println(db.selectAllCanzoni());
                    break;

                case 6:
                    System.out.print("Inserisci titolo canzone: ");
                    String titolo = sc.nextLine();
                    System.out.print("Inserisci durata canzone: ");
                    int durata = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Inserisci anno pubblicazione canzone: ");
                    int annoPublicazione = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Inserisci id dell'artista che ha creato la canzone: ");
                    int idArtista = sc.nextInt();
                    sc.nextLine();

                    if(db.createNewCanzone(titolo, durata, annoPublicazione, idArtista))
                        System.out.println("Canzone creata con successo");
                    else
                        System.out.println("Errore nella creazione della canzone");

                    break;

                case 7:
                    System.out.print("Inserisci ID canzone da eliminare: ");
                    ID = sc.nextInt();
                    sc.nextLine();

                    if(db.deleteCanzoneByID(ID))
                        System.out.println("Canzone eliminata con successo");
                    else
                        System.out.println("Errore nell'eliminazione della canzone");
                    break;
            }

        }while(scelta >= 1 && scelta <= 7);
    }
}