package BDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/EStore";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static Connection connection;

    public DataBaseConnection(){
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connexion réussie !");
        } catch (SQLException e) {
            System.err.println("Erreur de connexion : " + e.getMessage());
        }
    }

    public Connection getConnection(){
        return connection;
    }

    public void closeConnection(){
        try {
            connection.close();
            System.out.println("Connexion fermée.");
        } catch (SQLException e) {
            System.err.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        DataBaseConnection dbCo = new DataBaseConnection();
        dbCo.closeConnection();

    }
}
