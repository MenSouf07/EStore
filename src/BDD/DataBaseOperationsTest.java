package BDD;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

public class DataBaseOperationsTest {
    private Connection connection;

    @BeforeEach
    public void setUp() {
        try {
            connection = DataBaseConnection.getConnection();
            // Créez une table temporaire pour les tests
            try (Statement stmt = connection.createStatement()) {
                stmt.execute("CREATE TABLE IF NOT EXISTS TestTable (" +
                        "id INT PRIMARY KEY AUTO_INCREMENT, " +
                        "name VARCHAR(50) NOT NULL)");
            }
        } catch (SQLException e) {
            fail("Could not connect to the database: " + e.getMessage());
        }
    }

    @AfterEach
    public void tearDown() {
        // Supprimez la table temporaire après chaque test
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS TestTable");
        } catch (SQLException e) {
            fail("Could not drop test table: " + e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                fail("Could not close connection: " + e.getMessage());
            }
        }
    }

    @Test
    public void testInsertData() {
        DataBaseOperations.insertData("INSERT INTO TestTable (name) VALUES ('Test Name')");
        
        // Vérifiez si les données ont été insérées
        int count = DataBaseOperations.getCount("SELECT COUNT(*) FROM TestTable");
        assertEquals(1, count, "Should be 1 record in TestTable");
    }

    @Test
    public void testUpdateData() {
        DataBaseOperations.insertData("INSERT INTO TestTable (name) VALUES ('Old Name')");
        DataBaseOperations.updateData("UPDATE TestTable SET name = 'Updated Name' WHERE name = 'Old Name'");

        // Vérifiez si la mise à jour a réussi
        String name = DataBaseOperations.getSingleValue("SELECT name FROM TestTable WHERE id = 1");
        assertEquals("Updated Name", name, "Name should be updated");
    }

    @Test
    public void testDeleteData() {
        DataBaseOperations.insertData("INSERT INTO TestTable (name) VALUES ('Name to Delete')");
        DataBaseOperations.deleteData("DELETE FROM TestTable WHERE name = 'Name to Delete'");

        // Vérifiez si la suppression a réussi
        int count = DataBaseOperations.getCount("SELECT COUNT(*) FROM TestTable");
        assertEquals(0, count, "Should be 0 records in TestTable");
    }

    @Test
    public void testGetData() {
        DataBaseOperations.insertData("INSERT INTO TestTable (name) VALUES ('Name to Retrieve')");
        
        // Récupérez les données
        String name = DataBaseOperations.getSingleValue("SELECT name FROM TestTable WHERE name = 'Name to Retrieve'");
        assertEquals("Name to Retrieve", name, "Should retrieve the correct name");
    }
}
