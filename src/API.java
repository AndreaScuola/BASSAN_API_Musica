// API sarà affidata a una classe dedicata che si occuperà di comunicare con servizi esterni
// per recuperare informazioni su canzoni e artisti.
// Questa classe dovrà anche permettere di aggiungere, modificare ed eliminare artisti attraverso le API.

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class API {
    private final HttpClient client = HttpClient.newHttpClient();

    private HttpRequest getHttpRequest(String url){
        return HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .uri(java.net.URI.create(url))
                .GET()
                .build();
    }

    private HttpResponse<String> sendHttpRequest(HttpRequest request) {
        HttpResponse<String> response;

        try{
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch(IOException | InterruptedException e){
            throw new RuntimeException(e);
        }

        return response;
    }

    public boolean checkHealthApi(){
        String url = "http://localhost:4567/api/health";

        HttpRequest request = getHttpRequest(url);
        HttpResponse<String> response = sendHttpRequest(request);

        System.out.println("RESPONSE: " + response.body());
        return response.statusCode() == 200;
    }

    public ArrayList<EntitaArtista> selectAllArtisti(){
        String url = "http://localhost:4567/api/artisti";

        HttpRequest request = getHttpRequest(url);
        HttpResponse<String> response = sendHttpRequest(request);

        Gson gson = new Gson();
        ArrayList<EntitaArtista> apiResponse = gson.fromJson(response.body(), new TypeToken<ArrayList<EntitaArtista>>(){}.getType());
        //new TypeToken<T> serve a specificare il tipo di lista in cui deserializzare il json

        return apiResponse;
    }

    public EntitaArtista selectArtistaByID(int ID){
        String url = "http://localhost:4567/api/artisti/" + ID;

        HttpRequest request = getHttpRequest(url);
        HttpResponse<String> response = sendHttpRequest(request);

        Gson gson = new Gson();
        EntitaArtista apiResponse = gson.fromJson(response.body(), EntitaArtista.class);
        return apiResponse;
    }

    public ArrayList<EntitaCanzone> selectCanzoniByArtistaID(int ID){
        String url = "http://localhost:4567/api/artisti/" + ID + "/canzoni";

        HttpRequest request = getHttpRequest(url);
        HttpResponse<String> response = sendHttpRequest(request);

        Gson gson = new Gson();
        ArrayList<EntitaCanzone> apiResponse = gson.fromJson(response.body(), new TypeToken<ArrayList<EntitaCanzone>>(){}.getType());
        //new TypeToken<T> serve a specificare il tipo di lista in cui deserializzare il json
        return apiResponse;
    }

    public ArrayList<EntitaCanzone> selectAllCanzoni(){
        String url = "http://localhost:4567/api/canzoni";

        HttpRequest request = getHttpRequest(url);
        HttpResponse<String> response = sendHttpRequest(request);

        Gson gson = new Gson();
        ArrayList<EntitaCanzone> apiResponse = gson.fromJson(response.body(), new TypeToken<ArrayList<EntitaCanzone>>(){}.getType());
        //new TypeToken<T> serve a specificare il tipo di lista in cui deserializzare il json
        return apiResponse;
    }

    public EntitaCanzone selectCanzoneByID(int ID){
        String url = "http://localhost:4567/api/canzoni/" + ID;

        HttpRequest request = getHttpRequest(url);
        HttpResponse<String> response = sendHttpRequest(request);

        Gson gson = new Gson();
        EntitaCanzone apiResponse = gson.fromJson(response.body(), EntitaCanzone.class);
        return apiResponse;
    }

    public EntitaArtista postNewArtista(String JsonBody){
        String url = "http://localhost:4567/api/artisti";

        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .uri(java.net.URI.create(url))
                .POST(HttpRequest.BodyPublishers.ofString(JsonBody))
                .build();

        HttpResponse<String> response = sendHttpRequest(request);

        Gson gson = new Gson();
        EntitaArtista apiResponse = gson.fromJson(response.body(), EntitaArtista.class);
        return apiResponse;
    }

    public EntitaArtista putNewArtista(int ID, String JsonBody){
        String url = "http://localhost:4567/api/artisti/" + ID;

        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .uri(java.net.URI.create(url))
                .PUT(HttpRequest.BodyPublishers.ofString(JsonBody))
                .build();

        HttpResponse<String> response = sendHttpRequest(request);

        Gson gson = new Gson();
        EntitaArtista apiResponse = gson.fromJson(response.body(), EntitaArtista.class);
        return apiResponse;
    }

    public EntitaArtista deleteArtistaByID(int ID){
        String url = "http://localhost:4567/api/artisti/" + ID;

        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .uri(java.net.URI.create(url))
                .DELETE()
                .build();

        HttpResponse<String> response = sendHttpRequest(request);

        Gson gson = new Gson();
        EntitaArtista apiResponse = gson.fromJson(response.body(), EntitaArtista.class);
        return apiResponse;
    }
}