import java.sql.*;
import java.util.ArrayList;

public class Database {
    private static Database instance;
    private Connection connection;

    private Database() {
        try{
            String url = "jdbc:sqlite:database/Spotify.db";
            connection = DriverManager.getConnection(url);
            System.out.println("Connected to database");
        } catch (SQLException e) {
            System.err.println("Errore connessione: " + e.getMessage());
        }
    }

    public static Database getInstance() {
        if (instance == null)
            instance = new Database();

        return instance;
    }

    private boolean testConnection() {
        try{
            if(connection == null || !connection.isValid(5)) {
                System.err.println("Errore di connessione al DB locale");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Errore di connessione al DB locale: " + e.getMessage());
            return false;
        }

        return true;
    }

    public String selectAllArtisti(){
        String result = "";
        if(testConnection()) {
            String query = "SELECT * FROM Artisti";
            try{
                PreparedStatement stmt = connection.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()){
                    result = rs.getString(1) + "\t"
                            + rs.getString(2) + "\t"
                            + rs.getString(3) + "\t"
                            + rs.getString(4) + "\n";
                }
            } catch (SQLException e) {
                System.err.println("Errore di query: " + e.getMessage());
                return null;
            }

            return result;
        }

        return null;
    }

    public String selectArtistaByID(int ID){
        String result = "";
        if(testConnection()) {
            String query = "SELECT * FROM Artista WHERE ID = ?";

            try{
                PreparedStatement stmt = connection.prepareStatement(query);
                stmt.setInt(1, ID);
                ResultSet rs = stmt.executeQuery();

                result = rs.getString(1) + "\t"
                         + rs.getString(2) + "\t"
                         + rs.getString(3) + "\t"
                         + rs.getString(4) + "\n";

                return result;
            } catch (SQLException e) {
                System.err.println("Errore di query: " + e.getMessage());
                return null;
            }
        }

        return null;
    }

    public String selectAllCanzoni() {
        String result = "";
        if(testConnection()) {
            String query = "SELECT * FROM Canzoni";

            try{
                PreparedStatement stmt = connection.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();

                result = rs.getString(1) + "\t"
                        + rs.getString(2) + "\t"
                        + rs.getString(3) + "\t"
                        + rs.getString(4) + "\t"
                        + rs.getString(5) + "\n";

                return result;
            } catch (SQLException e) {
                System.err.println("Errore di query: " + e.getMessage());
                return null;
            }
        }

        return null;
    }

    public boolean createNewArtista(String nome, String paese, String genere) {
        if(testConnection()) {
            String query = "INSERT INTO Artisti (nome, paese, genere) VALUES (?, ?, ?)";
            try {
                PreparedStatement stmt = connection.prepareStatement(query);
                stmt.setString(1, nome);
                stmt.setString(2, paese);
                stmt.setString(3, genere);
                stmt.executeUpdate();

                return true;
            } catch (SQLException e) {
                System.err.println("Errore di query: " + e.getMessage());
                return false;
            }
        }

        return false;
    }

    public boolean deleteArtistaByID(int ID) {
        if(testConnection()) {
            String query = "DELETE FROM Artisti WHERE ID = ?";
            try {
                PreparedStatement stmt = connection.prepareStatement(query);
                stmt.setInt(1, ID);
                stmt.executeUpdate();

                return true;
            } catch (SQLException e) {
                System.err.println("Errore di query: " + e.getMessage());
                return false;
            }
        }

        return false;
    }

    public boolean createNewCanzone(String titolo, int durata, int annoPubblicazione, int IdArtista) {
        if(testConnection()) {
            String query = "INSERT INTO Artisti (titolo, durata, annoPublicazione, idArtista) VALUES (?, ?, ?, ?)";
            try {
                PreparedStatement stmt = connection.prepareStatement(query);
                stmt.setString(1, titolo);
                stmt.setInt(2, durata);
                stmt.setInt(3, annoPubblicazione);
                stmt.setInt(4, IdArtista);
                stmt.executeUpdate();

                return true;
            } catch (SQLException e) {
                System.err.println("Errore di query: " + e.getMessage());
                return false;
            }
        }

        return false;
    }

    public boolean deleteCanzoneByID(int ID) {
        if(testConnection()) {
            String query = "DELETE FROM Canzoni WHERE ID = ?";
            try {
                PreparedStatement stmt = connection.prepareStatement(query);
                stmt.setInt(1, ID);
                stmt.executeUpdate();

                return true;
            } catch (SQLException e) {
                System.err.println("Errore di query: " + e.getMessage());
                return false;
            }
        }

        return false;
    }
}