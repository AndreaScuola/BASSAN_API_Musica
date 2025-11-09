public class EntitaCanzone {
    int id;
    String titolo;
    int durata;
    int annoPubblicazione;
    EntitaArtista artista;

    @Override
    public String toString() {
        String result = "ID: " + id +
                "\tTitolo: " + titolo +
                "\tDurata: " + durata + "s" +
                "\tAnno pubblicazione: " + annoPubblicazione;

        if (artista != null) {
            result += "\n   Artista: " + artista.nome +
                    " (" + artista.paese + ", " + artista.genere + ")";
        }

        return result;
    }
}