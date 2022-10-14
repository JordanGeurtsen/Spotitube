package nl.han.oose.dea.jordan.beroepsproduct.data.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DatabasePropertiesTest {

    @Test
    public void testIfCanGetConnectionString() {
        // Arrange
        DatabaseProperties databaseProperties = new DatabaseProperties();

        // Act
        String connectionString = databaseProperties.getDBConnectionString();

        // Assert
        assertEquals("jdbc:mysql://localhost/spotitube?user=spotitube&password=spotitubeDBlogin&serverTimezone=UTC", connectionString);
    }
}