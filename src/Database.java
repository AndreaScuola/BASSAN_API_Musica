import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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


}