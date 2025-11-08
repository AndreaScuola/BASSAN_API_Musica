// API sarà affidata a una classe dedicata che si occuperà di comunicare con servizi esterni
// per recuperare informazioni su canzoni e artisti.
// Questa classe dovrà anche permettere di aggiungere, modificare ed eliminare artisti attraverso le API.

import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class API {
    private final HttpClient client = HttpClient.newHttpClient();

    public boolean checkHealthApi(){
        String url = "http://localhost:4567/api/health";

        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response;

        try{
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch(IOException | InterruptedException e){
            System.err.println("ERRORE: " + e.getMessage());
            return false;
        }

        System.out.println("RESPONSE: " + response.body());
        return response.statusCode() == 200;
    }

    public ArrayList<EntitaCantante> selectAllCantanti(){
        String url = "http://localhost:4567/api/artisti";

        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response;

        try{
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e){
            System.err.println("ERRORE: " + e.getMessage());
            return null;
        }

        Gson gson = new Gson();
        Entita apiResponse = gson.fromJson(response.body(), Entita.class);

        if(apiResponse.responseCode != 0){
            System.out.println("Errore " + apiResponse.responseCode);
            return null;
        }

        return apiResponse.cantanti;
    }

}
