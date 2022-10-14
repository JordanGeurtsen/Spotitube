package nl.han.oose.dea.jordan.beroepsproduct.data.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DatabaseConnectorTest {

    private DatabaseConnector sut;
    private DatabaseProperties mockedDatabaseProperties;

    @BeforeEach
    public void setup() {
        sut = new DatabaseConnector();
        mockedDatabaseProperties = mock(DatabaseProperties.class);
        sut.setDatabaseProperties(mockedDatabaseProperties);

        when(mockedDatabaseProperties.getDBConnectionString()).thenReturn("jdbc:mysql://localhost/spotitube?user=spotitube&password=spotitubeDBlogin&serverTimezone=UTC");
    }

    @Test
    public void getConnectionReturnsConnection() throws SQLException {
        assertNotNull(sut.getConnection());
    }

}