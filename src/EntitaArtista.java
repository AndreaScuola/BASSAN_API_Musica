import java.util.ArrayList;

public class EntitaArtista {
    int id;
    String nome;
    String paese;
    String genere;
    ArrayList<EntitaCanzone> canzoni;

    public EntitaArtista() {} //Costruttore vuoto per la deserializzazione di Gson

    @Override
    public String toString(){
        String result = "ID: " + id +
                "\tNome: " + nome +
                "\tPaese: " + paese +
                "\tGenere: " + genere;

        if (canzoni != null && !canzoni.isEmpty()) {
            result += "\n   Canzoni:";
            for (EntitaCanzone c : canzoni) {
                result += "\n    - " + c.titolo +
                        " (" + c.annoPubblicazione + ", " + c.durata + "s)";
            }
        }

        return result;
    }
}
