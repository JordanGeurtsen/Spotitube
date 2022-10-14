package nl.han.oose.dea.jordan.beroepsproduct.data.utils;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@ApplicationScoped
public class DatabaseConnector {
    private DatabaseProperties databaseProperties;

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseProperties.getDBConnectionString());
    }

    @Inject
    public void setDatabaseProperties(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }
}
